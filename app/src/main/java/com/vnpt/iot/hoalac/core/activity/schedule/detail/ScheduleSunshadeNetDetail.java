package com.vnpt.iot.hoalac.core.activity.schedule.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.model.FogFanConfigurationDetail;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleSunshadeNetDetail extends CommonActivity {
    @BindView(R.id.name) TextView name;
    @BindView(R.id.sector) TextView sector;
    @BindView(R.id.gateway) TextView gateway;
    @BindView(R.id.from) TextView from;
    @BindView(R.id.to) TextView to;
    @BindView(R.id.openHightC) TextView openHightC;
    @BindView(R.id.closeLowC) TextView closeLowC;

    private FogFanConfigurationDetail fogFanConfigurationDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_sunshade_net_detail);
        commonSetting();
        ButterKnife.bind(this);

        fogFanConfigurationDetail = gson.fromJson(getIntent().getStringExtra("data"), FogFanConfigurationDetail.class);
        loadView();
    }

    private void loadView(){
        name.setText(refactorTitle(fogFanConfigurationDetail.getName()));
        sector.setText(fogFanConfigurationDetail.getSector());
        gateway.setText(fogFanConfigurationDetail.getGatewayName());
        from.setText(fogFanConfigurationDetail.getShadow_time_below());
        to.setText(fogFanConfigurationDetail.getShadow_time_above());
        openHightC.setText(fogFanConfigurationDetail.getShadow_temp_above());
        closeLowC.setText(fogFanConfigurationDetail.getShadow_temp_below());
    }

    public void pressBack(View view) {
        onBackPressed();
    }
}
