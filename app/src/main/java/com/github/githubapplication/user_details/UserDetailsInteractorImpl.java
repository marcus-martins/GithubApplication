package com.github.githubapplication.user_details;

import com.github.githubapplication.services.network.contracts.GitHubClient;
import com.github.githubapplication.user_details.contracts.UserDetailsInteractor;

import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by marcus on 04/03/17.
 */

public class UserDetailsInteractorImpl implements UserDetailsInteractor {

    private GitHubClient gitHubClient;
    private CompositeSubscription compositeSubscription;

    public UserDetailsInteractorImpl(GitHubClient gitHubClient, CompositeSubscription compositeSubscription) {
        this.gitHubClient = gitHubClient;
        this.compositeSubscription = compositeSubscription;
    }

    @Override
    public void getRepositories(OnRepositoryList callback, String loginName) {
        if (gitHubClient != null) {
            compositeSubscription.add(
                    gitHubClient.getUserRepositories(loginName)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(

                                    callback::returnRepositories,

                                    Throwable::printStackTrace,

                                    () -> {

                                    })
            );
        }
    }
}
