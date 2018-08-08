package com.vnpt.iot.hoalac.core.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.Utils;

import java.util.Locale;


public class CommonActivity extends AppCompatActivity{
    public Gson gson = new Gson();
    public KProgressHUD kProgressHUD;
    public void commonSetting(){

        if (AppSharedPreferences.getLanguage(getApplicationContext()) == null) {
            String deviceLang = Locale.getDefault().getLanguage();
            if (!Utils.languageApp().contains(deviceLang)){
                deviceLang = "en";
            }
            AppSharedPreferences.setLanguage(getApplicationContext(), deviceLang);
        }
        rotateDislay();
        Configuration config = getResources().getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            String language = AppSharedPreferences.getLanguage(getApplicationContext());
            config.setLocale(new Locale(language));
            getResources().updateConfiguration(config, null);
        }

        kProgressHUD = KProgressHUD.create(this);
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        kProgressHUD.setWindowColor(getResources().getColor(R.color.colorPrimary));
    }

    private void rotateDislay() {
        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    public String refactorTitle(String text) {
        if ((text != null)  && (text.length() > 20)) {
            text = text.substring(0, 20) + "...";
        }
        return text;
    }

    public void toast(Context c, int textId){
        Toast.makeText(c, getStringLang(textId), Toast.LENGTH_SHORT).show();
    }

    public String getStringLang(int id) {
        return getResources().getString(id);
    }

}
