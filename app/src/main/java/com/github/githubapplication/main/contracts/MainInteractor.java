package com.github.githubapplication.main.contracts;

import com.github.githubapplication.entities.User;

import java.util.List;

/**
 * Created by marcus on 02/03/17.
 */

public interface MainInteractor {

    void getUsers(OnUserList callback);

    void getUsersByName(OnUserList callback, String name);

    interface OnUserList {

        void returnUsers(List<User> userList);

    }
}
