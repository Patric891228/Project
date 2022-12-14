package tw.scu.edu.graduationprojrct.scene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.R.drawable;

public class EnterScene extends AppCompatActivity {
    SharedPreferences shared;
    String UserName;
    ImageButton EnterButton;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        shared = getSharedPreferences("data",MODE_PRIVATE);
        EnterButton = findViewById(R.id.EnterButton);

        EnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnterButton.setBackgroundResource(drawable.sdo);
                SharedPreferences.Editor editor = shared.edit();
                editor.putBoolean("isEdit",true);
                editor.commit();
                Check();
            }
        });
    }

    private void Check() {
        shared = getSharedPreferences("data",MODE_PRIVATE); // 取得資料
        boolean isRegist = shared.getBoolean("isRegist",false); // 後方為，若沒有資料，預設值為false
        if(!isRegist){
            startActivity(new Intent(EnterScene.this,CheckAccountScene.class));
            finish();
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            Log.d("Anim","Use");
        }else{
            UserName = shared.getString("UserName","Nan"); //取得暫存器中的名字
            Log.d("Warn",UserName);//將名字存入全域變數

            startActivity( new Intent(EnterScene.this,MainScene.class));
            finish();
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            Log.d("Anim","Use");
        }

    }


}