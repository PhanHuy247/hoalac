package com.vnpt.iot.hoalac.core.activity.dailyNoteActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.JournalApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.adapter.JournalAdapter;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.Farm;
import com.vnpt.iot.hoalac.core.model.Journal;
import com.vnpt.iot.hoalac.core.model.Parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyNoteActivity extends CommonActivity {
    @BindView(R.id.listJournal) ListView listJournal;
    private JournalAdapter journalAdapter;
    private List<Journal> journals;
    private ArrayList<Object> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_daily_note);
        ButterKnife.bind(this);
        initObject();
        loadData();
    }

    private void initObject(){
        listJournal.setDivider(null);
        listJournal.setDividerHeight(0);
        journalAdapter = new JournalAdapter(this, new ArrayList<>());

        listJournal.setAdapter(journalAdapter);
        listJournal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (data.get(position) instanceof Journal) {
                    Journal journal = (Journal) data.get(position);
                    String data = gson.toJson(journal);
                    Intent intent = new Intent(DailyNoteActivity.this, DailyNoteDetailActivity.class);
                    intent.putExtra("data", data);
                    startActivity(intent);
                }
            }
        });
    }

    private void loadData(){
        Farm farm = AppSharedPreferences.getFarm(getApplicationContext());
        HashMap<String, Object> params = new HashMap<>();
        params.put("farmId", farm.getId());
        JournalApi.getList(getApplicationContext(), params, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                journals = Parse.getInstance().journal(result);
                String date = "";
                data = new ArrayList<Object>();
                for (Journal journal : journals) {
                    if (journal.getFromDate() == null) return;
                    String[] fromDates = journal.getFromDate().split(" ");
                    if (!fromDates[0].equals(date)) {
                        data.add(fromDates[0]);
                        date = fromDates[0];
                    }
                    data.add(journal);
                }

                journalAdapter.updateList(data);

            }

            @Override
            public void onRequestError(ApiResponse error) {

            }
        });
    }

    public void pressBack(View view){
        finish();
    }

    public void goAdd(View view){
        startActivity(new Intent(this, AddDailyNoteActivity.class));
    }


}
