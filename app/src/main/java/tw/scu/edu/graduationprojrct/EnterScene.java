package tw.scu.edu.graduationprojrct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;



public class EnterScene extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        EditText editTextA = findViewById(R.id.Account);
        EditText editTextB = findViewById(R.id.Password);

        Button login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                ContentValues contentValues = new ContentValues();
                String s1 = editTextA.getText().toString();
                String s2 = editTextB.getText().toString();
                contentValues.put("MAIL",s1);
                contentValues.put("PASSWORD",s1);


            }
        });

    }
}