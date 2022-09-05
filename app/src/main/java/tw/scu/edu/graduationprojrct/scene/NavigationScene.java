package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import tw.scu.edu.graduationprojrct.R;

public class NavigationScene extends AppCompatActivity {
    String SportType;
    SharedPreferences shared;
    Button GO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_scene);
        shared = getSharedPreferences("data",MODE_PRIVATE);
        SportType = shared.getString("SportType","Belly");
        Log.d("EnterScene","NavigationScene");
        GO = findViewById(R.id.GO);
        GO.setText(SportType);
        GO.setTextSize(60.0f);
        GO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationScene.this,SportScene.class));
            }
        });
    }
}