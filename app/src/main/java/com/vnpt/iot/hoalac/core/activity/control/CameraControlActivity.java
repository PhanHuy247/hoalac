package com.vnpt.iot.hoalac.core.activity.control;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.CameraApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.github.niqdev.mjpeg.DisplayMode;
import com.github.niqdev.mjpeg.Mjpeg;
import com.github.niqdev.mjpeg.MjpegInputStream;
import com.github.niqdev.mjpeg.MjpegSurfaceView;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CameraControlActivity extends CommonActivity {
    private String cam;
    private String deviceId;
    private int camNumber;
    public static Activity fa;
    @BindView(R.id.camView) MjpegSurfaceView camView;
    @BindView(R.id.imageControl) ImageView imageControl;
    @BindView(R.id.iconCam1) ImageView iconCam1;
    @BindView(R.id.iconCam2) ImageView iconCam2;
    private AsyncTask<Void, MjpegInputStream, Void> asyncTaskCam;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_camera_control);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        fa = this;
        ButterKnife.bind(this);
        camNumber = getIntent().getIntExtra("cam", 1);
        loadCamSelect(camNumber);
        asyncTaskCam = new ShowCamTask().execute();
    }

    private void loadCamSelect(int camNumber){
        resetIconCam();
        if (camNumber == 1){
            iconCam1.setImageResource(R.mipmap.camera_1_active);
            cam = "http://" + AppSharedPreferences.getIpCam1(getApplicationContext())+Constatnts.PATH_STEAM;
            deviceId = AppSharedPreferences.getIdCam1(getApplicationContext());
        } else {
            iconCam2.setImageResource(R.mipmap.camera_2_active);
            cam = "http://" + AppSharedPreferences.getIpCam2(getApplicationContext())+Constatnts.PATH_STEAM;
            deviceId = AppSharedPreferences.getIdCam2(getApplicationContext());
        }
    }

    public void selectCam1(View view){
//        camNumber = 1;
//        loadCamSelect(camNumber);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("rtsp://admin:delco123@gsv-project.dyndns.org:554/Streaming/Channels/101"));
        startActivity(intent);

    }

    public void selectCam2(View view){
        camNumber = 2;
        loadCamSelect(camNumber);
    }

    private void resetIconCam(){
        iconCam1.setImageResource(R.mipmap.camera_1_disactive);
        iconCam2.setImageResource(R.mipmap.camera_2_disactive);
    }

    public void camUp(View v){cameraControl("UP");}
    public void camDown(View v){cameraControl("DOWN");}
    public void camLeft(View v){cameraControl("LEFT");}
    public void camRight(View v){cameraControl("RIGHT");}

    private void controlOffline(String url){
        CameraApi.cmdOffline(getApplicationContext(), url, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                imageControl.setImageResource(R.mipmap.camera_control);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                imageControl.setImageResource(R.mipmap.camera_control);
            }
        });
    }

    private void cameraControl(String cmd){
        String cmdData = "";
        String urlOffLine = "";
        if (camNumber == 1) urlOffLine = "http://" + AppSharedPreferences.getIpCam1(getApplicationContext());
        if (camNumber == 2) urlOffLine = "http://" + AppSharedPreferences.getIpCam2(getApplicationContext());
        switch (cmd){
            case "UP":
                urlOffLine += "/decoder_control.cgi?user=iot_itc&pwd=ThingxyzNet&command=0&onestep=1";
                cmdData = "CONTROL_TURN_UP";
                imageControl.setImageResource(R.mipmap.camera_control_up);
                break;
            case "DOWN":
                urlOffLine += "/decoder_control.cgi?user=iot_itc&pwd=ThingxyzNet&command=2&onestep=1";
                cmdData = "CONTROL_TURN_DOWN";
                imageControl.setImageResource(R.mipmap.camera_control_down);
                break;
            case "LEFT":
                urlOffLine += "/decoder_control.cgi?user=iot_itc&pwd=ThingxyzNet&command=4&onestep=1";
                cmdData = "CONTROL_TURN_LEFT";
                imageControl.setImageResource(R.mipmap.camera_control_left);
                break;
            case "RIGHT":
                urlOffLine += "/decoder_control.cgi?user=iot_itc&pwd=ThingxyzNet&command=6&onestep=1";
                cmdData = "CONTROL_TURN_RIGHT";
                imageControl.setImageResource(R.mipmap.camera_control_right);
                break;
        }

        if (urlOffLine.contains("192")) {
            controlOffline(urlOffLine);
            return;
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("deviceId", deviceId);
        params.put("cmdType", 1);

        Map<String, String> map1 = new HashMap<>();
        map1.put("name", cmdData);
        map1.put("value", cmdData);

        Map<String, List<Map<String, String>>> map2 = new HashMap<>();
        map2.put("anyArg", Arrays.asList(map1));

        Map<String, List<Map<String, List<Map<String, String>>>>> map3 = new HashMap<>();
        map3.put("reset", Arrays.asList(map2));


        JSONObject body = new JSONObject(map3);

        CameraApi.cmd(getApplicationContext(), params, body, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                imageControl.setImageResource(R.mipmap.camera_control);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                imageControl.setImageResource(R.mipmap.camera_control);
                toast(getApplicationContext(), R.string.controlErr);
            }
        });

    }

    private class ShowCamTask extends AsyncTask<Void, MjpegInputStream, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            if (!isNetworkAvailable()) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        toast(getApplicationContext(), R.string.lostInternet);
                    }
                });

            } else {
                Mjpeg.newInstance()
                    .credential("", "")
                    .open(cam, 5)
                    .subscribe(
                        inputStream -> {
                            publishProgress(inputStream);
                        },
                        throwable -> {
                            Log.e(getClass().getSimpleName(), "mjpeg error", throwable);
                        }
                    );


            }
            return null;
        }

        @Override
        protected void onProgressUpdate(MjpegInputStream... values) {
            camView.setSource(values[0]);
            camView.setDisplayMode(DisplayMode.BEST_FIT);
            camView.showFps(false);
        }
    }

    @Override
    protected void onPause() {
        camView.stopPlayback();
        if (asyncTaskCam != null) asyncTaskCam.cancel(true);
        super.onPause();

    }

    public void pressBack(View view){
        onBackPressed();
    }
}
