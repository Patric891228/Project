package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import tw.scu.edu.graduationprojrct.R;

public class PersonalScene extends AppCompatActivity {
    ImageView Back,toAccountSet,toSetting;
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_scene);

        Back = findViewById(R.id.Back_From_Personal);
        toAccountSet = findViewById(R.id.Personal_Account);
        toSetting = findViewById(R.id.Personal_Setting);


        shared = getSharedPreferences("data",MODE_PRIVATE);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalScene.this,MainScene.class));
            }
        });
        toAccountSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalScene.this,AccountEditScene.class));
            }
        });


        toSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalScene.this,SettingScene.class));
            }
        });

    }
}