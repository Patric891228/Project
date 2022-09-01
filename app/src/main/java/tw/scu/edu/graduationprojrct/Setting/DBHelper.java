package tw.scu.edu.graduationprojrct.Setting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DBNAME = "Login.db";
    private static final String TABLE_NAME = "users";
    private static final String TABLE_NAME_TIME = "RemindTime";
    private static final String TABLE_NAME_PERSONALINFO = "PI";

    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_ACCOUNT = "account";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_SEXUAL = "sexual";
    private static final String COLUMN_BIRHDAY = "birthday";
    private static final String COLUMN_EMAIL = "email";

    public DBHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, account TEXT,password TEXT)");
        MyDB.execSQL("create Table PI(username TEXT primary key,sexual TEXT,birthday TEXT,email TEXT)");
        MyDB.execSQL("create Table RemindTime(username TEXT primary key,time TEXT)");//HH:MM
    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        onCreate(MyDB);

    }
    public Boolean insertData(String username,String account, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_ACCOUNT,account);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = MyDB.replace("users", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Boolean insertPersonalInfo(String username,String sexual,String birthday,String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_SEXUAL,sexual);
        contentValues.put(COLUMN_BIRHDAY,birthday);
        contentValues.put(COLUMN_EMAIL,email);
        long result = MyDB.replace("PI", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public String getUserName(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from PI",null);
        if (cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                return cursor.getString(0);
            }
        }
        return "No Data in PI";
    }
    public Boolean insertTime(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_TIME,"08:00");
        long result = MyDB.replace("RemindTIme", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Boolean EditTime(String username, String Time){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_TIME, Time);
        Log.d("isTimeEnterSucess?","YES");
        long result = MyDB.update(TABLE_NAME_TIME, cv, "username=?", new String[]{username});
        if(result == -1)
            return false;
        else
            return true;
    }
    public Boolean EditPersonalInfo(String username,String sexual,String birthday,String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_SEXUAL,sexual);
        cv.put(COLUMN_BIRHDAY,birthday);
        cv.put(COLUMN_EMAIL,email);
        long result = MyDB.update("PI", cv, "username=?", new String[]{username});
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
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }
    public String getAccount(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? ", new String[]{username});
        if (cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                return cursor.getString(1);
            }
        }
        return cursor.getString(1);

    }
    public String getPassWord(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? ", new String[]{username});
        if (cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                return cursor.getString(2);
            }
        }
        return cursor.getString(2);

    }
    public String getRemindTime(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from RemindTime where username = ? ", new String[]{username});
        Log.d("TimeInfo", String.valueOf(cursor.getCount()));
        if (cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                return cursor.getString(1);
            }
        }
        return "Time Invalid";

    }
    public String getSexual(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from PI where username = ? ", new String[]{username});
        Log.d("PersonalInfo", String.valueOf(cursor.getCount()));
        if (cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                return cursor.getString(1);
            }
        }
        return cursor.getString(1);

    }
    public String getBirthday(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from PI where username = ? ", new String[]{username});
        Log.d("PersonalInfo", String.valueOf(cursor.getCount()));
        if (cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                return cursor.getString(2);
            }
        }
        return cursor.getString(2);

    }
    public String getEmail(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from PI where username = ? ", new String[]{username});
        Log.d("PersonalInfo", String.valueOf(cursor.getCount()));
        if (cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                return cursor.getString(3);
            }
        }
        return cursor.getString(3);

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
    public Cursor readAllPIData(){
        String query = "SELECT * FROM PI";
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