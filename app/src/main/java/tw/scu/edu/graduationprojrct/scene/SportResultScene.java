package tw.scu.edu.graduationprojrct.scene;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.R;

public class SportResultScene extends AppCompatActivity {
    ImageView BackHome;
    TextView Probaibility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_result);
        Log.d("EnterScene","SportResultScene");
        BackHome = findViewById(R.id.BackHome);
        Probaibility = findViewById(R.id.Probaibility);

        BackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportResultScene.this,MainScene.class));
            }
        });

        Probaibility.setText("0.0");
    }
}