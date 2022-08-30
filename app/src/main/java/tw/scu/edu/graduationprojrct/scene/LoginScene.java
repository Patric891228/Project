package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import tw.scu.edu.graduationprojrct.GlobalVariable;
import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.DBHelper;


public class LoginScene extends AppCompatActivity {
    DBHelper DB;
    SharedPreferences shared;
    ImageButton Start_Login , Back_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Start_Login = findViewById(R.id.finish_regist);
        Back_check = findViewById(R.id.Back_Check);
        EditText account = findViewById(R.id.userName);
        EditText password = findViewById(R.id.Password);

        DB = new DBHelper(this);

        shared = getSharedPreferences("data",MODE_PRIVATE);
        Back_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScene.this,CheckAccountScene.class);
                startActivity(intent);
            }
        });

        Start_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ac = account.getText().toString();
                String pass = password.getText().toString();

                if(ac.equals("") || pass.equals("")) {
                    Toast.makeText(LoginScene.this, "Please enter all this fields", Toast.LENGTH_SHORT).show();
                    // 有沒填寫的表格
                }else if (ac.equals("admin") || pass.equals("admin")){
                    // 登入官方帳號
                    SharedPreferences.Editor editor = shared.edit();
                    editor.putBoolean("isRegist",true);
                    editor.putString("UserName","admin");
                    editor.commit();
//                    DB.insertTime("admin","00:00");
                    Toast.makeText(LoginScene.this, "Already use Admin Account", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginScene.this, AdminManagement.class));
                    finish();

                }else{
                    Boolean checkuserpass = DB.checkusernamepassword(ac, pass);
                    if(checkuserpass){
                        Toast.makeText(LoginScene.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainScene.class));
                        finish();

                        SharedPreferences.Editor editor = shared.edit();
                        editor.putBoolean("isRegist",true);
                        editor.putString("UserName",ac);
                        editor.commit();
                    }else {
                        Toast.makeText(LoginScene.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}