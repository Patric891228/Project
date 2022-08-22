package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import tw.scu.edu.graduationprojrct.R;

public class CheckAccountScene extends AppCompatActivity {
    ImageButton isMember, noMember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_account_scene);

        isMember = findViewById(R.id.isMember);
        noMember = findViewById(R.id.noMember);

        isMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(CheckAccountScene.this,LoginScene.class)));
            }
        });
        noMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(CheckAccountScene.this,RegistScene.class)));
            }
        });
    }
}