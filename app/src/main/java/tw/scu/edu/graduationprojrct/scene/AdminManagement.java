package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.CustomAdapter;
import tw.scu.edu.graduationprojrct.Setting.DBHelper;

public class AdminManagement extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DBHelper MyDB;
    ArrayList<String> username, password;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_management);
        TextView Account = findViewById(R.id.UN);
        TextView Password = findViewById(R.id.PS);


//        recyclerView = findViewById(R.id.RecyclerView);
        MyDB = new DBHelper(this);
        username = new ArrayList<>();
        password = new ArrayList<>();



        storeDataArrays();
        Account.setText(username.get(1));
        Password.setText(password.get(1));
//        customAdapter = new CustomAdapter(AdminManagement.this, username, password);
//        recyclerView.setAdapter(customAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(AdminManagement.this));

    }

    void storeDataArrays() {
        Cursor cursor = MyDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "NO DATA!!", Toast.LENGTH_SHORT).show();
            Log.d("Account","Not have");
        } else {
            Log.d("Account","have");

            while (cursor.moveToNext()) {
                username.add(cursor.getString(0));
                password.add(cursor.getString(1));
            }
        }
    }
}