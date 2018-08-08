package com.vnpt.iot.hoalac.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.model.Procedure;

import java.util.List;

/**
 * Created by thohc on 7/3/17.
 */

public class ProcedureAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Procedure> procedures;
    private Procedure procedure;

    public ProcedureAdapter(Activity activity, List<Procedure> procedures) {
        this.activity = activity;
        this.procedures = procedures;
    }

    @Override
    public int getCount() {
        return procedures.size();
    }

    @Override
    public Object getItem(int position) {
        return procedures.get(position);
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
            convertView = inflater.inflate(R.layout.item_4_text, null);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView bed = (TextView) convertView.findViewById(R.id.bed);
        TextView dateStart = (TextView) convertView.findViewById(R.id.dateStart);
        TextView dateEnd = (TextView) convertView.findViewById(R.id.dateEnd);
        procedure = procedures.get(position);
        name.setText(procedure.getName());
        bed.setText(activity.getResources().getString(R.string.code) + ": "+procedure.getCode());
        dateStart.setText(activity.getResources().getString(R.string.product)+": "+procedure.getProductName());
        return convertView;
    }

    public void updateList(List<Procedure> procedures){
        this.procedures = procedures;
        notifyDataSetChanged();
    }
}
