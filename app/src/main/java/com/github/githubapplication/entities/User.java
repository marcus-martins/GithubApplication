package com.github.githubapplication.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marcus on 03/03/17.
 */

public class User {

    private String login;

    @SerializedName("avatar_url")
    private String photo;

    private String score;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
