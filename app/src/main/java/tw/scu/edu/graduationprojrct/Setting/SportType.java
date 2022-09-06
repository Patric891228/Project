package tw.scu.edu.graduationprojrct.Setting;

import android.util.Log;
import android.widget.ImageView;

import tw.scu.edu.graduationprojrct.R;

public class SportType {
    public String ST;
    public String SportContent[];
    public int SportContentTime[];
    public int SportImgID[];
    ImageView CurrentImage;
    public final int BlankTime = 10000;
    public SportType(String Type){
        switch(Type){
            case"Belly":this.ST = "Belly";break;
            case"Oxleg":this.ST = "Oxleg";
            case"Pelvic":this.ST = "Pelvic";
            case"Saddlebag":this.ST = "Saddlebag";
            case"Slouch":this.ST = "Slouch";
        }
        Log.d("Structure",Type);
    }
    public void LoadSportData(){
        if(ST.equals("Belly")){
            this.SportContent = new String[]{"LegRaise","LegRaise10","LegRaise60","LegRaise10","LegRaise30","Bicycle Crunch","LegRaise10","LegRaise90","Raise Leg Crunch","LegRaise60",
                                             "LegRaise30","LegRaise10","Boat Pose","Bridge","Plank","SidePlank","SidePlank","Plank","Locust Pose","Super Men Pose",
                                             "Cobra Pose","Child Pose"};
            this.SportContentTime = new int[]{30,10,10,10,10,40,10,10,10,40,
                                              10,10,30,45,60,30,30,30,45,45,
                                              30,30};
            this.SportImgID = new int[]{R.drawable.belly_01,R.drawable.belly_02,R.drawable.belly_03,R.drawable.belly_04,
                    R.drawable.belly_05,R.drawable.belly_06,R.drawable.belly_07,R.drawable.belly_08,R.drawable.belly_09,R.drawable.belly_10,
                    R.drawable.belly_11,R.drawable.belly_12,R.drawable.belly_13,R.drawable.belly_14,R.drawable.belly_15,R.drawable.belly_16_17,
                    R.drawable.belly_16_17,R.drawable.belly_18,R.drawable.belly_19,R.drawable.belly_20,R.drawable.belly_21,R.drawable.belly_22 };
            Log.d("LoadSportData","Belly");
        }else if(ST=="Slouch"){
            this.SportContent = new String[]{"Upper Trapezius Stretch","Upper Trapezius Stretch","Deltoid Muscle Stretch","Deltoid Muscle Stretch","Triceps Stretch",
                                             "Triceps Stretch","Reverse Prayer Pose","Cat Cow Pose","Cobra Pose","Child Pose",
                                             "Locust Pose","Superman Pose","Camel Pose","Seated Side Bend","Seated Side Bend",
                                             "Cross Leg Forward Bend","Cross Leg Forward Bend"};
            this.SportContentTime = new int[]{30,30,30,30,30,30,30,60,30,30,
                                              45,45,20,30,30,45,45};
            this.SportImgID = new int[]{R.drawable.slouch_01_02,R.drawable.slouch_01_02,R.drawable.slouch_03_04,R.drawable.slouch_03_04
            ,R.drawable.slouch_05_06,R.drawable.slouch_05_06,R.drawable.slouch_07,R.drawable.slouch_10,
                    R.drawable.slouch_11,R.drawable.slouch_13,R.drawable.slouch_12,R.drawable.slouch_14,R.drawable.slouch_15,R.drawable.slouch_16_17,
                    R.drawable.slouch_16_17,R.drawable.slouch_18_19,R.drawable.slouch_18_19};
            Log.d("LoadSportData","Slouch");
        }else if (ST=="Pelvic"){
            this.SportContent = new String[]{"Iliopsoas Muscle Stretch","Downward-Facing Dog Pose","Child Pose","Bent Knee Crunch",
                                             "Raise Leg Crunch","Side Plank","Plank","Side Plank","Plank",
                                             "Bridge","Lunge","Lunge","Iliopsoas Muscle Stretch","Iliopsoas Muscle Stretch",
                                             "Pigeon Pose","Pigeon Pose","Child Pose"};
            this.SportContentTime = new int[]{60,30,30,30,30,30,30,30,30,
                                              30,30,30,30,30,30,30,30};
            this.SportImgID = new int[]{R.drawable.pelvic_01_02,R.drawable.pelvic_03,R.drawable.pelvic_04,
                    R.drawable.pelvic_05,R.drawable.pelvic_06,R.drawable.pelvic_07,R.drawable.pelvic_08,R.drawable.pelvic_09,
                    R.drawable.pelvic_10,R.drawable.pelvic_11,R.drawable.pelvic_12_13,R.drawable.pelvic_12_13,
                    R.drawable.pelvic_14,R.drawable.pelvic_15,R.drawable.pelvic_16,R.drawable.pelvic_17,R.drawable.pelvic_18};
            Log.d("LoadSportData","Pelvic");
        }else if(ST=="Saddlebag"){
            this.SportContent = new String[]{"Iliopsoas Muscle Stretch","Iliopsoas Muscle Stretch","Cross Leg Forward Bend","Cross Leg Forward Bend","Frog Pose",
                                             "Raise Leg Crunch","Bicycle Crunch","Side Plank","Side Plank","Clamshell Exercise",
                                             "Clamshell Exercise","Inner Thigh Lift","Inner Thigh Lift","Bridge","Bound Angle Pose",
                                             "Pigeon Pose","Pigeon Pose","Child Pose"};
            this.SportContentTime = new int[]{30,30,30,30,30,30,30,30,30,30,
                                              30,30,30,30,30,30,30,30};
            this.SportImgID = new int[]{R.drawable.saddlebags_01_02,R.drawable.saddlebags_01_02,R.drawable.saddlebags_03_04,
                    R.drawable.saddlebags_03_04,R.drawable.saddlebags_05,R.drawable.saddlebags_06,R.drawable.saddlebags_07,
                    R.drawable.saddlebags_08_09,R.drawable.saddlebags_08_09,R.drawable.saddlebags_10_11,R.drawable.saddlebags_10_11,
                    R.drawable.saddlebags_12_13,R.drawable.saddlebags_12_13,R.drawable.saddlebags_14,R.drawable.saddlebags_15,
                    R.drawable.saddlebags_16_17,R.drawable.saddlebags_16_17,R.drawable.saddlebags_18};
            Log.d("LoadSportData","Saddlebag");
        }else{ //Oxleg
            this.SportContent = new String[]{"Bound Angle Pose","Frog Pose","Standing Forward Bend","Squat","Clamshell Exercise",
                                             "Clamshell Exercise","Inner Thigh Lift","Inner Thigh Lift","Bridge","Iliopsoas Muscle Stretch",
                                             "Iliopsoas Muscle Stretch","Pigeon Pose","Pigeon Pose","Child Pose"};
            this.SportContentTime = new int[]{20,30,30,45,45,45,45,45,45,30 ,
                                              30,30,30,30};
            this.SportImgID = new int[]{R.drawable.oxleg_01,R.drawable.oxleg_02,R.drawable.oxleg_03,R.drawable.oxleg_04,R.drawable.oxleg_05_06,
                    R.drawable.oxleg_05_06,R.drawable.oxleg_07_08,R.drawable.oxleg_07_08,R.drawable.oxleg_09,R.drawable.oxleg_10_11,R.drawable.oxleg_10_11,
                    R.drawable.oxleg_12_13,R.drawable.oxleg_12_13,R.drawable.oxleg_14};
            Log.d("LoadSportData","Oxleg");
        }
    }
    public void ShowImage(int i){

    }

}
