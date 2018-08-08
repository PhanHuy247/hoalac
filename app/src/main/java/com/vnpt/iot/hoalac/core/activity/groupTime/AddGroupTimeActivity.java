package com.vnpt.iot.hoalac.core.activity.groupTime;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.custom.multiSelectionSpinner.MultiSelectionSpinner;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class AddGroupTimeActivity extends CommonActivity {
    private MultiSelectionSpinner multiSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_add_group_time);
        initView();
        loadSpinner();
    }

    private void loadSpinner(){
        String[] array = {"Loại cây trồng","Rau muống cạn", "Rau diếp cá", "Rau bạc hà", "Rau diếm cá", "Rau muống cạn", "Rau diếp cá", "Rau bạc hà","Rau muống cạn", "Rau diếp cá", "Rau bạc hà"};
        multiSpinner.setItems(array);
        multiSpinner.setListener(new MultiSelectionSpinner.OnMultipleItemsSelectedListener() {
            @Override
            public void selectedIndices(List<Integer> indices) {

            }

            @Override
            public void selectedStrings(List<String> strings) {

            }
        });

    }

    public void goSubmit(View view){
        startActivity(new Intent(this, SummaryGroupTimeActivity.class));
    }

    public void showCalendar(View view){
        final Calendar newCalendar = Calendar.getInstance(TimeZone.getDefault());
        DatePickerDialog dialog = new DatePickerDialog(AddGroupTimeActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void initView(){
        multiSpinner = (MultiSelectionSpinner) findViewById(R.id.multiSpinner);
    }

    public void pressBack(View view) {
        finish();
    }
}
