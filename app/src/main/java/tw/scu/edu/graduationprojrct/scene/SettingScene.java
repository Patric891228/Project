package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import tw.scu.edu.graduationprojrct.R;


public class SettingScene extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_scene);

        ImageButton toPersonalScene = findViewById(R.id.toPersonalScene);
        ImageButton toRemindScene = findViewById(R.id.toShop);
        ImageButton toBGMScene = findViewById(R.id.toExchange);
        ImageButton Back_From_Setting_Scene = findViewById(R.id.Back_From_Setting_Scene);

        toPersonalScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingScene.this, PersonalScene.class));
            }
        });
        toRemindScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingScene.this,RemindScene.class));
            }
        });
        toBGMScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingScene.this, BGMScene.class));
            }
        });
        Back_From_Setting_Scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingScene.this, MainScene.class));
            }
        });
    }
}