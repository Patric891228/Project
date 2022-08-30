package tw.scu.edu.graduationprojrct.Setting;

public class SportType {
    public Boolean Belly,Oxleg,Pelvic,Saddlebag,Slouch = false;
    public String SportContent[];
    public int SportContentTime[];
    public final int BlankTime = 10000;
    public SportType(String Type){
        switch(Type){
            case"Belly":this.Belly = true;break;
            case"Oxleg":this.Oxleg = true;break;
            case"Pelvic":this.Pelvic = true;break;
            case"Saddlebag":this.Saddlebag = true;break;
            case"Slouch":this.Slouch = true;break;
        }
    }
    public void LoadSportData(){
        if(Belly){
            this.SportContent = new String[]{"LegRaise","LegRaise10","LegRaise60","LegRaise10","LegRaise30","Bicycle Crunch","LegRaise10","LegRaise90","Raise Leg Crunch","LegRaise60",
                                             "LegRaise30","LegRaise10","Boat Pose","Bridge","Plank","SidePlank","SidePlank","Plank","Locust Pose","Super Men Pose",
                                             "Cobra Pose","Child Pose"};
            this.SportContentTime = new int[]{30000,10000,10000,10000,10000,40000,10000,10000,10000,40000,
                                              10000,10000,30000,45000,60000,30000,30000,30000,45000,45000,
                                              30000,30000};
        }else if(Slouch){
            this.SportContent = new String[]{"Upper Trapezius Stretch","Upper Trapezius Stretch","Deltoid Muscle Stretch","Deltoid Muscle Stretch","Triceps Stretch",
                                             "Triceps Stretch","Reverse Prayer Pose","Cat Cow Pose","Cobra Pose","Child Pose",
                                             "Locust Pose","Superman Pose","Camel Pose","Seated Side Bend","Seated Side Bend",
                                             "Cross Leg Forward Bend","Cross Leg Forward Bend"};
            this.SportContentTime = new int[]{30000,30000,30000,30000,30000,30000,30000,60000,30000,30000,
                                              45000,45000,20000,30000,30000,45000,45000};
        }else if (Pelvic){
            this.SportContent = new String[]{"Iliopsoas Muscle Stretch","Iliopsoas Muscle Stretch","Downward-Facing Dog Pose","Child Pose","Bent Knee Crunch",
                                             "Raise Leg Crunch","Side Plank","Plank","Side Plank","Plank",
                                             "Bridge","Lunge","Lunge","Iliopsoas Muscle Stretch","Iliopsoas Muscle Stretch",
                                             "Pigeon Pose","Pigeon Pose","Child Pose"};
            this.SportContentTime = new int[]{30000,30000,30000,30000,30000,30000,30000,30000,30000,30000,
                                              30000,30000,30000,30000,30000,30000,30000,30000};
        }else if(Saddlebag){
            this.SportContent = new String[]{"Iliopsoas Muscle Stretch","Iliopsoas Muscle Stretch","Cross Leg Forward Bend","Cross Leg Forward Bend","Frog Pose",
                                             "Raise Leg Crunch","Bicycle Crunch","Side Plank","Side Plank","Clamshell Exercise",
                                             "Clamshell Exercise","Inner Thigh Lift","Inner Thigh Lift","Bridge","Bound Angle Pose",
                                             "Pigeon Pose","Pigeon Pose","Child Pose"};
            this.SportContentTime = new int[]{30000,30000,30000,30000,30000,30000,30000,30000,30000,30000,
                                              30000,30000,30000,30000,30000,30000,30000,30000};
        }else{ //Oxleg
            this.SportContent = new String[]{"Bound Angle Pose","Frog Pose","Standing Forward Bend","Squat","Clamshell Exercise",
                                             "Clamshell Exercise","Inner Thigh Lift","Inner Thigh Lift","Bridge","Iliopsoas Muscle Stretch",
                                             "Iliopsoas Muscle Stretch","Pigeon Pose","Pigeon Pose","Child Pose"};
            this.SportContentTime = new int[]{30000,30000,30000,45000,45000,45000,45000,45000,45000,30000,
                                              30000,30000,30000,30000};
        }
    }
}
