package tw.scu.edu.graduationprojrct.Setting;

import android.util.Log;

import tw.scu.edu.graduationprojrct.R;

public class PokedexObject {
    public String PokedexAll;
    public int PokedexContent[];
//    public String PokedexPart;
    public int PokedexID[];
    public String ChineseName[];
    public String EnglishName[];
    public String ImprovePart[];
    public String Introduce[];
    public int SportImgID[];
    public PokedexObject(String Type){
        switch(Type){
            case"All":this.PokedexAll = "All";break;
            case"Belly":this.PokedexAll = "Belly";break;
            case"Oxleg":this.PokedexAll= "Oxleg";break;
            case"Pelvic":this.PokedexAll = "Pelvic";break;
            case"Saddlebag":this.PokedexAll = "Saddlebag";break;
            case"Slouch":this.PokedexAll = "Slouch";break;
            case"Hand":this.PokedexAll = "Hand";break;
            case"Back":this.PokedexAll= "Back";break;
            case"Abdomen":this.PokedexAll = "Abdomen";break;
            case"Buttocks":this.PokedexAll = "Buttocks";break;
            case"Leg":this.PokedexAll = "Leg";
        }
        CreativePokedexIndex();
        Log.d("Structure",Type);
    }
    public void CreativePokedexIndex(){
        this.PokedexID = new int[]{1,2,3,4,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36};
        this.ChineseName = new String[]{"斜方肌拉伸","三角肌拉伸","肱三頭肌拉伸","反向祈禱式","貓牛式","眼鏡蛇式","嬰兒式","蝗蟲式",
                "超人式","駱駝式","盤腿側腰伸展","盤腿前彎式","髂腰肌伸展","下犬式","屈膝捲腹","抬腿捲腹","側平板","平板撐",
                "臀橋","弓步蹲","鴿子式","青蛙式","交叉捲腹","蚌式開合","側臥內抬腿","束角式","立姿前彎式","膝蓋夾書深蹲"
                ,"仰臥抬腿","仰臥抬腿10度","仰臥抬腿30度","仰臥抬腿60度","仰臥抬腿90度","船式"};
        this.EnglishName = new String[]{"Upper Trapezius Stretch","Deltoid Muscle Stretch","Triceps Stretch","Reverse Prayer Pose","Cat Cow Pose",
                "Cobra pose","Child Pose","Locust Pose",
                "Superman Pose","Camel Pose","Seated Side Bend","Cross Leg Forward Bend","Iliopsoas Muscle Stretch",
                "Downward-Facing Dog Pose","Bent Knee Crunch","Raise Leg Crunch","Side Plank","Plank",
                "Bridge","Lunge","Pigeon Pose","Frog Pose","Bicycle Crunch",
                "Clamshell Exercise","Inner Thigh Lift","Bound Angle Pose","Standing Forward Bend","Squat"
                ,"Lying Leg Raises","Lying Leg Raises10°","Lying Leg Raises30°","Lying Leg Raises60°","Lying Leg Raises90°","Boat Pose"};
        this.SportImgID = new int[]{R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p7,
                R.drawable.p8,R.drawable.p9,R.drawable.p10,R.drawable.p11,R.drawable.p12,
                R.drawable.p13,R.drawable.p14,R.drawable.p15,R.drawable.p16,R.drawable.p17,
                R.drawable.p18,R.drawable.p19,R.drawable.p20,R.drawable.p21,R.drawable.p22,
                R.drawable.p23,R.drawable.p24,R.drawable.p25,R.drawable.p26,R.drawable.p27,
                R.drawable.p28,R.drawable.p29,R.drawable.p30,R.drawable.p31,R.drawable.p32,
                R.drawable.p33,R.drawable.p34,R.drawable.p35,R.drawable.p36};
        this.ImprovePart = new String[]{"手","手","手","手,臀","手,臀",
                                        "腹,臀","腹,臀","手,腹,臀,腿","臀","臀",
                                        "臀","臀","腹,臀","腹,臀","腹,臀",
                                        "腹","手,腹","手,腹,臀","腹,臀","臀,腿",
                                        "腿","腹,臀,腿","腹","腿","腿",
                                        "腿","腿","臀,腿","腹,臀,腿","腹,臀,腿",
                                        "腹,臀,腿","腹,臀,腿","腹,臀,腿","腹,臀,腿"};
        this.Introduce = new String[]{"Step1:坐著或站著都可以，身體固定。 \n" +
                "Step2:一手輕壓頭部，將頭往側邊傾斜。 \n" +
                "Step3:角度不用太大，有伸展開的感覺即可。 配合胸式呼吸法，能讓伸展效果更好。\n",
                "採用站姿，挺直腰身頭部微向上抬，然後雙手放於背後掌心相對，雙手十指相扣繃緊雙臂用力向上抬起，使得肩膀前方的三角肌前束有明顯的緊繃感，" +
                        "這個動作維持15秒左右，接著放鬆再重複上述動作，大約2-3組就可以。",
                "(1)坐姿或站姿，將右手臂伸向天花板，彎曲手肘放置上背\n" +
                        "(2)左手掌放在右手肘上，輕輕地將手臂向下推\n" +
                        "(3)停留30秒鐘，換邊並重複動作\n",
                "步驟1：雙腳站在原地或是坐在瑜伽墊上，將背部打直，抬頭挺胸。 \n" +
                        "步驟2：將雙手放置在後方腰椎，雙手手指先互相觸碰。 \n" +
                        "步驟3：吸氣，雙手和起，慢慢的往上移動到背部。 \n" +
                        "步驟4：停留3-5個呼吸後再慢慢放下。\n",
                "Step1. 吸氣，腹部收緊，一節節延展脊柱，背部下凹，緩滿而有控制地使骨盆往下轉動，頸部保持放鬆。 " +
                        "Step2. 緩緩吐氣，背脊往上，想像貓咪拱背的姿態，彎成弓形。盡量使下巴貼到身體，感受頸部肌肉緩緩拉伸。 " +
                        "Step3. 重複步驟1與2，也就是「牛式」與「貓式」，以平緩的步驟規律交替約5-10次，使背部與脊椎確實地舒展開。",
                "Step1:俯臥在瑜伽墊上，手肘彎曲夾緊，手掌朝下置於身側。 " +
                        "Step2:背、腹肌出力抬起上半身，下巴抬起，後仰使脖子延伸，停留3～8個呼吸。 " +
                        "Step3:過程中注意不要聳肩或縮頸，可重複步驟一、二3～5回。",
                "Step1: 雙膝微微張開，跪在瑜伽墊上做準備。Step2: 雙手向前伸展，上身貼近大腿，額頭碰觸瑜伽墊，伸展髖部、背部和肩膀。",
                "Step1: 呈俯臥姿，手臂在身側，掌心貼地，雙腿併攏往後伸直。 " +
                        "Step2: 左腳保持打直貼地，吸氣，右腿向後抬高，吐氣，右腿放回地上；接著換左腿重複同樣動作。" +
                        "Step3: 背部、臀部、腿後腱收縮以抬起雙腿，直到身體重量落在下巴；收緊臀肌但稍微內旋大腿，收縮恥骨尾骨肌將尾骨前推，並藉此抬高骨盆，保持雙腳併攏伸直。\n" +
                        "停留，自然地呼吸。" +
                        "Step4: 吸氣，頭、胸、雙手和雙腿同時離地，身體盡量抬高且前後拉長。將雙腿抬起進入髖伸動作，大腿有力併攏不外旋，收縮恥尾肌穩定骨盆、收緊腹部保護下背。將肩胛骨往內拉向脊椎、往下拉向坐骨，這有助於開展前胸，肋骨遠離地板，但避免聳肩或者壓迫頸椎，僅餘腹部著地。動作停留，自然地呼吸，接著吐氣，慢慢趴回地上，呈俯臥姿放鬆。\n",
                "採取趴姿，臉朝下趴在地墊上，兩腿伸直，兩隻手臂都朝前伸展，手心朝地面。 臀部與下背收緊，同時把兩手臂與兩腳抬離地面15公分，等於從手臂到腳會像個U字型，停留在這個姿勢幾秒，想像自己像超人在飛行",
                "腰腹部為軸心，讓肩膀帶動上半身向後、向下彎曲時核心收緊，臀部夾緊",
                "step1 盤腿坐下來，左手往上抬高，並用右手抓住左手手腕。Step2 一邊吐氣，一邊將身體往側邊傾倒。 Step3 維持動作時，須留意臀部不能離開地面。另一隻手也可以以相同方式進行。",
                "(1) 採坐姿，屈膝散盤坐姿，身體向後弓起，臀部盡力而為、坐在瑜珈墊上。\n" +
                        "(2) 邊吐氣上半身邊往前、趴在瑜珈墊上，以及額頭慢慢輕碰地板，手掌平放在瑜珈墊、身體慢慢往前伸展，讓全身完全放鬆下來。\n" +
                        "(3) 身體往前至極限、停留時，感覺大腿內側有微酸感，下背延伸，並保持自然呼吸吐氣。\n",
                "step1 兩腳打開與肩同寬，接著後腳膝蓋著地，前後拉開到覺得舒服的程度。Step2 雙手併攏高舉至頭頂上，並將頭部朝向正前方。將上半身向前傾倒，可提升伸展強度。",
                "膝蓋彎曲。右腿往左打開髖關節。頸部向右轉。視線從腋下往天花板方向看",
                "step1 雙手伸直離開地面，雙腳伸直併攏離開地面，使身體呈一個V字型預備。Step2 雙手、雙腿向身體收回，重複來回",
                "平躺於地上，雙腿彎曲抬起懸空，小腿與地面平行",
                "1、單手撐住身體，肘部和肩膀在一條直線上。 2、腹部、腰部持續保持緊張。 3、臀部儘可能收緊。 4、保持動作至計劃的時間",
                "以雙手撐著雙膝蓋跪地開始，手臂保持在肩膀正下方。 為了讓身體獲得更大的穩定性，請將雙腳打開至比髖部還開的距離。 收緊腹肌，股四頭，臀肌保持穩定，注意從腳後跟、腰部、頭頂保持一條直線，視線向下，不用刻意抬頭",
                "躺在地墊或床上，雙手平放於身體兩側，膝蓋彎曲，兩腳腳掌踩在地墊或床上。 收緊臀部，由臀部發力抬高髖部，不要拱背，直到肩膀到膝蓋呈現一直線；接著放下髖部；重複以上動作將髖部上下活動數次",
                "步驟1：雙腳打開與肩同寬。 步驟2：挺起胸部，一隻腳朝前方跨出一大步，後腳膝蓋向下沉，停留1秒然後用前腳及臀部將身體推回原來位置。",
                "Step1.以低弓箭步開始，右腿在前彎曲成90度，左腿在後。 Step2.右腳移至左手後方，身體向地板下壓直到小腿與墊子邊緣平行。 Step3.如果有需要，將左腿往後移一點，騰出一些空間讓腳放鬆，確定左腳尖、腳跟、腳踝、膝蓋和臀部呈同一直線。 Step4.軀幹向前下壓伸展，肩膀位置超過臀部，保持呼吸，維持一分鐘。",
                "雙膝跪地後腿朝兩側分開至大腿內側感到緊繃，此時膝蓋朝向正前方，上半身往前趴，可以手肘撐地或打直手臂以手掌撐地，並保持骨盆、脊椎與頸部三處成一線。",
                "Step1.仰躺在瑜珈墊上，膝蓋彎曲呈 90 度，接著慢慢抬起小腿，使小腿與地面平行。 Step2.手輕放在耳朵兩側，或輕扶著後腦勺，千萬不要用手將頭硬往前拉。 " +
                        "Step3.上半身微微起來後向右扭轉，同時抬起右膝、以左肘盡量靠近右膝，並伸直左腿。回正穩定後換邊進行，左右完成算 1 下。 " +
                        "Step4.過程中須保持正常呼吸，注意腿部皆為懸空，要用核心肌群的力量撐住。 Step5.一組可依能力做 10～20 下，一次做 2～3 組，組間間隔一分鐘。",
                "保持脊椎穩定、骨盆維持在中立位置，將重心放在臀部、以臀部施力，另外腳跟與臀部維持同一線上，做的時後雙腿腳跟相貼。",
                "Step1. 側躺，雙腿伸直並上下相疊，一側肘部貼地，另一側手掌撐地，切記緊縮臀部和腹部。Step 2. 上方的腿先抬高，腳掌保持與地面平行，如此重複練習20∼30下； 呼氣時抬高腿部，吸氣時放低。Step 3. 換邊練習。",
                "Step1：坐在瑜伽墊上，雙腳盤開，腳掌對齊。 Step2：背部打直，雙手抓住腳掌。 Step3：：將身體慢慢往前傾，停留5～10個呼吸後休息。",
                "Step1：動作解說: 雙腳選擇併攏或與骨盆同寬。 \n" +
                        "Step2：前彎時保持膝蓋微彎不鎖死，大腿前側用力。 \n" +
                        "Step3：身體慢慢向前，盡量不拱背，手掌貼地。（ 若無法貼地可用瑜珈磚或抓腳後跟）\n" +
                        "Step4：若可以的話，慢慢讓腹部向膝蓋收，頸部放鬆。 \n" +
                        "Step5：保持大腿用力，再慢慢帶起身體，回到原位。\n",
                "Step1：一開始，雙腳打開與肩同寬站立，腳尖朝前(或微微往外)  " +
                        "Step2：吸氣時，核心用力，臀部像坐椅子一樣往下坐，同時手臂向前，在胸口位置闔起。(利用臀部啟動動作，而不是膝蓋！) " +
                        "Step3：上身維持抬頭挺胸，注意膝蓋不要內倒，重心平均在雙腳間。" +
                        "Step4：繼續蹲低，直到大腿與地面平行(不同程度的練習者可先從1/4蹲開始)，注意膝蓋持續朝腳尖方向移動 " +
                        "Step5：最後，吐氣時，腳跟站穩回到站姿。",
                "Step1：坐下後雙腿合併膝蓋立起，上半身向後倒。使用手肘接觸地面支撐上半身。" +
                        "Step2：維持雙膝彎曲的狀態，抬起雙腳，用腳尖畫出無限符號「∞」。注意不要讓呼吸停止，維持一定的速度。重複3次即可。",
                "Step1：坐下後雙腿合併膝蓋立起，上半身向後倒。使用手肘接觸地面支撐上半身。" +
                "Step2：維持雙膝彎曲的狀態，抬起雙腳，用腳尖畫出無限符號「∞」。注意不要讓呼吸停止，維持一定的速度。重複3次即可。",
                "Step1：坐下後雙腿合併膝蓋立起，上半身向後倒。使用手肘接觸地面支撐上半身。" +
                        "Step2：維持雙膝彎曲的狀態，抬起雙腳，用腳尖畫出無限符號「∞」。注意不要讓呼吸停止，維持一定的速度。重複3次即可。",
                "Step1：坐下後雙腿合併膝蓋立起，上半身向後倒。使用手肘接觸地面支撐上半身。" +
                        "Step2：維持雙膝彎曲的狀態，抬起雙腳，用腳尖畫出無限符號「∞」。注意不要讓呼吸停止，維持一定的速度。重複3次即可。",
                "Step1：坐下後雙腿合併膝蓋立起，上半身向後倒。使用手肘接觸地面支撐上半身。" +
                        "Step2：維持雙膝彎曲的狀態，抬起雙腳，用腳尖畫出無限符號「∞」。注意不要讓呼吸停止，維持一定的速度。重複3次即可。",
                "Step1：從坐姿開始，屈膝雙腳掌踩地，把手放大腿下方 " +
                        "Step2：吸氣時重心往後，吐氣時把雙腳小腿抬至與地面平行 " +
                        "Step3：找到平衡後伸直雙腿，並將手往前延伸"};
    }
    public void CreativePokedex(){
        if(PokedexAll.equals("All")){
            this.PokedexContent = new int[]{1,2,3,4,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36};
        }else if(PokedexAll.equals("Belly")){
            this.PokedexContent = new int[]{8,9,10,11,18,19,20,21,25,32,33,34,35,36};
        }else if (PokedexAll.equals("Oxleg")){
            this.PokedexContent = new int[]{9,15,21,23,24,26,27,28,29,30};
        }else if (PokedexAll.equals("Pelvic")){
            this.PokedexContent = new int[]{15,16,9,17,18,19,20,21,22,15,23,9};
        }else if (PokedexAll.equals("Saddlebag")){
            this.PokedexContent = new int[]{15,14,24,18,25,16,26,17,21,28,23,9};
        }else if(PokedexAll.equals("Slouch")){
            this.PokedexContent = new int[]{1,2,4,7,8,9,10,11,12,13,14};
        }else if(PokedexAll.equals("Hand")){ //手
            this.PokedexContent = new int[]{1,2,3,4,7,10,19,20};
        }else if(PokedexAll.equals("Back")){ //背
            this.PokedexContent = new int[]{7,8,10,11,12,20};
        }else if(PokedexAll.equals("Abdomen")){ //腹
            this.PokedexContent = new int[]{8,9,10,15,16,17,18,19,20,21,24,25,31,32,33,34,35,36};
        }else if(PokedexAll.equals("Buttocks")){ //臀
            this.PokedexContent = new int[]{4,7,8,9,10,11,12,13,14,15,16,20,21,22,24,30,36};
        }else{ //腿
            this.PokedexContent = new int[]{10,22,23,24,26,27,28,29,30,31,32,33,34,35,36};
        }
    }
}
