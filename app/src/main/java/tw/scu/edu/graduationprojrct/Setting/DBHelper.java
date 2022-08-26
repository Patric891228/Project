package tw.scu.edu.graduationprojrct.Setting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Time;


public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DBNAME = "Login.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_ACCOUNT = "account";
    private static final String COLUMN_PASSWORD = "password";

    public DBHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, account TEXT,password TEXT)");
        MyDB.execSQL("create Table RemindTime(username TEXT primary key,time String)");//HH:MM
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        onCreate(MyDB);

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
    public void updateData(String username, String account,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_ACCOUNT, account);
        cv.put(COLUMN_PASSWORD, password);
        Log.d("NewUserName",username);
        Log.d("NewAccount",account);
        Log.d("NewPassWord",password);
        long result = MyDB.update(TABLE_NAME, cv, "username=?", new String[]{username});
        if(result == -1){
            Toast.makeText(context, "Fail to Update", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Update Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteOneRow(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "username=?", new String[]{username});
        if(result == -1){
            Toast.makeText(context, "Fail to Delete.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}