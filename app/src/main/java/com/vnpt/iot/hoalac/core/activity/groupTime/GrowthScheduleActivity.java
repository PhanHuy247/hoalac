package com.vnpt.iot.hoalac.core.activity.groupTime;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.PhaseApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.adapter.DailyGrowthAdapter;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.Farm;
import com.vnpt.iot.hoalac.core.model.GreenHouse;
import com.vnpt.iot.hoalac.core.model.Parse;
import com.vnpt.iot.hoalac.core.model.Phase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GrowthScheduleActivity extends CommonActivity {
    @BindView(R.id.listGrowth) ListView listGrowth;

    private List<Phase> phases = new ArrayList<>();
    private DailyGrowthAdapter dailyGrowthAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_growth_schdule);
        ButterKnife.bind(this);
        initObject();
        loadData();
    }

    private void initObject(){
        listGrowth.setDivider(null);
        listGrowth.setDividerHeight(0);
    }

    private void loadData(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String thisDate = sdf.format(new Date());
        HashMap<String, Object> param = new HashMap<>();
        GreenHouse greenHouse = AppSharedPreferences.getGreenHouse(getApplicationContext());
        Farm farm = AppSharedPreferences.getFarm(getApplicationContext());
        param.put("sectorId", greenHouse.getId());
        param.put("farmId", farm.getId());
        param.put("startTime", thisDate);

        PhaseApi.getList(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                if (result.get_jsonObject() == null) return;
                phases = Parse.getInstance().getListPhase(result);
                dailyGrowthAdapter = new DailyGrowthAdapter(GrowthScheduleActivity.this, phases, thisDate);
                listGrowth.setAdapter(dailyGrowthAdapter);
            }

            @Override
            public void onRequestError(ApiResponse error) {

            }
        });
    }

    public void pressBack(View view){
        finish();
    }

    public void showCalendar(View view){
        final Calendar newCalendar = Calendar.getInstance(TimeZone.getDefault());
        DatePickerDialog dialog = new DatePickerDialog(GrowthScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
