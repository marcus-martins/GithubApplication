package com.github.githubapplication.services.network.contracts;


import com.github.githubapplication.entities.Repository;
import com.github.githubapplication.entities.SearchUser;
import com.github.githubapplication.entities.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by marcus on 02/03/17.
 */

public interface GitHubClient {

    @GET("/users")
    Observable<List<User>> listUsers();

    @GET("/search/users")
    Observable<SearchUser> searchUsers(
            @Query("q") String name
    );

    @GET("/users/{user}/repos")
    Observable<List<Repository>> getUserRepositories(
            @Path("user") String user
    );

}
