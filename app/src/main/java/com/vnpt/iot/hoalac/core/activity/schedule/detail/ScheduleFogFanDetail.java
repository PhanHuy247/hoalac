package com.vnpt.iot.hoalac.core.activity.schedule.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.vnpt.iot.hoalac.core.model.FogFanConfigurationDetail;
import com.vnpt.iot.hoalac.core.model.GreenHouse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleFogFanDetail extends CommonActivity {
    @BindView(R.id.name) TextView name;
    @BindView(R.id.sectorName) TextView sectorName;
    @BindView(R.id.gateway) TextView gateway;
    @BindView(R.id.startDate) TextView startDate;
    @BindView(R.id.endDate) TextView endDate;
    @BindView(R.id.startWhen) TextView startWhen;
    @BindView(R.id.turnOffWhen) TextView turnOffWhen;
    @BindView(R.id.startAfterSpray) TextView startAfterSpray;
    @BindView(R.id.startWhenHightPercent) TextView startWhenHightPercent;
    @BindView(R.id.stopWhenLowPercent) TextView stopWhenLowPercent;
    @BindView(R.id.stopPerTimeMinute) TextView stopPerTimeMinute;
    @BindView(R.id.startWhenLowPercent) TextView startWhenLowPercent;
    @BindView(R.id.stopWhenHightPercent) TextView stopWhenHightPercent;
    @BindView(R.id.checkFromTime) TextView checkFromTime;
    @BindView(R.id.turnOffWhenTitle) TextView turnOffWhenTitle;
    @BindView(R.id.toTime) TextView toTime;
    @BindView(R.id.llHydro) LinearLayout llHydro;

    private FogFanConfigurationDetail fogFanConfigurationDetail;
    private GreenHouse sector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_fog_fan_detail);
        ButterKnife.bind(this);
        commonSetting();
        fogFanConfigurationDetail = gson.fromJson(getIntent().getStringExtra("data"), FogFanConfigurationDetail.class);
        sector = AppSharedPreferences.getGreenHouse(getApplicationContext());
        loadView(fogFanConfigurationDetail);
    }

    private void loadView(FogFanConfigurationDetail fogFanConfigurationDetail){
        name.setText(refactorTitle(fogFanConfigurationDetail.getName()));
        sectorName.setText(fogFanConfigurationDetail.getSector());
        gateway.setText(fogFanConfigurationDetail.getGatewayName());
        startDate.setText(fogFanConfigurationDetail.getDate_start());
        endDate.setText(fogFanConfigurationDetail.getDate_end());
        checkFromTime.setText(fogFanConfigurationDetail.getValid_time_below());
        toTime.setText(fogFanConfigurationDetail.getValid_time_above());

        startWhen.setText(String.valueOf(fogFanConfigurationDetail.getFog_start_when_temp_gt()));
        turnOffWhen.setText(String.valueOf(fogFanConfigurationDetail.getFog_stop_when_temp_lt()));

        startAfterSpray.setText(String.valueOf(fogFanConfigurationDetail.getFan_start_in()));
        startWhenHightPercent.setText(String.valueOf(fogFanConfigurationDetail.getFan_start_when_humid_gt()));
        stopWhenLowPercent.setText(String.valueOf(fogFanConfigurationDetail.getFan_stop_when_humid_lt()));

        if (sector.getTypeCul() == Constatnts.SECTOR_THUYCANH) {
            llHydro.setVisibility(View.VISIBLE);
            stopPerTimeMinute.setText(String.valueOf(fogFanConfigurationDetail.getFog_stop_in()));
            startWhenLowPercent.setText(String.valueOf(fogFanConfigurationDetail.getFog_start_when_humid_lt()));
            stopWhenHightPercent.setText(String.valueOf(fogFanConfigurationDetail.getFog_stop_when_humid_gt()));
            turnOffWhenTitle.setText(R.string.durationMin);
        }
    }

    public void pressBack(View view) {
        finish();
    }
}
