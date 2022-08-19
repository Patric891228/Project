package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import tw.scu.edu.graduationprojrct.R;

public class PokedexSelectedScene extends AppCompatActivity {
    TextView A,B,C,D,E;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_selected_scene);

        A = findViewById(R.id.A);
        B = findViewById(R.id.B);
        C = findViewById(R.id.C);
        D = findViewById(R.id.D);
        E = findViewById(R.id.E);

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PokedexSelectedScene.this,PokedexScene.class));
            }
        });
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PokedexSelectedScene.this,PokedexScene.class));
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PokedexSelectedScene.this,PokedexScene.class));
            }
        });
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PokedexSelectedScene.this,PokedexScene.class));
            }
        });
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PokedexSelectedScene.this,PokedexScene.class));
            }
        });

    }

}