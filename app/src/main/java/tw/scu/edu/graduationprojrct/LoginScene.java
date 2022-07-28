package tw.scu.edu.graduationprojrct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginScene extends AppCompatActivity {
    /*//public SQLite DH = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //DH = new SQLite(this);
        add("123");//加入的資料
    }

    private void add(String s){
        //SQLiteDatabase db = DH.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_title",s.toString());//載入資料123
        //db.insert("Personal_Profile", null,values);//寫入123
        //ListView LV1 = (ListView)findViewById(R.id.LV);//讀取元件

        //查詢資料並載入
        //Cursor cursor = db.query("Personal_Profile", new String[]{"_id","_title"},null,null,null, null,null);
        List<Map<String,Object>> items = new ArrayList<Map<String, Object>>();
        cursor.moveToFirst();//創建資料集後movetoFirst移動到第一筆

        //叫出資料庫資料
        for(int i = 0; i < cursor.getCount(); i++){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("_id",cursor.getString(0));
            item.put("_title",cursor.getString(1));
            items.add(item);//新增
            cursor.moveToNext();//移下一筆資料
        }
        SimpleAdapter SA = new SimpleAdapter(this, items, android.R.layout.simple_expandable_list_item_2,new String[]{"_id","_title"}, new int[]{android.R.id.text1,android.R.id.text2});
        //LV1.setAdapter(SA);
    }

     */
}