package tw.scu.edu.graduationprojrct.scene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.PokedexObject;

public class PokedexPartScene extends AppCompatActivity implements View.OnClickListener {
    ImageView Leave_BTN,SelectButton,SelectButton_Clicked;
    ImageView B1,B2,B3,B4,B5;
    ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9,
              image10,image11,image12,image13,image14,image15,image16,image17,image18;
    public ImageView ImageArray[] = new ImageView[18];
    PokedexObject PO;
    int ImageNumber;
    SharedPreferences SP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_part_scene);

        Leave_BTN = findViewById(R.id.Leave_Button);
        SelectButton = findViewById(R.id.A);
        SelectButton_Clicked = findViewById(R.id.B);
        B1 = findViewById(R.id.B1);
        B2 = findViewById(R.id.B2);
        B3 = findViewById(R.id.B3);
        B4 = findViewById(R.id.B4);
        B5 = findViewById(R.id.B5);

        ImageObjectSet();
        init();


        Leave_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PokedexPartScene.this,PokedexSelectedScene.class));
            }
        });
        SelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectButton.setVisibility(View.GONE);
                SelectButton_Clicked.setVisibility(View.VISIBLE);
                B1.setVisibility(View.VISIBLE);
                B2.setVisibility(View.VISIBLE);
                B3.setVisibility(View.VISIBLE);
                B4.setVisibility(View.VISIBLE);
                B5.setVisibility(View.VISIBLE);
            }
        });
        SelectButton_Clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectButton.setVisibility(View.VISIBLE);
                SelectButton_Clicked.setVisibility(View.GONE);
                B1.setVisibility(View.GONE);
                B2.setVisibility(View.GONE);
                B3.setVisibility(View.GONE);
                B4.setVisibility(View.GONE);
                B5.setVisibility(View.GONE);
            }
        });
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetPart("Hand");
                ImageVisibilitySet();
                B5.setImageResource(R.drawable.part_button_clicked_arm);
                Log.d("SelectPokedexPart","Hand");
                Log.d("Number", String.valueOf(ImageNumber));
                reset(0);
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetPart("Back");
                ImageVisibilitySet();
                B5.setImageResource(R.drawable.part_button_clicked_shoulder);
                Log.d("SelectPokedexPart","Back");
                Log.d("Number", String.valueOf(ImageNumber));
                reset(1);
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetPart("Abdomen");
                ImageVisibilitySet();
                B3.setImageResource(R.drawable.part_button_clicked_belly);
                Log.d("SelectPokedexPart","Abdomen");
                Log.d("Number", String.valueOf(ImageNumber));
                reset(2);
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetPart("Buttocks");
                ImageVisibilitySet();
                B4.setImageResource(R.drawable.part_button_clicked_bottom);
                Log.d("SelectPokedexPart","Buttocks");
                Log.d("Number", String.valueOf(ImageNumber));
                reset(3);
            }
        });
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetPart("Leg");
                ImageVisibilitySet();
                B5.setImageResource(R.drawable.part_button_clicked_leg);
                Log.d("SelectPokedexPart","Leg");
                Log.d("Number", String.valueOf(ImageNumber));
                reset(4);
            }
        });
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);
        image7.setOnClickListener(this);
        image8.setOnClickListener(this);
        image9.setOnClickListener(this);
        image10.setOnClickListener(this);
        image11.setOnClickListener(this);
        image12.setOnClickListener(this);
        image13.setOnClickListener(this);
        image14.setOnClickListener(this);
        image15.setOnClickListener(this);
        image16.setOnClickListener(this);
        image17.setOnClickListener(this);
        image18.setOnClickListener(this);

    }
    private void init(){
        PO = new PokedexObject("Hand");//預設為手
        SetPart("Hand");
        ImageVisibilitySet();
        SelectButton.setVisibility(View.VISIBLE);
        SelectButton_Clicked.setVisibility(View.GONE);
        B1.setVisibility(View.GONE);
        B2.setVisibility(View.GONE);
        B3.setVisibility(View.GONE);
        B4.setVisibility(View.GONE);
        B5.setVisibility(View.GONE);
    }
    private void reset(int i ){
        ImageView IV[] = {B1,B2,B3,B4,B5};
        int ImageID [] = {R.drawable.part_button_clicked_arm,R.drawable.part_button_clicked_shoulder,R.drawable.part_button_clicked_belly,
                R.drawable.part_button_clicked_bottom,R.drawable.part_button_clicked_leg};
        SelectButton.setVisibility(View.VISIBLE);
        SelectButton_Clicked.setVisibility(View.GONE);
        B1.setImageResource(R.drawable.part_button_small_arm);
        B1.setVisibility(View.GONE);
        B2.setImageResource(R.drawable.part_button_small_shoulder);
        B2.setVisibility(View.GONE);
        B3.setImageResource(R.drawable.part_button_small_belly);
        B3.setVisibility(View.GONE);
        B4.setImageResource(R.drawable.part_button_small_bottom);
        B4.setVisibility(View.GONE);
        B5.setImageResource(R.drawable.part_button_small_leg);
        B5.setVisibility(View.GONE);
        IV[i].setImageResource(ImageID[i]);

    }
    public void SetPart(String part){
        PO.PokedexAll = part;
        PO.CreativePokedex();
        this.ImageNumber = PO.PokedexContent.length;
    }
    private void ImageObjectSet(){
        image1 = findViewById(R.id.Image1);
        image2 = findViewById(R.id.Image2);
        image3 = findViewById(R.id.Image3);
        image4 = findViewById(R.id.Image4);
        image5 = findViewById(R.id.Image5);
        image6 = findViewById(R.id.Image6);
        image7 = findViewById(R.id.Image7);
        image8 = findViewById(R.id.Image8);
        image9 = findViewById(R.id.Image9);
        image10 = findViewById(R.id.Image10);
        image11 = findViewById(R.id.Image11);
        image12 = findViewById(R.id.Image12);
        image13= findViewById(R.id.Image13);
        image14 = findViewById(R.id.Image14);
        image15 = findViewById(R.id.Image15);
        image16 = findViewById(R.id.Image16);
        image17 = findViewById(R.id.Image17);
        image18 = findViewById(R.id.Image18);
        ImageArray[0] = image1;
        ImageArray[1] = image2;
        ImageArray[2] = image3;
        ImageArray[3] = image4;
        ImageArray[4] = image5;
        ImageArray[5] = image6;
        ImageArray[6] = image7;
        ImageArray[7] = image8;
        ImageArray[8] = image9;
        ImageArray[9] = image10;
        ImageArray[10] = image11;
        ImageArray[11] = image12;
        ImageArray[12] = image13;
        ImageArray[13] = image14;
        ImageArray[14] = image15;
        ImageArray[15] = image16;
        ImageArray[16] = image17;
        ImageArray[17] = image18;
    }
    public void ImageVisibilitySet(){
        for(int i =0;i<ImageNumber;i++){
            ImageArray[i].setVisibility(View.VISIBLE);
            int DataNum = PO.PokedexContent[i];
            if(DataNum>4){ //沒有ID5 6
                DataNum-=2;
            }
            Log.d("DefaultImageID", String.valueOf(PO.SportImgID[DataNum-1]));
            ImageArray[i].setImageResource(PO.SportImgID[DataNum-1]);
        }
        for(int i=ImageNumber;i<18;i++){
            ImageArray[i].setVisibility(View.GONE);
        }
    }
    private void CatchData(int i ){
        SP = getSharedPreferences("data",MODE_PRIVATE);
        int DataNum = PO.PokedexContent[i-1];
        Log.d("DataNum", String.valueOf(DataNum));
        if(DataNum>4){ //沒有ID5 6
            DataNum-=2;
        }
        String ChineseName = PO.ChineseName[DataNum-1];
        String EnglishName = PO.EnglishName[DataNum-1];
        String ImprovePart = PO.ImprovePart[DataNum-1];
        String Introduce = PO.Introduce[DataNum-1];
        int ImageID = PO.SportImgID[DataNum-1];

        SharedPreferences.Editor editor = SP.edit();
        editor.putString("ChineseName",ChineseName);
        editor.putString("EnglishName",EnglishName);
        editor.putString("Introduce",Introduce);
        editor.putString("ImprovePart",ImprovePart);
        editor.putInt("ImageID",ImageID);
        editor.commit();
        startActivity(new Intent(PokedexPartScene.this,ProfileUI.class));
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.Image1:CatchData(1);break;
            case R.id.Image2:CatchData(2);break;
            case R.id.Image3:CatchData(3);break;
            case R.id.Image4:CatchData(4);break;
            case R.id.Image5:CatchData(5);break;
            case R.id.Image6:CatchData(6);break;
            case R.id.Image7:CatchData(7);break;
            case R.id.Image8:CatchData(8);break;
            case R.id.Image9:CatchData(9);break;
            case R.id.Image10:CatchData(10);break;
            case R.id.Image11:CatchData(11);break;
            case R.id.Image12:CatchData(12);break;
            case R.id.Image13:CatchData(13);break;
            case R.id.Image14:CatchData(14);break;
            case R.id.Image15:CatchData(15);break;
            case R.id.Image16:CatchData(16);break;
            case R.id.Image17:CatchData(17);break;
            case R.id.Image18:CatchData(18);
        }
    }
}