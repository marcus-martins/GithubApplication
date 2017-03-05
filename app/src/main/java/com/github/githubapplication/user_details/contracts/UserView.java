package com.github.githubapplication.user_details.contracts;

import com.github.githubapplication.entities.Repository;

import java.util.List;

/**
 * Created by marcus on 02/03/17.
 */

public interface UserView {

    void loadRepositories(List<Repository> repositoryList);

    void showProgress();

    void hideProgress();

}
