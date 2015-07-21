package com.methodia.android.testautomation.Activity;

/**
 * Created by Stefan.Doychev on 21.07.2015.
 */

import android.app.Activity;

import com.methodia.android.testautomation.Network.SampleRetrofitSpiceService;
import com.methodia.android.testautomation.Util;
import com.octo.android.robospice.SpiceManager;

/**
 * This class is the base class of all activities of the sample project. This class offers all
 * subclasses an easy access to a {@link SpiceManager} that is linked to the {@link Activity}
 * lifecycle. Typically, in a new project, you will have to create a base class like this one and
 * copy the content of the {@link BaseSampleSpiceActivity} into your own class.
 *
 * @author sni
 */
public abstract class BaseSampleSpiceActivity extends Activity {
    private SpiceManager spiceManager = new SpiceManager(SampleRetrofitSpiceService.class);

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
        Util.toolsSetup(this, this);
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }

}
