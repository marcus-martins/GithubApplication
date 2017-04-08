package com.github.githubapplication.main;

import com.github.githubapplication.main.contracts.MainInteractor;
import com.github.githubapplication.services.network.contracts.GitHubClient;

import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by marcus on 04/03/17.
 */

public class MainInteractorImpl implements MainInteractor {

    private GitHubClient gitHubClient;
    private CompositeSubscription compositeSubscription;

    public MainInteractorImpl(GitHubClient gitHubClient, CompositeSubscription compositeSubscription) {
        this.gitHubClient = gitHubClient;
        this.compositeSubscription = compositeSubscription;
    }

    @Override
    public void getUsers(OnUserList callback) {
        if (gitHubClient != null) {
            compositeSubscription.add(
                    gitHubClient.listUsers()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(

                                    callback::returnUsers,

                                    Throwable::printStackTrace,

                                    () -> {

                                    })
            );
        }
    }

    @Override
    public void getUsersByName(OnUserList callback, String name) {
        if (gitHubClient != null) {
            compositeSubscription.add(
                    gitHubClient.searchUsers(name)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(

                                    userList -> callback.returnUsers(userList.getItems()),

                                    Throwable::printStackTrace,

                                    () -> {

                                    })
            );
        }
    }
}
