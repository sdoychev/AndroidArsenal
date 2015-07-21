package com.methodia.android.testautomation;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by Doychev on 21.7.2015 г..
 */
public class ActiveAndroidApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ActiveAndroid.initialize(this);
    }
}
