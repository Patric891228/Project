package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;


import tw.scu.edu.graduationprojrct.R;

public class RemindScene extends Activity implements View.OnClickListener {

    TextView time;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_scene);
        time = findViewById(R.id.Time);
        time.setOnClickListener(RemindScene.this);
    }

    @Override
    public void onClick(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                str = hour+":"+minute;//将时间表示为我们想要的字符串格式
                time.setText(str);//文本框设置时间
            }

        };
        new TimePickerDialog(RemindScene.this,3,onTimeSetListener,15,30,true).show();
    }

}
