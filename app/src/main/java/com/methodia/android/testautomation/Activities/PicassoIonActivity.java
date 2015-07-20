package com.methodia.android.testautomation.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.methodia.android.testautomation.R;
import com.methodia.android.testautomation.Util;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import timber.log.Timber;


public class PicassoIonActivity extends Activity {
    @Bind(R.id.imageViewOne)
    ImageView imageViewOne;
    @Bind(R.id.imageViewTwo)
    ImageView imageViewTwo;
    @Bind(R.id.imageViewThree)
    ImageView imageViewThree;
    @Bind(R.id.imageViewFour)
    ImageView imageViewFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_ion);

        Util.toolsSetup(this, this);

        String imgOne = "http://www.longislandpress.com/wp-content/uploads/2013/03/DropkickMurphys.jpg";
        String imgTwo = "http://i.ytimg.com/vi/diSN8l1DyNY/maxresdefault.jpg";

        Picasso.with(this)
                .load(imgOne)
                .resize(500, 500)   //optional
                .into(imageViewOne);

        Picasso.with(this)
                .load(imgTwo)
                .resize(500, 500)
                        //.fit()        //You have to choose fit or resize. Good practice - always use fit with centerCrop.
                .centerCrop()
                .rotate(360)
                .transform(new CropCircleTransformation())
                    /* Available transformations:
                        CropCircleTransformation
                        CropSquareTransformation
                        CropTransformation(width, height, CropTransformation.FORM{Top, Center, Bottom})
                        ColorFilterTransformation(int color)
                        GrayscaleTransformation
                        BlurTransformation(context) (requires setting in defaultConfig of app build.gradle - renderscriptTargetApi 21 and renderscriptSupportModeEnabled true)
                        Effect(context) - where effect can be
                            ToonFilterTransformation, SepiaFilterTransformation, ContrastFilterTransformation, InvertFilterTransformation, PixelationFilterTransformation
                            SketchFilterTransformation, SwirlFilterTransformation, KuwaharaFilterTransformation, VignetteFilterTransformation.
                        RoundedCornersTransformation(int radius, int margin)
                    */
                .placeholder(android.R.drawable.stat_notify_sync)
                .error(android.R.drawable.stat_notify_error)
                .into(imageViewTwo);

        Ion.with(this)
                .load("http://api.openweathermap.org/data/2.5/weather?q=London,uk")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Timber.d(result.toString());
                    }
                });

        /* Also available:
            - Post JSON and read JSON.
            - Post application/x-www-form-urlencoded and read a String.
            - Post multipart/form-data and read JSON with an upload progress bar.
            - Download a File with a progress bar.
            - Setting Headers.
            - Load an image into an ImageView.
            - Futures - All operations return a custom Future that allows you to specify a callback that runs on completion.
            - Seamlessly use your own Java classes with Gson. */

        Ion.with(this)
                .load(imgOne)
                .withBitmap()
                .resize(500, 500)   //optional
                .intoImageView(imageViewThree);

        Ion.with(imageViewFour)
                .placeholder(android.R.drawable.stat_notify_sync)
                .error(android.R.drawable.stat_notify_error)
                        //.animateLoad(spinAnimation)
                        //.animateIn(fadeInAnimation)
                .resize(500, 500)
                .centerCrop()
                .load(imgTwo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_picasso_ion, menu);
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
