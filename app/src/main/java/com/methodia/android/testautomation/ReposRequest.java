package com.methodia.android.testautomation;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

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

        //String url = String.format("https://api.github.com/users/%s/followers", user);
        String url = String.format("https://api.github.com/users/%s/repos", user);
        return getRestTemplate().getForObject(url, ReposList.class);
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