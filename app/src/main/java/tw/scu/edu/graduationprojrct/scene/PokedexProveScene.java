package tw.scu.edu.graduationprojrct.scene;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.PokedexObject;

public class PokedexProveScene extends AppCompatActivity implements View.OnClickListener {
    ImageView Leave_Button, Left_Button, Right_Button;
    ImageView Select_Button,CenterImage;
    TextView ChineseName,EnglishName,Prove,Introduce;
    ImageView Image_array[] = new ImageView[5];
    int Image_array_int[] = new int[5];
    String ProveType[] = {"Slouch", "Oxleg", "Pelvic", "Saddlebag", "Belly"};
    int SelectProveImage[] = {R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5};
    PokedexObject PO;
    int CurrentProve, CurrentBarNum, CurrentNum, initImageNum = 0; // 預設為Slouch // 左邊尚餘頁數
    int ImageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_prove_scene);

        Leave_Button = findViewById(R.id.Leave_Button);
        Left_Button = findViewById(R.id.Left_Button);
        Right_Button = findViewById(R.id.Right_Button);

        Select_Button = findViewById(R.id.Select_Button);
        CenterImage = findViewById(R.id.Center_Iamge);

        Image_array[0] = findViewById(R.id.I1);
        Image_array[1] = findViewById(R.id.I2);
        Image_array[2] = findViewById(R.id.I3);
        Image_array[3] = findViewById(R.id.I4);
        Image_array[4] = findViewById(R.id.I5);

        PO = new PokedexObject("Slouch");
        Image_array[0].setOnClickListener(this);
        Image_array[1].setOnClickListener(this);
        Image_array[2].setOnClickListener(this);
        Image_array[3].setOnClickListener(this);
        Image_array[4].setOnClickListener(this);

        ChineseName = findViewById(R.id.ChineseName2);
        EnglishName = findViewById(R.id.EnglishName2);
        Prove = findViewById(R.id.Prove2);
        Introduce = findViewById(R.id.Introduce2);

        init();

        Select_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentProve = (CurrentProve + 1) % 5;
                Select_Button.setImageResource(SelectProveImage[CurrentProve]);
                SetPart(ProveType[CurrentProve]);
                ImageNumber = PO.PokedexContent.length;
                CurrentBarNum = ImageNumber%5==0?ImageNumber/5-1:ImageNumber/5;
                CurrentNum = 0;
                initImageNum = 0;
                SetIndex();
                CenterImage.setImageResource(PO.SportImgID[PO.PokedexContent[initImageNum]-1]);
                ChineseName.setText("中文名稱:"+PO.ChineseName[PO.PokedexContent[initImageNum]-1]);
                EnglishName.setText("英文名稱:"+PO.EnglishName[PO.PokedexContent[initImageNum]-1]);
                Prove.setText("改善部位:"+PO.ImprovePart[PO.PokedexContent[initImageNum]-1]);
                Introduce.setText("改善部位:"+PO.Introduce[PO.PokedexContent[initImageNum]-1]);
                Log.d("現在改善部位", ProveType[CurrentProve]);
                Log.d("個數", String.valueOf(ImageNumber));
            }
        });
        Leave_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PokedexProveScene.this, PokedexSelectedScene.class));
            }
        });
        Left_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CurrentNum > 0) {
                    CurrentNum -=1;
                    initImageNum -= 5;
                    SetIndex();
                }
                Log.d("應有頁數",String.valueOf(CurrentBarNum+1));
                Log.d("當前頁數(含0頁)", String.valueOf(CurrentNum));
            }
        });
        Right_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CurrentNum < CurrentBarNum) {
                    initImageNum += 5;
                    CurrentNum +=1;
                    SetIndex();
                }
                Log.d("應有頁數",String.valueOf(CurrentBarNum+1));
                Log.d("當前頁數(含0頁)", String.valueOf(CurrentNum));
            }
        });
    }
    public void SetPart(String prove) {
        PO.PokedexAll = prove;
        PO.CreativePokedex();
        Log.d("狀態","已更改類型");
    }
    public void SetIndex() {
        Image_array_int[0] = initImageNum;
        Image_array_int[1] = initImageNum + 1;
        Image_array_int[2] = initImageNum + 2;
        Image_array_int[3] = initImageNum + 3;
        Image_array_int[4] = initImageNum + 4;
        Log.d("行為","SetIndex()");
        for (int i = 0; i < 5; i++) {
            if (Image_array_int[i] >= ImageNumber) {
                Image_array_int[i] = -1;
                Image_array[i].setVisibility(View.GONE);
            }else{
                Image_array[i].setVisibility(View.VISIBLE);
                int DataNum = PO.PokedexContent[Image_array_int[i]];
                if(DataNum>4){ //沒有ID5 6
                    DataNum-=2;
                }
                Image_array[i].setImageResource(PO.SportImgID[DataNum-1]);
                Log.d("圖片載入","已載入");
            }
//
        }
    }
    private void CatchData(int i) {
        Log.d("現在點擊", String.valueOf(initImageNum + i));
        Log.d("編號", String.valueOf(PO.PokedexContent[initImageNum+i]));
        if(PO.PokedexContent[initImageNum+i]>4){
            PO.PokedexContent[initImageNum+i]-=2;
        }
        Log.d("更改後編號", String.valueOf(PO.PokedexContent[initImageNum+i]));
        CenterImage.setImageResource(PO.SportImgID[PO.PokedexContent[initImageNum+i]-1]);
        ChineseName.setText("中文名稱:"+PO.ChineseName[PO.PokedexContent[initImageNum+i]-1]);
        EnglishName.setText("英文名稱:"+PO.EnglishName[PO.PokedexContent[initImageNum+i]-1]);
        Prove.setText("改善部位:"+PO.ImprovePart[PO.PokedexContent[initImageNum+i]-1]);
        Introduce.setText("改善部位:"+PO.Introduce[PO.PokedexContent[initImageNum+i]-1]);
//        int DataNum = PO.PokedexContent[i-1];
//        Log.d("DataNum", String.valueOf(DataNum));
//        if(DataNum>4){ //沒有ID5 6
//            DataNum-=2;
//        }
//        String ChineseName = PO.ChineseName[DataNum-1];
//        String EnglishName = PO.EnglishName[DataNum-1];
//        String ImprovePart = PO.ImprovePart[DataNum-1];
//        String Introduce = PO.Introduce[DataNum-1];
//        Log.d("ImageData",ChineseName+" "+EnglishName+" "+ImprovePart);
//        Log.d("ImageIntrduce",Introduce);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.I1:
                CatchData(0);
                break;
            case R.id.I2:
                CatchData(1);
                break;
            case R.id.I3:
                CatchData(2);
                break;
            case R.id.I4:
                CatchData(3);
                break;
            case R.id.I5:
                CatchData(4);
        }
    }
    public void init(){
        CurrentProve = (CurrentProve) % 5;
        Select_Button.setImageResource(SelectProveImage[CurrentProve]);
        SetPart(ProveType[CurrentProve]);
        ImageNumber = PO.PokedexContent.length;
        CurrentBarNum = ImageNumber%5==0?ImageNumber/5-1:ImageNumber/5;
        CurrentNum = 0;
        initImageNum = 0;
        SetIndex();
        CenterImage.setImageResource(PO.SportImgID[PO.PokedexContent[initImageNum]-1]);
        ChineseName.setText("中文名稱:"+PO.ChineseName[PO.PokedexContent[initImageNum]-1]);
        EnglishName.setText("英文名稱:"+PO.EnglishName[PO.PokedexContent[initImageNum]-1]);
        Prove.setText("改善部位:"+PO.ImprovePart[PO.PokedexContent[initImageNum]-1]);
        Introduce.setText("改善部位:"+PO.Introduce[PO.PokedexContent[initImageNum]-1]);
        Log.d("現在改善部位", ProveType[CurrentProve]);

    }
}