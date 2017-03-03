package com.github.githubapplication.services.rest.contracts;


import com.github.githubapplication.entities.Repository;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by marcus on 02/03/17.
 */

public interface GitHubClient {

    @GET("/users/{user}/repos")
    Observable<List<Repository>> getUserRepositories(
            @Path("user") String user
    );

}
