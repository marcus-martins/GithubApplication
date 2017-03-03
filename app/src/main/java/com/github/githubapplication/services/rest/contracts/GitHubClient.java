package com.github.githubapplication.services.rest.contracts;


import com.github.githubapplication.entities.Repository;
import com.github.githubapplication.entities.SearchUser;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by marcus on 02/03/17.
 */

public interface GitHubClient {

    @GET("/search/users")
    Observable<SearchUser> searchUsers(
            @Query("q") String name
    );

    @GET("/users/{user}/repos")
    Observable<List<Repository>> getUserRepositories(
            @Path("user") String user
    );

}
