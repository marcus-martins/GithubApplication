package com.github.githubapplication.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marcus on 02/03/17.
 */

public class Repository {

    private String name;

    @SerializedName("full_name")
    private String fullName;

    private String description;

    @SerializedName("html_url")
    private String url;

    private String language;

    @SerializedName("forks_count")
    private int forksCount;

    @SerializedName("stargazers_count")
    private int stargazersCount;

    @SerializedName("created_at")
    private String createDate;

    @SerializedName("updated_at")
    private String updateDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
