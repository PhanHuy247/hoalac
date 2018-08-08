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

import java.util.List;

/**
 * Created by thohc on 6/21/17.
 */

public class PhaseAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Phase> phases;
    private Phase phase;

    public PhaseAdapter(Activity activity, List<Phase> phases) {
        this.activity = activity;
        this.phases = phases;
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
            convertView = inflater.inflate(R.layout.item_phase, null);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView bed = (TextView) convertView.findViewById(R.id.bed);
        TextView dateStart = (TextView) convertView.findViewById(R.id.startDate);

        phase = phases.get(position);
        name.setText(phase.getName());
        if (phase.getStartTime() != null) {
            String[] startTime = phase.getStartTime().split(" ");
            dateStart.setText(startTime[0]);
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
