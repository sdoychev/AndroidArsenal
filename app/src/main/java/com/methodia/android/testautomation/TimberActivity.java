package com.methodia.android.testautomation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import timber.log.Timber;


public class TimberActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timber);

        Timber.plant(new Timber.DebugTree());

        Timber.i("Test INFO logging.");
        Timber.d("Test DEBUG logging.");
        Timber.e("Test ERROR logging.");

        String stringParam = "String";
        int intParam = 123;
        Timber.i("Test logging with string param - %s.", stringParam);
        Timber.d("Test logging with int param - %d.", intParam);
        Timber.e("Test logging with string and int params - %s, %d.", stringParam, intParam);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timber_pid_cat, menu);
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
