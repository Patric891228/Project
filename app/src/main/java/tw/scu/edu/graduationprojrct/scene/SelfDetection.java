package tw.scu.edu.graduationprojrct.scene;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.R;

public class SelfDetection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_detection);
        ImageView bg=findViewById(R.id.bg);
        Intent intent=getIntent();
        String part=intent.getStringExtra("part");
        if (part.equals("belly")) {
            bg.setImageResource(R.drawable.check_belly);
        } else if (part.equals("oxleg")) {
            bg.setImageResource(R.drawable.check_oxleg);
        } else if (part.equals("pelvic")) {
            bg.setImageResource(R.drawable.check_pelvic);
        } else if (part.equals("saddlebags")) {
            bg.setImageResource(R.drawable.check_saddlebags);
        }else if(part.equals("slouch")){
            bg.setImageResource(R.drawable.check_slouch );
        }
        ImageView back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelfDetection.this,TestSelfScene.class));
            }
        });
    }
}