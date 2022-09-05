package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.SportType;

public class SportScene extends AppCompatActivity {
    String SportType_String;
    String Current_Sport;
    TextView t1;
    int CurrentTime;
    int Current_SportTime;
    final int RestTime = 10;
    SharedPreferences shared;
    Timer timer;
    Boolean isSport = false;
    int order=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        shared = getSharedPreferences("data", MODE_PRIVATE);

        SportType_String = shared.getString("SportType", "Belly");
        t1 = findViewById(R.id.t1);
        timer = new Timer();
        SportType ST = new SportType(SportType_String);
        ST.LoadSportData();
//        初始化
        Current_SportTime = ST.SportContentTime[order];
        CurrentTime = RestTime;

        final TimerTask timertask = new TimerTask() {
            @Override
            public void run(){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CurrentTime--;//時間倒數
                        t1.setText(CurrentTime+"second");//讓TextView元件顯示時間倒數情況
                        //if判斷示裡面放置在時間結束後想要完成的事件
                        if (CurrentTime < 1) {
                            if(isSport) {
                                isSport = false;
                                Log.d("Event","Strat Rest");
                                CurrentTime = RestTime;
                                Log.d("CurrentTimeNeedTo", String.valueOf(CurrentTime));
                            }else{
                                isSport = true;
                                order++; //讓時間執行緒保持輪迴
                                Log.d("Event", "Start Sport");
                                CurrentTime = ST.SportContentTime[order];
                                Log.d("CurrentTimeNeedTo", String.valueOf(Current_SportTime));
                            }
                        }
                    }
                });
            }
        };
        timer.schedule(timertask, 1000, 1000);
    }
}