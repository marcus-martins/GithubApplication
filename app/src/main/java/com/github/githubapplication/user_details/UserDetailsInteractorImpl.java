package com.github.githubapplication.user_details;

import com.github.githubapplication.services.network.contracts.GitHubClient;
import com.github.githubapplication.user_details.contracts.UserDetailsInteractor;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by marcus on 04/03/17.
 */

public class UserDetailsInteractorImpl implements UserDetailsInteractor {

    private GitHubClient gitHubClient;

    public UserDetailsInteractorImpl(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    @Override
    public void getRepositories(OnRepositoryList callback, String loginName) {
        if (gitHubClient != null) {
            callback.getCompositeSubscription().add(
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
