package com.methodia.android.testautomation.Network;

import com.methodia.android.testautomation.Model.Contributor;
import com.methodia.android.testautomation.Model.ReposList;
import com.methodia.android.testautomation.Model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Stefan.Doychev on 20.07.2015.
 */
public interface GithubService {

    @GET("/users/{user}/repos")
    ReposList listRepos(@Path("user") String user);

    @GET("/users")
    void listUsersFromCity(@Query("location") String city, Callback<List<User>> cb);

    @GET("/repos/{user}/{repo}/contributors")
    List<Contributor> listContributors(@Path("user") String user, @Path("repo") String repo);
}