package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import tw.scu.edu.graduationprojrct.R;

public class SportSelectScene extends AppCompatActivity {
    ImageView Tie1,Tie2,Tie3,Tie4,Tie5,Tie6,Tie7,Tie8;
    ImageView Tie_Click1,Tie_Click2,Tie_Click3,Tie_Click4,
              Tie_Click5,Tie_Click6,Tie_Click7,Tie_Click8;
    ImageButton Back_From_SportSelect;
    int t1,t2,t3,t4,t5;
    SharedPreferences shared;
    String SportType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_select_scene);
        shared = getSharedPreferences("data",MODE_PRIVATE);
        SportType = shared.getString("SportType","Belly");
        ResetInt();
        Tie1 = findViewById(R.id.Tie1);
        Tie2 = findViewById(R.id.Tie2);
        Tie3 = findViewById(R.id.Tie3);
        Tie4 = findViewById(R.id.Tie4);
        Tie5 = findViewById(R.id.Tie5);
        Tie6 = findViewById(R.id.Tie6);
        Tie7 = findViewById(R.id.Tie7);
        Tie8 = findViewById(R.id.Tie8);

        Tie_Click1 = findViewById(R.id.Tie_Click1);
        Tie_Click2 = findViewById(R.id.Tie_Click2);
        Tie_Click3 = findViewById(R.id.Tie_Click3);
        Tie_Click4 = findViewById(R.id.Tie_Click4);
        Tie_Click5 = findViewById(R.id.Tie_Click5);
        Tie_Click6 = findViewById(R.id.Tie_Click6);
        Tie_Click7 = findViewById(R.id.Tie_Click7);
        Tie_Click8 = findViewById(R.id.Tie_Click8);

        Back_From_SportSelect = findViewById(R.id.Back_From_SportSelect);

        Tie_Click1.setVisibility(View.GONE);
        Tie_Click2.setVisibility(View.GONE);
        Tie_Click3.setVisibility(View.GONE);
        Tie_Click4.setVisibility(View.GONE);
        Tie_Click5.setVisibility(View.GONE);
        Tie_Click6.setVisibility(View.GONE);
        Tie_Click7.setVisibility(View.GONE);
        Tie_Click8.setVisibility(View.GONE);

        Tie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("SportType","Slouch");
                editor.commit();
                SetAllDown();
                SetAllUp();
                Tie1.setVisibility(View.GONE);
                Tie_Click1.setVisibility(View.VISIBLE);
            }
        });
        Tie_Click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportSelectScene.this,NavigationScene.class));
            }
        });
        Tie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("SportType","Oxleg");
                editor.commit();
                SetAllDown();
                SetAllUp();
                Tie2.setVisibility(View.GONE);
                Tie_Click2.setVisibility(View.VISIBLE);
            }
        });
        Tie_Click2.setOnClickListener(new View.OnClickListener()    {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportSelectScene.this,NavigationScene.class));
            }
        });
        Tie3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("SportType","Pelvic");
                editor.commit();
                SetAllDown();
                SetAllUp();
                Tie3.setVisibility(View.GONE);
                Tie_Click3.setVisibility(View.VISIBLE);
            }
        });
        Tie_Click3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportSelectScene.this,NavigationScene.class));
            }
        });
        Tie4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("SportType","Saddlebagc");
                editor.commit();
                SetAllDown();
                SetAllUp();
                Tie4.setVisibility(View.GONE);
                Tie_Click4.setVisibility(View.VISIBLE);
            }
        });
        Tie_Click4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportSelectScene.this,NavigationScene.class));
            }
        });
        Tie5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Showt();
//                ResetOther(5);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("SportType","Belly");
                editor.commit();
                SetAllDown();
                SetAllUp();
                Tie5.setVisibility(View.GONE);
                Tie_Click5.setVisibility(View.VISIBLE);
            }
        });
        Tie_Click5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportSelectScene.this,NavigationScene.class));
            }
        });
        Tie6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetAllDown();
                SetAllUp();
                Tie6.setVisibility(View.GONE);
                Tie_Click6.setVisibility(View.VISIBLE);
            }
        });
        Tie7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetAllDown();
                SetAllUp();
                Tie7.setVisibility(View.GONE);
                Tie_Click7.setVisibility(View.VISIBLE);
            }
        });
        Tie8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetAllDown();
                SetAllUp();
                Tie8.setVisibility(View.GONE);
                Tie_Click8.setVisibility(View.VISIBLE);
            }
        });

        Back_From_SportSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SportSelectScene.this,MainScene.class));
            }
        });
    }
    private void SetAllDown(){
        Tie_Click1.setVisibility(View.GONE);
        Tie_Click2.setVisibility(View.GONE);
        Tie_Click3.setVisibility(View.GONE);
        Tie_Click4.setVisibility(View.GONE);
        Tie_Click5.setVisibility(View.GONE);
        Tie_Click6.setVisibility(View.GONE);
        Tie_Click7.setVisibility(View.GONE);
        Tie_Click8.setVisibility(View.GONE);
    }
    private void SetAllUp(){
        Tie1.setVisibility(View.VISIBLE);
        Tie2.setVisibility(View.VISIBLE);
        Tie3.setVisibility(View.VISIBLE);
        Tie4.setVisibility(View.VISIBLE);
        Tie5.setVisibility(View.VISIBLE);
        Tie6.setVisibility(View.VISIBLE);
        Tie7.setVisibility(View.VISIBLE);
        Tie8.setVisibility(View.VISIBLE);
    }
    private void ResetInt(){
        t1=0;
        t2=0;
        t3=0;
        t4=0;
        t5=0;
    }
    private void ResetOther(int i){
        switch(i){
            case 1:t2=0;t3=0;t4=0;t5=0;break;
            case 2:t1=0;t3=0;t4=0;t5=0;break;
            case 3:t1=0;t2=0;t4=0;t5=0;break;
            case 4:t1=0;t2=0;t3=0;t5=0;break;
            case 5:t1=0;t2=0;t3=0;t4=0;
        }
    }
    private void Showt(){
        Log.d("t1", String.valueOf(t1));
        Log.d("t2", String.valueOf(t2));
        Log.d("t3", String.valueOf(t3));
        Log.d("t4", String.valueOf(t4));
        Log.d("t5", String.valueOf(t5));
    }
}