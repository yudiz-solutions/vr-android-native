package com.yudiz.vrdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity {

    private VrVideoView vrVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        vrVideoView = findViewById(R.id.vr_video);

        vrVideoView.setEventListener(new VrVideoEventListener() {
            @Override
            public void onCompletion() {
                vrVideoView.seekTo(0);          //loop
            }
        });

        loadVideo();

    }

    private void loadVideo() {
        try {
            vrVideoView.loadVideoFromAsset("video.mp4",
                    new VrVideoView.Options());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        vrVideoView.pauseRendering();
    }

    @Override
    public void onResume() {
        super.onResume();
        vrVideoView.resumeRendering();
    }

    @Override
    public void onDestroy() {
        vrVideoView.shutdown();
        super.onDestroy();
    }
}
