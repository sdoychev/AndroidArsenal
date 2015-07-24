package com.methodia.android.testautomation.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.methodia.android.testautomation.Model.Contributor;
import com.methodia.android.testautomation.Network.GithubService;
import com.methodia.android.testautomation.R;
import com.methodia.android.testautomation.Util;

import java.util.List;

import retrofit.RestAdapter;
import rx.Observable;
import rx.Subscriber;
import timber.log.Timber;

public class RXActivity extends AppCompatActivity {

    private static <T> Observable.Operator<T, List<T>> flattenList() {
        return new Observable.Operator<T, List<T>>() {
            @Override
            public Subscriber<? super List<T>> call(final Subscriber<? super T> subscriber) {
                return new Subscriber<List<T>>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(List<T> contributors) {
                        for (T c : contributors)
                            subscriber.onNext(c);
                    }
                };
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        Util.toolsSetup(this, this);

        //Get repo data from background task
        BackgroundTask rt = new BackgroundTask();
        rt.execute();
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

    private class BackgroundTask extends AsyncTask<String, Void, Observable<List<Contributor>>> {
        RestAdapter restAdapter;
        GithubService service;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder().setEndpoint("https://api.github.com").build();
            service = restAdapter.create(GithubService.class);
        }

        @Override
        protected Observable<List<Contributor>> doInBackground(String... params) {
            return service.listContributors("JakeWharton", "butterknife");
        }

        @Override
        protected void onPostExecute(Observable<List<Contributor>> contributors) {
            contributors.lift(flattenList())
                    //.flatMap(c -> service.getUser(c.g ) )
                    .filter(contributor -> contributor.getContributions() >= 3)
                    .forEach(contributor -> Timber.d(contributor.toString()));
        }
    }
}
