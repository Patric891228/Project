package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.DBHelper;

public class AccountEditScene extends AppCompatActivity {
    EditText account_input, password_input;
    Button update_button, delete_button;
    TextView UserName;
    String username, account, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit_scene);
        account_input = findViewById(R.id.EditAccount);
        password_input = findViewById(R.id.EditPassWord);
        update_button = findViewById(R.id.update_button);

        username = getIntent().getStringExtra("username");
        account = getIntent().getStringExtra("account");
        password = getIntent().getStringExtra("password");

        UserName = findViewById(R.id.UserName);
        UserName.setText(username);
        //呼叫method
        //getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(username);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper DB = new DBHelper(AccountEditScene.this);
                account = account_input.getText().toString().trim();
                password = password_input.getText().toString().trim();
//                Log.d("UpdateUserName",username);
//                Log.d("UpdateAccount",password);
//                Log.d("UpdatePassWord",account);
                DB.updateData(username, account, password);
                Log.d("更新資訊","成功");
//                startActivity(new Intent(AccountEditScene.this, AdminManagement.class));
            }
        });
//        delete_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                confirmDialog();
//            }
//        });

    }


    void getAndSetIntentData() {
        if (getIntent().hasExtra("username")) {
            //先取得帳號密碼資料
            //設定帳號密碼資料
            account_input.setText(account);
            password_input.setText(password);
            Log.d("", username + " " + password);
        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + username + " ?");
        builder.setMessage("Are you sure you want to delete " + username + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper DB = new DBHelper(AccountEditScene.this);
                DB.deleteOneRow(username);
                finish();
            }
        });


        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
