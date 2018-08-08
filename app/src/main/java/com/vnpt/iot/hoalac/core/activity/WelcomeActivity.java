package com.vnpt.iot.hoalac.core.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.common.Utils;

public class WelcomeActivity extends CommonActivity {

    private static final Integer TIME_DELAY = 3000;//ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        commonSetting();

        boolean swLang = getIntent().getBooleanExtra("sw", false);
        if (swLang) Utils.toast(getApplicationContext(), getStringLang(R.string.msgChangeLanguage));
        setContentView(R.layout.activity_welcome);

        //sleep 3s forward login
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }, TIME_DELAY);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
