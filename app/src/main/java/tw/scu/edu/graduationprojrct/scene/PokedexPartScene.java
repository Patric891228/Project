package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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

        init();
        ImageObjectSet();

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
                SetPart("Belly");
                ImageVisibilitySet();
                Log.d("SelectPokedexPart","Belly");
                Log.d("Number", String.valueOf(ImageNumber));
                reset();
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetPart("Oxleg");
                ImageVisibilitySet();
                Log.d("SelectPokedexPart","Oxleg");
                Log.d("Number", String.valueOf(ImageNumber));
                reset();
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetPart("Pelvic");
                ImageVisibilitySet();
                Log.d("SelectPokedexPart","Pelvic");
                Log.d("Number", String.valueOf(ImageNumber));
                reset();
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetPart("Saddlebag");
                ImageVisibilitySet();
                Log.d("SelectPokedexPart","Saddlebag");
                Log.d("Number", String.valueOf(ImageNumber));
                reset();
            }
        });
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetPart("Slouch");
                ImageVisibilitySet();
                Log.d("SelectPokedexPart","Slouch");
                Log.d("Number", String.valueOf(ImageNumber));
                reset();
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
        PO = new PokedexObject("Belly");//預設為小腹
        SetPart("Belly");
        SelectButton.setVisibility(View.VISIBLE);
        SelectButton_Clicked.setVisibility(View.GONE);
        B1.setVisibility(View.GONE);
        B2.setVisibility(View.GONE);
        B3.setVisibility(View.GONE);
        B4.setVisibility(View.GONE);
        B5.setVisibility(View.GONE);
    }
    private void reset(){
        SelectButton.setVisibility(View.VISIBLE);
        SelectButton_Clicked.setVisibility(View.GONE);
        B1.setVisibility(View.GONE);
        B2.setVisibility(View.GONE);
        B3.setVisibility(View.GONE);
        B4.setVisibility(View.GONE);
        B5.setVisibility(View.GONE);
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
        }
        for(int i=ImageNumber;i<18;i++){
            ImageArray[i].setVisibility(View.GONE);
        }
    }
    private void CatchData(int i ){
        int DataNum = PO.PokedexContent[i-1];
        Log.d("DataNum", String.valueOf(DataNum));
        if(DataNum>4){ //沒有ID5 6
            DataNum-=2;
        }
        String ChineseName = PO.ChineseName[DataNum-1];
        String EnglishName = PO.EnglishName[DataNum-1];
        String ImprovePart = PO.ImprovePart[DataNum-1];
        String Introduce = PO.Introduce[DataNum-1];
        Log.d("ImageData",ChineseName+" "+EnglishName+" "+ImprovePart);
        Log.d("ImageIntrduce",Introduce);
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