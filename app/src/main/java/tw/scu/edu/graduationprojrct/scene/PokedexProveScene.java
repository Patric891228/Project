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
    ImageView Select_Button, CenterImage;
    TextView ChineseName, EnglishName, Prove, Introduce;
    ImageView Image_array[] = new ImageView[5];
    int Image_array_int[] = new int[5];
    String ProveType[] = {"Slouch", "Oxleg", "Pelvic", "Saddlebag", "Belly"};
    int SelectProveImage[] = {R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5};
    PokedexObject PO;
    int CurrentProve, CurrentBarNum, CurrentNum, initImageNum = 0; // 預設為Slouch // 左邊尚餘頁數
    int ImageNumber;
    int CurrentButton = -1;
    int DataNum;

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
                CurrentBarNum = ImageNumber % 5 == 0 ? ImageNumber / 5 - 1 : ImageNumber / 5;
                CurrentNum = 0;
                initImageNum = 0;
                SetIndex();
                int NewImageNum = PO.PokedexContent[initImageNum];
                if (NewImageNum > 4) {
                    NewImageNum -= 2;
                }
                CenterImage.setImageResource(PO.SportImgID[ NewImageNum- 1]);
                ChineseName.setText("中文名稱:" + PO.ChineseName[ NewImageNum- 1]);
                EnglishName.setText("英文名稱:" + PO.EnglishName[ NewImageNum- 1]);
                Prove.setText("改善部位:" + PO.ImprovePart[ NewImageNum- 1]);
                Introduce.setText("改善部位:" + PO.Introduce[ NewImageNum- 1]);
                CurrentButton = -1;
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
                    CurrentNum -= 1;
                    initImageNum -= 5;
                    SetIndex();
                }


                CurrentButton = -1;
                Log.d("上個點擊", String.valueOf(CurrentButton));
                Log.d("應有頁數", String.valueOf(CurrentBarNum + 1));
                Log.d("當前頁數(含0頁)", String.valueOf(CurrentNum));
            }
        });
        Right_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CurrentNum < CurrentBarNum) {
                    initImageNum += 5;
                    CurrentNum += 1;
                    SetIndex();
                }
                CurrentButton = -1;
                Log.d("上個點擊", String.valueOf(CurrentButton));
                Log.d("應有頁數", String.valueOf(CurrentBarNum + 1));
                Log.d("當前頁數(含0頁)", String.valueOf(CurrentNum));
            }
        });
    }

    public void SetPart(String prove) {
        PO.PokedexAll = prove;
        PO.CreativePokedex();
        Log.d("狀態", "已更改類型");
    }

    public void SetIndex() {
        Log.d("初始號碼", String.valueOf(initImageNum));
        Image_array_int[0] = initImageNum;
        Image_array_int[1] = initImageNum + 1;
        Image_array_int[2] = initImageNum + 2;
        Image_array_int[3] = initImageNum + 3;
        Image_array_int[4] = initImageNum + 4;
        for (int i = 0; i < 5; i++) {
            Log.d("本頁選項順序", String.valueOf(Image_array_int[i]));
        }
        for (int i = 0; i < 5; i++) {
            if (Image_array_int[i] >= ImageNumber) {
                Image_array_int[i] = -1;
                Image_array[i].setVisibility(View.GONE);
            } else {
                Image_array[i].setVisibility(View.VISIBLE);
                //TODO
                DataNum = PO.PokedexContent[Image_array_int[i]];
                Log.d("本頁動作編號", String.valueOf(DataNum));
                if (DataNum > 4) { //沒有ID5 6
                    DataNum -= 2;
                }
                Image_array[i].setImageResource(PO.SportImgID[DataNum - 1]);
                Log.d("會顯示的號碼", String.valueOf((DataNum - 1)));
            }
//
        }
    }

    private void CatchData(int i) {
        Log.d("現在點擊", String.valueOf(initImageNum + i));
        Log.d("編號", String.valueOf(PO.PokedexContent[initImageNum + i]));
        int NewImageNum = PO.PokedexContent[initImageNum + i];
        if (NewImageNum > 4) {
            NewImageNum -= 2;
        }
        Log.d("更改後編號", String.valueOf(NewImageNum));
        CenterImage.setImageResource(PO.SportImgID[NewImageNum - 1]);
        ChineseName.setText("中文名稱:" + PO.ChineseName[NewImageNum - 1]);
        EnglishName.setText("英文名稱:" + PO.EnglishName[NewImageNum - 1]);
        Prove.setText("改善部位:" + PO.ImprovePart[NewImageNum - 1]);
        Introduce.setText("改善部位:" + PO.Introduce[NewImageNum - 1]);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.I1:
                if (CurrentButton != 0) {
                    CatchData(0);
                    CurrentButton = 0;
                }
                Log.d("上個點擊", String.valueOf(CurrentButton));
                break;
            case R.id.I2:
                if (CurrentButton != 1) {
                    CatchData(1);
                    CurrentButton = 1;
                }
                Log.d("上個點擊", String.valueOf(CurrentButton));
                break;
            case R.id.I3:
                if (CurrentButton != 2) {
                    CatchData(2);
                    CurrentButton = 2;
                }
                Log.d("上個點擊", String.valueOf(CurrentButton));
                break;
            case R.id.I4:
                if (CurrentButton != 3) {
                    CatchData(3);
                    CurrentButton = 3;
                }
                Log.d("上個點擊", String.valueOf(CurrentButton));
                break;
            case R.id.I5:
                if (CurrentButton != 4) {
                    CatchData(4);
                    CurrentButton = 4;
                }
                Log.d("上個點擊", String.valueOf(CurrentButton));
        }
    }

    public void init() {
        CurrentProve = (CurrentProve) % 5;
        Select_Button.setImageResource(SelectProveImage[CurrentProve]);
        SetPart(ProveType[CurrentProve]);
        ImageNumber = PO.PokedexContent.length;
        CurrentBarNum = ImageNumber % 5 == 0 ? ImageNumber / 5 - 1 : ImageNumber / 5;
        CurrentNum = 0;
        initImageNum = 0;
        SetIndex();
        CenterImage.setImageResource(PO.SportImgID[PO.PokedexContent[initImageNum] - 1]);
        ChineseName.setText("中文名稱:" + PO.ChineseName[PO.PokedexContent[initImageNum] - 1]);
        EnglishName.setText("英文名稱:" + PO.EnglishName[PO.PokedexContent[initImageNum] - 1]);
        Prove.setText("改善部位:" + PO.ImprovePart[PO.PokedexContent[initImageNum] - 1]);
        Introduce.setText("改善部位:" + PO.Introduce[PO.PokedexContent[initImageNum] - 1]);
        Log.d("現在改善部位", ProveType[CurrentProve]);

    }
}