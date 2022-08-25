package tw.scu.edu.graduationprojrct.Setting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Time;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, account TEXT,password TEXT)");
        MyDB.execSQL("create Table RemindTime(username TEXT primary key,time String)");//HH:MM
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");

    }

    public Boolean insertData(String username,String account, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("account",account);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Boolean insertTime(String username, String Time){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("time", Time);
        long result = MyDB.insert("RemindTime", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where account = ? and password = ?", new String[] {username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }
    public String getRemindTime(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from RemindTime where username = ? ", new String[] {username});
        return cursor.getString(4);

    }

    public Cursor readAllData(){
        String query = "SELECT * FROM users";
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = null;
        if(MyDB != null){
            cursor = MyDB.rawQuery(query, null);
        }

        return cursor;
    }


}