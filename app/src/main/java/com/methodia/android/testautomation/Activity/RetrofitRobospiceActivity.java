package com.methodia.android.testautomation.Activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.methodia.android.testautomation.Model.ReposList;
import com.methodia.android.testautomation.Model.User;
import com.methodia.android.testautomation.Network.GithubService;
import com.methodia.android.testautomation.Network.JsonSpiceService;
import com.methodia.android.testautomation.Network.ReposRequest;
import com.methodia.android.testautomation.R;
import com.methodia.android.testautomation.Util;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class RetrofitRobospiceActivity extends Activity {

    protected SpiceManager spiceManager = new SpiceManager(JsonSpiceService.class);

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_robospice);
        Util.toolsSetup(this, this);

        //Good practices for Retrofit - http://blog.robinchutaux.com/blog/a-smart-way-to-use-retrofit/

        //Get repo data from background task
        BackgroundTask rt = new BackgroundTask();
        rt.execute();

        //Same result using Robospice (stand alone Robospice)
        performRequest("sdoychev");

        //Get user data with retrofit Callback
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .build();
        GithubService service = restAdapter.create(GithubService.class);
        Callback callback = new Callback() {
            @Override
            public void success(Object o, Response response) {
                List<User> users = (List<User>) o;
                for (int i = 0; i < users.size(); i++) {
                    Timber.d(users.get(i).toString());
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Timber.e("Error while obtaining the user data " + retrofitError);
            }
        };
        service.listUsersFromCity("Sofia", callback);
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    private void performRequest(String user) {
        ReposRequest request = new ReposRequest(user);
        String lastRequestCacheKey = request.createCacheKey();
        spiceManager.execute(request, lastRequestCacheKey, DurationInMillis.ONE_MINUTE, new ListReposRequestListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_retrofit_robospice, menu);
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

    private class ListReposRequestListener implements RequestListener<ReposList> {
        @Override
        public void onRequestFailure(SpiceException e) {
            Timber.e("Error while obtaining the user data " + e);
        }

        @Override
        public void onRequestSuccess(ReposList reposList) {
            for (int i = 0; i < reposList.size(); i++) {
                Timber.d(reposList.get(i).toString());
            }
        }
    }

    private class BackgroundTask extends AsyncTask<String, Void, ReposList> {
        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://api.github.com")
                    .build();
        }

        @Override
        protected ReposList doInBackground(String... params) {
            GithubService service = restAdapter.create(GithubService.class);
            return service.listRepos("sdoychev");
        }

        @Override
        protected void onPostExecute(ReposList repos) {
            for (int i = 0; i < repos.size(); i++) {
                Timber.d(repos.get(i).toString());
            }
        }
    }
}
