package com.github.githubapplication.user_details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.githubapplication.R;
import com.github.githubapplication.entities.Repository;
import com.github.githubapplication.services.network.RestServiceGenerator;
import com.github.githubapplication.services.network.contracts.GitHubClient;
import com.github.githubapplication.user_details.contracts.UserDetailsInteractor;
import com.github.githubapplication.user_details.contracts.UserDetailsPresenter;
import com.github.githubapplication.user_details.contracts.UserDetailsView;

import java.util.ArrayList;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

public class UserDetailsActivity extends AppCompatActivity implements UserDetailsView {

    private UserDetailsPresenter presenter;
    private CompositeSubscription compositeSubscription;

    private ProgressBar progressBar;

    private String loginName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        GitHubClient gitHubClient = RestServiceGenerator.createService(GitHubClient.class);
        compositeSubscription = new CompositeSubscription();
        UserDetailsInteractor interactor = new UserDetailsInteractorImpl(gitHubClient, compositeSubscription);
        presenter = new UserDetailsPresenterImpl(this, interactor);

        progressBar = (ProgressBar) findViewById(R.id.loadUserProgress);

        loginName = getIntent().getExtras().getString("loginName");
        String photo = getIntent().getExtras().getString("photo");

        TextView userLogin = (TextView) findViewById(R.id.loginName);
        userLogin.setText(loginName);

        ImageView userPhoto = (ImageView) findViewById(R.id.userImage);
        loadPhoto(userPhoto, photo);

    }

    private void loadPhoto(ImageView userPhoto, String photo) {
        Glide.with(this)
                .load(photo)
                .centerCrop()
                .crossFade()
                .into(userPhoto);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.showUserRepositories(loginName);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
        presenter.viewDestroy();
    }

    @Override
    public void loadRepositories(List<Repository> repositoryList) {
        if (repositoryList != null) {
            RepositoryListAdapter adapter = new RepositoryListAdapter(this, (ArrayList<Repository>) repositoryList);
            ListViewCompat listView = (ListViewCompat) findViewById(R.id.listViewRepositories);
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
}
