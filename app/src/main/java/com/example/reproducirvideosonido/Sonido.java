package com.example.reproducirvideosonido;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

public class Sonido extends AppCompatActivity implements MediaController.MediaPlayerControl {

    /**Esta implementacion es para poder agregar los metodos necesarios para el controlador
     * por defecto de la musica ===>> MediaController.MediaPlayerControl  */

    //datos para los sonidos
    private ImageButton sonidoMoneda, sonidoVida;
    private SoundPool sound1moneda, sound2vida; // servira pra reproducir sonidos cortos
    private int song1 = 0, song2 = 0;

    //Datos para la reproducion de musica
    private MediaPlayer mediaPlayer; // servira pra reproducir sonidos largos
    private MediaController mediaController; // servira para controlar la reproduccion del audio por medio de una barra
    private Button reproducir, pausar, detener, reproducirPrincipal;
    private String PARADO, PAUSA, INICIO; //sirve para almacenar los mensajes del toast


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonido);

        //declaracion de lo botones de sonido
        sonidoMoneda = findViewById(R.id.imageButtonSonidoMoneda);
        sonidoVida = findViewById(R.id.imageButtonSonidoVida);
        //declaracion de los botones de reproduccion de la musica
        reproducir = findViewById(R.id.buttonReproducir);
        pausar = findViewById(R.id.buttonPausa);
        detener = findViewById(R.id.buttonDetener);
        reproducirPrincipal = findViewById(R.id.buttonSuperMarioInicio);

        //declaro e indico que musica se usara
        mediaPlayer = MediaPlayer.create(this, R.raw.super_mario_bros_music);
        // con esto pondremos los controles para el audio por defecto
        mediaController = new MediaController(this);
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(findViewById(R.id.soundSonido)); // indica en que pantalla aparece la barra

        //Sonido de moneda
        sound1moneda = new SoundPool(5, AudioManager.STREAM_MUSIC, 1);
        song1 = sound1moneda.load(this, R.raw.mariobros_moneda, 1);
        //Sonido de vida
        sound2vida = new SoundPool(5, AudioManager.STREAM_MUSIC, 1);
        song2 = sound2vida.load(this, R.raw.mariobros_vidaextra, 1);

        //esto nos servire para saber en que canal de sonido trabajaremos
        //this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    public void onResume() {
        super.onResume();

        //botones a presionar de los sonidos
        sonidoMoneda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //      soundID:int  leftVolume:float  rightVolume:float  priority:int  loop:int  rate:float
                sound1moneda.play(song1,1,1,1,0,1);
            }
        });

        sonidoVida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //      soundID:int  leftVolume:float  rightVolume:float  priority:int  loop:int  rate:float
                sound2vida.play(song1,1,1,1,0,1);
            }
        });

        //------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------

        //botones de la reproducion de musica
        reproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                //Aqui se obtiene el mensaje desde el archivo string.xml para el toast personalizado
                String INICIO = getString(R.string.inicioAudio);
                toastPersonalizado(INICIO);
                /* // ver como usar esta forma de hilos para evitar sobrecarga en el hilo principal
                Thread playThread = new Thread(){
                  public void run(){
                  }
                };
                playThread.start(); */

                //mediaPlayer.setLooping(true);
                //mediaPlayer.isPlaying();
            }
        });

        pausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    //verifica que la musica este activa despues de hacer un stop, evita un error
                    mediaPlayer.pause();
                    String PAUSA = getString(R.string.pausaAudio);
                    toastPersonalizado(PAUSA);
                }

            }
        });

        detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                //detiene y prepara nuevamente la musica a reproducir
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.super_mario_bros_music);
                String PARADO = getString(R.string.paradoAudio);
                toastPersonalizado(PARADO);
            }
        });

        reproducirPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                String INICIO = getString(R.string.inicioAudio);
                toastPersonalizado(INICIO);
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
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

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //Este metodo servira para mostrar el seek en la pantalla (barra de audio)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mediaController.show();
        return false;
    }
    //----------------------------------------------------------------------------------------------
    // metodos implementados de la interfaz para poder usar el seek(barra de audio)
    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        // Cuando el usuario toca el botón de pausa
        mediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        // Vuelve la duración de la pista de audio
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        // Vuelve la posición actual en la pista de audio
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        // Para ir a una posición de la pista
        mediaPlayer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        // Vuelve cierto cuando se está reproduciendo audio
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true; // este hay que cambiarlo a true, por defecto viene en false
    }

    @Override
    public boolean canSeekBackward() {
        return true; // cambiar a true porque asi se puede controlar por medio de la barra de audio
    }

    @Override
    public boolean canSeekForward() {
        return true; // cambiar a true porque asi se puede controlar por medio de la barra de audio
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}


/**NOTAS
 * MediaPlayer
 * Esta clase será usada para reproducir archivos de audio largos, es decir, para
 * reproducir, por ejemplo, música de fondo.
 *
 * SoundPool
 * Esta clase es usada para reproducir archivos de audio muy cortos, tales como efectos
 * de botones. Hay que tener en cuenta que el archivo de audio que reproduzca esta clase tiene que
 * tener un tamaño máximo de 1 Mb.
 *
 * La estructura del objeto SoundPool es la siguiente:
 *
 * SoundPool(int maxStreams , int streamType , int srcQuality)
 *     int maxStreams: indica el número de veces que podemos reproducir el sonido a la vez.
 *     int streamType: indica el tipo de flujo que usaremos .
 *     int srcQuality: indica la calidad. Este atributo actualmente no esta en uso.
 *
 *
 *
 */