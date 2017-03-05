package com.github.githubapplication.user_details.contracts;

import com.github.githubapplication.entities.Repository;

import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by marcus on 02/03/17.
 */

public interface UserInteractor {

    void getRepositories(OnRepositoryList callback, String loginName);

    interface OnRepositoryList {

        void returnRepositories(List<Repository> repositoryList);

        CompositeSubscription getCompositeSubscription();

    }

}
