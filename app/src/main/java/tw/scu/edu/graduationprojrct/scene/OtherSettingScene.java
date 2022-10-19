package tw.scu.edu.graduationprojrct.scene;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.DBHelper;

public class OtherSettingScene extends AppCompatActivity implements View.OnClickListener {
    ImageView Back_Button_From_Other_Setting;
    String UserName ;
    TextView time,bgm;
    String str;
    int BGMNumber;
    DBHelper DB;
    Boolean isEnter = false;
    Boolean isEdit = false;
    SharedPreferences shared;
    MediaPlayer mysong;

    String BGMName[] = {"音樂0","音樂1","音樂2","音樂3","音樂4"};
    int BGMList[] = {R.raw.m0,R.raw.m1,R.raw.m2,R.raw.m3,R.raw.m4};
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_setting_scene);
        DB = new DBHelper(this);
        Back_Button_From_Other_Setting = findViewById(R.id.Back_Button_From_Other_Setting);

        time = findViewById(R.id.Time_Text);
        bgm = findViewById(R.id.BGM_Text);

        shared = getSharedPreferences("data",MODE_PRIVATE);
        isEnter = shared.getBoolean("isEnter",false);
        UserName = shared.getString("UserName","admin");
        isEdit = shared.getBoolean("isEdit",false);

        mysong = MediaPlayer.create(OtherSettingScene.this, BGMList[BGMNumber]);
        if(isEnter){
            time.setText(DB.getRemindTime(shared.getString("UserName","Nan")));
        }else{
            DB.insertTime(UserName);
            time.setText("08:00");
            Log.d("初次灌入時間",DB.insertTime(UserName).toString());
        }
        bgm.setText(BGMName[BGMNumber]);
        time.setOnClickListener(OtherSettingScene.this);
        bgm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BGMNumber=(BGMNumber+1)%5;
                SharedPreferences.Editor editor = shared.edit();
                editor.putInt("BGMNumber",BGMNumber);
                editor.putBoolean("isEdit",true);
                editor.commit();
                if(i==0){
                    mysong = MediaPlayer.create(OtherSettingScene.this, BGMList[BGMNumber]);
                    mysong.start();
                    mysong.setLooping(true);
                    i++;
                }else{
                    mysong.pause();
                    mysong.release();
                    mysong = null;
                    mysong = MediaPlayer.create(OtherSettingScene.this, BGMList[BGMNumber]);
                    mysong.start();
                    mysong.setLooping(true);
                }

                bgm.setText(BGMName[BGMNumber]);
            }
        });
        Back_Button_From_Other_Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mysong.release();
                mysong = null;
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