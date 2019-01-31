package com.example.reproducirvideosonido;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoConfigurado extends AppCompatActivity {

    private Button reiniciar, stop, pause;
    private VideoView videoView;
    private MediaController media;
    private String REINICIO, PARADO, PAUSA, INICIO; //sirve para almacenar los mensajes del toast

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_configurado);

        reiniciar = findViewById(R.id.buttonReinicio);
        stop = findViewById(R.id.buttonStop);
        pause = findViewById(R.id.buttonPausa);
        videoView = findViewById(R.id.videoConfigurado);
        media = new MediaController(this);

        //Inicia el video automaticamente
        videoPlay();
        //Aqui se obtiene el mensaje desde el archivo string.xml para el toast personalizado
        String INICIO = getString(R.string.inicioVideo);
        toastPersonalizado(INICIO);
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    public void onResume() {
        super.onResume();
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoPlay();// actuara como reinicio del video despues de pararlo
                //Aqui se obtiene el mensaje desde el archivo string.xml para el toast personalizado
                String REINICIO = getString(R.string.reinicioVideo);
                toastPersonalizado(REINICIO);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.pause();
                String PAUSA = getString(R.string.pausaVideo);
                toastPersonalizado(PAUSA);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.stopPlayback();
                String PARADO = getString(R.string.paradoVideo);
                toastPersonalizado(PARADO);
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
        videoView.stopPlayback();
    }


    //Funcion para iniciar o reiniciar el video
    public void videoPlay(){
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.pacmam_vs_mario2;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.setMediaController(media);
        media.setAnchorView(videoView);
        videoView.start();
        /* //Comparar con la configuracion del video standar
        VideoView visor = findViewById(R.id.videoStandar);// servira para cargar el
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pacmam_vs_mario1);
        visor.setVideoURI(video);
        visor.setMediaController(new MediaController(this));
        visor.start();     */
    }


    //Funcion que personaliza el mensaje del toast
    public void toastPersonalizado(String mensaje){
        Toast toast = new Toast(this);
        //usamos cualquier layout como Toast
        View toast_layout = getLayoutInflater().inflate(R.layout.toast_personalizado, (ViewGroup) findViewById(R.id.lytLayout));
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(toast_layout);
        //se podría definir el texto en el layout si es invariable pero lo hacemos programáticamente
        //tenemos acceso a cualquier widget del layout del Toast
        TextView textView = (TextView) toast_layout.findViewById(R.id.mensajeToast);
        textView.setText(mensaje);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
