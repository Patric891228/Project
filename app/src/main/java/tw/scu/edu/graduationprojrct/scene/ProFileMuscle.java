package tw.scu.edu.graduationprojrct.scene;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.R;

public class ProFileMuscle extends AppCompatActivity {
    SharedPreferences SP;
    ImageView Image;
    TextView Text, ChineseName, EnglishName, Prove, Introduce;
    android.widget.Button Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_file_muscle);
        SP = getSharedPreferences("data", MODE_PRIVATE);
        String PassChineseName = SP.getString("ChineseName", "");
        String PassEnglishName = SP.getString("EnglishName", "");
        String PassIntroduce = SP.getString("Introduce", "");
        String PassImprovePart = SP.getString("ImprovePart", "");
        int PassImageID = SP.getInt("ImageID", 0);
        Image = findViewById(R.id.Image);
        Text = findViewById(R.id.Text);
        ChineseName = findViewById(R.id.ChineseName);
        EnglishName = findViewById(R.id.EnglishName);
        Prove = findViewById(R.id.Prove);
        Introduce = findViewById(R.id.Introduce);
        Button = findViewById(R.id.button2);
        EnglishName.setMovementMethod(ScrollingMovementMethod.getInstance());
        Introduce.setMovementMethod(ScrollingMovementMethod.getInstance());

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                startActivity(new Intent(ProFileMuscle.this,PokedexPartScene.class));
            }
        });

        Image.setImageResource(PassImageID);
        Text.setText(PassChineseName);
        ChineseName.setText("運動中文名稱：" + PassChineseName);
        EnglishName.setText("運動英文名稱：" + PassEnglishName);
        Prove.setText("改善部位：" + PassImprovePart);
        Introduce.setText("動作講解：" + PassIntroduce);
    }
}