package com.vnpt.iot.hoalac.core.activity.dailyNoteActivity;

import android.os.Bundle;
import android.view.View;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;

public class AddDailyNoteActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_add_daily_note);
    }

    public void pressBack(View view){
        finish();
    }

}
