package com.example.reproducirvideosonido;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    private Button reproStandar, reproConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //Se declara los botones
        reproStandar = findViewById(R.id.buttonReproStandar);
        reproConfig = findViewById(R.id.buttonReproConfig);
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    public void onResume() {
        super.onResume();


        reproStandar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reproducirStardar = new Intent(Video.this, VideoStandar.class);
                startActivity(reproducirStardar);
            }
        });

        reproConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reproducirconfigurado = new Intent(Video.this, VideoConfigurado.class);
                startActivity(reproducirconfigurado);
            }
        });
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

    }
}
