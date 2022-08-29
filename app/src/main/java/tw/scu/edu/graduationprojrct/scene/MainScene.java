package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import tw.scu.edu.graduationprojrct.GlobalVariable;
import tw.scu.edu.graduationprojrct.R;

import static tw.scu.edu.graduationprojrct.R.anim.fade_in;


public class MainScene extends AppCompatActivity {
    SharedPreferences shared;
    MediaPlayer mysong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        shared = getSharedPreferences("data",MODE_PRIVATE);
        Log.d("Info",shared.getString("UserName","Nan"));

        setContentView(R.layout.activity_main_scene);
        ImageButton mirror = findViewById(R.id.mirror);
        ImageButton counter = findViewById(R.id.counter);
        ImageButton tiecabinet = findViewById(R.id.tiecabinet);
        ImageButton magazine = findViewById(R.id.magazine);

        mysong = MediaPlayer.create(MainScene.this, R.raw.donigen);
        //mysong.start();

        mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScene.this,TestSelfScene.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainScene.this);
                startActivity(intent,options.toBundle());
            }
        });

        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScene.this,LoginScene.class);
                startActivity(intent);
            }
        });
        tiecabinet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScene.this,SportSelectScene.class);
                startActivity(intent);
            }
        });
        magazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScene.this,PokedexSelectedScene.class);
                startActivity(intent);
            }
        });
    }
    private void initButton(ImageButton bt){
        Animation animation=new AlphaAnimation(1.0f,0.0f);
        animation.setDuration(300);
        bt.startAnimation(animation);
    }
    protected void onPause(){
        super.onPause();
        mysong.release();
        finish();
    }
}