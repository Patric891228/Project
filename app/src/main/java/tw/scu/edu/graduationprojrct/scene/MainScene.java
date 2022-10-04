package tw.scu.edu.graduationprojrct.scene;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.R;


public class MainScene extends AppCompatActivity {
    SharedPreferences shared;
    MediaPlayer mysong;
    ImageView tiecabinet,Setting,Logout,Setting_Click,Logout_Click;
    ImageButton mirror,counter,magazine;
    int BGMList[] = {R.raw.donigen,R.raw.donigen,R.raw.donigen,R.raw.donigen,R.raw.donigen};

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

        int BGMNumber = shared.getInt("BGMNumber",0);
        Log.d("歌曲編號", String.valueOf(BGMNumber));
        mysong = MediaPlayer.create(MainScene.this,BGMList[BGMNumber]);
        mysong.start();
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