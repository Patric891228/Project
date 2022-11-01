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

package tw.scu.edu.graduationprojrct.java;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;

import com.google.android.gms.common.annotation.KeepName;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import tw.scu.edu.graduationprojrct.CameraXViewModel;
import tw.scu.edu.graduationprojrct.GraphicOverlay;
import tw.scu.edu.graduationprojrct.R;
import tw.scu.edu.graduationprojrct.Setting.SportType;
import tw.scu.edu.graduationprojrct.VisionImageProcessor;
import tw.scu.edu.graduationprojrct.java.posedetector.PoseDetectorProcessor;
import tw.scu.edu.graduationprojrct.java.posedetector.PoseGraphic;
import tw.scu.edu.graduationprojrct.preference.PreferenceUtils;
import tw.scu.edu.graduationprojrct.preference.SettingsActivity;
import tw.scu.edu.graduationprojrct.scene.SportResultScene;
import tw.scu.edu.graduationprojrct.tflite.YoloV5Classifier;

/**
 * Live preview demo app for ML Kit APIs using CameraX.
 */
@KeepName
@RequiresApi(VERSION_CODES.LOLLIPOP)
public final class CameraXLivePreviewActivity extends AppCompatActivity
        implements OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "CameraXLivePreview";
    private static final String POSE_DETECTION = "Pose Detection";

    private static final String STATE_SELECTED_MODEL = "selected_model";

    private PreviewView previewView;
    private GraphicOverlay graphicOverlay;

    @Nullable
    private ProcessCameraProvider cameraProvider;
    @Nullable
    private Preview previewUseCase;
    @Nullable
    private ImageAnalysis analysisUseCase;
    @Nullable
    private VisionImageProcessor imageProcessor;
    private boolean needUpdateGraphicOverlayImageSourceInfo;

    private String selectedModel = POSE_DETECTION;
    private int lensFacing = CameraSelector.LENS_FACING_BACK;
    private CameraSelector cameraSelector;

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
    String CurrentPose = "";
    MediaPlayer mysong;
    int Success, Fail = 0;
    int BGMList[] = {R.raw.m0, R.raw.m1, R.raw.m2, R.raw.m3, R.raw.m4};
    int BGMNumber = 0;
    public static ArrayList<Float> strike = new ArrayList<Float>();
    String temp = "";
    int individualsuccess = 0;
    int individualfail = 0;
    float individualtotal;

    @RequiresApi(api = VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");


        if (savedInstanceState != null) {
            selectedModel = savedInstanceState.getString(STATE_SELECTED_MODEL, POSE_DETECTION);
        }
        cameraSelector = new CameraSelector.Builder().requireLensFacing(lensFacing).build();

        setContentView(R.layout.activity_vision_camerax_live_preview);
        previewView = findViewById(R.id.preview_view);
        if (previewView == null) {
            Log.d(TAG, "previewView is null");
        }
        graphicOverlay = findViewById(R.id.graphic_overlay);
        if (graphicOverlay == null) {
            Log.d(TAG, "graphicOverlay is null");
        }

        ToggleButton facingSwitch = findViewById(R.id.facing_switch);
        facingSwitch.setOnCheckedChangeListener(this);

        new ViewModelProvider(this, (ViewModelProvider.Factory) AndroidViewModelFactory.getInstance(getApplication()))
                .get(CameraXViewModel.class)
                .getProcessCameraProvider()
                .observe(
                        this,
                        provider -> {
                            cameraProvider = provider;
                            bindAllCameraUseCases();
                        });

        shared = getSharedPreferences("data", MODE_PRIVATE);
        SportType_String = shared.getString("SportType", "Belly");
        BGMNumber = shared.getInt("BGMNumber", 0);
//        綁定物件
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
//        建立計時器
        timer = new Timer();
//        建立物件
        ST = new SportType(SportType_String);
        Log.d("ChooseSport", SportType_String);

        ST.LoadSportData();
//        初始化
        Current_SportTime = ST.SportContentTime[0];
        CurrentTime = RestTime;
//
        InitalUI();
        mysong = MediaPlayer.create(CameraXLivePreviewActivity.this, BGMList[BGMNumber]);
        mysong.start();
        mysong.setLooping(true);

        final TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CurrentTime--;//時間倒數
                        Time_Word.setText(CurrentTime + "");
                        //if判斷示裡面放置在時間結束後想要完成的事件
                        if (CurrentTime < 1) {
                            if (Order < ST.SportContentTime.length - 1) {
                                if (isSport) {//進入休息狀態
                                    isSport = false;
                                    Next_Sport_Word.setImageDrawable(null);
                                    Next_Sport_Word.setImageResource(ST.SportImgID[ImgOrder]);
                                    EnterRestState();
                                    CurrentTime = RestTime;
                                } else {
                                    isSport = true;
                                    Now_Sport_Word.setImageDrawable(null);
                                    Now_Sport_Word.setImageResource(ST.SportImgID[ImgOrder]);
                                    CurrentPose = ST.SportContent[ImgOrder];
                                    EnterSportState();
                                    Order++; //讓時間執行緒保持輪迴
                                    ImgOrder++;
                                    CurrentTime = ST.SportContentTime[Order];
                                    Log.d("現在取得動作", CurrentPose);
                                    Log.d("辨識動作", PoseGraphic.result);
                                }
                            } else {
                                Log.d("運動狀態", "結束");
                                timer.cancel();
                                mysong.release();
                                mysong = null;
                                startActivity(new Intent(CameraXLivePreviewActivity.this, SportResultScene.class));
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
                            if (temp.equals(PoseGraphic.result)) {
                                Log.d("個別辨識結果", "成功");
                                individualsuccess++;
                            } else {
                                Log.d("個別辨識結果", "失敗");
                                individualfail++;
                            }
                        }
                        if (CurrentPose.equals(PoseGraphic.result)) {
                            Log.d("辨識結果", "成功");
                            Success++;
                        } else {
                            Log.d("辨識結果", "失敗");
                            Fail++;
                        }

                    }
                });
                Log.d("Order:", String.valueOf(Order));
                SharedPreferences.Editor editor = shared.edit();
                float total = (float) (Success) + (float) (Fail);
                Log.d("成功", String.valueOf(Success));
                Log.d("失敗", String.valueOf(Fail));
                float supercent = (float) (Math.round(Success / total * 1000.0) / 10.0);
                Log.d("成功率", String.valueOf(supercent));
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(STATE_SELECTED_MODEL, selectedModel);
    }

    @Override
    public synchronized void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        selectedModel = parent.getItemAtPosition(pos).toString();
        Log.d(TAG, "Selected model: " + selectedModel);
        bindAnalysisUseCase();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing.
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (cameraProvider == null) {
            return;
        }
        int newLensFacing =
                lensFacing == CameraSelector.LENS_FACING_FRONT
                        ? CameraSelector.LENS_FACING_BACK
                        : CameraSelector.LENS_FACING_FRONT;
        CameraSelector newCameraSelector =
                new CameraSelector.Builder().requireLensFacing(newLensFacing).build();
        try {
            if (cameraProvider.hasCamera(newCameraSelector)) {
                Log.d(TAG, "Set facing to " + newLensFacing);
                lensFacing = newLensFacing;
                cameraSelector = newCameraSelector;
                bindAllCameraUseCases();
                return;
            }
        } catch (CameraInfoUnavailableException e) {
            // Falls through
        }
        Toast.makeText(
                getApplicationContext(),
                "This device does not have lens with facing: " + newLensFacing,
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();
        bindAllCameraUseCases();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (imageProcessor != null) {
            imageProcessor.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (imageProcessor != null) {
            imageProcessor.stop();
        }
    }

    private void bindAllCameraUseCases() {
        if (cameraProvider != null) {
            // As required by CameraX API, unbinds all use cases before trying to re-bind any of them.
            cameraProvider.unbindAll();
            bindPreviewUseCase();
            bindAnalysisUseCase();
        }
    }

    private void bindPreviewUseCase() {
        if (!PreferenceUtils.isCameraLiveViewportEnabled(this)) {
            return;
        }
        if (cameraProvider == null) {
            return;
        }
        if (previewUseCase != null) {
            cameraProvider.unbind(previewUseCase);
        }

        Preview.Builder builder = new Preview.Builder();
        Size targetResolution = PreferenceUtils.getCameraXTargetResolution(this, lensFacing);
        if (targetResolution != null) {
            builder.setTargetResolution(targetResolution);
        }
        previewUseCase = builder.build();
        previewUseCase.setSurfaceProvider(previewView.getSurfaceProvider());
        cameraProvider.bindToLifecycle(/* lifecycleOwner= */ this, cameraSelector, previewUseCase);
    }

    private void bindAnalysisUseCase() {
        if (cameraProvider == null) {
            return;
        }
        if (analysisUseCase != null) {
            cameraProvider.unbind(analysisUseCase);
        }
        if (imageProcessor != null) {
            imageProcessor.stop();
        }

        try {
            switch (selectedModel) {

                case POSE_DETECTION:
                    PoseDetectorOptionsBase poseDetectorOptions =
                            PreferenceUtils.getPoseDetectorOptionsForLivePreview(this);
                    boolean shouldShowInFrameLikelihood =
                            PreferenceUtils.shouldShowPoseDetectionInFrameLikelihoodLivePreview(this);
                    boolean visualizeZ = PreferenceUtils.shouldPoseDetectionVisualizeZ(this);
                    boolean rescaleZ = PreferenceUtils.shouldPoseDetectionRescaleZForVisualization(this);
                    boolean runClassification = PreferenceUtils.shouldPoseDetectionRunClassification(this);
                    imageProcessor =
                            new PoseDetectorProcessor(
                                    this,
                                    poseDetectorOptions,
                                    shouldShowInFrameLikelihood,
                                    visualizeZ,
                                    rescaleZ,
                                    runClassification,
                                    /* isStreamMode = */ true);
                    break;

                default:
                    throw new IllegalStateException("Invalid model name");
            }
        } catch (Exception e) {
            Log.e(TAG, "Can not create image processor: " + selectedModel, e);
            Toast.makeText(
                    getApplicationContext(),
                    "Can not create image processor: " + e.getLocalizedMessage(),
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        ImageAnalysis.Builder builder = new ImageAnalysis.Builder();
        Size targetResolution = PreferenceUtils.getCameraXTargetResolution(this, lensFacing);
        if (targetResolution != null) {
            builder.setTargetResolution(targetResolution);
        }
        analysisUseCase = builder.build();

        needUpdateGraphicOverlayImageSourceInfo = true;
        analysisUseCase.setAnalyzer(
                // imageProcessor.processImageProxy will use another thread to run the detection underneath,
                // thus we can just runs the analyzer itself on main thread.
                ContextCompat.getMainExecutor(this),
                imageProxy -> {
                    if (needUpdateGraphicOverlayImageSourceInfo) {
                        boolean isImageFlipped = lensFacing == CameraSelector.LENS_FACING_FRONT;
                        int rotationDegrees = imageProxy.getImageInfo().getRotationDegrees();
                        if (rotationDegrees == 0 || rotationDegrees == 180) {
                            graphicOverlay.setImageSourceInfo(
                                    imageProxy.getWidth(), imageProxy.getHeight(), isImageFlipped);
                        } else {
                            graphicOverlay.setImageSourceInfo(
                                    imageProxy.getHeight(), imageProxy.getWidth(), isImageFlipped);
                        }
                        needUpdateGraphicOverlayImageSourceInfo = false;
                    }
                    try {
                        imageProcessor.processImageProxy(imageProxy, graphicOverlay);
                    } catch (MlKitException e) {
                        Log.e(TAG, "Failed to process image. Error: " + e.getLocalizedMessage());
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        cameraProvider.bindToLifecycle(/* lifecycleOwner= */ this, cameraSelector, analysisUseCase);


    }

    private void InitalUI() {//一開始為休息狀態
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
}
