package com.vnpt.iot.hoalac.core.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.softw4re.views.InfiniteListView;
import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.AlarmApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.adapter.AlarmAdapter;
import com.vnpt.iot.hoalac.core.adapter.NotificationAdapter;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.Farm;
import com.vnpt.iot.hoalac.core.model.GreenHouse;
import com.vnpt.iot.hoalac.core.model.Parse;
import com.vnpt.iot.hoalac.core.model.SendAlarmHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends CommonActivity {
    @BindView(R.id.listNotify) ListView listNotify;
    @BindView(R.id.infiniteListView) InfiniteListView infiniteListView;
    private AlarmAdapter alarmAdapter;
    private NotificationAdapter notificationAdapter;
    private ArrayList<SendAlarmHistory> sendAlarmHistories = new ArrayList<>();
    private List<SendAlarmHistory> alarmList;
    private int totalInList = 20;
    private int to = 20;
    private int start = 0;
    private boolean loading;
    private Farm farm;
    private GreenHouse sector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        farm = AppSharedPreferences.getFarm(getApplicationContext());
        sector = AppSharedPreferences.getGreenHouse(getApplicationContext());
        listNotify.setDividerHeight(0);
        listNotify.setDivider(null);

        notificationAdapter = new NotificationAdapter(this, R.layout.item_alarm, sendAlarmHistories);
        infiniteListView.setAdapter(notificationAdapter);
        loadNewItems();
    }

    public void loadNewItems(){
        if (loading) { return; }
        loading = true;
        infiniteListView.startLoading();
        HashMap<String, Object> param = new HashMap<>();
        param.put("farmId", farm.getId());
        param.put("sectorId", sector.getId());
        param.put("total", to);
        param.put("start", start);

        AlarmApi.getListAlarm(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                infiniteListView.stopLoading();
                sendAlarmHistories = Parse.getInstance().alarmList(result);

                infiniteListView.addAll(sendAlarmHistories);

                start = to + 1;
                to += totalInList;

                if (sendAlarmHistories.size() < totalInList ) {
                    infiniteListView.hasMore(false);
                } else {
                    infiniteListView.hasMore(true);
                }

                loading = false;
            }

            @Override
            public void onRequestError(ApiResponse error) {
                infiniteListView.stopLoading();
                loading = false;
            }
        });
    }

    public void refreshList() {
        start = 0;
        to = 20;
        infiniteListView.clearList();
        loadNewItems();
    }

    private void loadData(){

        HashMap<String, Object> param = new HashMap<>();
        param.put("farmId", farm.getId());
        param.put("sectorId", sector.getId());
        param.put("start", 0);
        param.put("total", 20);
        kProgressHUD.show();

        AlarmApi.getListAlarm(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                alarmList = Parse.getInstance().alarmList(result);
                alarmAdapter = new AlarmAdapter(NotificationActivity.this, alarmList);
                listNotify.setAdapter(alarmAdapter);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
            }
        });
    }

    public void pressBack(View view){
        finish();
    }
}
