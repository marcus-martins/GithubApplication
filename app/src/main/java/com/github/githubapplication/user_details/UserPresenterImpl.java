package com.github.githubapplication.user_details;

import com.github.githubapplication.entities.Repository;
import com.github.githubapplication.user_details.contracts.UserInteractor;
import com.github.githubapplication.user_details.contracts.UserPresenter;
import com.github.githubapplication.user_details.contracts.UserView;

import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by marcus on 04/03/17.
 */

public class UserPresenterImpl implements UserPresenter, UserInteractor.OnRepositoryList {
    private UserView userView;
    private UserInteractor userInteractor;
    private CompositeSubscription compositeSubscription;

    public UserPresenterImpl(UserView userView, UserInteractor userInteractor, CompositeSubscription compositeSubscription) {
        this.userView = userView;
        this.userInteractor = userInteractor;
        this.compositeSubscription = compositeSubscription;
    }

    @Override
    public void showUserRepositories(String loginName) {
        if (userView != null)  {
            userView.showProgress();
            userInteractor.getRepositories(this, loginName);
        }
    }

    @Override
    public void viewDestroy() {
        if (userView != null) {
            userView = null;
        }
    }

    @Override
    public void returnRepositories(List<Repository> repositoryList) {
        if (userView != null)  {
            userView.hideProgress();
            userView.loadRepositories(repositoryList);
        }
    }

    @Override
    public CompositeSubscription getCompositeSubscription() {
        return compositeSubscription;
    }
}
