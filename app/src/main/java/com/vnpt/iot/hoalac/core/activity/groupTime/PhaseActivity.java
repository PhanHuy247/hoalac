package com.vnpt.iot.hoalac.core.activity.groupTime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.PhaseApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.adapter.PhaseAdapter;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.Farm;
import com.vnpt.iot.hoalac.core.model.GreenHouse;
import com.vnpt.iot.hoalac.core.model.Parse;
import com.vnpt.iot.hoalac.core.model.Phase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhaseActivity extends CommonActivity implements SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.list) ListView listView;
    @BindView(R.id.swipe) SwipeRefreshLayout swipe;
    private List<Phase> phases = new ArrayList<>();
    private PhaseAdapter phaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_group_time);
        ButterKnife.bind(this);
        initObject();
        loadData();
    }

    private void loadData(){
        swipe.setRefreshing(true);
        HashMap<String, Object> param = new HashMap<>();
        GreenHouse greenHouse = AppSharedPreferences.getGreenHouse(getApplicationContext());
        Farm farm = AppSharedPreferences.getFarm(getApplicationContext());
        param.put("sectorId", greenHouse.getId());
        param.put("farmId", farm.getId());
        PhaseApi.getList(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                swipe.setRefreshing(false);
                if (result.get_jsonObject() == null) return;
                phases = Parse.getInstance().getListPhase(result);
                phaseAdapter.updateList(phases);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                swipe.setRefreshing(false);
            }
        });
    }

    private void initObject(){
        listView.setDivider(null);
        listView.setDividerHeight(0);
        phaseAdapter = new PhaseAdapter(this, phases);
        listView.setAdapter(phaseAdapter);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(getResources().getColor(R.color.green));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Phase phase = phases.get(position);
                goDetail(phase);
            }
        });
    }

    private void goDetail(Phase phase) {
        String data = gson.toJson(phase);
        Intent intent = new Intent(this, PhaseDetailActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    public void pressBack(View view) {
        onBackPressed();
    }

    @Override
    public void onRefresh() {
        loadData();
    }
}
