package tw.scu.edu.graduationprojrct.scene;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.DetectorActivity;
import tw.scu.edu.graduationprojrct.R;

public class PokedexSelectedScene extends AppCompatActivity {
    ImageView Left_Magazine,Center_Magazine,Right_Magazine;
    ImageView Left_Magazine_Move,Center_Magazine_Move,Right_Magazine_Move;
    ImageButton Back_From_Magazine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_selected_scene);

        Left_Magazine = findViewById(R.id.Left_Magazine);
        Center_Magazine = findViewById(R.id.Center_Magazine);
        Right_Magazine = findViewById(R.id.Right_Magazine);
        Left_Magazine_Move = findViewById(R.id.Left_Magazine_Move);
        Center_Magazine_Move = findViewById(R.id.Center_Magazine_Move);
        Right_Magazine_Move = findViewById(R.id.Right_Magazine_Move);

        Back_From_Magazine = findViewById(R.id.Back_From_Magazine);

        Magazine_GONE(Left_Magazine_Move);
        Magazine_GONE(Center_Magazine_Move);
        Magazine_GONE(Right_Magazine_Move);

        Left_Magazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Magazine_GONE(Left_Magazine);
                Magazine_GONE(Center_Magazine_Move);
                Magazine_GONE(Right_Magazine_Move);
                Magazine_Appear(Left_Magazine_Move);
                Magazine_Appear(Center_Magazine);
                Magazine_Appear(Right_Magazine);
            }
        });
        Center_Magazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Magazine_GONE(Center_Magazine);
                Magazine_GONE(Left_Magazine_Move);
                Magazine_GONE(Right_Magazine_Move);
                Magazine_Appear(Center_Magazine_Move);
                Magazine_Appear(Left_Magazine);
                Magazine_Appear(Right_Magazine);
            }
        });
        Right_Magazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Magazine_GONE(Right_Magazine);
                Magazine_GONE(Left_Magazine_Move);
                Magazine_GONE(Center_Magazine_Move);
                Magazine_Appear(Right_Magazine_Move);
                Magazine_Appear(Left_Magazine);
                Magazine_Appear(Center_Magazine);
            }
        });
        Left_Magazine_Move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PokedexSelectedScene.this,PokedexProveScene.class));
            }
        });
        Right_Magazine_Move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PokedexSelectedScene.this,PokedexPartScene.class));
            }
        });
        Center_Magazine_Move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PokedexSelectedScene.this, DetectorActivity.class));
            }
        });
        Back_From_Magazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PokedexSelectedScene.this,MainScene.class));
            }
        });
    }
    void Magazine_GONE(View view){
        view.setVisibility(view.GONE);
    }
    void Magazine_Appear(View view){
        view.setVisibility(view.VISIBLE);
    }

}