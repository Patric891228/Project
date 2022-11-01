package tw.scu.edu.graduationprojrct.scene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.PokedexObject;

public class MuscleSideScene extends AppCompatActivity {
    ImageView B1,B2,B3,B4;
    ImageView Back;
    SharedPreferences SP;
    PokedexObject PO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_side_scene);

        B1 = findViewById(R.id.BB1);
        B2 = findViewById(R.id.BB2);
        B3 = findViewById(R.id.BB3);
        B4 = findViewById(R.id.BB4);

        Back = findViewById(R.id.Back);
        SP = getSharedPreferences("data",MODE_PRIVATE);
        PO = new PokedexObject("All");

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatchData(9);
                startActivity(new Intent(MuscleSideScene.this,ProFileMuscle.class));
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatchData(15);
                startActivity(new Intent(MuscleSideScene.this,ProFileMuscle.class));
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatchData(22);
                startActivity(new Intent(MuscleSideScene.this,ProFileMuscle.class));
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatchData(23);
                startActivity(new Intent(MuscleSideScene.this,ProFileMuscle.class));
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MuscleSideScene.this,PokedexMuscleScene.class));
            }
        });

    }
    private void CatchData(int i ){
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