package com.vnpt.iot.hoalac.core.activity.groupTime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.activity.MainActivity;

public class SummaryGroupTimeActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_summary_group_time);
    }

    public void pressBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
