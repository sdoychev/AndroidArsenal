package com.methodia.android.testautomation.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.methodia.android.testautomation.Model.ReposList;
import com.methodia.android.testautomation.Network.SampleRetrofitSpiceRequest;
import com.methodia.android.testautomation.R;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import timber.log.Timber;

public class RetrofitAndRobospice extends BaseSampleSpiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_and_robospice);

        SampleRetrofitSpiceRequest githubRequest = new SampleRetrofitSpiceRequest("sdoychev");
        getSpiceManager().execute(githubRequest, "github", DurationInMillis.ONE_MINUTE, new ListContributorRequestListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_retrofit_and_robospice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ListContributorRequestListener implements RequestListener<ReposList> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Timber.e("Error while obtaining the user data " + spiceException);
        }

        @Override
        public void onRequestSuccess(ReposList repos) {
            for (int i = 0; i < repos.size(); i++) {
                Timber.d(repos.get(i).toString());
            }
        }
    }
}
