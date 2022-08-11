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

import tw.scu.edu.graduationprojrct.GlobalVariable;
import tw.scu.edu.graduationprojrct.R;

public class EnterScene extends AppCompatActivity {
    SharedPreferences shared;
    String UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        shared = getSharedPreferences("data",MODE_PRIVATE);
        Button EnterButton = findViewById(R.id.EnterButton);

        EnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check();
            }
        });
    }
    private void Check(){
        shared = getSharedPreferences("data",MODE_PRIVATE); // 取得資料
        boolean isRegist = shared.getBoolean("isRegist",false); // 後方為，若沒有資料，預設值為false

        if(!isRegist){
           startActivity(new Intent(EnterScene.this,LoginScene.class));
            finish();
        }else{
            UserName = shared.getString("UserName","Nan"); //取得暫存器中的名字
            Log.d("Warn",UserName);//將名字存入全域變數
            startActivity( new Intent(EnterScene.this,MainScene.class));
            finish();
        }

    }


}