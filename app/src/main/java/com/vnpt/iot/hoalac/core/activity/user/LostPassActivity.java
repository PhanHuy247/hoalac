package com.vnpt.iot.hoalac.core.activity.user;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;

public class LostPassActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_pass);
        commonSetting();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
    }

    public void pressBack(View view){
        onBackPressed();
    }
}
