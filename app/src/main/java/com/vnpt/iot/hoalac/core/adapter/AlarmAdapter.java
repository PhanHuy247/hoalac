package com.vnpt.iot.hoalac.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.Utils;
import com.vnpt.iot.hoalac.core.model.GreenHouse;
import com.vnpt.iot.hoalac.core.model.SendAlarmHistory;

import java.util.List;

/**
 * Created by thohc on 7/26/17.
 */

public class AlarmAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SendAlarmHistory> sendAlarmHistories;
    private SendAlarmHistory sendAlarmHistory;

    public AlarmAdapter(Activity activity, List<SendAlarmHistory> sendAlarmHistories) {
        this.activity = activity;
        this.sendAlarmHistories = sendAlarmHistories;
    }

    @Override
    public int getCount() {
        return sendAlarmHistories.size();
    }

    @Override
    public Object getItem(int position) {
        return sendAlarmHistories.get(position);
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
            convertView = inflater.inflate(R.layout.item_alarm, null);
        TextView subject = (TextView) convertView.findViewById(R.id.subject);
        TextView greenHouse = (TextView) convertView.findViewById(R.id.greenHouse);
        TextView value = (TextView) convertView.findViewById(R.id.value);
        TextView valueRule = (TextView) convertView.findViewById(R.id.valueRule);
        TextView content = (TextView) convertView.findViewById(R.id.content);
        TextView time = (TextView) convertView.findViewById(R.id.time);

        sendAlarmHistory = sendAlarmHistories.get(position);

        GreenHouse gH = AppSharedPreferences.getGreenHouse(activity.getApplicationContext());
        subject.setText(Utils.typeAlarm(activity, sendAlarmHistory.getType()));
        greenHouse.setText(gH.getName());
        value.setText(String.valueOf(sendAlarmHistory.getOverValue())+ " " + Utils.unitAlarm(sendAlarmHistory.getType(), gH.getTypeCul()));
        valueRule.setText(String.valueOf(sendAlarmHistory.getRuleValue())+ " " + Utils.unitAlarm(sendAlarmHistory.getType(), gH.getTypeCul()));
        String[] sp = sendAlarmHistory.getContent().split(" - ");
        if (sp.length >= 3) {
            content.setText(sp[2]);
        }
        time.setText(sendAlarmHistory.getCreatedDate());
        return convertView;
    }

    public void updateList(List<SendAlarmHistory> sendAlarmHistories){
        this.sendAlarmHistories = sendAlarmHistories;
        notifyDataSetChanged();
    }
}
