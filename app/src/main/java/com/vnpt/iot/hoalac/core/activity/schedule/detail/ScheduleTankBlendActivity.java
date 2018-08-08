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
import com.vnpt.iot.hoalac.core.model.ScheduleListApp;
import com.vnpt.iot.hoalac.core.model.ScheduleTankBlend;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleTankBlendActivity extends CommonActivity {
    @BindView(R.id.name) TextView name;
    @BindView(R.id.code) TextView code;
    @BindView(R.id.sectorName) TextView sectorName;
    @BindView(R.id.gateway) TextView gateway;
    @BindView(R.id.nutrientTank) TextView nutrientTank;
    @BindView(R.id.startDate) TextView startDate;
    @BindView(R.id.endDate) TextView endDate;
    @BindView(R.id.fromEC) TextView fromEC;
    @BindView(R.id.toEC) TextView toEC;
    @BindView(R.id.progress) TextView progress;
    @BindView(R.id.extraPumpWhenEC) TextView extraPumpWhenEC;
    @BindView(R.id.startTimeAeration) TextView startTimeAeration;
    @BindView(R.id.endTimeAeration) TextView endTimeAeration;

    private ScheduleListApp scheduleListApp;
    private GreenHouse sector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_tank_blend);
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
                ScheduleTankBlend scheduleTankBlend = gson.fromJson(result.get_jsonObject().toString(), ScheduleTankBlend.class);
                loadView(scheduleTankBlend);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
            }
        });
    }

    private void loadView(ScheduleTankBlend scheduleTankBlend) {
        name.setText(refactorTitle(scheduleTankBlend.getName()));
        code.setText(scheduleTankBlend.getCode());
        nutrientTank.setText(scheduleTankBlend.getTankName());
        progress.setText(scheduleTankBlend.getProgressName());
        gateway.setText(scheduleTankBlend.getGatewayName());
        startDate.setText(scheduleTankBlend.getStartTime());
        endDate.setText(scheduleTankBlend.getEndTime());
        fromEC.setText(String.valueOf(scheduleTankBlend.getEcTankFrom()));
        toEC.setText(String.valueOf(scheduleTankBlend.getEcTankTo()));
        extraPumpWhenEC.setText(String.valueOf(scheduleTankBlend.getEcTankMin()));
        startTimeAeration.setText(String.valueOf(scheduleTankBlend.getAerationStart()));
        endTimeAeration.setText(String.valueOf(scheduleTankBlend.getAerationStop()));

        sectorName.setText(sector.getName());
    }

    public void pressBack(View view) {
        finish();
    }
}
