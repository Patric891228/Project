package tw.scu.edu.graduationprojrct.scene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.PokedexObject;

public class MuscleLowBackScene extends AppCompatActivity {
    ImageView B1,B2,B3,B4,B5,B6,B7,B8,B9;
    ImageView Back;
    SharedPreferences SP;


    PokedexObject PO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_low_back_scene);


        SP = getSharedPreferences("data",MODE_PRIVATE);
        PO = new PokedexObject("All");


        B1 = findViewById(R.id.BF1);
        B2 = findViewById(R.id.BF2);
        B3 = findViewById(R.id.BF3);
        B4 = findViewById(R.id.BF4);
        B5 = findViewById(R.id.BF5);
        B6 = findViewById(R.id.BF6);
        B7 = findViewById(R.id.BF7);
        B8 = findViewById(R.id.BF8);
        B9 = findViewById(R.id.BF9);


        Back = findViewById(R.id.Back);
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatchData(5);
                startActivity(new Intent(MuscleLowBackScene.this,ProFileMuscle.class));

            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatchData(6);
                startActivity(new Intent(MuscleLowBackScene.this,ProFileMuscle.class));

            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatchData(7);
                startActivity(new Intent(MuscleLowBackScene.this,ProFileMuscle.class));

            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatchData(8);
                startActivity(new Intent(MuscleLowBackScene.this,ProFileMuscle.class));
            }
        });
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatchData(9);
                startActivity(new Intent(MuscleLowBackScene.this,ProFileMuscle.class));

            }
        });
        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatchData(10);
                startActivity(new Intent(MuscleLowBackScene.this,ProFileMuscle.class));

            }
        });
        B7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatchData(12);
                startActivity(new Intent(MuscleLowBackScene.this,ProFileMuscle.class));

            }
        });
        B8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatchData(14);
                startActivity(new Intent(MuscleLowBackScene.this,ProFileMuscle.class));
            }
        });
        B9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatchData(27);
                startActivity(new Intent(MuscleLowBackScene.this,ProFileMuscle.class));
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MuscleLowBackScene.this,PokedexMuscleScene.class));
            }
        });
    }
    private void CatchData(int i){
        String ChineseName = PO.ChineseName[i-1];
        String EnglishName = PO.EnglishName[i-1];
        String ImprovePart = PO.ImprovePart[i-1];
        String Introduce = PO.Introduce[i-1];
        int ImageID = PO.SportImgID[i-1];

        SharedPreferences.Editor editor = SP.edit();
        editor.putString("ChineseName",ChineseName);
        editor.putString("EnglishName",EnglishName);
        editor.putString("Introduce",Introduce);
        editor.putString("ImprovePart",ImprovePart);
        editor.putInt("ImageID",ImageID);
        editor.commit();
    }
}