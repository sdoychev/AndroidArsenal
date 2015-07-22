package com.methodia.android.testautomation.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;

import com.methodia.android.testautomation.Model.ParcelerModel;
import com.methodia.android.testautomation.R;

import org.parceler.Parcels;

import timber.log.Timber;

public class ReceivingBundleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_bundle);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundleExtra");
        Parcelable wrappedOne = bundle.getParcelable("testOne");
        ParcelerModel testOne = Parcels.unwrap(wrappedOne);
        ParcelerModel testTwo = Parcels.unwrap(bundle.getParcelable("testTwo"));
        Timber.d(testOne.toString());
        Timber.d(testTwo.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_receiving_bundle, menu);
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
