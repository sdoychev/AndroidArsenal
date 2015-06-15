package com.methodia.android.testautomation;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Stefan.Doychev on 15.06.2015.
 */
public class TestTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] params) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("ASYNC TEST INNER", "TASK START");
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d("ASYNC TEST INNER", "TASK END");
    }
}
