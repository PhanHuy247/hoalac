package com.vnpt.iot.hoalac.core.activity.user;

import android.content.Intent;
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
import com.vnpt.iot.hoalac.core.activity.MainActivity;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.User;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditUserActivity extends CommonActivity {
    @BindView(R.id.name) EditText name;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.address) EditText address;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        commonSetting();
        ButterKnife.bind(this);
        user = AppSharedPreferences.getUser(getApplicationContext());
        initObject();
        loadDataUser();

    }

    private void initObject(){
        kProgressHUD = KProgressHUD.create(this);
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        kProgressHUD.setWindowColor(getResources().getColor(R.color.green));

        address.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handler = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    saveInfo();
                    handler = true;
                }

                return handler;
            }
        });
    }

    private void loadDataUser(){
        name.setText(user.getName());
        email.setText(user.getEmail());
        address.setText(user.getAddress());
    }

    public void goSave(View view){
        saveInfo();
    }

    private void saveInfo(){
        HashMap<String, Object> param = new HashMap<>();
        param.put("name", name.getText().toString());
        param.put("address", address.getText().toString());
        param.put("email", email.getText().toString());
        kProgressHUD.show();
        UserApi.update(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                user.setEmail(email.getText().toString());
                user.setName(name.getText().toString());
                user.setAddress(address.getText().toString());
                AppSharedPreferences.setUser(getApplicationContext(), user);
                if (MainActivity.accountName != null) MainActivity.accountName.setText(name.getText().toString());
                if (MainActivity.accountEmail != null) MainActivity.accountEmail.setText(email.getText().toString());
                backUpdateInfo();
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                toast(getApplicationContext(), R.string.saveErr);
            }
        });
    }

    private void backUpdateInfo(){
        Intent intent = new Intent(this, UserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("update", true);
        startActivity(intent);
        finish();
    }

    public void pressBack(View view) {
        onBackPressed();
    }
}
