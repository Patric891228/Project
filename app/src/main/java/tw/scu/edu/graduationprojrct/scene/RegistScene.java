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

public class RegistScene extends AppCompatActivity {
    EditText userName,Password,Sexual,PhoneNumber;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_scene);

        Button Back = findViewById(R.id.Back);
        Button finish = findViewById(R.id.finish);

        userName = findViewById(R.id.userName);
        Password = findViewById(R.id.Password);
        Sexual = findViewById(R.id.Sexual);
        PhoneNumber = findViewById(R.id.PhoneNumber);

        DB = new DBHelper(this);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistScene.this,LoginScene.class);
                startActivity(intent);
                finish();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();
                String pass = Password.getText().toString();
                String sex = Sexual.getText().toString();
                String phone = PhoneNumber.getText().toString();

                if(user.equals("") || pass.equals("") || sex.equals("")||phone.equals("")) {
                    Toast.makeText(RegistScene.this, "Please re-enter all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuser = DB.checkusername(user);
                    if(checkuser == false){
                        Boolean insert = DB.insertData(user,pass,sex,phone);
                        if(insert){
                            Toast.makeText(RegistScene.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginScene.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(RegistScene.this, "Registration Failed ", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegistScene.this, "Account has already existed ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}