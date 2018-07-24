package com.yudiz.vrdemo;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.IOException;
import java.io.InputStream;

public class ImageActivity extends AppCompatActivity {

    private VrPanoramaView vrImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        vrImageView = findViewById(R.id.vr_image);

        loadImage();
    }

    @Override
    public void onPause() {
        vrImageView.pauseRendering();
        super.onPause();
    }

    @Override
    public void onResume() {
        vrImageView.resumeRendering();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        vrImageView.shutdown();
        super.onDestroy();
    }

    private void loadImage() {
        VrPanoramaView.Options viewOptions = new VrPanoramaView.Options();
        viewOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;

        try (InputStream istr = getAssets().open("image.jpg")) {
            vrImageView.loadImageFromBitmap(BitmapFactory.decodeStream(istr), viewOptions);
            BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            Log.e("", "Could not decode default bitmap: " + e);
        }
    }


}
