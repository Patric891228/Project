package tw.scu.edu.graduationprojrct.scene;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Size;
import android.util.TypedValue;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

public class TieUtil {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getScreenWidthInDPs(Context context){

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getRealMetrics(dm);// .getMetrics(dm);

        int widthInDP = Math.round(dm.widthPixels / dm.density);
        return widthInDP;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getScreenHeightInDPs(Context context) {

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getRealMetrics(dm);// .getMetrics(dm);

        int heightInDP = Math.round(dm.heightPixels / dm.density);
        return heightInDP;
    }
    public static int dpToPx(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static float getFullScreenScaleFactor(Context context) {
        Size deivceSize = getDeviceSize(context);
        float widthFactor = (float) deivceSize.getWidth() / 540;
        float heightFactor = (float) deivceSize.getHeight() / 960;

        return Math.max(widthFactor, heightFactor);
    }

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Size getDeviceSize(Context context) {
        int width = TieUtil.getScreenWidthInDPs(context);
        int height = TieUtil.getScreenHeightInDPs(context);


            return new Size(width, height);

    }
}
