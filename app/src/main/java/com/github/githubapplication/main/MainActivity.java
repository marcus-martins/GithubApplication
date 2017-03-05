package com.github.githubapplication.main;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.github.githubapplication.R;
import com.github.githubapplication.entities.User;
import com.github.githubapplication.main.contracts.MainInteractor;
import com.github.githubapplication.main.contracts.MainPresenter;
import com.github.githubapplication.main.contracts.MainView;
import com.github.githubapplication.services.network.RestServiceGenerator;
import com.github.githubapplication.services.network.contracts.GitHubClient;
import com.github.githubapplication.user_details.UserDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity implements MainView, SearchView.OnQueryTextListener {

    private MainPresenter presenter;
    private ProgressBar progressBar;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GitHubClient gitHubClient = RestServiceGenerator.createService(GitHubClient.class);
        MainInteractor interactor = new MainInteractorImpl(gitHubClient);
        presenter = new MainPresenterImpl(this, interactor, compositeSubscription);

        progressBar = (ProgressBar) findViewById(R.id.loadUserProgress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.showUsers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.viewDestroy();
        compositeSubscription.unsubscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenter.searchUsers(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void loadUsers(List<User> userList) {
        if (userList != null) {
            UserListAdapter adapter = new UserListAdapter(this, (ArrayList<User>) userList, this);
            ListViewCompat listView = (ListViewCompat) findViewById(R.id.listViewUsers);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openUser(String login, String photo) {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("loginName", login);
        intent.putExtra("photo", photo);
        startActivity(intent);
    }
}
