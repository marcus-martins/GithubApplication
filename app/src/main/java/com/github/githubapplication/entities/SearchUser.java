package com.github.githubapplication.entities;

import java.util.List;

/**
 * Created by marcus on 03/03/17.
 */

public class SearchUser {

    List<User> items;

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }
}
