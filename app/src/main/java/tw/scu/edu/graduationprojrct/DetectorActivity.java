/*
 * Copyright 2019 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tw.scu.edu.graduationprojrct;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.media.ImageReader.OnImageAvailableListener;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.util.Size;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tw.scu.edu.graduationprojrct.Setting.SportType;
import tw.scu.edu.graduationprojrct.customview.OverlayView;
import tw.scu.edu.graduationprojrct.customview.OverlayView.DrawCallback;
import tw.scu.edu.graduationprojrct.env.BorderedText;
import tw.scu.edu.graduationprojrct.env.ImageUtils;
import tw.scu.edu.graduationprojrct.env.Logger;
import tw.scu.edu.graduationprojrct.java.posedetector.PoseGraphic;
import tw.scu.edu.graduationprojrct.preference.SettingsActivity;
import tw.scu.edu.graduationprojrct.scene.SportResultScene;
import tw.scu.edu.graduationprojrct.tflite.Classifier;
import tw.scu.edu.graduationprojrct.tflite.DetectorFactory;
import tw.scu.edu.graduationprojrct.tflite.YoloV5Classifier;
import tw.scu.edu.graduationprojrct.tracking.MultiBoxTracker;

/**
 * An activity that uses a TensorFlowMultiBoxDetector and ObjectTracker to detect and then track
 * objects.
 */
public class DetectorActivity extends CameraActivity
        implements OnImageAvailableListener {
    private static final Logger LOGGER = new Logger();

    private static final DetectorMode MODE = DetectorMode.TF_OD_API;
    private static final float MINIMUM_CONFIDENCE_TF_OD_API = 0.3f;
    private static final boolean MAINTAIN_ASPECT = true;
    private static final Size DESIRED_PREVIEW_SIZE = new Size(640, 640);
    private static final boolean SAVE_PREVIEW_BITMAP = false;
    private static final float TEXT_SIZE_DIP = 10;
    OverlayView trackingOverlay;
    private Integer sensorOrientation;

    private YoloV5Classifier detector;

    private long lastProcessingTimeMs;
    private Bitmap rgbFrameBitmap = null;
    private Bitmap croppedBitmap = null;
    private Bitmap cropCopyBitmap = null;

    private boolean computingDetection = false;

    private long timestamp = 0;

    private Matrix frameToCropTransform;
    private Matrix cropToFrameTransform;

    private MultiBoxTracker tracker;

    private BorderedText borderedText;

    String SportType_String;
    String Current_Sport;
    TextView Time_Word;
    int CurrentTime, Current_SportTime;
    ImageView Time_BG, White_BG;
    ImageView Rest_Photo_BG, Rest_Next_Word, Rest_Sign, Rest_Word_Sign, Next_Sport_Word;
    ImageView Now_Photo_BG, Now_Word, Now_Sport_Word;
    final int RestTime = 10;
    SharedPreferences shared;
    Timer timer;
    Boolean isSport = false;
    int Order = -1;
    int ImgOrder;
    SportType ST;
    int Success, Fail = 0;

    public PoseGraphic PG;
    public String CurrentPose = "";
    public static ArrayList<Float> strike = new ArrayList<Float>();
    String temp = "";
    int individualsuccess = 0;
    int individualfail = 0;
    float individualtotal;
    int BGMList[] = {R.raw.m0, R.raw.m1, R.raw.m2, R.raw.m3, R.raw.m4};
    int BGMNumber = 0;
    MediaPlayer mysong;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shared = getSharedPreferences("data", MODE_PRIVATE);
        SportType_String = shared.getString("SportType", "Belly");
        BGMNumber = shared.getInt("BGMNumber", 0);
//        ????????????
        Time_BG = findViewById(R.id.Time_BG);
        Time_Word = findViewById(R.id.Time_Word);
        Rest_Photo_BG = findViewById(R.id.Rest_Photo_BG);
        Rest_Next_Word = findViewById(R.id.Rest_Next_Word);
        Rest_Sign = findViewById(R.id.Rest_Sign);
        Rest_Word_Sign = findViewById(R.id.Rest_Word_Sign);
        Next_Sport_Word = findViewById(R.id.Next_Sport_Word);

        Now_Photo_BG = findViewById(R.id.Now_Photo_BG);
        Now_Word = findViewById(R.id.Now_Word);
        Now_Sport_Word = findViewById(R.id.Now_Sport_Word);
        White_BG = findViewById(R.id.white_BG);
//        ???????????????
        timer = new Timer();
//        ????????????
        ST = new SportType(SportType_String);
        Log.d("ChooseSport", SportType_String);
        ST.LoadSportData();
        for (int i = 0; i < ST.SportContent.length; i++) {
            Log.d("????????????", ST.SportContent[i]);
        }

//        ?????????
        Current_SportTime = ST.SportContentTime[0];
        CurrentTime = RestTime;
//
        InitalUI();
        mysong = MediaPlayer.create(DetectorActivity.this, BGMList[BGMNumber]);
        mysong.start();
        mysong.setLooping(true);

        final TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CurrentTime --;//????????????
                        Time_Word.setText(CurrentTime + "");
                        //if????????????????????????????????????????????????????????????
                        if (CurrentTime < 1) {
                            if (Order < ST.SportContentTime.length - 1) {
                                if (isSport) {//??????????????????
                                    isSport = false;
                                    Next_Sport_Word.setImageDrawable(null);
                                    Next_Sport_Word.setImageResource(ST.SportImgID[ImgOrder]);
                                    CurrentPose = ST.SportContent[ImgOrder];
                                    Log.d("????????????????????????", CurrentPose);
                                    EnterRestState();
                                    CurrentTime = RestTime;
                                } else {
                                    isSport = true;
                                    Now_Sport_Word.setImageDrawable(null);
                                    Now_Sport_Word.setImageResource(ST.SportImgID[ImgOrder]);
                                    CurrentPose = ST.SportContent[ImgOrder];
                                    EnterSportState();
                                    Order++; //??????????????????????????????
                                    ImgOrder++;
                                    CurrentTime = ST.SportContentTime[Order];
                                }
                            } else {
                                timer.cancel();
                                mysong.release();
                                mysong = null;
                                startActivity(new Intent(DetectorActivity.this, SportResultScene.class));
                            }
                        }
                        if (temp.equals("")) {
                            temp = CurrentPose;
                        } else if (!temp.equals(CurrentPose)) {
                            temp = CurrentPose;
                            individualtotal = individualsuccess + individualfail;
                            float indivisualpercent = (float) (Math.round(individualsuccess / individualtotal * 1000.0) / 10.0);
                            strike.add(indivisualpercent);
                            individualsuccess = 0;
                            individualfail = 0;
                        } else if (temp.equals(CurrentPose)) {
                            if (temp.equals(YoloV5Classifier.result)) {
                                Log.d("??????????????????", "??????");
                                individualsuccess++;
                            } else {
                                Log.d("??????????????????", "??????");
                                individualfail++;
                            }
                        }
                        if (CurrentPose.equals(YoloV5Classifier.result)) {
                            Log.d("????????????", "??????");
                            Success++;
                        } else {
                            Log.d("????????????", "??????");
                            Fail++;
                        }

                    }
                });
                Log.d("Order:", String.valueOf(Order));
                SharedPreferences.Editor editor = shared.edit();
                float total = (float) (Success) + (float) (Fail);
                Log.d("??????", String.valueOf(Success));
                Log.d("??????", String.valueOf(Fail));
                float supercent = (float) (Math.round(Success / total * 1000.0) / 10.0);
                Log.d("?????????", String.valueOf(supercent));
                editor.putFloat("Pro", supercent);
                editor.commit();
            }
        };
        timer.schedule(timertask, 1000, 1000);


        ImageView settingsButton = findViewById(R.id.settings_button_land);
        settingsButton.setOnClickListener(
                v -> {
                    Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                    intent.putExtra(
                            SettingsActivity.EXTRA_LAUNCH_SOURCE,
                            SettingsActivity.LaunchSource.CAMERAX_LIVE_PREVIEW);
                    startActivity(intent);
                });
    }

    private void InitalUI() {//????????????????????????
        //Now_Sport_Word.setImageDrawable(null);
        Next_Sport_Word.setImageDrawable(null);
        Next_Sport_Word.setImageResource(ST.SportImgID[0]);
        White_BG.setVisibility(View.VISIBLE);
        Time_BG.setVisibility(View.VISIBLE);
        Time_Word.setVisibility(View.VISIBLE);
        Rest_Word_Sign.setVisibility(View.VISIBLE);
        Rest_Sign.setVisibility(View.VISIBLE);
        Rest_Next_Word.setVisibility(View.VISIBLE);
        Rest_Photo_BG.setVisibility(View.VISIBLE);
        Next_Sport_Word.setVisibility(View.VISIBLE);
        Now_Sport_Word.setVisibility(View.GONE);
        Now_Photo_BG.setVisibility(View.GONE);
        Now_Word.setVisibility(View.GONE);
    }

    private void EnterRestState() {
        White_BG.setVisibility(View.VISIBLE);
        Time_BG.setVisibility(View.VISIBLE);
        Time_Word.setVisibility(View.VISIBLE);
        Rest_Word_Sign.setVisibility(View.VISIBLE);
        Rest_Sign.setVisibility(View.VISIBLE);
        Rest_Next_Word.setVisibility(View.VISIBLE);
        Rest_Photo_BG.setVisibility(View.VISIBLE);
        Next_Sport_Word.setVisibility(View.VISIBLE);
        Now_Sport_Word.setVisibility(View.GONE);
        Now_Photo_BG.setVisibility(View.GONE);
        Now_Word.setVisibility(View.GONE);
    }

    private void EnterSportState() {
        White_BG.setVisibility(View.GONE);
        Time_BG.setVisibility(View.VISIBLE);
        Time_Word.setVisibility(View.VISIBLE);
        Rest_Word_Sign.setVisibility(View.GONE);
        Rest_Sign.setVisibility(View.GONE);
        Rest_Next_Word.setVisibility(View.GONE);
        Rest_Photo_BG.setVisibility(View.GONE);
        Next_Sport_Word.setVisibility(View.GONE);
        Now_Sport_Word.setVisibility(View.VISIBLE);
        Now_Photo_BG.setVisibility(View.VISIBLE);
        Now_Word.setVisibility(View.VISIBLE);

    }

    @Override
    public void onPreviewSizeChosen(final Size size, final int rotation) {
        final float textSizePx =
                TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
        borderedText = new BorderedText(textSizePx);
        borderedText.setTypeface(Typeface.MONOSPACE);

        tracker = new MultiBoxTracker(this);

        final int modelIndex = modelView.getCheckedItemPosition();
        final String modelString = "best-fp16s.tflite";

        try {
            detector = DetectorFactory.getDetector(getAssets(), modelString);
        } catch (final IOException e) {
            e.printStackTrace();
            LOGGER.e(e, "Exception initializing classifier!");
            Toast toast =
                    Toast.makeText(
                            getApplicationContext(), "Classifier could not be initialized", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }

        int cropSize = detector.getInputSize();

        previewWidth = size.getWidth();
        previewHeight = size.getHeight();

        sensorOrientation = rotation - getScreenOrientation();
        LOGGER.i("Camera orientation relative to screen canvas: %d", sensorOrientation);

        LOGGER.i("Initializing at size %dx%d", previewWidth, previewHeight);
        rgbFrameBitmap = Bitmap.createBitmap(previewWidth, previewHeight, Config.ARGB_8888);
        croppedBitmap = Bitmap.createBitmap(cropSize, cropSize, Config.ARGB_8888);

        frameToCropTransform =
                ImageUtils.getTransformationMatrix(
                        previewWidth, previewHeight,
                        cropSize, cropSize,
                        sensorOrientation, MAINTAIN_ASPECT);

        cropToFrameTransform = new Matrix();
        frameToCropTransform.invert(cropToFrameTransform);

        trackingOverlay = (OverlayView) findViewById(R.id.tracking_overlay);
        trackingOverlay.addCallback(
                new DrawCallback() {
                    @Override
                    public void drawCallback(final Canvas canvas) {
                        tracker.draw(canvas);
                        if (isDebug()) {
                            tracker.drawDebug(canvas);
                        }
                    }
                });

        tracker.setFrameConfiguration(previewWidth, previewHeight, sensorOrientation);
    }

    protected void updateActiveModel() {
        // Get UI information before delegating to background
        final int modelIndex = modelView.getCheckedItemPosition();
        final int deviceIndex = deviceView.getCheckedItemPosition();
        String threads = threadsTextView.getText().toString().trim();
        final int numThreads = Integer.parseInt(threads);
        handler.post(() -> {
            if (modelIndex == currentModel && deviceIndex == currentDevice
                    && numThreads == currentNumThreads) {
                return;
            }
            currentModel = modelIndex;
            currentDevice = deviceIndex;
            currentNumThreads = numThreads;

            // Disable classifier while updating
            if (detector != null) {
                detector.close();
                detector = null;
            }

            // Lookup names of parameters.
//            String modelString = modelStrings.get(modelIndex);
//            String device = deviceStrings.get(deviceIndex);

//            LOGGER.i("Changing model to " + modelString + " device " + device);

            // Try to load model.

//            try {
//                detector = DetectorFactory.getDetector(getAssets(), modelString);
//                // Customize the interpreter to the type of device we want to use.
//                if (detector == null) {
//                    return;
//                }
//            }
//            catch(IOException e) {
//                e.printStackTrace();
//                LOGGER.e(e, "Exception in updateActiveModel()");
//                Toast toast =
//                        Toast.makeText(
//                                getApplicationContext(), "Classifier could not be initialized", Toast.LENGTH_SHORT);
//                toast.show();
//                finish();
//            }

            detector.useCPU();
//            if (device.equals("CPU")) {
//                detector.useCPU();
//            } else if (device.equals("GPU")) {
//                detector.useGpu();
//            } else if (device.equals("NNAPI")) {
//                detector.useNNAPI();
//            }
//            detector.setNumThreads(numThreads);
            detector.setNumThreads(2);

            int cropSize = detector.getInputSize();
            croppedBitmap = Bitmap.createBitmap(cropSize, cropSize, Config.ARGB_8888);

            frameToCropTransform =
                    ImageUtils.getTransformationMatrix(
                            previewWidth, previewHeight,
                            cropSize, cropSize,
                            sensorOrientation, MAINTAIN_ASPECT);

            cropToFrameTransform = new Matrix();
            frameToCropTransform.invert(cropToFrameTransform);
        });
    }

    @Override
    protected void processImage() {
        ++timestamp;
        final long currTimestamp = timestamp;
        trackingOverlay.postInvalidate();

        // No mutex needed as this method is not reentrant.
        if (computingDetection) {
            readyForNextImage();
            return;
        }
        computingDetection = true;
        LOGGER.i("Preparing image " + currTimestamp + " for detection in bg thread.");

        rgbFrameBitmap.setPixels(getRgbBytes(), 0, previewWidth, 0, 0, previewWidth, previewHeight);

        readyForNextImage();

        final Canvas canvas = new Canvas(croppedBitmap);
        canvas.drawBitmap(rgbFrameBitmap, frameToCropTransform, null);
        // For examining the actual TF input.
        if (SAVE_PREVIEW_BITMAP) {
            ImageUtils.saveBitmap(croppedBitmap);
        }

        runInBackground(
                new Runnable() {
                    @Override
                    public void run() {
                        LOGGER.i("Running detection on image " + currTimestamp);
                        final long startTime = SystemClock.uptimeMillis();
                        final List<Classifier.Recognition> results = detector.recognizeImage(croppedBitmap);
                        lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime;

                        Log.e("CHECK", "run: " + results.size());
                        Log.d("get", YoloV5Classifier.result);
                        cropCopyBitmap = Bitmap.createBitmap(croppedBitmap);
                        final Canvas canvas = new Canvas(cropCopyBitmap);
                        final Paint paint = new Paint();
                        paint.setColor(Color.RED);
                        paint.setStyle(Style.STROKE);
                        paint.setStrokeWidth(2.0f);

                        float minimumConfidence = MINIMUM_CONFIDENCE_TF_OD_API;
                        switch (MODE) {
                            case TF_OD_API:
                                minimumConfidence = MINIMUM_CONFIDENCE_TF_OD_API;
                                break;
                        }

                        final List<Classifier.Recognition> mappedRecognitions =
                                new LinkedList<Classifier.Recognition>();

                        for (final Classifier.Recognition result : results) {
                            final RectF location = result.getLocation();
                            if (location != null && result.getConfidence() >= minimumConfidence) {
                                canvas.drawRect(location, paint);

                                cropToFrameTransform.mapRect(location);

                                result.setLocation(location);
                                mappedRecognitions.add(result);
                            }
                        }

                        tracker.trackResults(mappedRecognitions, currTimestamp);
                        trackingOverlay.postInvalidate();

                        computingDetection = false;

                        runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        showFrameInfo(previewWidth + "x" + previewHeight);
                                        showCropInfo(cropCopyBitmap.getWidth() + "x" + cropCopyBitmap.getHeight());
                                        showInference(lastProcessingTimeMs + "ms");
                                    }
                                });
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tfe_od_camera_connection_fragment_tracking;
    }

    @Override
    protected Size getDesiredPreviewFrameSize() {
        return DESIRED_PREVIEW_SIZE;
    }

    // Which detection model to use: by default uses Tensorflow Object Detection API frozen
    // checkpoints.
    private enum DetectorMode {
        TF_OD_API;
    }

    @Override
    protected void setUseNNAPI(final boolean isChecked) {
        runInBackground(() -> detector.setUseNNAPI(isChecked));
    }

    @Override
    protected void setNumThreads(final int numThreads) {
        runInBackground(() -> detector.setNumThreads(numThreads));
    }
}
