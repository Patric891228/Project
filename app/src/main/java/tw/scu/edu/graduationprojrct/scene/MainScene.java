package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import tw.scu.edu.graduationprojrct.R;



public class MainScene extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scene);
        //到此一遊
        //同上
        ImageButton mirror = findViewById(R.id.mirror);
        ImageButton counter = findViewById(R.id.counter);
        ImageButton tiecabinet = findViewById(R.id.tiecabinet);
        ImageButton magazine = findViewById(R.id.magazine);



        mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mirror.setAnimation(AnimationUtils.loadAnimation(mirror.getContext(), R.anim.fade_in));
                try{
                    Thread.sleep(1000);
                    Intent intent = new Intent(MainScene.this,TestSelfScene.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScene.this, SettingScene.class);
                startActivity(intent);
            }
        });
        tiecabinet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScene.this,BasicSportScene.class);
                startActivity(intent);
            }
        });
        magazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScene.this,PokedexScene.class);
                startActivity(intent);
            }
        });
    }
    private void initButton(ImageButton bt){
        Animation animation=new AlphaAnimation(1.0f,0.0f);
        animation.setDuration(300);
        bt.startAnimation(animation);
    }
}