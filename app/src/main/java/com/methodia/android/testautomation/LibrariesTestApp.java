package com.methodia.android.testautomation;

import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Doychev on 21.7.2015 г..
 */
public class LibrariesTestApp extends Application {

    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        LibrariesTestApp application = (LibrariesTestApp) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ActiveAndroid.initialize(this);
        refWatcher = LeakCanary.install(this);
    }
}
