package com.github.githubapplication.main;

import com.github.githubapplication.entities.User;
import com.github.githubapplication.main.contracts.MainInteractor;
import com.github.githubapplication.main.contracts.MainPresenter;
import com.github.githubapplication.main.contracts.MainView;

import java.util.List;

/**
 * Created by marcus on 04/03/17.
 */

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnUserList {

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView, MainInteractor mainInteractor) {
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }


    @Override
    public void showUsers() {
        if (mainView != null)  {
            mainView.showProgress();
            mainInteractor.getUsers(this);
        }
    }

    @Override
    public void searchUsers(String query) {
        if (mainView != null)  {
            mainView.showProgress();
            mainInteractor.getUsersByName(this, query);
        }
    }

    @Override
    public void viewDestroy() {
        if (mainView != null)  {
            mainView = null;
        }
    }

    @Override
    public void returnUsers(List<User> userList) {
        if (mainView != null)  {
            mainView.hideProgress();
            mainView.loadUsers(userList);
        }
    }

    public MainView getMainView() {
        return mainView;
    }
}
