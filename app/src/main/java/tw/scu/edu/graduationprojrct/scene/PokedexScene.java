package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import tw.scu.edu.graduationprojrct.R;

public class PokedexScene extends AppCompatActivity {
    public int scrollWidth,scrollHeight;
    public int itemWidth;
    public int  itemHeight;
    public int  itemSpace;
    public Button item[] = new Button[55];
    private RelativeLayout itemPlace;
    final String[] part = {"全部","大腿","腹部","手部","小腿"};
    final String[] difficulty = {"全部","簡單","中等","困難"};
    final int[] partItemNum = {55,11,12,11,20};
    final int[] difficultyItemNum = {55,11,22,22};
    private int CurrentSportNum = 55;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_scene);

        itemPlace = findViewById(R.id.itemPlace);
        Button Select_Button_By_Part = findViewById(R.id.Select_Button_By_Part);
        Button Select_Button_By_Difficulty = findViewById(R.id.Select_Button_By_Difficulty);
        Button Back_From_Pokedex = findViewById(R.id.Back_From_Pokedex);

        checkScrollSize();
        caluateItemSize(scrollWidth,scrollHeight);
        createPokedexFrame();

        Select_Button_By_Difficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAlertDialog_Difficulty();
            }
        });
        Select_Button_By_Part.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               initAlertDialog_Part();
           }
       });
        Back_From_Pokedex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PokedexScene.this,MainScene.class));
            }
        });
        
    }
    private void checkScrollSize(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        this.scrollWidth = (int)(dm.widthPixels*0.68);
        this.scrollHeight = (int)(dm.heightPixels*0.78);
    } // 取得視窗大小
    private void caluateItemSize(double scrollWidth,double scrollHeight){
        double space = 0.025;
        double wh = 0.17;
        this.itemWidth = (int)(scrollWidth*wh);
        this.itemHeight = itemWidth; // 正方形
        this.itemSpace = (int)(scrollWidth*space);//間隔
    } // 計算間隔、物件大小
    private void createPokedexFrame(){
        int j = -1;
        for(int i =0;i<CurrentSportNum;i++){
            item[i]=new Button(this);
            item[i].setId(i);
            item[i].setText(Integer.toString(i));
//            item[i].setImageDrawable(ContextCompat.getDrawable(PokedexScene.this,R.drawable.background));
            RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams((int)itemWidth,(int)itemHeight);
            if(i%5==0) {
                j++;
            }
            btParams.leftMargin = (int)((i%5)*itemWidth+(i%5+1)*itemSpace);//橫坐標
            btParams.topMargin = (int)((j+1)*itemSpace+j*itemHeight); //縱座標定位
            itemPlace.addView(item[i],btParams); //將按鈕放入layout元件
        }
    } // 動態建立表格
    private void initAlertDialog_Part(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(PokedexScene.this);
        dialog.setTitle("選擇條件");
        dialog.setItems(part, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(PokedexScene.this,"你選擇的是"+part[i],Toast.LENGTH_SHORT).show();
                CurrentSportNum = partItemNum[i];
                itemPlace.removeAllViews();
                createPokedexFrame();
            }
        });
        dialog.show();
    } // 依照部位篩選
    private void initAlertDialog_Difficulty(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(PokedexScene.this);
        dialog.setTitle("選擇條件");
        dialog.setItems(difficulty, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(PokedexScene.this,"你選擇的是"+difficulty[i],Toast.LENGTH_SHORT).show();
                CurrentSportNum = difficultyItemNum[i];
                itemPlace.removeAllViews();
                createPokedexFrame();
            }
        });
        dialog.show();
    } // 依照難易度篩選
}