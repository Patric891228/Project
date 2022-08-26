package tw.scu.edu.graduationprojrct.scene;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.CustomAdapter;
import tw.scu.edu.graduationprojrct.Setting.DBHelper;

public class AdminManagement extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    public int scrollWidth, scrollHeight;
    public int blankHeight, blankWidth;
    private RelativeLayout blankPlace;

    DBHelper MyDB;
    ArrayList<String> username = new ArrayList<>();
    ArrayList<String> account = new ArrayList<>();
    ArrayList<String> password = new ArrayList<>();
    ArrayList<TextView> AccountInfo = new ArrayList<>();
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_management);
        MyDB = new DBHelper(this);
        blankPlace = findViewById(R.id.blankPlace);
        checkScrollSize();
        caluateItemSize(scrollWidth, scrollHeight);
        storeDataArrays();
        PrintAccountInfo();
        createPokedexFrame();
        BTNEvent();
    }

    private void checkScrollSize() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        this.scrollWidth = (int) (dm.widthPixels * 0.8);
        this.scrollHeight = (int) (dm.heightPixels * 0.8);
    } // 取得視窗大小

    private void caluateItemSize(double scrollWidth, double scrollHeight) {
        this.blankWidth = (int) scrollWidth;
        this.blankHeight = 100;
    } // 計算間隔、物件大小

    private void createPokedexFrame() {
        Cursor cursor = MyDB.readAllData();
        int DataSize = cursor.getCount(); // ==4
       for(int i =0;i<DataSize;i++){
          TextView TV = new TextView(  this);
          TV.setText(username.get(i)+" "+account.get(i)+" "+password.get(i));
          TV.setId(i);
          AccountInfo.add(TV);
          RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams((int)blankWidth,(int)blankHeight);
          btParams.topMargin = (int)((i-1)*blankHeight); //縱座標定位
          blankPlace.addView(AccountInfo.get(i),btParams); //將按鈕放入layout元件
      }
    } // 動態建立表格

    private void PrintAccountInfo() {
        for (int i = 0; i < username.size(); i++){
            Log.d("AccountInfo", username.get(i));
            Log.d("AccountInfo",account.get(i));
            Log.d("AccountInfo", password.get(i));
        }
    }

    void storeDataArrays() {
        Cursor cursor = MyDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "NO DATA!!", Toast.LENGTH_SHORT).show();
            Log.d("Account", "Not have");
        } else {
            Log.d("Account", "have");

            while (cursor.moveToNext()) {
                username.add(cursor.getString(0));
                account.add(cursor.getString(1));
                password.add(cursor.getString(2));
            }
        }
    }

    void BTNEvent(){
        for(int i =0;i<AccountInfo.size();i++){
            AccountInfo.get(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(AdminManagement.this,AccountSetScene.class);
        intent.putExtra("username",username.get(view.getId()));
        intent.putExtra("account",username.get(view.getId()));
        intent.putExtra("password",username.get(view.getId()));
        Log.d("username",username.get(view.getId()));
        Log.d("account",account.get(view.getId()));
        Log.d("password",password.get(view.getId()));
        startActivity(intent);

    }
}
