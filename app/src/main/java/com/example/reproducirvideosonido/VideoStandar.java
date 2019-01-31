package com.example.reproducirvideosonido;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoStandar extends AppCompatActivity {

    private VideoView visor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_standar);

        visor = findViewById(R.id.videoStandar);// servira para cargar el
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pacmam_vs_mario1);
        visor.setVideoURI(video);
        visor.setMediaController(new MediaController(this));
        visor.start();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void onResume() {
        super.onResume();

    }

    public void onRestart() {
        super.onRestart();

    }


    public void onPause() {
        super.onPause();

    }

    public void onStop() {
        super.onStop();

    }

    public void onDestroy() {
        super.onDestroy();
        visor.stopPlayback();
    }
}
