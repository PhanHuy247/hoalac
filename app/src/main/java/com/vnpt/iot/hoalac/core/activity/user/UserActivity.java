package com.vnpt.iot.hoalac.core.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {
    @BindView(R.id.name) TextView name;
    @BindView(R.id.email) TextView email;
    @BindView(R.id.address) TextView address;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        user = AppSharedPreferences.getUser(getApplicationContext());
        loadDataUser();
    }

    public void goEdit(View view){
        startActivity(new Intent(this, EditUserActivity.class));
    }

    public void goChangePass(View view){
        startActivity(new Intent(this, ChangePassActivity.class));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        boolean update = intent.getBooleanExtra("update", false);
        if (update) {
            user = AppSharedPreferences.getUser(getApplicationContext());
            loadDataUser();
        }
    }

    private void loadDataUser(){
        name.setText(user.getName());
        email.setText(user.getEmail());
        address.setText(user.getAddress());
    }

    public void pressBack(View view) {onBackPressed();}
}
