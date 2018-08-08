package com.vnpt.iot.hoalac.core.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.activity.control.CameraControlActivity;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends CommonActivity {
    @BindView(R.id.ipServer) TextView ipServer;
    @BindView(R.id.portServer) TextView portServer;
    @BindView(R.id.ipCam1) TextView ipCam1;
    @BindView(R.id.ipCam2) TextView ipCam2;
    @BindView(R.id.idCam1) TextView idCam1;
    @BindView(R.id.idCam2) TextView idCam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        loadData();
    }

    private void loadData(){
        ipServer.setText(AppSharedPreferences.getIpBroker(getApplicationContext()));
        portServer.setText(AppSharedPreferences.getPortBroker(getApplicationContext()));
        ipCam1.setText(AppSharedPreferences.getIpCam1(getApplicationContext()));
        ipCam2.setText(AppSharedPreferences.getIpCam2(getApplicationContext()));
        idCam1.setText(AppSharedPreferences.getIdCam1(getApplicationContext()));
        idCam2.setText(AppSharedPreferences.getIdCam2(getApplicationContext()));
    }

    public void save(View view){
        AppSharedPreferences.setIpBroker(getApplicationContext(), ipServer.getText().toString());
        AppSharedPreferences.setPortBroker(getApplicationContext(), portServer.getText().toString());

        String ipCam1Text = ipCam1.getText().toString();
        ipCam1Text = ipCam1Text.toLowerCase().replace("http://", "").replace("https://", "");

        String ipCam2Text = ipCam2.getText().toString();
        ipCam2Text = ipCam2Text.toLowerCase().replace("http://", "").replace("https://", "");

        AppSharedPreferences.setIpCam1(getApplicationContext(), ipCam1Text);
        AppSharedPreferences.setIpCam2(getApplicationContext(), ipCam2Text);
        AppSharedPreferences.setIdCam1(getApplicationContext(), idCam1.getText().toString());
        AppSharedPreferences.setIdCam2(getApplicationContext(), idCam2.getText().toString());
        if (CameraControlActivity.fa != null) CameraControlActivity.fa.finish();
        Utils.toast(getApplicationContext(), getStringLang(R.string.saveSS));
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

    public void pressBack(View view){
        finish();
    }
}
