package com.methodia.android.testautomation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;


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

        Picasso.with(this)
                .load("http://www.longislandpress.com/wp-content/uploads/2013/03/DropkickMurphys.jpg")
                .resize(500, 500)   //optional
                .into(imageViewOne);

        Picasso.with(this)
                .load("http://i.ytimg.com/vi/diSN8l1DyNY/maxresdefault.jpg")
                .resize(500, 500)
                .centerCrop()
                .rotate(360)
                .placeholder(android.R.drawable.stat_notify_sync)
                .error(android.R.drawable.stat_notify_error)
                .into(imageViewTwo);

        /*
        Ion.with(this)
                .load("http://api.openweathermap.org/data/2.5/weather?q=London,uk")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Timber.e(result.getAsString());
                    }
                });

        Ion.with(this)
                .load("http://cupegraf.com/data_images/wallpapers/36/414419-dropkick-murphys.jpg")
                .withBitmap()
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .animateLoad(spinAnimation)
                .animateIn(fadeInAnimation)
                .intoImageView(imageView);

        Ion.with(imageViewFour)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .animateLoad(spinAnimation)
                .animateIn(fadeInAnimation)
                .load("http://example.com/image.png");
        */
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
