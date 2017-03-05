package com.github.githubapplication.main.contracts;

import com.github.githubapplication.entities.User;

import java.util.List;

/**
 * Created by marcus on 02/03/17.
 */

public interface MainView {

    void loadUsers(List<User> userList);

    void showProgress();

    void hideProgress();

    void openUser(String login, String photo);

}
