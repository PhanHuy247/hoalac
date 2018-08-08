package com.vnpt.iot.hoalac.core.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.UserApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.user.LostPassActivity;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.MD5Convert;
import com.vnpt.iot.hoalac.core.model.User;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends CommonActivity {
    @BindView(R.id.username) EditText username;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.login) Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        ButterKnife.bind(this);
        initObject();
        checkLogin();


    }

    private void checkLogin(){
        boolean logged = AppSharedPreferences.getRememberLoggedIn(getApplicationContext());

        if (logged) {
            String username = AppSharedPreferences.getUsername(getApplicationContext());
            String password = AppSharedPreferences.getPassword(getApplicationContext());
            loginApp(username, password);
        }
    }

    private void initObject() {
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    getDataSubmit();
                    handled = true;
                }
                return handled;
            }
        });
    }

    public void login(View v) {
        getDataSubmit();
    }

    private void getDataSubmit(){
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        if (username.equals("")) {
            toast(getApplicationContext(), R.string.inputUsername);
            return;
        }

        if (password.equals("")) {
            toast(getApplicationContext(), R.string.inputTextPass);
            return;
        }
        password = MD5Convert.md5(password);

        loginApp(username, password);
    }

    private void loginApp(String username, String password){

        HashMap<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        kProgressHUD.show();
        String finalPassword = password;
        UserApi.login(getApplicationContext(), params, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                AppSharedPreferences.setUsername(getApplicationContext(), username);
                AppSharedPreferences.setPassword(getApplicationContext(), finalPassword);
                User user = gson.fromJson(result.get_jsonObject().toString(), User.class);
                AppSharedPreferences.setUser(getApplicationContext(), user);
                AppSharedPreferences.setRememberLoggedIn(getApplicationContext(), true);
                registerDeviceToken(user);
                selectGreenHouse();
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                toast(getApplicationContext(), R.string.loginParamNotFound);
            }
        });
    }

    private void registerDeviceToken(User user){
        String token = AppSharedPreferences.getDeviceToken(getApplicationContext());
        if (token == null) return;


        HashMap<String, Object> param = new HashMap<>();
        param.put("accountId", user.getId());
        param.put("token", token);
        param.put("deviceMobile", "");
        JSONObject jsonObject = new JSONObject(param);

        UserApi.createToken(getApplicationContext(), jsonObject, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {

            }

            @Override
            public void onRequestError(ApiResponse error) {

            }
        });
    }

    private void selectGreenHouse(){
        Intent intent = new Intent(this, SectorSelectActivity.class);
        startActivity(intent);
        finish();
    }

//    public void pressBack(View view){
//        Intent intent = new Intent(this, WelcomeActivity.class);
//        startActivity(intent);
//        finish();
//    }

    public void goLostPass(View view){
        Intent intent = new Intent(this, LostPassActivity.class);
        startActivity(intent);
    }

}
