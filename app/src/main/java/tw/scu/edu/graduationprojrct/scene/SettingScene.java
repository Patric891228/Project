package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.DBHelper;


public class SettingScene extends AppCompatActivity {
    TextView Name_show;
    EditText Birthday_input, Email_input;
    ImageButton Back_Button;
    ImageView Male, Female, Male_Click, Female_Click,Pen1,Pen2,Pen3;
    String Name;
    String Sexual ;
    String Birthday = "1/1";
    String Email = "NAN";
    SharedPreferences shared;
    boolean isFirstSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_scene);

        Name_show = findViewById(R.id.Name);
        Birthday_input = findViewById(R.id.Birthday);
        Email_input = findViewById(R.id.Email);

        Back_Button = findViewById(R.id.Back_From_Setting);


        Male = findViewById(R.id.Male);
        Female = findViewById(R.id.Female);
        Male_Click = findViewById(R.id.Male_Click);
        Female_Click = findViewById(R.id.Female_Click);

        Pen1 = findViewById(R.id.Pen1);
        Pen2 = findViewById(R.id.Pen2);
        Pen3 = findViewById(R.id.Pen3);

        shared = getSharedPreferences("data", MODE_PRIVATE);
        Name = shared.getString("UserName", "Nan");
        isFirstSetting = shared.getBoolean("isFirstSetting", true);

        Init();
        ResetImage();
        SetSexualImage();

        Back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingScene.this,PersonalScene.class));
            }
        });
        Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sexual = "男";
                Log.d("更改性別","男");
                Male.setVisibility(View.GONE);
                Male_Click.setVisibility(View.VISIBLE);
                Female.setVisibility(View.VISIBLE);
                Female_Click.setVisibility(View.GONE);
            }
        });
        Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sexual = "女";
                Log.d("更改性別","女");
                Female.setVisibility(View.GONE);
                Female_Click.setVisibility(View.VISIBLE);
                Male.setVisibility(View.VISIBLE);
                Male_Click.setVisibility(View.GONE);
            }
        });

        Pen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData();
            }
        });
        Pen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData();
            }
        });
        Pen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData();
            }
        });
    }
    void UpdateData(){
        DBHelper DB = new DBHelper(SettingScene.this);
        Birthday = Birthday_input.getText().toString().trim();
        Email = Email_input.getText().toString().trim();
        DB.EditPersonalInfo(Name, Sexual, Birthday, Email);
        Log.d("更新資訊", Name);
        Log.d("更新資訊", Sexual);
        Log.d("更新資訊", Birthday);
        Log.d("更新資訊", Email);
        Log.d("更新成否",DB.EditPersonalInfo(Name, Sexual, Birthday, Email).toString());
    }
    void Init() {
        Male.setVisibility(View.VISIBLE);
        Female.setVisibility(View.VISIBLE);
        Female_Click.setVisibility(View.GONE);
        Male_Click.setVisibility(View.GONE);
        DBHelper DB = new DBHelper(SettingScene.this);
        if (isFirstSetting) {
            Log.d("FirstSetting","YES");
            Name_show.setText(Name);
            Birthday_input.setText(DB.getBirthday(Name));
            Email_input.setText(DB.getEmail(Name));
            SharedPreferences.Editor editor = shared.edit();
            editor.putBoolean("isFirstSetting",false);
            editor.commit();
        } else {
            Log.d("FirstSetting","NO");
            Name_show.setText(Name);
            Birthday_input.setText(DB.getBirthday(Name));
            Email_input.setText(DB.getEmail(Name));
        }
    }
    void SetSexualImage(){
        DBHelper DB = new DBHelper(SettingScene.this);
        Sexual = DB.getSexual(Name);
        Log.d("性別",Sexual);
        if(Sexual.equals("男")){
            Log.d("DEBUG","進入第一個判斷式");
            Male.setVisibility(View.GONE);
            Female.setVisibility(View.VISIBLE);
            Female_Click.setVisibility(View.GONE);
            Male_Click.setVisibility(View.VISIBLE);
        }else{
            Log.d("DEBUG","進入第二個判斷式");
            Male.setVisibility(View.VISIBLE);
            Female.setVisibility(View.GONE);
            Female_Click.setVisibility(View.VISIBLE);
            Male_Click.setVisibility(View.GONE);
        }
    }
    void ResetImage(){
        Male.setVisibility(View.VISIBLE);
        Female.setVisibility(View.VISIBLE);
        Female_Click.setVisibility(View.GONE);
        Male_Click.setVisibility(View.GONE);
    }
}