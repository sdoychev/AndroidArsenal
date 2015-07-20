package com.methodia.android.testautomation;

import com.methodia.android.testautomation.Model.Repo;
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
    List<Repo> listRepos(@Path("user") String user);

    @GET("/users")
    void listUsersFromCity(@Query("location") String city, Callback<List<User>> cb);
}