package com.vnpt.iot.hoalac.core.activity.groupTime;

import android.os.Bundle;
import android.view.View;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;

public class CropActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_crop);
    }

    public void pressBack(View view){
        finish();
    }
}
