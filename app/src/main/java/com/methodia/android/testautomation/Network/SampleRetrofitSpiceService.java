package com.methodia.android.testautomation.Network;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by Stefan.Doychev on 21.07.2015.
 */
public class SampleRetrofitSpiceService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "https://api.github.com";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(GithubService.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }

}