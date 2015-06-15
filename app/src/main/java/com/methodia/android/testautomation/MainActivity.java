package com.methodia.android.testautomation;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("ASYNC TEST", "Task ONE created");
        TestTask taskOne = new TestTask();
        Log.d("ASYNC TEST", "Task TWO created");
        TestTask taskTwo = new TestTask();
        Log.d("ASYNC TEST", "Task THREE created");
        TestTask taskThree = new TestTask();
        Log.d("ASYNC TEST", "Task FOUR created");

        TestTask taskFour = new TestTask();
        Log.d("ASYNC TEST", "Task ONE execute");
        taskOne.execute();
        Log.d("ASYNC TEST", "Task TWO execute");
        taskTwo.execute();

        Log.d("ASYNC TEST", "Execute tasks THREE and FOUR in parallel");
        taskThree.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        taskFour.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
