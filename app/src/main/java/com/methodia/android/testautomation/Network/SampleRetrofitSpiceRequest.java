package com.methodia.android.testautomation.Network;

import com.methodia.android.testautomation.Model.ReposList;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Stefan.Doychev on 21.07.2015.
 */
public class SampleRetrofitSpiceRequest extends RetrofitSpiceRequest<ReposList, GithubService> {

    private String user;

    public SampleRetrofitSpiceRequest(String user) {
        super(ReposList.class, GithubService.class);
        this.user = user;
    }

    @Override
    public ReposList loadDataFromNetwork() {
        return getService().listRepos(user);
    }
}
