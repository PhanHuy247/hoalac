package com.vnpt.iot.hoalac.core.activity.dailyNoteActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.model.AccListSmall;
import com.vnpt.iot.hoalac.core.model.Journal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyNoteDetailActivity extends CommonActivity {
    @BindView(R.id.accountName) TextView accountName;
    @BindView(R.id.bed) TextView bed;
    @BindView(R.id.workName) TextView workName;
    @BindView(R.id.from) TextView from;
    @BindView(R.id.to) TextView to;
    @BindView(R.id.status) TextView status;

    private Journal journal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_note_detail);
        commonSetting();
        ButterKnife.bind(this);
        journal = gson.fromJson(getIntent().getStringExtra("data"), Journal.class);
        loadView();
    }

    private void loadView(){
        accountName.setText(journal.getStaffName());
        workName.setText(journal.getWorkName());
        from.setText(journal.getFromDate());
        to.setText(journal.getToDate());

        if ((journal.getLstBeds() != null) && (journal.getLstBeds().size() > 0)){
            String bedName = "";
            for (AccListSmall accListSmall : journal.getLstBeds()) {
                bedName += accListSmall.getName() + ", ";
            }
            bed.setText(bedName.substring(0, bedName.length() - 2));
        }

        String statusText = "";
        switch (journal.getStatus()) {
            case 1:
                statusText = getResources().getString(R.string.complete);
                break;
            case 2:
                statusText = getResources().getString(R.string.processing);
                break;
            case 3:
                statusText = getResources().getString(R.string.incomplete);
                break;
        }
        status.setText(statusText);
    }

    public void pressBack(View view) {
        finish();
    }
}
