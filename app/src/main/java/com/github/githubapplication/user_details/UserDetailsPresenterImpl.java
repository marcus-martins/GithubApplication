package com.github.githubapplication.user_details;

import com.github.githubapplication.entities.Repository;
import com.github.githubapplication.user_details.contracts.UserDetailsInteractor;
import com.github.githubapplication.user_details.contracts.UserDetailsPresenter;
import com.github.githubapplication.user_details.contracts.UserDetailsView;

import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by marcus on 04/03/17.
 */

public class UserDetailsPresenterImpl implements UserDetailsPresenter, UserDetailsInteractor.OnRepositoryList {
    private UserDetailsView userDetailsView;
    private UserDetailsInteractor userDetailsInteractor;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public UserDetailsPresenterImpl(UserDetailsView userDetailsView, UserDetailsInteractor userDetailsInteractor) {
        this.userDetailsView = userDetailsView;
        this.userDetailsInteractor = userDetailsInteractor;
    }

    @Override
    public void showUserRepositories(String loginName) {
        if (userDetailsView != null)  {
            userDetailsView.showProgress();
            userDetailsInteractor.getRepositories(this, loginName);
        }
    }

    @Override
    public void viewDestroy() {
        if (userDetailsView != null) {
            compositeSubscription.clear();
            userDetailsView = null;
        }
    }

    @Override
    public void returnRepositories(List<Repository> repositoryList) {
        if (userDetailsView != null)  {
            userDetailsView.hideProgress();
            userDetailsView.loadRepositories(repositoryList);
        }
    }

    @Override
    public CompositeSubscription getCompositeSubscription() {
        return compositeSubscription;
    }

    public UserDetailsView getUserDetailsView() {
        return userDetailsView;
    }
}
