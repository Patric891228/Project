package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.ActionBar;
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
import android.widget.Toast;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.DBHelper;


public class SettingScene extends AppCompatActivity {
    TextView Name_show;
    EditText Birthday_input, Email_input;
    Button Update_Button;
    ImageButton Back_Button;
    ImageView Male, Female, Male_Click, Female_Click;
    String Name;
    String Sexual = "男";
    String Birthday = "1/1";
    String Email = "NAN";
    SharedPreferences shared;
    boolean isFirstSetting;
    int SexualByInt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_scene);

        Name_show = findViewById(R.id.Name);
        Birthday_input = findViewById(R.id.Birthday);
        Email_input = findViewById(R.id.Email);

        Back_Button = findViewById(R.id.Back_From_Setting);

        Update_Button = findViewById(R.id.Update_BTN);

        Male = findViewById(R.id.Male);
        Female = findViewById(R.id.Female);
        Male_Click = findViewById(R.id.Male_Click);
        Female_Click = findViewById(R.id.Female_Click);

        shared = getSharedPreferences("data", MODE_PRIVATE);
        Name = shared.getString("UserName", "Nan");
        Log.d("inSettingScene",Name);
        isFirstSetting = shared.getBoolean("isFirstSetting", true);

        Init();

        Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SexualByInt = 1;
                Male.setVisibility(View.GONE);
                Female_Click.setVisibility(View.GONE);
                Male_Click.setVisibility(View.VISIBLE);
            }
        });
        Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SexualByInt = 0;
                Female.setVisibility(View.GONE);
                Female_Click.setVisibility(View.VISIBLE);
                Male_Click.setVisibility(View.GONE);
            }
        });

        Update_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
    }
    void Init() {
        Male.setVisibility(View.VISIBLE);
        Female.setVisibility(View.VISIBLE);
        Female_Click.setVisibility(View.GONE);
        Male_Click.setVisibility(View.GONE);
        Log.d("SettingSceneName",Name);
        DBHelper DB = new DBHelper(SettingScene.this);
        if (isFirstSetting) {
            DB.insertPersonalInfo(Name, Sexual, Birthday, Email); //寫進預設資料
            Name_show.setText(Name);
            Birthday_input.setText(DB.getBirthday(Name));
            Email_input.setText(DB.getEmail(Name));
            SetSexualImage();
            SharedPreferences.Editor editor = shared.edit();
            editor.putBoolean("isFirstSetting",false);
            editor.commit();
        } else {
            Name_show.setText(Name);
            Birthday_input.setText(DB.getBirthday(Name));
            Email_input.setText(DB.getEmail(Name));
            SetSexualImage();


        }
    }

    void SetSexualImage() {
        if (Sexual == "男") {
            Male.setVisibility(View.GONE);
            Female.setVisibility(View.GONE);
            Male_Click.setVisibility(View.VISIBLE);
            Female_Click.setVisibility(View.GONE);
        } else {
            Male.setVisibility(View.GONE);
            Female.setVisibility(View.GONE);
            Male_Click.setVisibility(View.GONE);
            Female_Click.setVisibility(View.VISIBLE);

        }
    }
}