package com.methodia.android.testautomation;

import android.app.Activity;
import android.content.Context;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Doychev on 16.7.2015 г..
 */
public class Util {

    public static void toolsSetup(Context context, Activity activity) {

        ButterKnife.bind(activity);
        Timber.plant(new Timber.DebugTree());
    }
}
