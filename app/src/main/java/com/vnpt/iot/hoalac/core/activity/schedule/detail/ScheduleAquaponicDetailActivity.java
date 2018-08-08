package com.vnpt.iot.hoalac.core.activity.schedule.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.AquaticConfigurationDetail;
import com.vnpt.iot.hoalac.core.model.EcJson;
import com.vnpt.iot.hoalac.core.model.GreenHouse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleAquaponicDetailActivity extends CommonActivity {
    @BindView(R.id.sectorName) TextView sectorName;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.code) TextView code;
    @BindView(R.id.area) TextView area;
    @BindView(R.id.progress) TextView progress;
    @BindView(R.id.gateway) TextView gateway;
    @BindView(R.id.startDate) TextView startDate;
    @BindView(R.id.endDate) TextView endDate;
    @BindView(R.id.nutrientTank) TextView nutrientTank;
    @BindView(R.id.timeOfRunAgain) TextView timeOfRunAgain;
    @BindView(R.id.stopWhenTemperature) TextView stopWhenTemperature;
    @BindView(R.id.timePumpA) TextView timePumpA;
    @BindView(R.id.timePumpB) TextView timePumpB;
    @BindView(R.id.PHBellow) TextView PHBellow;
    @BindView(R.id.PHAbove) TextView PHAbove;
    @BindView(R.id.mainContainer) LinearLayout mainContainer;

    private AquaticConfigurationDetail aquaticConfigurationDetail;
    private GreenHouse sector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_aquaponic_detail);
        commonSetting();
        ButterKnife.bind(this);
        aquaticConfigurationDetail = gson.fromJson(getIntent().getStringExtra("data"), AquaticConfigurationDetail.class);
        sector = AppSharedPreferences.getGreenHouse(getApplicationContext());
        loadView(aquaticConfigurationDetail);
    }

    private void loadView(AquaticConfigurationDetail aquaticConfigurationDetail) {
        sectorName.setText(sector.getName());
        name.setText(refactorTitle(aquaticConfigurationDetail.getName()));
        code.setText(aquaticConfigurationDetail.getScheduleCode());
        area.setText(aquaticConfigurationDetail.getBedName());
        progress.setText(aquaticConfigurationDetail.getProgress());
        gateway.setText(aquaticConfigurationDetail.getGatewayName());
        startDate.setText(aquaticConfigurationDetail.getDate_start());
        endDate.setText(aquaticConfigurationDetail.getDate_end());
        nutrientTank.setText(aquaticConfigurationDetail.getTank());
        timeOfRunAgain.setText(aquaticConfigurationDetail.getTime_exec());
        stopWhenTemperature.setText(aquaticConfigurationDetail.getStop_temperature());
        timePumpA.setText(aquaticConfigurationDetail.getTime_exec_val_A());
        timePumpB.setText(aquaticConfigurationDetail.getTime_exec_val_B());
        PHBellow.setText(aquaticConfigurationDetail.getPh_below());
        PHAbove.setText(aquaticConfigurationDetail.getPh_above());

        if ((aquaticConfigurationDetail.getListEc() != null) && (aquaticConfigurationDetail.getListEc().size() > 0)) {
            for(EcJson ecJson : aquaticConfigurationDetail.getListEc()) {
                View viewEc = View.inflate(getApplicationContext(), R.layout.item_aqua_ec, null);
                mainContainer.addView(viewEc);

                TextView from = (TextView) viewEc.findViewById(R.id.from);
                TextView to = (TextView) viewEc.findViewById(R.id.to);
                TextView ECAbove = (TextView) viewEc.findViewById(R.id.ECAbove);
                TextView ECBellow = (TextView) viewEc.findViewById(R.id.ECBellow);

                from.setText(ecJson.getFromHour());
                to.setText(ecJson.getToHour());
                ECAbove.setText(ecJson.getToEc());
                ECBellow.setText(ecJson.getFromEc());
            }
        }

    }

    public void pressBack(View view) {
        finish();
    }
}
