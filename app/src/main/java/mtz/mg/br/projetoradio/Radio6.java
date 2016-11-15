package mtz.mg.br.projetoradio;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class Radio6 extends AppCompatActivity implements MediaPlayer.OnPreparedListener,MediaPlayer.OnSeekCompleteListener,MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnErrorListener {




    private ImageView btplay;
    private ProgressDialog dialog;
    private MediaPlayer player;
    private Boolean isplaying;
    final   IcyStreamMeta meta = new IcyStreamMeta();
    final String urlRock6 = "http://online-radioroks2.tavrmedia.ua/RadioROKS_HardnHeavy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio6);




        btplay = (ImageView) findViewById(R.id.btplay);










        // Código para animação da textView ir da Esquerda - Direita
        //   final Animation animTranslate = AnimationUtils.loadAnimation(this,R.anim.anim);
        //   TextView tv = (TextView) findViewById(R.id.tv);
        //   tv.startAnimation(animTranslate);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.shutdownicon:
                finish();
                return true;

            case R.id.sobre:
                //   startActivity(new Intent(getBaseContext(),MainActivity.class));
                //    finish();
                setContentView(R.layout.about);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Toast.makeText(getBaseContext(),"Toque Para Voltar",Toast.LENGTH_LONG).show();
                return true;



        }





        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle output){
        super.onSaveInstanceState(output);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null){
            player.stop();
            player.release();
            player = null;
        }
    }





    public void onNext(View view){


        if (player != null) {
            player.stop();
        }
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;
        vibe.vibrate(30);
        startActivity(new Intent(this, Radio7.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void onPrevious(View view){

        if (player != null) {
            player.stop();
        }
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;
        vibe.vibrate(30);
        startActivity(new Intent(this, Radio5.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    public void about(View view){


        setContentView(R.layout.radio6);
        final TextView tv = (TextView) findViewById(R.id.tv);
        final TextView tv2 = (TextView) findViewById(R.id.tv2);
        tv.setText(null);
        tv2.setText(null);
        try {


            tv.setText("" + meta.getArtist());
            tv2.setText("" + meta.getTitle());

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    // ONCLICK LISTENER



    public void playMusic(View view) throws IOException {



        if (player == null) {


            try {

                final TextView tv = (TextView) findViewById(R.id.tv);
                final TextView tv2 = (TextView) findViewById(R.id.tv2);


                Log.i("Script", "playMusic()");
  /*                  //Carregar do Cartão primeiro SDK
                    File sdcard = Environment.getExternalStorageDirectory();
                    File file = new File(sdcard, "/storage/sdcard1/w.mp3");
                    player = new MediaPlayer();
                    player.setDataSource(file.getAbsolutePath().toString());
                    player.prepareAsync();



 */                // Toast.makeText(this,"Play",Toast.LENGTH_SHORT).show();
                // Carregar da Rede
                player = new MediaPlayer();
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);

                player.setDataSource("" + urlRock6);



                // Toast.makeText(this,"Musica: " + nome,Toast.LENGTH_SHORT).show();
                player.prepareAsync();


                player.setOnBufferingUpdateListener(this);
                player.setOnCompletionListener(this);
                player.setOnErrorListener(this);
                player.setOnPreparedListener(this);









/*


                String title = "";
          //      final   IcyStreamMeta meta = new IcyStreamMeta();
                try {
                    meta.setStreamUrl(new URL("http://188.40.32.140:8242/poprockdobrasil"));
                    title = meta.getStreamTitle();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.i("Script","esta caindo aqui error");
                    e.printStackTrace();
                }

*/

                //     tv.setText("" + meta.getArtist());
                //     tv2.setText("" + meta.getTitle());
// /*

                final Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        try {

                            //      tv.setText(null);
                            //      tv2.setText(null);
                            tv.setText("" + meta.getArtist());
                            tv2.setText("" + meta.getTitle());

                            Log.d("Banda: ", "" + tv.getText());
                            Log.w("Música: ", "" + tv2.getText());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                };


                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        long futureTime = System.currentTimeMillis() + 3500;
                        while (System.currentTimeMillis() != 0) {
                            synchronized (this) {
                                try {
                                    Log.i("I", "ATUALIZANDO TITULO DAS MÚSICAS");

                                    // Leva 4 segundos a partir do momento que o radio começa a tocar pra atualizar a cada 4 min.
                                    wait(4000);
                                    String title = "";
                                    //      final   IcyStreamMeta meta = new IcyStreamMeta();


                                    // As 9 linhas abaixo e pra atualizar o nome da banda e da musica a cada 8000 ms.
                                    try {


                                        meta.setStreamUrl(new URL("" + urlRock6));
                                        title = meta.getStreamTitle();

                                    } catch (MalformedURLException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        Log.i("Script", "esta caindo aqui error");
                                        e.printStackTrace();
                                    }
                                    handler.sendEmptyMessage(0);
                                } catch (Exception e) {
                                }
                            }
                        }
                        // handler.sendEmptyMessage(1000);
                    }
                };
                Thread buttonThread = new Thread(r);
                buttonThread.start();


// */


                // Try do metódo PlayMusic.
            } catch (IOException e) {
                Log.i("Script", "esta caindo aqui error");
                e.printStackTrace();
            }


        } else {
            player.start();
            isplaying = true;

        }


        dialog = new ProgressDialog(Radio6.this);
        dialog.setCancelable(true);
        dialog.setMessage("Carregando Música...");
        dialog.show();



    }
    // FIM DO PLAY MUSIC









    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.i("Script","onPrepared()");


        dialog.cancel();
        isplaying = true;



        //  btplay.setEnabled(false);

        //mp start inicia o som
        mp.start();
        btplay.setEnabled(false);





       /* // Esse try constrói o SeekBar de volume e controla ele...
        try
        {
           final SeekBar volumeSeekbar = (SeekBar)findViewById(R.id.seekbar);
           final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));


            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0)
                {
                }
                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
                {

                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

*/









        //  mp.setVolume(1,0);




    }

    public void pauseMusic(View view){
        if (player != null){
            player.pause();


        }
    }

    public void stopMusic(View view){

        if (player != null){
            player.stop();
            player.release();
            player = null;

            TextView tv = (TextView) findViewById(R.id.tv);
            TextView tv2 = (TextView) findViewById(R.id.tv2);

            tv.setText("");
            tv2.setText("");



        }

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.i("Script","onError()");
        Log.i("Script","SEM INTERNET");
        Toast.makeText(this,"VERIFIQUE SUA CONEXÃO COM A INTERNET",Toast.LENGTH_LONG).show();


        if (player != null){
            player.stop();
            player.release();
            player = null;

            TextView tv = (TextView) findViewById(R.id.tv);
            TextView tv2 = (TextView) findViewById(R.id.tv2);

            tv.setText("");
            tv2.setText("");
        }


        return false;
    }




    @Override
    public void onCompletion(MediaPlayer mp) {

    }


    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }










































}

