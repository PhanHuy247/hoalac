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
import com.vnpt.iot.hoalac.core.model.Phase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by thohc on 7/24/17.
 */

public class DailyGrowthAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Phase> phases;
    private Phase phase;
    private String thisDate;
    private SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");

    public DailyGrowthAdapter(Activity activity, List<Phase> phases, String thisDate) {
        this.activity = activity;
        this.phases = phases;
        this.thisDate = thisDate;

    }

    @Override
    public int getCount() {
        return phases.size();
    }

    @Override
    public Object getItem(int position) {
        return phases.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_growth, null);
        TextView name = (TextView) convertView.findViewById(R.id.workName);
        TextView bed = (TextView) convertView.findViewById(R.id.bed);
        TextView time = (TextView) convertView.findViewById(R.id.time);

        phase = phases.get(position);
        name.setText(phase.getName());

        try {
            Date endTime = myFormat.parse(phase.getEndTime());
            Date startTime = myFormat.parse(phase.getStartTime());
            Date now = myFormat.parse(thisDate);

            if (now.before(startTime)) {
                time.setText(activity.getResources().getString(R.string.notStart));
            } else if (now.after(endTime)) {
                time.setText(activity.getResources().getString(R.string.over));
            } else {
                long diff = endTime.getTime() - now.getTime();
                long times = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                time.setText(String.valueOf(times)+" "+activity.getResources().getString(R.string.day));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if ((phase.getLstBeds() != null) && (phase.getLstBeds().size() > 0)) {
            String bedText = "";
            for (AccListSmall accListSmall : phase.getLstBeds()) {
                bedText += accListSmall.getName()+", ";
            }

            bedText = bedText.substring(0, bedText.length() - 2);
            bed.setText(bedText);
        }

        return convertView;
    }

    public void updateList(List<Phase> phases){
        this.phases = phases;
        notifyDataSetChanged();
    }
}
