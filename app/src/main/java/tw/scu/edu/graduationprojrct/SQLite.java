package tw.scu.edu.graduationprojrct;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {

    private final static String DB = "Project.db"; //資料庫
    private final static String TB = "Personal_Profile"; //資料表
    private final static int VS = 3; //版本
    private static SQLiteDatabase db;

    public SQLite( Context context) {
        super(context, DB, null, VS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = "CREATE TABLE IF NOT EXISTS " + TB +"(_id INTEGER PRIMARY KEY AUTOINCREMENT ,_title VARCHAR(50)";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL = "DROP TABLE" + TB;
        db.execSQL(SQL);

    }
}
