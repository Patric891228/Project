package tw.scu.edu.graduationprojrct;

import android.app.Application;

public class GlobalVariable extends Application {
    private String username;     //User 名稱

    //修改 變數値
    public void setName(String name){
        this.username = name;
    }

    //取得 變數值
    public String getName() {
        return username;
    }
}
