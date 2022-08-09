package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.DBHelper;


public class LoginScene extends AppCompatActivity {
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = findViewById(R.id.userName);
        EditText password = findViewById(R.id.Password);
        DB = new DBHelper(this);

        Button login = findViewById(R.id.Back);
        Button Regist = findViewById(R.id.finish);

        SharedPreferences shared = getSharedPreferences("isRegist",MODE_PRIVATE);
        Regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScene.this,RegistScene.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals("")) {
                    Toast.makeText(LoginScene.this, "Please enter all this fields", Toast.LENGTH_SHORT).show();
                }else if (user.equals("admin") || pass.equals("admin")){
                    Toast.makeText(LoginScene.this, "Already use Admin Account", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), AdminManagement.class);
                    startActivity(intent);
                    finish();
                }else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass){
                        Toast.makeText(LoginScene.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainScene.class);
                        startActivity(intent);
                        finish();
                        SharedPreferences.Editor editor = shared.edit();
                        editor.putBoolean("isRegist",true);
                        editor.commit();
                    }else {
                        Toast.makeText(LoginScene.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}