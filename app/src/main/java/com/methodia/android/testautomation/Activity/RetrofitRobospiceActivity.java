package com.methodia.android.testautomation.Activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.methodia.android.testautomation.GithubService;
import com.methodia.android.testautomation.Model.Repo;
import com.methodia.android.testautomation.Model.User;
import com.methodia.android.testautomation.R;
import com.methodia.android.testautomation.Util;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class RetrofitRobospiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_robospice);
        Util.toolsSetup(this, this);

        //Good practices for Retrofit - http://blog.robinchutaux.com/blog/a-smart-way-to-use-retrofit/

        //Get repo data from background task
        BackgroundTask rt = new BackgroundTask();
        rt.execute();

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

    private class BackgroundTask extends AsyncTask<String, Void, List<Repo>> {
        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://api.github.com")
                    .build();
        }

        @Override
        protected List<Repo> doInBackground(String... params) {
            GithubService service = restAdapter.create(GithubService.class);
            return service.listRepos("sdoychev");
        }

        @Override
        protected void onPostExecute(List<Repo> repos) {
            for (int i = 0; i < repos.size(); i++) {
                Timber.d(repos.get(i).toString());
            }
        }
    }
}
