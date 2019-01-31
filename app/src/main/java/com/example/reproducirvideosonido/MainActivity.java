package com.example.reproducirvideosonido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /**Icons made by https://www.flaticon.es/autores/smalllikeart
     *is licensed by http://creativecommons.org/licenses/by/3.0/
     Creative Commons BY 3.0     */
    //Mensaje a mostrar en el Logcat.
    private static final String AVISO = "====>>> 'AVISO' <<<====";

    Button sonido, video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sonido = findViewById(R.id.buttonSonidos);
        video = findViewById(R.id.buttonVideos);


    }


    @Override
    public void onStart() {
        super.onStart();

    }

    public void onResume() {
        super.onResume();


        sonido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // envia a la pantalla de sonido
                Intent irSonido = new Intent(MainActivity.this, Sonido.class);
                startActivity(irSonido);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // envia a la pantalla de video
                Intent irVideo = new Intent(MainActivity.this, Video.class);
                startActivity(irVideo);
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

        /*
        Toast toast = Toast.makeText(this, "Ventana 1 Estoy en onDestroy", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 1400);
        toast.show();
        Log.i(AVISO, getLocalClassName() + ".onDestroy;");
        */
    }

}
