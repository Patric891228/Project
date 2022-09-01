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
import android.widget.ImageView;

import tw.scu.edu.graduationprojrct.GlobalVariable;
import tw.scu.edu.graduationprojrct.R;

import static tw.scu.edu.graduationprojrct.R.anim.fade_in;


public class MainScene extends AppCompatActivity {
    SharedPreferences shared;
    MediaPlayer mysong;
    ImageView tiecabinet,Setting,Logout,Setting_Click,Logout_Click;
    ImageButton mirror,counter,magazine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scene);


        shared = getSharedPreferences("data",MODE_PRIVATE);

        mirror = findViewById(R.id.mirror);
        counter = findViewById(R.id.counter);
        tiecabinet = findViewById(R.id.tiecabinet);
        magazine = findViewById(R.id.magazine);

        Setting = findViewById(R.id.Setting_original);
        Logout = findViewById(R.id.Logout_original);
        Setting_Click = findViewById(R.id.Setting_Click);
        Logout_Click = findViewById(R.id.Logout_Click);

        mysong = MediaPlayer.create(MainScene.this, R.raw.donigen);
        //mysong.start();
        initButton();
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
                Intent intent = new Intent(MainScene.this,PersonalScene.class);
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

        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Setting.setVisibility(View.GONE);
                Setting_Click.setVisibility(View.VISIBLE);
                startActivity(new Intent(MainScene.this,OtherSettingScene.class));
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout.setVisibility(View.GONE);
                Logout_Click.setVisibility(View.VISIBLE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putBoolean("isRegist",false);
                editor.putBoolean("isEnter",false);
                editor.putString("UserName","Nan");
                editor.putBoolean("isFirstSetting",false);
                editor.commit();
                startActivity(new Intent(MainScene.this,LoginScene.class));
            }
        });
    }
    private void initButton(){
       Setting.setVisibility(View.VISIBLE);
       Logout.setVisibility(View.VISIBLE);
       Setting_Click.setVisibility(View.GONE);
       Logout_Click.setVisibility(View.GONE);
    }
    protected void onPause(){
        super.onPause();
        mysong.release();
        finish();
    }
}