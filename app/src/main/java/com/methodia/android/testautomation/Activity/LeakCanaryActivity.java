package com.methodia.android.testautomation.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.methodia.android.testautomation.LibrariesTestApp;
import com.methodia.android.testautomation.Model.LeakCanary.Cat;
import com.methodia.android.testautomation.Model.LeakCanary.Docker;
import com.methodia.android.testautomation.Model.LeakCanary.SchrodingerBox;
import com.methodia.android.testautomation.R;
import com.methodia.android.testautomation.Util;
import com.squareup.leakcanary.RefWatcher;

public class LeakCanaryActivity extends Activity {

    Cat schrodingerCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);
        Util.toolsSetup(this, this);

        SchrodingerBox box = new SchrodingerBox();
        schrodingerCat = new Cat();
        box.hiddenCat = schrodingerCat;
        Docker.setContainer(box);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = LibrariesTestApp.getRefWatcher(this);
        refWatcher.watch(schrodingerCat);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_leak_canary, menu);
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
