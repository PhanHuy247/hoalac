package com.vnpt.iot.hoalac.core.activity.schedule.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.ScheduleApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.GreenHouse;
import com.vnpt.iot.hoalac.core.model.ScheduleListApp;
import com.vnpt.iot.hoalac.core.model.ScheduleTankBaste;
import com.vnpt.iot.hoalac.core.model.TimeBaste;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleTankBasteActivity extends CommonActivity {
    @BindView(R.id.name) TextView name;
    @BindView(R.id.code) TextView code;
    @BindView(R.id.sectorName) TextView sectorName;
    @BindView(R.id.gateway) TextView gateway;
    @BindView(R.id.nutrientTank) TextView nutrientTank;
    @BindView(R.id.progress) TextView progress;
    @BindView(R.id.startDate) TextView startDate;
    @BindView(R.id.endDate) TextView endDate;
    @BindView(R.id.numberSensor) TextView numberSensor;
    @BindView(R.id.timeDelay) TextView timeDelay;
    @BindView(R.id.soilMax) TextView soilMax;
    @BindView(R.id.maxTimePump) TextView maxTimePump;
    @BindView(R.id.basteWaterMinuteTank) TextView basteWaterMinuteTank;
    @BindView(R.id.whenECTank) TextView whenECTank;
    @BindView(R.id.turnOnWhenBelowPH) TextView turnOnWhenBelowPH;
    @BindView(R.id.turnOnWhenBelowHumidity) TextView turnOnWhenBelowHumidity;
    @BindView(R.id.turnOnWhenEC) TextView turnOnWhenEC;
    @BindView(R.id.llTime) LinearLayout llTime;

    private ScheduleListApp scheduleListApp;
    private GreenHouse sector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_tank_baste);
        ButterKnife.bind(this);
        commonSetting();
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
                ScheduleTankBaste scheduleTankBaste = gson.fromJson(result.get_jsonObject().toString(), ScheduleTankBaste.class);
                loadView(scheduleTankBaste);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
            }
        });
    }

    private void loadView(ScheduleTankBaste scheduleTankBaste){
        name.setText(refactorTitle(scheduleTankBaste.getName()));
        code.setText(scheduleTankBaste.getCode());
        sectorName.setText(sector.getName());
        gateway.setText(scheduleTankBaste.getGatewayName());
        startDate.setText(scheduleTankBaste.getStartTime());
        endDate.setText(scheduleTankBaste.getEndTime());
        numberSensor.setText(scheduleTankBaste.getNumberSensor());
        timeDelay.setText(scheduleTankBaste.getTimeDelay());
        soilMax.setText(scheduleTankBaste.getSoilMax());
        maxTimePump.setText(scheduleTankBaste.getMaxTimePump());
        basteWaterMinuteTank.setText(scheduleTankBaste.getBasteWaterMinuteTank());
        whenECTank.setText(String.valueOf(scheduleTankBaste.getEcTankValue()));
        turnOnWhenBelowPH.setText(String.valueOf(scheduleTankBaste.getPhValue()));
        turnOnWhenBelowHumidity.setText(String.valueOf(scheduleTankBaste.getSoilValue()));
        turnOnWhenEC.setText(String.valueOf(scheduleTankBaste.getEcValue()));
        nutrientTank.setText(scheduleTankBaste.getTankId());
        progress.setText(scheduleTankBaste.getProgressName());
        if ((scheduleTankBaste.getListTime() != null) && (scheduleTankBaste.getListTime().size() > 0)) {
            for (TimeBaste timeBaste : scheduleTankBaste.getListTime()) {
                View view = View.inflate(getApplicationContext(), R.layout.item_schedule_tank_baste_time, null);
                TextView time = (TextView) view.findViewById(R.id.time);
                TextView volume = (TextView) view.findViewById(R.id.volume);

                time.setText(timeBaste.getFromHour());
                volume.setText(String.valueOf(timeBaste.getTraffic()));

                llTime.addView(view);
            }
        }
    }

    public void pressBack(View view) {
        finish();
    }
}
