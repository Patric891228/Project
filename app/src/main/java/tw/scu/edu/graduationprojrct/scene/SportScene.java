package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.SportType;

public class SportScene extends AppCompatActivity {
    String SportType_String;
    String Current_Sport;
    TextView Time_Word;
    int CurrentTime,Current_SportTime;
    ImageView Time_BG,White_BG;
    ImageView Rest_Photo_BG,Rest_Next_Word,Rest_Sign,Rest_Word_Sign,Next_Sport_Word;
    ImageView Now_Photo_BG,Now_Word,Now_Sport_Word;
    final int RestTime = 10;
    SharedPreferences shared;
    Timer timer;
    Boolean isSport = false;
    int Order= -1;
    int ImgOrder;
    SportType ST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        shared = getSharedPreferences("data", MODE_PRIVATE);
        SportType_String = shared.getString("SportType", "Belly");
//        綁定物件
        Time_BG = findViewById(R.id.Time_BG);
        Time_Word = findViewById(R.id.Time_Word);
        Rest_Photo_BG = findViewById(R.id.Rest_Photo_BG);
        Rest_Next_Word = findViewById(R.id.Rest_Next_Word);
        Rest_Sign = findViewById(R.id.Rest_Sign);
        Rest_Word_Sign = findViewById(R.id.Rest_Word_Sign);
        Next_Sport_Word = findViewById(R.id.Next_Sport_Word);

        Now_Photo_BG = findViewById(R.id.Now_Photo_BG);
        Now_Word =findViewById(R.id.Now_Word);
        Now_Sport_Word = findViewById(R.id.Now_Sport_Word);
        White_BG = findViewById(R.id.white_BG);
//        建立計時器
        timer = new Timer();
//        建立物件
        ST = new SportType(SportType_String);
        ST.LoadSportData();
//        初始化
        Current_SportTime = ST.SportContentTime[0];
        CurrentTime = RestTime;
//
        InitalUI();

        final TimerTask timertask = new TimerTask() {
            @Override
            public void run(){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CurrentTime--;//時間倒數
                        Time_Word.setText(CurrentTime+"");
                        //if判斷示裡面放置在時間結束後想要完成的事件
                        if (CurrentTime < 1) {
                            if(Order<ST.SportContentTime.length-1){
                                if(isSport) {//進入休息狀態
                                    isSport = false;
                                    Next_Sport_Word.setImageDrawable(null);
                                    Next_Sport_Word.setImageResource(ST.SportImgID[ImgOrder]);
                                    EnterRestState();
                                    CurrentTime = RestTime;
                                }else {
                                    isSport = true;
                                    Now_Sport_Word.setImageDrawable(null);
                                    Now_Sport_Word.setImageResource(ST.SportImgID[ImgOrder]);
                                    EnterSportState();
                                    Order++; //讓時間執行緒保持輪迴
                                    ImgOrder++;
                                    CurrentTime = ST.SportContentTime[Order];
                                }
                            }else{
                                timer.cancel();
                                startActivity(new Intent(SportScene.this,SportResultScene.class));
                            }
                        }
                    }
                });
            }
        };
        timer.schedule(timertask, 1000, 1000);
    }
    private void InitalUI(){//一開始為休息狀態
        Now_Sport_Word.setImageDrawable(null);
        Next_Sport_Word.setImageDrawable(null);
        Next_Sport_Word.setImageResource(ST.SportImgID[0]);
        White_BG.setVisibility(View.VISIBLE);
        Time_BG.setVisibility(View.VISIBLE);
        Time_Word.setVisibility(View.VISIBLE);
        Rest_Word_Sign.setVisibility(View.VISIBLE);
        Rest_Sign.setVisibility(View.VISIBLE);
        Rest_Next_Word.setVisibility(View.VISIBLE);
        Rest_Photo_BG.setVisibility(View.VISIBLE);
        Next_Sport_Word.setVisibility(View.VISIBLE);
        Now_Sport_Word.setVisibility(View.GONE);
        Now_Photo_BG.setVisibility(View.GONE);
        Now_Word.setVisibility(View.GONE);
    }
    private void EnterRestState(){
        White_BG.setVisibility(View.VISIBLE);
        Time_BG.setVisibility(View.VISIBLE);
        Time_Word.setVisibility(View.VISIBLE);
        Rest_Word_Sign.setVisibility(View.VISIBLE);
        Rest_Sign.setVisibility(View.VISIBLE);
        Rest_Next_Word.setVisibility(View.VISIBLE);
        Rest_Photo_BG.setVisibility(View.VISIBLE);
        Next_Sport_Word.setVisibility(View.VISIBLE);
        Now_Sport_Word.setVisibility(View.GONE);
        Now_Photo_BG.setVisibility(View.GONE);
        Now_Word.setVisibility(View.GONE);
    }
    private void EnterSportState(){
        White_BG.setVisibility(View.GONE);
        Time_BG.setVisibility(View.VISIBLE);
        Time_Word.setVisibility(View.VISIBLE);
        Rest_Word_Sign.setVisibility(View.GONE);
        Rest_Sign.setVisibility(View.GONE);
        Rest_Next_Word.setVisibility(View.GONE);
        Rest_Photo_BG.setVisibility(View.GONE);
        Next_Sport_Word.setVisibility(View.GONE);
        Now_Sport_Word.setVisibility(View.VISIBLE);
        Now_Photo_BG.setVisibility(View.VISIBLE);
        Now_Word.setVisibility(View.VISIBLE);

    }
}