package com.vnpt.iot.hoalac.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.model.AccListSmall;
import com.vnpt.iot.hoalac.core.model.Journal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by thohc on 7/4/17.
 */

public class JournalAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<Object> journals;
    private Journal journal;
    static final int ROW = 0;
    static final int HEADER = 1;
    private DateFormat stringToDateFM = new SimpleDateFormat("dd-MM-yyyy");
    private DateFormat dateToStringFM = new SimpleDateFormat("EEEE dd-MM-yyyy");
    private Date fromDate;


    public JournalAdapter(Activity activity, ArrayList<Object> journals) {
        this.activity = activity;
        this.journals = journals;
    }

    @Override
    public int getCount() {
        return journals.size();
    }

    @Override
    public Object getItem(int position) {
        return journals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);

        if (convertView == null){
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (type) {
                case ROW:
                    convertView = inflater.inflate(R.layout.item_journal, null);
                    break;
                case HEADER:
                    convertView = inflater.inflate(R.layout.item_journal_header, null);
                    break;
                default:
                    break;

            }
        }

        switch (type) {
            case ROW:
                journal = (Journal) getItem(position);
                TextView workName = (TextView) convertView.findViewById(R.id.workName);
                workName.setText(journal.getWorkName());

                TextView bed = (TextView) convertView.findViewById(R.id.bed);
                if (journal.getLstBeds().size() > 0) {
                    String beds = "";
                    for (AccListSmall accListSmall : journal.getLstBeds()){
                        beds += accListSmall.getName()+", ";
                    }
                    beds = beds.substring(0, beds.length()-2);
                    bed.setText(beds);
                }

                TextView time = (TextView) convertView.findViewById(R.id.time);
                String dateTime[] = journal.getFromDate().split(" ");
                if (dateTime.length < 1) break;
                String timeDetail[] = dateTime[1].split(":");
                if (timeDetail.length < 1) break;
                time.setText(timeDetail[0]+":"+timeDetail[1]);

                break;
            case HEADER:
                String dateData = (String) getItem(position);
                TextView textView = (TextView) convertView.findViewById(R.id.textHeader);
                try {
                    fromDate = stringToDateFM.parse(dateData);
                    String newDate = dateToStringFM.format(fromDate);
                    textView.setText(newDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                break;
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof Journal) {
            return ROW;
        }

        return HEADER;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    public void updateList(ArrayList<Object> journals){
        this.journals = journals;
        notifyDataSetChanged();
    }
}
