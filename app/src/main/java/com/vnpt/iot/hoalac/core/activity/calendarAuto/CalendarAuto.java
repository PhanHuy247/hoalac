package com.vnpt.iot.hoalac.core.activity.calendarAuto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.adapter.CalendarAutoAdapter;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.vnpt.iot.hoalac.core.model.HouseChicken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarAuto extends CommonActivity {
    List<HouseChicken> houseChickenList;
    CalendarAutoAdapter calendarAutoAdapter;

    @BindView(R.id.lvCalendarAuto)
    ListView lvCalendar;
    @BindView(R.id.txtAutoCalendar)
    TextView txtAutoCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_auto);
        ButterKnife.bind(this);
        commonSetting();
        initData();
        getDataIntent();
        setDataListCalendar();
        setTitleText();
    }

    private void setTitleText() {
        if(houseChickenList != null && !houseChickenList.isEmpty()){
            HouseChicken houseChicken = houseChickenList.get(0);
            switch (houseChicken.getNameObject()){
                case Constatnts.FEEDING_SCHEDULE:
                    txtAutoCalendar.setText(getString(R.string.LICH_CHO_GA_AN));
                    break;
                case Constatnts.COOLING_SCHEDULE:
                    txtAutoCalendar.setText(getString(R.string.LICH_LAM_MAT));
                    break;
                case Constatnts.LIGHT_SCHEDULE:
                    txtAutoCalendar.setText(getString(R.string.LICH_CHIEU_SANG));
                    break;
                case Constatnts.DRUG_SCHEDULE:
                    txtAutoCalendar.setText(getString(R.string.LICH_UONG_NUOC));
                    break;
            }
        }
    }

    private void setDataListCalendar() {
        calendarAutoAdapter.updateList(houseChickenList);
    }
//
    private void initData() {

        lvCalendar.setDivider(null);
        lvCalendar.setDividerHeight(0);
        calendarAutoAdapter = new CalendarAutoAdapter(this,new ArrayList<>());
        lvCalendar.setAdapter(calendarAutoAdapter);
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Constatnts.BUNDLE);
        houseChickenList = (List<HouseChicken>) bundle.getSerializable(Constatnts.LIST_ITEM_HOUSE_CHICKEN);

    }
    public void pressBack(View view) {finish();}
}
