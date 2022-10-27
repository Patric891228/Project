package tw.scu.edu.graduationprojrct.scene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.DetectorActivity;
import tw.scu.edu.graduationprojrct.R;

public class SportResultScene extends AppCompatActivity {
    ImageView BackHome;
    TextView Probaibility;
    SharedPreferences shared;
    Float P;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_result);
        Log.d("EnterScene","SportResultScene");
        BackHome = findViewById(R.id.BackHome);
        Probaibility = findViewById(R.id.Probaibility);
        shared = getSharedPreferences("data", MODE_PRIVATE);

        BackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportResultScene.this,MainScene.class));
            }
        });
        P = shared.getFloat("Pro", (float) 0.0);
        SharedPreferences.Editor editor = shared.edit();
        editor.remove("Pro");
        editor.commit();
        for (int i = 0; i<DetectorActivity.strike.size();i++){
            Log.d("準確率", String.valueOf(DetectorActivity.strike.get(i)));
        }
        DetectorActivity.strike.clear();
        Probaibility.setText(P.toString());
    }
}