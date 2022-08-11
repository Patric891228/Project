package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import tw.scu.edu.graduationprojrct.GlobalVariable;
import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.DBHelper;

public class RemindScene extends Activity implements View.OnClickListener {
    GlobalVariable gv ;
    TextView time;
    String str;
    DBHelper DB;
    Boolean isEnter = false;
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_scene);
        DB = new DBHelper(this);

        time = findViewById(R.id.Time);
        shared = getSharedPreferences("data",MODE_PRIVATE);
        isEnter = shared.getBoolean("isEnter",false);

        if(isEnter){
            time.setText(DB.getRemindTime(shared.getString("UserName","Nan")));
        }else{
            time.setText("08:00");
        }
        time.setOnClickListener(RemindScene.this);


    }

    @Override
    public void onClick(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                str = hour+":"+minute;//将时间表示为我们想要的字符串格式
                time.setText(str);//文本框设置时间
                isEnter = DB.insertTime(gv.getName(),str);
                SharedPreferences.Editor editor = shared.edit();
                editor.putBoolean("isEnter",true);
                editor.commit();
                if(isEnter){
                    Log.d("Database","Time insert Success");
                    Toast.makeText(RemindScene.this, "Time insert Success", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("Database","Time insert Invalid");
                    Toast.makeText(RemindScene.this, "Time insert Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        };
            new TimePickerDialog(RemindScene.this,3,onTimeSetListener,24,60,true).show();
    }


}
