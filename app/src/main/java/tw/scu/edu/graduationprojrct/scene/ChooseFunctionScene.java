package tw.scu.edu.graduationprojrct.scene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.R;

public class ChooseFunctionScene extends AppCompatActivity {
    ImageView Back,ToPoseDetector,ToYOLO,BackChoose;
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_function_scene);

        ToPoseDetector = findViewById(R.id.ToPostDetector);
        ToYOLO = findViewById(R.id.ToYOLO);
        BackChoose = findViewById(R.id.BackChoose);

        shared = getSharedPreferences("data",MODE_PRIVATE);
        BackChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseFunctionScene.this,SportSelectScene.class));
            }
        });


        ToPoseDetector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = shared.edit();
                editor.putInt("ChooseFunc",0);
                editor.commit();
                startActivity(new Intent(ChooseFunctionScene.this,NavigationScene.class));
            }
        });

        ToYOLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = shared.edit();
                editor.putInt("ChooseFunc",1);
                editor.commit();
                startActivity(new Intent(ChooseFunctionScene.this,NavigationScene.class));
            }
        });
    }
}