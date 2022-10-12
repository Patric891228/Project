/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tw.scu.edu.graduationprojrct.java.posedetector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.google.common.primitives.Ints;
import com.google.mlkit.vision.common.PointF3D;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseLandmark;

import java.util.List;
import java.util.Locale;

import tw.scu.edu.graduationprojrct.GraphicOverlay;
import tw.scu.edu.graduationprojrct.GraphicOverlay.Graphic;
import tw.scu.edu.graduationprojrct.tflite.YoloV5Classifier;

import static java.lang.Math.atan2;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Draw the detected pose in preview.
 */
public class PoseGraphic extends Graphic {

    private static final float DOT_RADIUS = 8.0f;
    private static final float IN_FRAME_LIKELIHOOD_TEXT_SIZE = 30.0f;
    private static final float STROKE_WIDTH = 10.0f;
    private static final float POSE_CLASSIFICATION_TEXT_SIZE = 60.0f;

    private final Pose pose;
    private final boolean showInFrameLikelihood;
    private final boolean visualizeZ;
    private final boolean rescaleZForVisualization;
    private float zMin = Float.MAX_VALUE;
    private float zMax = Float.MIN_VALUE;

    private final List<String> poseClassification;
    private final Paint classificationTextPaint;
    private final Paint leftPaint;
    private final Paint rightPaint;
    private final Paint whitePaint;

    PoseGraphic(
            GraphicOverlay overlay,
            Pose pose,
            boolean showInFrameLikelihood,
            boolean visualizeZ,
            boolean rescaleZForVisualization,
            List<String> poseClassification) {
        super(overlay);
        this.pose = pose;
        this.showInFrameLikelihood = showInFrameLikelihood;
        this.visualizeZ = visualizeZ;
        this.rescaleZForVisualization = rescaleZForVisualization;

        this.poseClassification = poseClassification;
        classificationTextPaint = new Paint();
        classificationTextPaint.setColor(Color.WHITE);
        classificationTextPaint.setTextSize(POSE_CLASSIFICATION_TEXT_SIZE);
        classificationTextPaint.setShadowLayer(5.0f, 0f, 0f, Color.BLACK);

        whitePaint = new Paint();
        whitePaint.setStrokeWidth(STROKE_WIDTH);
        whitePaint.setColor(Color.WHITE);
        whitePaint.setTextSize(IN_FRAME_LIKELIHOOD_TEXT_SIZE);
        leftPaint = new Paint();
        leftPaint.setStrokeWidth(STROKE_WIDTH);
        leftPaint.setColor(Color.GREEN);
        rightPaint = new Paint();
        rightPaint.setStrokeWidth(STROKE_WIDTH);
        rightPaint.setColor(Color.YELLOW);
    }

    @Override
    public void draw(Canvas canvas) {
        List<PoseLandmark> landmarks = pose.getAllPoseLandmarks();
        if (landmarks.isEmpty()) {
            return;
        }

        // Draw pose classification text.
        float classificationX = POSE_CLASSIFICATION_TEXT_SIZE * 0.5f;
        for (int i = 0; i < poseClassification.size(); i++) {
            float classificationY = (canvas.getHeight() - POSE_CLASSIFICATION_TEXT_SIZE * 1.5f
                    * (poseClassification.size() - i));
            canvas.drawText(
                    poseClassification.get(i),
                    classificationX,
                    classificationY,
                    classificationTextPaint);
        }

        // Draw all the points
        for (PoseLandmark landmark : landmarks) {
            drawPoint(canvas, landmark, whitePaint);
            if (visualizeZ && rescaleZForVisualization) {
                zMin = min(zMin, landmark.getPosition3D().getZ());
                zMax = max(zMax, landmark.getPosition3D().getZ());
            }
        }

        PoseLandmark nose = pose.getPoseLandmark(PoseLandmark.NOSE);
        PoseLandmark lefyEyeInner = pose.getPoseLandmark(PoseLandmark.LEFT_EYE_INNER);
        PoseLandmark lefyEye = pose.getPoseLandmark(PoseLandmark.LEFT_EYE);
        PoseLandmark leftEyeOuter = pose.getPoseLandmark(PoseLandmark.LEFT_EYE_OUTER);
        PoseLandmark rightEyeInner = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE_INNER);
        PoseLandmark rightEye = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE);
        PoseLandmark rightEyeOuter = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE_OUTER);
        PoseLandmark leftEar = pose.getPoseLandmark(PoseLandmark.LEFT_EAR);
        PoseLandmark rightEar = pose.getPoseLandmark(PoseLandmark.RIGHT_EAR);
        PoseLandmark leftMouth = pose.getPoseLandmark(PoseLandmark.LEFT_MOUTH);
        PoseLandmark rightMouth = pose.getPoseLandmark(PoseLandmark.RIGHT_MOUTH);

        PoseLandmark leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER);
        PoseLandmark rightShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER);
        PoseLandmark leftElbow = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW);
        PoseLandmark rightElbow = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW);
        PoseLandmark leftWrist = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST);
        PoseLandmark rightWrist = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST);
        PoseLandmark leftHip = pose.getPoseLandmark(PoseLandmark.LEFT_HIP);
        PoseLandmark rightHip = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP);
        PoseLandmark leftKnee = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE);
        PoseLandmark rightKnee = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE);
        PoseLandmark leftAnkle = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE);
        PoseLandmark rightAnkle = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE);

        PoseLandmark leftPinky = pose.getPoseLandmark(PoseLandmark.LEFT_PINKY);
        PoseLandmark rightPinky = pose.getPoseLandmark(PoseLandmark.RIGHT_PINKY);
        PoseLandmark leftIndex = pose.getPoseLandmark(PoseLandmark.LEFT_INDEX);
        PoseLandmark rightIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_INDEX);
        PoseLandmark leftThumb = pose.getPoseLandmark(PoseLandmark.LEFT_THUMB);
        PoseLandmark rightThumb = pose.getPoseLandmark(PoseLandmark.RIGHT_THUMB);
        PoseLandmark leftHeel = pose.getPoseLandmark(PoseLandmark.LEFT_HEEL);
        PoseLandmark rightHeel = pose.getPoseLandmark(PoseLandmark.RIGHT_HEEL);
        PoseLandmark leftFootIndex = pose.getPoseLandmark(PoseLandmark.LEFT_FOOT_INDEX);
        PoseLandmark rightFootIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_FOOT_INDEX);

        // Face
        drawLine(canvas, nose, lefyEyeInner, whitePaint);
        drawLine(canvas, lefyEyeInner, lefyEye, whitePaint);
        drawLine(canvas, lefyEye, leftEyeOuter, whitePaint);
        drawLine(canvas, leftEyeOuter, leftEar, whitePaint);
        drawLine(canvas, nose, rightEyeInner, whitePaint);
        drawLine(canvas, rightEyeInner, rightEye, whitePaint);
        drawLine(canvas, rightEye, rightEyeOuter, whitePaint);
        drawLine(canvas, rightEyeOuter, rightEar, whitePaint);
        drawLine(canvas, leftMouth, rightMouth, whitePaint);

        drawLine(canvas, leftShoulder, rightShoulder, whitePaint);
        drawLine(canvas, leftHip, rightHip, whitePaint);

        // Left body
        drawLine(canvas, leftShoulder, leftElbow, leftPaint);
        drawLine(canvas, leftElbow, leftWrist, leftPaint);
        drawLine(canvas, leftShoulder, leftHip, leftPaint);
        drawLine(canvas, leftHip, leftKnee, leftPaint);
        drawLine(canvas, leftKnee, leftAnkle, leftPaint);
        drawLine(canvas, leftWrist, leftThumb, leftPaint);
        drawLine(canvas, leftWrist, leftPinky, leftPaint);
        drawLine(canvas, leftWrist, leftIndex, leftPaint);
        drawLine(canvas, leftIndex, leftPinky, leftPaint);
        drawLine(canvas, leftAnkle, leftHeel, leftPaint);
        drawLine(canvas, leftHeel, leftFootIndex, leftPaint);

        // Right body
        drawLine(canvas, rightShoulder, rightElbow, rightPaint);
        drawLine(canvas, rightElbow, rightWrist, rightPaint);
        drawLine(canvas, rightShoulder, rightHip, rightPaint);
        drawLine(canvas, rightHip, rightKnee, rightPaint);
        drawLine(canvas, rightKnee, rightAnkle, rightPaint);
        drawLine(canvas, rightWrist, rightThumb, rightPaint);
        drawLine(canvas, rightWrist, rightPinky, rightPaint);
        drawLine(canvas, rightWrist, rightIndex, rightPaint);
        drawLine(canvas, rightIndex, rightPinky, rightPaint);
        drawLine(canvas, rightAnkle, rightHeel, rightPaint);
        drawLine(canvas, rightHeel, rightFootIndex, rightPaint);

        double test = getAngle(leftElbow,
                leftShoulder,
                leftWrist);
        Log.d("test", "angle" + test);
//        try {
//            Yolo model = Yolo.newInstance(getApplicationContext());
//
//            // Creates inputs for reference.
//            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{4, 3, 640, 640}, DataType.FLOAT32);
//            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 640 * 640 * 3);
//            byteBuffer.order(ByteOrder.nativeOrder());
//
//            int[] intValues = new int[640 * 640];
//            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
//            int pixel = 0;
//            for (int i = 0; i < 640; i++) {
//                for (int j = 0; i < 640; j++) {
//                    int val = intValues[pixel++];
//                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 1));
//                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 1));
//                    byteBuffer.putFloat((val & 0xFF) * (1.f / 1));
//                }
//            }
//
//            inputFeature0.loadBuffer(byteBuffer);
//
//            // Runs model inference and gets result.
//            Yolo.Outputs outputs = model.process(inputFeature0);
//            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
//            TensorBuffer outputFeature1 = outputs.getOutputFeature1AsTensorBuffer();
//            TensorBuffer outputFeature2 = outputs.getOutputFeature2AsTensorBuffer();
//
//            float [] confidence0=outputFeature0.getFloatArray();
//            float [] confidence1=outputFeature1.getFloatArray();
//            float [] confidence2=outputFeature2.getFloatArray();
//            int maxPos = 0;
//            float maxConfidence = 0;
//            for (int i = 0; i < confidence0.length; i++) {
//                if (confidence0[i] > maxConfidence) {
//                    maxConfidence = confidence0[i];
//                    maxPos = i;
//                }
//            }
//            String[] classes = {"Apple", "Banana", "Orange"};
//            result.setText(classes[maxPos]);
//
//            // Releases model resources if no longer used.
//            model.close();
//        } catch (IOException e) {
//            // TODO Handle the exception
//        }
        String posture=YoloV5Classifier.result;
        String result = determine(posture);
//        String result = determine();
        canvas.drawText(result, POSE_CLASSIFICATION_TEXT_SIZE * 1.0f, POSE_CLASSIFICATION_TEXT_SIZE * 5.5f, classificationTextPaint);
        // Draw inFrameLikelihood for all points
        if (showInFrameLikelihood) {
            for (PoseLandmark landmark : landmarks) {
                canvas.drawText(
                        String.format(Locale.US, "%.2f", landmark.getInFrameLikelihood()),
                        translateX(landmark.getPosition().x),
                        translateY(landmark.getPosition().y),
                        whitePaint);
            }
        }
    }
    public void TEST(){
        Log.d("測試訊息1","呦齁齁齁齁齁齁");
    }
    String determine(String posture) {
        String result = "偵測中";
        PoseLandmark nose = pose.getPoseLandmark(PoseLandmark.NOSE);

        PoseLandmark leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER);
        PoseLandmark rightShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER);
        PoseLandmark leftElbow = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW);
        PoseLandmark rightElbow = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW);
        PoseLandmark leftWrist = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST);
        PoseLandmark rightWrist = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST);
        PoseLandmark leftHip = pose.getPoseLandmark(PoseLandmark.LEFT_HIP);
        PoseLandmark rightHip = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP);
        PoseLandmark leftKnee = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE);
        PoseLandmark rightKnee = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE);
        PoseLandmark leftAnkle = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE);
        PoseLandmark rightAnkle = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE);

        if (posture == "Upper Trapezius Stretch") {
            double utsl1 = getAngle(leftElbow, leftShoulder, leftWrist);//斜方肌拉伸左
            double utsl2 = getAngle(leftHip, leftShoulder, leftElbow);//斜方肌拉伸左
            double utsr1 = getAngle(rightElbow, rightShoulder, rightWrist);//斜方肌拉伸右
            double utsr2 = getAngle(rightHip, rightShoulder, rightElbow);//斜方肌拉伸右
            if ((utsl1 < 70 && utsl2 > 115 && utsl2 < 125) || (utsr1 < 70 && utsr2 > 115 && utsr2 < 125)) {
                result = "斜方肌拉伸";
            }
        } else if (posture == "Deltoid Muscle Stretch") {
            double dms1 = getAngle(leftElbow, leftShoulder, rightShoulder);//三角肌拉伸左
            double dms2 = getAngle(rightElbow, rightShoulder, leftShoulder);//三角肌拉伸右
            if (dms1 < 30 && dms2 < 30) {
                result = "三角肌拉伸";
            }
        } else if (posture == "Triceps Stretch") {
            double tsl = getAngle(leftShoulder, leftElbow, rightElbow);//肱三頭肌拉伸左
            double tsr = getAngle(rightShoulder, rightElbow, leftElbow);//肱三頭肌拉伸右
            if ((tsl < 100 && tsl > 80) || (tsr < 100 && tsr > 80)) {
                result = "肱三頭肌拉伸";
            }
        } else if (posture == "Reverse Prayer Pose") {
            double rpp1 = getAngle(leftHip, leftShoulder, leftElbow);//反向祈禱式
            double rpp2 = getAngle(rightHip, rightShoulder, rightElbow);//反向祈禱式
            if (rpp1 < 30 && rpp2 < 30) {
                result = "反向祈禱式";
            }
        } else if (posture == "Cat Cow Pose") {
            double ccpl = getAngle(leftHip, leftShoulder, nose);//貓牛式左
            double ccpr = getAngle(rightHip, rightShoulder, nose);//貓牛式右
            if (ccpl < 150 || ccpr < 150) {
                result = "貓牛式";
            }
        } else if (posture == "Cobra Pose") {
            double copl = getAngle(leftKnee, leftHip, leftShoulder);//眼鏡蛇式左
            double copr = getAngle(rightKnee, rightHip, rightShoulder);//眼鏡蛇式右
            if (copl > 60 || copr > 60) {
                result = "眼鏡蛇式";
            }
        } else if (posture == "Child Pose") {
            double chpl = getAngle(leftHip, leftShoulder, leftElbow);//嬰兒式左
            double chpr = getAngle(rightHip, rightShoulder, rightElbow);//嬰兒式右
            if (chpl > 160 || chpr > 160) {
                result = "嬰兒式";
            }
        } else if (posture == "Locust Pose") {
            double lpl = getAngle(leftWrist, leftShoulder, leftHip);//蝗蟲式左
            double lpr = getAngle(rightWrist, rightShoulder, rightHip);//蝗蟲式右
            if (lpl < 20 || lpr < 20) {
                result = "蝗蟲式";
            }
        } else if (posture == "Superman Pose") {
            double supl = getAngle(leftKnee, leftHip, leftShoulder);//超人式左
            double supr = getAngle(rightKnee, rightHip, rightShoulder);//超人式右
            if (supl < 170 || supr < 170) {
                result = "超人式";
            }
        } else if (posture == "Camel Pose") {
            double cpl1 = getAngle(leftWrist, leftShoulder, leftHip);//駱駝式左
            double cpl2 = getAngle(leftAnkle, leftKnee, leftHip);
            double cpr1 = getAngle(rightWrist, rightShoulder, rightHip);//駱駝式右
            double cpr2 = getAngle(rightAnkle, rightKnee, rightHip);
            if ((cpl1 < 80 && (cpl2 > 80 || cpl2 < 100)) || (cpr1 < 80 && (cpr2 > 80 || cpr2 < 100))) {
                result = "駱駝式";
            }
        } else if (posture == "Seated Side Bend") {
            double ssbl = getAngle(leftWrist, leftShoulder, leftHip);//盤腿側腰伸展左
            double ssbr = getAngle(rightWrist, rightShoulder, rightHip);//盤腿側腰伸展右
            if (ssbl < 100 || ssbr < 100) {
                result = "盤腿側腰伸展";
            }
        } else if (posture == "Cross Leg Forward Bend") {
            double clfl = getAngle(leftHip, leftShoulder, leftWrist);//盤腿前彎式左
            double clfr = getAngle(rightHip, rightShoulder, rightWrist);//盤腿前彎式右
            if (clfl > 150 || clfr > 150) {
                result = "盤腿前彎式";
            }
        } else if (posture == "Iliopsoas Muscle Stretch") {
            double lmsl1 = getAngle(leftHip, leftKnee, leftWrist);//髂腰肌伸展左
            double lmsl2 = getAngle(rightHip, rightKnee, rightAnkle);
            double lmsr1 = getAngle(rightHip, rightKnee, rightWrist);//髂腰肌伸展右
            double lmsr2 = getAngle(leftHip, leftKnee, leftAnkle);
            if (((lmsl1 > 85 && lmsl1 < 95) && lmsl2 > 120) || ((lmsr1 > 85 && lmsr1 < 95) && lmsr2 > 120)) {
                result = "髂腰肌伸展";
            }
        } else if (posture == "Downward-Facing Dog Pose") {
            double dfdpl = getAngle(leftAnkle, leftHip, leftWrist);//下犬式左
            double dfdpr = getAngle(rightAnkle, rightHip, rightWrist);//下犬式右
            if ((dfdpl > 75 && dfdpl < 85) || (dfdpr > 75 && dfdpr < 85)) {
                result = "下犬式";
            }
        } else if (posture == "Bent Knee Crunch") {
            double bkcl = getAngle(leftHip, leftKnee, leftAnkle);//屈膝捲腹左
            double bkcr = getAngle(rightHip, rightKnee, rightAnkle);//屈膝捲腹右
            if ((bkcl > 85 && bkcl < 95) || (bkcr > 85 && bkcr < 95)) {
                result = "屈膝捲腹";
            }
        } else if (posture == "Raise Leg Crunch") {
            double rlcl = getAngle(leftHip, leftKnee, leftAnkle);//抬腿捲腹左
            double rlcr = getAngle(rightHip, rightKnee, rightAnkle);//抬腿捲腹右
            if ((rlcl > 85 && rlcl < 95) || (rlcr > 85 && rlcr < 95)) {
                result = "抬腿捲腹";
            }
        } else if (posture == "Side Plank") {
            double sidepl = getAngle(leftAnkle, leftShoulder, leftElbow);//側平板左
            double sidepr = getAngle(rightAnkle, rightShoulder, rightElbow);//側平板右
            if ((sidepl > 85 && sidepl < 95) || (sidepr > 85 && sidepr < 95)) {
                result = "側平板";
            }
        } else if (posture == "Plank") {
            double pl = getAngle(leftShoulder, leftHip, leftKnee);//平板撐左
            double pr = getAngle(rightShoulder, rightHip, rightKnee);//平板撐右
            if ((pl > 170) || (pr > 170)) {
                result = "平板撐";
            }
        } else if (posture == "Bridge") {
            double b11l = getAngle(leftAnkle, leftKnee, leftHip);//臀橋左
            double b11r = getAngle(rightAnkle, rightKnee, rightHip);//臀橋右
            if ((b11l > 25 && b11l < 35) || (b11r > 85 && b11r < 95) || (b11l > 55 && b11l < 65) || (b11r > 55 && b11r < 65)) {
                result = "臀橋";
            }
        } else if (posture == "Lunge") {
            double l11l = getAngle(leftKnee, leftHip, rightKnee);//弓步蹲左
            double l11r = getAngle(rightKnee, rightHip, leftKnee);//弓步蹲右
            double l21 = getAngle(leftAnkle, leftKnee, leftHip);
            double l22 = getAngle(rightAnkle, rightKnee, rightHip);
            if ((l11l > 55 && l11l < 65) || (l11r > 55 && l11r < 65) || (l21 > 85 && l21 < 95 && l22 > 85 && l22 < 95)) {
                result = "弓步蹲";
            }
        } else if (posture == "Pigeon Pose") {
            double ppl = getAngle(leftKnee, leftShoulder, leftWrist);//鴿子式
            double ppr = getAngle(rightKnee, rightShoulder, rightWrist);
            if ((ppl > 85 && ppl < 95) || (ppr > 85 && ppr < 95)) {
                result = "鴿子式";
            }
        } else if (posture == "Frog Pose") {
            double fp1l = getAngle(leftKnee, leftHip, leftAnkle);//青蛙式左
            double fp2l = getAngle(leftShoulder, leftElbow, leftWrist);
            double fp1r = getAngle(rightKnee, rightHip, rightAnkle);//青蛙式右
            double fp2r = getAngle(rightShoulder, rightElbow, rightWrist);
            if ((fp1l > 115 && fp1l < 135 && fp2l > 115 && fp2l < 135) || (fp1r > 115 && fp1r < 135 && fp2r > 115 && fp2r < 135)) {
                result = "青蛙式";
            }
        } else if (posture == "Bicycle Crunch") {
            double bcl = getAngle(nose, leftShoulder, leftHip);//交叉捲腹左
            double bcr = getAngle(nose, rightShoulder, rightHip);//交叉捲腹右
            if ((bcl > 150 && bcl < 160) || (bcr > 150 && bcr < 160)) {
                result = "交叉捲腹";
            }
        } else if (posture == "Clamshell Exercise") {
            double cel = getAngle(leftHip, leftKnee, rightKnee);//蚌式開合左
            double cer = getAngle(rightHip, rightKnee, leftKnee);//蚌式開合右
            if ((cel > 25 && cel < 35) || (cer > 25 && cer < 35) || (cel > 40 && cel < 50) || (cer > 40 && cer < 50)) {
                result = "蚌式開合";
            }
        } else if (posture == "Inner Thigh Lift") {
            double itl1l = getAngle(leftHip, leftKnee, leftAnkle);//側臥內抬腿左
            double itl2l = getAngle(leftHip, leftKnee, leftWrist);
            double itl1r = getAngle(rightHip, rightKnee, rightAnkle);//側臥內抬腿右
            double itl2r = getAngle(rightHip, rightKnee, rightWrist);
            if ((itl1l > 40 && itl1l < 50) || (itl1r > 40 && itl1r < 50) || (itl2l > 170) || (itl2r > 170)) {
                result = "側臥內抬腿";
            }
        } else if (posture == "Bound Angle Pose") {
            double bap1 = getAngle(leftKnee, leftWrist, leftShoulder);//束角式
            double bap2 = getAngle(rightKnee, rightWrist, rightShoulder);
            if ((bap1 > 85 && bap1 < 95) || (bap2 > 85 && bap2 < 95)) {
                result = "束角式";
            }
        } else if (posture == "Standing Forward Bend") {
            double sfbl = getAngle(leftElbow, leftShoulder, leftHip);//立姿前彎式左
            double sfbr = getAngle(rightElbow, rightShoulder, rightHip);//立姿前彎式右
            if ((sfbl < 45) || (sfbr < 45)) {
                result = "立姿前彎式";
            }
        } else if (posture == "Squat") {
            double s1l = getAngle(leftShoulder, leftHip, leftKnee);//膝蓋夾書深蹲左
            double s2l = getAngle(leftHip, leftKnee, leftAnkle);
            double s1r = getAngle(rightShoulder, rightHip, rightKnee);//膝蓋夾書深蹲右
            double s2r = getAngle(rightHip, rightKnee, rightAnkle);
            if ((s1l > 85 && s1l < 95 && s2l > 115 && s2l < 125) || (s1r > 85 && s1r < 95 && s2r > 115 && s2r < 125)) {
                result = "膝蓋夾書深蹲";
            }
        } else if (posture == "LegRaises0") {
            double llrl = getAngle(leftShoulder, leftHip, leftAnkle);//仰臥抬腿左
            double llrr = getAngle(rightShoulder, rightHip, rightAnkle);//仰臥抬腿右
            if ((llrl < 5) || (llrr < 5)) {
                result = "仰臥抬腿0";
            }
        } else if (posture == "LegRaises10") {
            double llrl = getAngle(leftShoulder, leftHip, leftAnkle);//仰臥抬腿左
            double llrr = getAngle(rightShoulder, rightHip, rightAnkle);//仰臥抬腿右
            if ((llrl < 15 && llrl > 5) || (llrr < 15 && llrr > 5)) {
                result = "仰臥抬腿10";
            }
        } else if (posture == "LegRaises30") {
            double llrl = getAngle(leftShoulder, leftHip, leftAnkle);//仰臥抬腿左
            double llrr = getAngle(rightShoulder, rightHip, rightAnkle);//仰臥抬腿右
            if ((llrl < 65 && llrl > 55) || (llrr < 65 && llrr > 55)) {
                result = "仰臥抬腿60";
            }
        } else if (posture == "LegRaises60") {
            double llrl = getAngle(leftShoulder, leftHip, leftAnkle);//仰臥抬腿左
            double llrr = getAngle(rightShoulder, rightHip, rightAnkle);//仰臥抬腿右
            if ((llrl < 65 && llrl > 55) || (llrr < 65 && llrr > 55)) {
                result = "仰臥抬腿60";
            }
        } else if (posture == "LegRaises90") {
            double llrl = getAngle(leftShoulder, leftHip, leftAnkle);//仰臥抬腿左
            double llrr = getAngle(rightShoulder, rightHip, rightAnkle);//仰臥抬腿右
            if ((llrl < 95 && llrl > 85) || (llrr < 95 && llrr > 85)) {
                result = "仰臥抬腿90";
            }
        } else if (posture == " Boat Pose") {
            double bpl = getAngle(leftShoulder, leftHip, leftAnkle);//船式左
            double bpr = getAngle(rightShoulder, rightHip, rightAnkle);//船式右
            if ((bpl > 70 && bpl < 80) || (bpr > 70 && bpr < 80)) {
                result = "船式";
            }
        }
//        if ((utsl1 < 70 && utsl2>115 && utsl2 < 125) || (utsr1 < 70 && utsr2 > 115 && utsr2 < 125)) {
//            result = "斜方肌拉伸";
//        } else if (dms1 < 30 && dms2 < 30) {
//            result = "三角肌拉伸";
//        } else if ((tsl < 100 && tsl > 80) || (tsr < 100 && tsr > 80)) {
//            result = "肱三頭肌拉伸";
//        } else if (rpp1 < 30 && rpp2 < 30) {
//            result = "反向祈禱式";
//        } else if (ccpl < 150 || ccpr < 150) {
//            result = "貓牛式";
//        } else if (copl > 60 || copr > 60) {
//            result = "眼鏡蛇式";
//        } else if (chpl > 160 || chpr > 160) {
//            result = "嬰兒式";
//        } else if (lpl < 20 || lpr < 20) {
//            result = "蝗蟲式";
//        } else if (supl < 170 || supr < 170) {
//            result = "超人式";
//        } else if ((cpl1 < 80 && (cpl2 > 80 || cpl2 < 100)) || (cpr1 < 80 && (cpr2 > 80 || cpr2 < 100))) {
//            result = "駱駝式";
//        } else if (ssbl < 100 || ssbr < 100) {
//            result = "盤腿側腰伸展";
//        } else if (clfl > 150 || clfr > 150) {
//            result = "盤腿前彎式";
//        } else if (((lmsl1 > 85 && lmsl1 < 95) && lmsl2 > 120) || ((lmsr1 > 85 && lmsr1 < 95) && lmsr2 > 120)) {
//            result = "髂腰肌伸展";
//        } else if ((dfdpl > 75 && dfdpl < 85) || (dfdpr > 75 && dfdpr < 85)) {
//            result = "下犬式";
//        } else if ((bkcl > 85 && bkcl < 95) || (bkcr > 85 && bkcr < 95)) {
//            result = "屈膝捲腹";
//        } else if ((rlcl > 85 && rlcl < 95) || (rlcr > 85 && rlcr < 95)) {
//            result = "抬腿捲腹";
//        } else if ((sidepl > 85 && sidepl < 95) || (sidepr > 85 && sidepr < 95)) {
//            result = "側平板";
//        } else if ((pl > 170) || (pr > 170)) {
//            result = "平板撐";
//        } else if ((b11l > 25 && b11l < 35) || (b11r > 85 && b11r < 95) || (b11l > 55 && b11l < 65) || (b11r > 55 && b11r < 65)) {
//            result = "臀橋";
//        } else if ((l11l > 55 && l11l < 65) || (l11r > 55 && l11r < 65) || (l21 > 85 && l21 < 95 && l22 > 85 && l22 < 95)) {
//            result = "弓步蹲";
//        } else if ((ppl > 85 && ppl < 95) || (ppr > 85 && ppr < 95)) {
//            result = "鴿子式";
//        } else if ((fp1l > 115 && fp1l < 135 && fp2l > 115 && fp2l < 135) || (fp1r > 115 && fp1r < 135 && fp2r > 115 && fp2r < 135)) {
//            result = "青蛙式";
//        } else if ((bcl > 150 && bcl < 160) || (bcr > 150 && bcr < 160)) {
//            result = "交叉捲腹";
//        } else if ((cel > 25 && cel < 35) || (cer > 25 && cer < 35) || (cel > 40 && cel < 50) || (cer > 40 && cer < 50)) {
//            result = "蚌式開合";
//        } else if ((itl1l > 40 && itl1l < 50) || (itl1r > 40 && itl1r < 50) || (itl2l > 170) || (itl2r > 170)) {
//            result = "側臥內抬腿";
//        } else if ((bap1 > 85 && bap1 < 95) || (bap2 > 85 && bap2 < 95)) {
//            result = "束角式";
//        } else if ((sfbl < 45) || (sfbr < 45)) {
//            result = "立姿前彎式";
//        } else if ((s1l > 85 && s1l < 95 && s2l > 115 && s2l < 125) || (s1r > 85 && s1r < 95 && s2r > 115 && s2r < 125)) {
//            result = "膝蓋夾書深蹲";
//        } else if ((llrl < 5) || (llrr < 5)) {
//            result = "仰臥抬腿0";
//        } else if ((llrl < 15 && llrl > 5) || (llrr < 15 && llrr > 5)) {
//            result = "仰臥抬腿10";
//        } else if ((llrl < 35 && llrl > 25) || (llrr < 35 && llrr > 25)) {
//            result = "仰臥抬腿30";
//        } else if ((llrl < 65 && llrl > 55) || (llrr < 65 && llrr > 55)) {
//            result = "仰臥抬腿60";
//        } else if ((llrl < 95 && llrl > 85) || (llrr < 95 && llrr > 85)) {
//            result = "仰臥抬腿90";
//        } else if ((bpl > 70 && bpl < 80) || (bpr > 70 && bpr < 80)) {
//            result = "船式";
//        }
        return result;
    }

    static double getAngle(PoseLandmark firstpoint, PoseLandmark midpoint, PoseLandmark lastpoint) {
        double result = Math.toDegrees(atan2(lastpoint.getPosition().y - midpoint.getPosition().y,
                lastpoint.getPosition().x - lastpoint.getPosition().x)
                - atan2(firstpoint.getPosition().y - midpoint.getPosition().y,
                firstpoint.getPosition().x - midpoint.getPosition().x));
        result = Math.abs(result);
        if (result > 180) {
            result = (360.0 - result);
        }
        return result;
    }

    void drawPoint(Canvas canvas, PoseLandmark landmark, Paint paint) {
        PointF3D point = landmark.getPosition3D();
        maybeUpdatePaintColor(paint, canvas, point.getZ());
        canvas.drawCircle(translateX(point.getX()), translateY(point.getY()), DOT_RADIUS, paint);
    }

    void drawLine(Canvas canvas, PoseLandmark startLandmark, PoseLandmark endLandmark, Paint paint) {
        PointF3D start = startLandmark.getPosition3D();
        PointF3D end = endLandmark.getPosition3D();

        // Gets average z for the current body line
        float avgZInImagePixel = (start.getZ() + end.getZ()) / 2;
        maybeUpdatePaintColor(paint, canvas, avgZInImagePixel);

        canvas.drawLine(
                translateX(start.getX()),
                translateY(start.getY()),
                translateX(end.getX()),
                translateY(end.getY()),
                paint);
    }

    private void maybeUpdatePaintColor(Paint paint, Canvas canvas, float zInImagePixel) {
        if (!visualizeZ) {
            return;
        }

        // When visualizeZ is true, sets up the paint to different colors based on z values.
        // Gets the range of z value.
        float zLowerBoundInScreenPixel;
        float zUpperBoundInScreenPixel;

        if (rescaleZForVisualization) {
            zLowerBoundInScreenPixel = min(-0.001f, scale(zMin));
            zUpperBoundInScreenPixel = max(0.001f, scale(zMax));
        } else {
            // By default, assume the range of z value in screen pixel is [-canvasWidth, canvasWidth].
            float defaultRangeFactor = 1f;
            zLowerBoundInScreenPixel = -defaultRangeFactor * canvas.getWidth();
            zUpperBoundInScreenPixel = defaultRangeFactor * canvas.getWidth();
        }

        float zInScreenPixel = scale(zInImagePixel);

        if (zInScreenPixel < 0) {
            // Sets up the paint to draw the body line in red if it is in front of the z origin.
            // Maps values within [zLowerBoundInScreenPixel, 0) to [255, 0) and use it to control the
            // color. The larger the value is, the more red it will be.
            int v = (int) (zInScreenPixel / zLowerBoundInScreenPixel * 255);
            v = Ints.constrainToRange(v, 0, 255);
            paint.setARGB(255, 255, 255 - v, 255 - v);
        } else {
            // Sets up the paint to draw the body line in blue if it is behind the z origin.
            // Maps values within [0, zUpperBoundInScreenPixel] to [0, 255] and use it to control the
            // color. The larger the value is, the more blue it will be.
            int v = (int) (zInScreenPixel / zUpperBoundInScreenPixel * 255);
            v = Ints.constrainToRange(v, 0, 255);
            paint.setARGB(255, 255 - v, 255 - v, 255);
        }
    }
}
