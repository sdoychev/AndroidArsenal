package com.methodia.android.testautomation.Network;

import com.methodia.android.testautomation.Model.ReposList;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import retrofit.RestAdapter;

/**
 * Created by Stefan.Doychev on 21.07.2015.
 */
public class ReposRequest extends SpringAndroidSpiceRequest<ReposList> {

    private String user;

    public ReposRequest(String user) {
        super(ReposList.class);
        this.user = user;
    }

    @Override
    public ReposList loadDataFromNetwork() throws Exception {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .build();
        GithubService service = restAdapter.create(GithubService.class);
        return service.listRepos("sdoychev");
    }

    /**
     * This method generates a unique cache key for this request. In this case
     * our cache key depends just on the keyword.
     *
     * @return
     */
    public String createCacheKey() {
        return "repos." + user;
    }
}