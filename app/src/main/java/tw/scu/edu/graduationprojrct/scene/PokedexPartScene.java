package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import tw.scu.edu.graduationprojrct.R;

public class PokedexPartScene extends AppCompatActivity {
    ImageView Leave_BTN,SelectButton,SelectButton_Clicked;
    ImageView B1,B2,B3,B4,B5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_part_scene);

        Leave_BTN = findViewById(R.id.Leave_Button);
        SelectButton = findViewById(R.id.A);
        SelectButton_Clicked = findViewById(R.id.B);
        B1 = findViewById(R.id.B1);
        B2 = findViewById(R.id.B2);
        B3 = findViewById(R.id.B3);
        B4 = findViewById(R.id.B4);
        B5 = findViewById(R.id.B5);

        init();

        Leave_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PokedexPartScene.this,PokedexSelectedScene.class));
            }
        });
        SelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectButton.setVisibility(View.GONE);
                SelectButton_Clicked.setVisibility(View.VISIBLE);
                B1.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                B3.setVisibility(View.VISIBLE);
                B4.setVisibility(View.VISIBLE);
                B5.setVisibility(View.VISIBLE);
            }
        });
        SelectButton_Clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectButton.setVisibility(View.VISIBLE);
                SelectButton_Clicked.setVisibility(View.GONE);
                B1.setVisibility(View.GONE);
                B2.setVisibility(View.GONE);
                B3.setVisibility(View.GONE);
                B4.setVisibility(View.GONE);
                B5.setVisibility(View.GONE);
            }
        });
    }
    private void init(){
        SelectButton.setVisibility(View.VISIBLE);
        SelectButton_Clicked.setVisibility(View.GONE);
        B1.setVisibility(View.GONE);
        B2.setVisibility(View.GONE);
        B3.setVisibility(View.GONE);
        B4.setVisibility(View.GONE);
        B5.setVisibility(View.GONE);
    }

}