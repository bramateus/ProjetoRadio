package mtz.mg.br.projetoradio;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        },2000); // 2 segundos de atraso


      //  final Animation loadAnimation = AnimationUtils.loadAnimation(this,R.anim.anim);
        final Animation a = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        ImageView splash = (ImageView) findViewById(R.id.imageView);

        splash.startAnimation(a);
        a.setDuration(3000);

    }










}
