package tw.scu.edu.graduationprojrct.scene;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.R;

public class EnterScene extends AppCompatActivity {
    SharedPreferences shared;
    @Override

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        Button EnterButton = findViewById(R.id.EnterButton);


        EnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check();

            }
        });


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    private void Check(){
        shared = getSharedPreferences("isRegist",MODE_PRIVATE);
        boolean isRegist = shared.getBoolean("isRegist",false);
        if(!isRegist){
            Intent intent = new Intent(EnterScene.this,LoginScene.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                finish();
            } else {
                startActivity(intent);
                finish();
            }
        }else{
            Intent intent = new Intent(EnterScene.this,MainScene.class);
            startActivity(intent);
            finish();
        }

    }


}