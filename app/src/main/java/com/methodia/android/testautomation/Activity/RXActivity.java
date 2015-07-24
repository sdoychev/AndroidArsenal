package com.methodia.android.testautomation.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.methodia.android.testautomation.Model.Contributor;
import com.methodia.android.testautomation.Network.GithubService;
import com.methodia.android.testautomation.R;
import com.methodia.android.testautomation.Util;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class RXActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        Util.toolsSetup(this, this);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("https://api.github.com").build();
        GithubService service = restAdapter.create(GithubService.class);
        Callback callback = new Callback() {
            @Override
            public void success(Object contributors, Response response) {
                List<Contributor> contributorsList = (List<Contributor>) contributors;
                for (int i = 0; i < contributorsList.size(); i++) {
                    Timber.d(contributorsList.get(i).toString());
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Timber.e("Error while obtaining the contributor data " + retrofitError);
            }
        };
        service.listContributors("JakeWharton", "butterknife", callback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rx, menu);
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
}
