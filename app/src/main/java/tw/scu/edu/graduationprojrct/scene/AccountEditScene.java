package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.DBHelper;

public class AccountEditScene extends AppCompatActivity {
    EditText account_input, password_input;
    ImageView update_button1,update_button2,Back_Button;
    TextView UserName;
    String username, account, password;
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit_scene);
        account_input = findViewById(R.id.EditAccount);
        password_input = findViewById(R.id.EditPassWord);
        update_button1 = findViewById(R.id.Pen1e);
        update_button2 = findViewById(R.id.Pen2e);
        Back_Button = findViewById(R.id.Back_From_Editting);
        shared = getSharedPreferences("data", MODE_PRIVATE);
        username = shared.getString("UserName", "Nan");
        DBHelper DB = new DBHelper(AccountEditScene.this);
        account = DB.getAccount(username);
        password = DB.getPassWord(username);



        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(username);
        }
        update_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper DB = new DBHelper(AccountEditScene.this);
                account = account_input.getText().toString().trim();
                password = password_input.getText().toString().trim();
                DB.updateData(username, account, password);
                Log.d("????????????","??????");
                startActivity(new Intent(AccountEditScene.this,PersonalScene.class));
            }
        });
        update_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper DB = new DBHelper(AccountEditScene.this);
                account = account_input.getText().toString().trim();
                password = password_input.getText().toString().trim();
                DB.updateData(username, account, password);
                Log.d("????????????","??????");
                startActivity(new Intent(AccountEditScene.this,PersonalScene.class));
            }
        });
        Back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountEditScene.this,PersonalScene.class));
            }
        });
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
