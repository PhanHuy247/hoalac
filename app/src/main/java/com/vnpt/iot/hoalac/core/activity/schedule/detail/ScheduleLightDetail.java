package com.vnpt.iot.hoalac.core.activity.schedule.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.ScheduleApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.GreenHouse;
import com.vnpt.iot.hoalac.core.model.ScheduleLight;
import com.vnpt.iot.hoalac.core.model.ScheduleListApp;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleLightDetail extends CommonActivity {

    @BindView(R.id.name) TextView name;
    @BindView(R.id.code) TextView code;
    @BindView(R.id.sectorName) TextView sectorName;
    @BindView(R.id.gateway) TextView gateway;
    @BindView(R.id.intensityBelowLux) TextView intensityBelowLux;
    @BindView(R.id.startDate) TextView startDate;
    @BindView(R.id.progress) TextView progress;
    @BindView(R.id.endDate) TextView endDate;
    @BindView(R.id.checkFrom) TextView checkFrom;
    @BindView(R.id.checkTo) TextView checkTo;
    @BindView(R.id.durationMin) TextView durationMin;

    private ScheduleListApp scheduleListApp;
    private GreenHouse sector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_light_detail);
        commonSetting();
        ButterKnife.bind(this);
        scheduleListApp = gson.fromJson(getIntent().getStringExtra("data"), ScheduleListApp.class);
        sector = AppSharedPreferences.getGreenHouse(getApplicationContext());
        loadData();
    }

    private void loadData(){
        HashMap<String, Object> param = new HashMap<>();
        param.put("id", scheduleListApp.getId());
        param.put("tblName", scheduleListApp.getTblName());
        kProgressHUD.show();
        ScheduleApi.getDetailFromListAllApp(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                ScheduleLight scheduleTankBaste = gson.fromJson(result.get_jsonObject().toString(), ScheduleLight.class);
                loadView(scheduleTankBaste);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
            }
        });
    }

    private void loadView(ScheduleLight scheduleLight){
        name.setText(refactorTitle(scheduleLight.getName()));
        code.setText(scheduleLight.getCode());
        gateway.setText(scheduleLight.getGatewayName());
        intensityBelowLux.setText(String.valueOf(scheduleLight.getLux()));
        startDate.setText(scheduleLight.getStartTime());
        endDate.setText(scheduleLight.getEndTime());
        progress.setText(scheduleLight.getProgressName());
        checkFrom.setText(scheduleLight.getHourStartCheck());
        checkTo.setText(scheduleLight.getHourStopCheck());
        durationMin.setText(String.valueOf(scheduleLight.getInMinute()));

        sectorName.setText(sector.getName());
    }

    public void pressBack(View view) {
        finish();
    }
}
