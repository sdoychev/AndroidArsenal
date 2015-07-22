package com.methodia.android.testautomation.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;

import com.methodia.android.testautomation.Model.ParcelerModel;
import com.methodia.android.testautomation.R;
import com.methodia.android.testautomation.Util;

import org.parceler.Parcels;

import icepick.Icepick;
import icepick.Icicle;
import timber.log.Timber;

public class ParcelerIcePickActivity extends Activity {
    @Icicle
    String automaticallySavedAndRestoredString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parceler_ice_pick);
        Util.toolsSetup(this, this);

        //IcePick restore states of all the @Icicle variables
        Icepick.restoreInstanceState(this, savedInstanceState);
        automaticallySavedAndRestoredString = "Sample new string.";

        ParcelerModel test = new ParcelerModel();
        test.setId(1);
        test.setName("Test object");
        Timber.d(test.toString());
        Parcelable wrappedOne = Parcels.wrap(test);

        Parcelable wrappedTwo = Parcels.wrap(new ParcelerModel("Test object Two", 2));

        Bundle bundle = new Bundle();
        bundle.putParcelable("testOne", wrappedOne);
        bundle.putParcelable("testTwo", wrappedTwo);

        Intent intent = new Intent(ParcelerIcePickActivity.this, ReceivingBundleActivity.class);
        intent.putExtra("bundleExtra", bundle);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //IcePick sav states of all the @Icicle variables
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parceler_ice_pick, menu);
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
