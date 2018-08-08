package com.vnpt.iot.hoalac.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.model.HouseChickenScheduleDetail;

import java.util.ArrayList;
import java.util.List;

public class CalendarItemAutoAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    List<HouseChickenScheduleDetail> houseChickenList;
    List<String> houseChickens;
    private HouseChickenScheduleDetail houseChicken;
    private String houseChickenDrug;

    public CalendarItemAutoAdapter(Activity activity, List<HouseChickenScheduleDetail> houseChickenList) {
        this.activity = activity;
        this.houseChickenList = houseChickenList;
        houseChickens = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return houseChickens.size() == 0 ? houseChickenList.size() : houseChickens.size();
    }

    @Override
    public Object getItem(int position) {
        return houseChickens.size() == 0 ? houseChickenList.get(position) : houseChickens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_list_calendar, null);
            holder.txtTime = (TextView) convertView.findViewById(R.id.txtTime);
            holder.txtNumberMaximum = (TextView) convertView.findViewById(R.id.txtNumberMaximum);

            holder.txtTimeValue = (TextView) convertView.findViewById(R.id.txtTimeValue);
            holder.txtNumberMaximumValue = (TextView) convertView.findViewById(R.id.txtNumberMaximumValue);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        if(houseChickenList != null && !houseChickenList.isEmpty()) {
            houseChicken = houseChickenList.get(position);
            if (houseChicken.getStopTime() != null) {
                holder.txtTime.setText(activity.getString(R.string.TO));
                holder.txtNumberMaximum.setText(activity.getString(R.string.FROM));
                holder.txtTimeValue.setText(houseChicken.getStartTime());
                holder.txtNumberMaximumValue.setText(houseChicken.getStopTime());
            } else {
                holder.txtTime.setText(activity.getString(R.string.TIME));
                holder.txtNumberMaximum.setText(activity.getString(R.string.NUMBER_MAXIMUM));
                holder.txtTimeValue.setText(houseChicken.getStartTime());
                holder.txtNumberMaximumValue.setText(houseChicken.getMaxWeight());
            }
        }else {
            houseChickenDrug = houseChickens.get(position);
            holder.txtTime.setText(activity.getString(R.string.TIME));
            holder.txtNumberMaximum.setVisibility(View.GONE);
            holder.txtTimeValue.setText(houseChickenDrug);
            holder.txtNumberMaximumValue.setVisibility(View.GONE);;
        }
        return convertView;
    }

    public void updateList(List<HouseChickenScheduleDetail> houseChickenList){
        this.houseChickenList = houseChickenList;
        notifyDataSetChanged();
    }

    public void updateListDrug(List<String> houseChickenList){
        this.houseChickens = houseChickenList;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        TextView txtTime ;
        TextView txtNumberMaximum;

        TextView txtTimeValue;
        TextView txtNumberMaximumValue;

    }
}
