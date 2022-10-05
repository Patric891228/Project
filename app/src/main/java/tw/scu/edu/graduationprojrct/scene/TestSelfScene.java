package tw.scu.edu.graduationprojrct.scene;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.R;

public class TestSelfScene extends AppCompatActivity {
    ImageView slouch, oxleg, saddlebags, pelvic, belly, MirrorBG;
    ImageView gray_slouch, gray_oxleg, gray_saddlebags, gray_pelvic, gray_belly, gray_MirrorBG;
    ImageView button_slouch, button_oxleg, button_saddlebags, button_belly, button_pelvic, button_MirrorBG;
    ImageButton Back_Test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_self_scene);
        createObject();
//        按鈕事件
        slouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gone(slouch);
                Gone(oxleg);
                Gone(saddlebags);
                Gone(pelvic);
                Gone(belly);

                Appear(button_slouch);
                Appear(gray_oxleg);
                Appear(gray_saddlebags);
                Appear(gray_pelvic);
                Appear(gray_belly);
            }
        });
        oxleg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gone(slouch);
                Gone(oxleg);
                Gone(saddlebags);
                Gone(pelvic);
                Gone(belly);

                Appear(button_oxleg);
                Appear(gray_slouch);
                Appear(gray_saddlebags);
                Appear(gray_pelvic);
                Appear(gray_belly);
            }
        });
        saddlebags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gone(slouch);
                Gone(oxleg);
                Gone(saddlebags);
                Gone(pelvic);
                Gone(belly);

                Appear(button_saddlebags);
                Appear(gray_slouch);
                Appear(gray_oxleg);
                Appear(gray_pelvic);
                Appear(gray_belly);
            }
        });
        pelvic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gone(slouch);
                Gone(oxleg);
                Gone(saddlebags);
                Gone(pelvic);
                Gone(belly);

                Appear(button_pelvic);
                Appear(gray_slouch);
                Appear(gray_oxleg);
                Appear(gray_saddlebags);
                Appear(gray_belly);
            }
        });
        belly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gone(slouch);
                Gone(oxleg);
                Gone(saddlebags);
                Gone(pelvic);
                Gone(belly);

                Appear(button_belly);
                Appear(gray_slouch);
                Appear(gray_oxleg);
                Appear(gray_saddlebags);
                Appear(gray_pelvic);
            }
        });

        gray_slouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gone(gray_slouch);
                Appear(button_slouch);

                Gone(button_oxleg);
                Gone(button_saddlebags);
                Gone(button_pelvic);
                Gone(button_belly);

                Appear(gray_oxleg);
                Appear(gray_saddlebags);
                Appear(gray_pelvic);
                Appear(gray_belly);
            }
        });
        gray_oxleg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gone(gray_oxleg);
                Appear(button_oxleg);

                Gone(button_slouch);
                Gone(button_saddlebags);
                Gone(button_pelvic);
                Gone(button_belly);

                Appear(gray_slouch);
                Appear(gray_saddlebags);
                Appear(gray_pelvic);
                Appear(gray_belly);
            }
        });
        gray_saddlebags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gone(gray_saddlebags);
                Appear(button_saddlebags);

                Gone(button_slouch);
                Gone(button_oxleg);
                Gone(button_pelvic);
                Gone(button_belly);

                Appear(gray_slouch);
                Appear(gray_oxleg);
                Appear(gray_pelvic);
                Appear(gray_belly);
            }
        });
        gray_pelvic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gone(gray_pelvic);
                Appear(button_pelvic);

                Gone(button_slouch);
                Gone(button_oxleg);
                Gone(button_saddlebags);
                Gone(button_belly);

                Appear(gray_slouch);
                Appear(gray_oxleg);
                Appear(gray_saddlebags);
                Appear(gray_belly);
            }
        });
        gray_belly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gone(gray_belly);
                Appear(button_belly);

                Gone(button_slouch);
                Gone(button_oxleg);
                Gone(button_saddlebags);
                Gone(button_pelvic);

                Appear(gray_slouch);
                Appear(gray_oxleg);
                Appear(gray_saddlebags);
                Appear(gray_pelvic);
            }
        });

        Back_Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestSelfScene.this, MainScene.class));
            }
        });
        button_belly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TestSelfScene.this,SelfDetection.class);
                intent.putExtra("part","belly");
                startActivity(intent);
            }
        });
        button_oxleg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TestSelfScene.this,SelfDetection.class);
                intent.putExtra("part","oxleg");
                startActivity(intent);
            }
        });
        button_pelvic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TestSelfScene.this,SelfDetection.class);
                intent.putExtra("part","pelvic");
                startActivity(intent);
            }
        });
        button_saddlebags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TestSelfScene.this,SelfDetection.class);
                intent.putExtra("part","saddlebags");
                startActivity(intent);
            }
        });
        button_slouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TestSelfScene.this,SelfDetection.class);
                intent.putExtra("part","slouch");
                startActivity(intent);
            }
        });
    }

    public void createObject() {
        slouch = findViewById(R.id.slouch);
        oxleg = findViewById(R.id.oxleg);
        saddlebags = findViewById(R.id.saddlebags);
        pelvic = findViewById(R.id.pelvic);
        belly = findViewById(R.id.belly);

        gray_slouch = findViewById(R.id.gray_slouch);
        gray_oxleg = findViewById(R.id.gray_oxleg);
        gray_saddlebags = findViewById(R.id.gray_saddlebags);
        gray_pelvic = findViewById(R.id.gray_pelvic);
        gray_belly = findViewById(R.id.gray_belly);

        gray_slouch.setVisibility(View.GONE);
        gray_oxleg.setVisibility(View.GONE);
        gray_saddlebags.setVisibility(View.GONE);
        gray_pelvic.setVisibility(View.GONE);
        gray_belly.setVisibility(View.GONE);

        button_slouch = findViewById(R.id.button_slouch);
        button_oxleg = findViewById(R.id.button_oxleg);
        button_saddlebags = findViewById(R.id.button_saddlebags);
        button_pelvic = findViewById(R.id.button_pelvic);
        button_belly = findViewById(R.id.button_belly);

        button_slouch.setVisibility(View.GONE);
        button_oxleg.setVisibility(View.GONE);
        button_saddlebags.setVisibility(View.GONE);
        button_pelvic.setVisibility(View.GONE);
        button_belly.setVisibility(View.GONE);

        Back_Test = findViewById(R.id.Back_From_Magazine);
        MirrorBG = findViewById(R.id.MirrorBG);
    }

    public void Gone(ImageView obj) {
        obj.setVisibility(View.GONE);
//        obj.startAnimation(GoneAnim);
    }

    public void Appear(ImageView obj) {
        obj.setVisibility(View.VISIBLE);
//        obj.startAnimation(AppearAnim);
    }
}
