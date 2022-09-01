package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.DBHelper;

public class RegistScene extends AppCompatActivity {
    EditText userName,Account,Password;
    ImageButton Back_Check , finish_regist;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_scene);

        Back_Check = findViewById(R.id.Back_Check);
        finish_regist = findViewById(R.id.finish_regist);

        userName = findViewById(R.id.userName);
        Account = findViewById(R.id.Account);
        Password = findViewById(R.id.Password);

        DB = new DBHelper(this);
        Back_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistScene.this,CheckAccountScene.class);
                startActivity(intent);
                finish();
            }
        });

        finish_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = userName.getText().toString();
                String account = Account.getText().toString();
                String pass = Password.getText().toString();


                if(user.equals("") || pass.equals("") || account.equals("")) {
                    Toast.makeText(RegistScene.this, "Please re-enter all the fields", Toast.LENGTH_SHORT).show();
                    //有輸入框沒有輸入東西
                }else{
                    Boolean checkuser = DB.checkusername(user);
                    //有無帳號
                    if(checkuser == false){
                        Boolean insert = DB.insertData(user,account,pass);
                        DB.insertPersonalInfo(user,"男","1/1","Nan"); //寫進預設資料
                        if(insert){
                            Toast.makeText(RegistScene.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity( new Intent(getApplicationContext(), LoginScene.class));
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