package com.example.reproducirvideosonido;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        VideoView visor = findViewById(R.id.videoView1);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pacmam_vs_mario1);
        visor.setVideoURI(video);
        visor.setMediaController(new MediaController(this));
        visor.start();


    }
}
