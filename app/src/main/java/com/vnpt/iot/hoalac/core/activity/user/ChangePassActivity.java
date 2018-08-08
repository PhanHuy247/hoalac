package com.vnpt.iot.hoalac.core.activity.user;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.UserApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.MD5Convert;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePassActivity extends CommonActivity {
    @BindView(R.id.oldPass) EditText oldPass;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.rePass) EditText rePass;
    private KProgressHUD kProgressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        commonSetting();
        ButterKnife.bind(this);
        initObject();

    }

    private void initObject(){
        kProgressHUD = KProgressHUD.create(this);
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        kProgressHUD.setWindowColor(getResources().getColor(R.color.green));
        rePass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handler = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    changePass();
                    handler = true;
                }
                return handler;
            }
        });
    }

    private void changePass(){
        String oldPass = MD5Convert.md5(this.oldPass.getText().toString());
        String pass = MD5Convert.md5(this.password.getText().toString());
        String rePass = MD5Convert.md5(this.rePass.getText().toString());

        if (!oldPass.equals(AppSharedPreferences.getPassword(getApplicationContext()))) {
            toast(getApplicationContext(), R.string.oldPassErr);
            return;
        }

        if (!pass.equals(rePass)) {
            toast(getApplicationContext(), R.string.rePassNotTrue);
            return;
        }
        kProgressHUD.show();
        HashMap<String, Object> param = new HashMap<>();
        param.put("password", rePass);

        UserApi.update(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                toast(getApplicationContext(), R.string.saveSS);
                AppSharedPreferences.setPassword(getApplicationContext(), rePass);
                onBackPressed();

            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                toast(getApplicationContext(), R.string.saveErr);
            }
        });

    }

    public void goSave(View view) { changePass();}

    public void pressBack(View view) { onBackPressed(); }
}
