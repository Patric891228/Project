package tw.scu.edu.graduationprojrct.Setting;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.chaquo.python.Kwarg;
import com.chaquo.python.PyObject;
import com.chaquo.python.android.AndroidPlatform;
import com.chaquo.python.Python;
import java.util.ArrayList;
import java.util.List;

import tw.scu.edu.graduationprojrct.R;

public class PythonTest extends AppCompatActivity {
    static final String TAG = "PythonOnAndroid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python_test);
        initPython();
        callPythonCode();
    }
    void initPython(){
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }
    void callPythonCode(){
        Python py = Python.getInstance();
        py.getModule("hello").callAttr("greet", "Android");
        py.getBuiltins().get("help").call();
        PyObject obj1 = py.getModule("hello").callAttr("add", 2,3);
        Integer sum = obj1.toJava(Integer.class);
        Log.d(TAG,"add = "+sum.toString());
        PyObject obj2 = py.getModule("hello").callAttr("sub", 10,new Kwarg("b", 1), new Kwarg("c", 3));
        Integer result = obj2.toJava(Integer.class);
        Log.d(TAG,"sub = "+result.toString());
        PyObject obj3 = py.getModule("hello").callAttr("get_list", 10,"xx",5.6,'c');
        List<PyObject> pyList = obj3.asList();
        Log.d(TAG,"get_list = "+pyList.toString());
        List<PyObject> params = new ArrayList<PyObject>();
        params.add(PyObject.fromJava("alex"));
        params.add(PyObject.fromJava("bruce"));
        py.getModule("hello").callAttr("print_list", params);
        PyObject obj4 = py.getModule("hello").callAttr("get_java_bean");
//        JavaBean data = obj4.toJava(JavaBean.class);
//        data.print();
    }
}