package com.methodia.android.testautomation.Activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.koushikdutta.ion.Ion;
import com.methodia.android.testautomation.R;
import com.methodia.android.testautomation.Util;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

public class ImageLibsActivity extends Activity {
    @Bind(R.id.imageViewPicasso)
    ImageView imageViewPicasso;
    @Bind(R.id.imageViewIon)
    ImageView imageViewIon;
    @Bind(R.id.imageViewGlide)
    ImageView imageViewGlide;
    @Bind(R.id.imageViewUIL)
    ImageView imageViewUIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_libs);
        Util.toolsSetup(this, this);

        String imgUrl = "http://www.mariowiki.com/images/a/a1/Mario_walk_smb.gif";
        final Animation anim = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);

        //Picasso
        Picasso.with(this)
                .load(imgUrl)
                .into(imageViewPicasso);

        //ion
        Ion.with(this)
                .load(imgUrl)
                .intoImageView(imageViewIon);

        //Glide
        Glide.with(this)
                .load(imgUrl)
                .animate(anim)
                .override(96, 96)
                .into(imageViewGlide);

        //Universal Image Loader
        final ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(imgUrl, imageViewUIL);
        imageLoader.loadImage(imgUrl, new SimpleImageLoadingListener() {
            //Highly customizable: onLoadingStarted, onLoadingComplete, onLoadingFailed, onLoadingCancelled, onProgressUpdate
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                imageViewUIL.setImageBitmap(loadedImage);
                imageViewUIL.setAnimation(anim);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_libs, menu);
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
