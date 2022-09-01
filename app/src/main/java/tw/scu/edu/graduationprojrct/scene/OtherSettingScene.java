package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.DBHelper;

public class OtherSettingScene extends AppCompatActivity implements View.OnClickListener {
    ImageView Back_Button_From_Other_Setting;
    String UserName ;
    TextView time;
    String str;
    DBHelper DB;
    Boolean isEnter = false;
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_setting_scene);
        DB = new DBHelper(this);
        Back_Button_From_Other_Setting = findViewById(R.id.Back_Button_From_Other_Setting);
        time = findViewById(R.id.Time_Text);
        shared = getSharedPreferences("data",MODE_PRIVATE);
        isEnter = shared.getBoolean("isEnter",false);
        UserName = shared.getString("UserName","admin");

        if(isEnter){
            time.setText(DB.getRemindTime(shared.getString("UserName","Nan")));
        }else{
            DB.insertTime(UserName);
            time.setText("08:00");
            Log.d("初次灌入時間",DB.insertTime(UserName).toString());
        }
        time.setOnClickListener(OtherSettingScene.this);
        Back_Button_From_Other_Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherSettingScene.this,MainScene.class));
            }
        });
    }
    @Override
    public void onClick(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                str = hour+":"+minute;//将时间表示为我们想要的字符串格式
                time.setText(str);//文本框设置时间
                isEnter = DB.EditTime(UserName,str);
                SharedPreferences.Editor editor = shared.edit();
                editor.putBoolean("isEnter",true);
                editor.commit();
                if(isEnter){
                    Log.d("Database","Time insert Success");
                    Toast.makeText(OtherSettingScene.this, "Time insert Success", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("Database","Time insert Invalid");
                    Toast.makeText(OtherSettingScene.this, "Time insert Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        };
        new TimePickerDialog(OtherSettingScene.this,3,onTimeSetListener,24,60,true).show();
    }

}