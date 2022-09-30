package tw.scu.edu.graduationprojrct.scene;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import tw.scu.edu.graduationprojrct.BuildConfig;
import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.java.CameraXLivePreviewActivity;

public class NavigationScene extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = "ChooserActivity";
    ImageView BG;
    @SuppressWarnings("NewApi") // CameraX is only available on API 21+
    private static final Class<?>[] CLASSES =
            Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
                    ? new Class<?>[] {
            }///////////
                    : new Class<?>[] {
                    CameraXLivePreviewActivity.class,
            };
    private static final int[] DESCRIPTION_IDS =
            Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
                    ? new int[] {
                    R.string.desc_camera_source_activity, R.string.desc_still_image_activity,
            }
                    : new int[] {
                    R.string.desc_camerax_live_preview_activity
            };
    String SportType;
    SharedPreferences shared;
    Button GO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(
                    new StrictMode.VmPolicy.Builder()
                            .detectLeakedSqlLiteObjects()
                            .detectLeakedClosableObjects()
                            .penaltyLog()
                            .build());
        }


        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_navigation_scene);
        shared = getSharedPreferences("data",MODE_PRIVATE);
        SportType = shared.getString("SportType","Belly");
        BG = findViewById(R.id.view);
        Log.d("SportType",SportType);
        if(SportType.equals("Belly")){
            BG.setImageResource(R.drawable.startexercise_belly);
        }else if (SportType.equals("Oxleg")){
            BG.setImageResource(R.drawable.startexercise_oxleg);
        }else if (SportType.equals("Pelvic")){
            BG.setImageResource(R.drawable.startexercise_pelvic);
        }else if (SportType.equals("Saddlebags")){
            BG.setImageResource(R.drawable.startexercise_saddlebags);
        }else{
            BG.setImageResource(R.drawable.startexercise_slouch);
        }
        Log.d("EnterScene","NavigationScene");
        ListView listView = findViewById(R.id.test_activity_list_view);

        MyArrayAdapter adapter = new MyArrayAdapter(this, android.R.layout.simple_list_item_2, CLASSES);
        adapter.setDescriptionIds(DESCRIPTION_IDS);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Class<?> clicked = CLASSES[i];
        startActivity(new Intent(this, clicked));
    }
    private static class MyArrayAdapter extends ArrayAdapter<Class<?>> {

        private final Context context;
        private final Class<?>[] classes;
        private int[] descriptionIds;

        MyArrayAdapter(Context context, int resource, Class<?>[] objects) {
            super(context, resource, objects);

            this.context = context;
            classes = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

//      if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(android.R.layout.simple_list_item_2, null);
//      }

//            ((TextView) view.findViewById(android.R.id.text1)).setText(classes[position].getSimpleName());
//            ((TextView) view.findViewById(android.R.id.text2)).setText(descriptionIds[position]);

            return view;
        }

        void setDescriptionIds(int[] descriptionIds) {
            this.descriptionIds = descriptionIds;
        }
    }
}
