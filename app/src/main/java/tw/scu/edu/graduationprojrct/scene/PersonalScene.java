package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import tw.scu.edu.graduationprojrct.R;

public class PersonalScene extends AppCompatActivity {
    ImageButton Back,toAccountSet,toRemind,toBGM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_scene);

        Back = findViewById(R.id.Back_From_Personal_Scene);
        toAccountSet = findViewById(R.id.toAccountSetScene);
        toRemind = findViewById(R.id.toRemind);
        toBGM = findViewById(R.id.toBGM);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalScene.this,SettingScene.class));
            }
        });
        toAccountSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalScene.this,RemindScene.class));
            }
        });
        toRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalScene.this,RemindScene.class));
            }
        });
        toBGM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalScene.this,BGMScene.class));
            }
        });
    }
}