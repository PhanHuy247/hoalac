package com.vnpt.iot.hoalac.core.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softw4re.views.InfiniteListAdapter;
import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.activity.NotificationActivity;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.Utils;
import com.vnpt.iot.hoalac.core.model.GreenHouse;
import com.vnpt.iot.hoalac.core.model.SendAlarmHistory;

import java.util.ArrayList;
import java.util.List;

import static com.vnpt.iot.hoalac.R.id.valueRule;

/**
 * Created by thohc on 8/10/17.
 */

public class NotificationAdapter<T> extends InfiniteListAdapter<T> {
    List<T> notificationList;
    private int itemLayoutRes;
    NotificationActivity activity;
    SendAlarmHistory sendAlarmHistory;

    public NotificationAdapter(NotificationActivity activity, int itemLayoutRes, ArrayList<T> notificationList) {
        super(activity, itemLayoutRes, notificationList);
        this.activity = activity;
        this.itemLayoutRes = itemLayoutRes;
        this.notificationList = notificationList;
    }

    @Override
    public void onNewLoadRequired() {
        activity.loadNewItems();
    }

    @Override
    public void onRefresh() {
        activity.refreshList();
    }

    @Override
    public void onItemClick(int i) {

    }

    @Override
    public void onItemLongClick(int i) {

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(itemLayoutRes, parent, false);

            holder = new ViewHolder();
            holder.subject = (TextView) convertView.findViewById(R.id.subject);
            holder.greenHouse = (TextView) convertView.findViewById(R.id.greenHouse);
            holder.value = (TextView) convertView.findViewById(R.id.value);
            holder.valueRule = (TextView) convertView.findViewById(valueRule);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            holder.time = (TextView) convertView.findViewById(R.id.time);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        sendAlarmHistory = (SendAlarmHistory) notificationList.get(position);

        GreenHouse gH = AppSharedPreferences.getGreenHouse(activity.getApplicationContext());
        holder.subject.setText(Utils.typeAlarm(activity, sendAlarmHistory.getType()));
        holder.greenHouse.setText(gH.getName());
        holder.value.setText(String.valueOf(sendAlarmHistory.getOverValue())+ " " + Utils.unitAlarm(sendAlarmHistory.getType(), gH.getTypeCul()));
        holder.valueRule.setText(String.valueOf(sendAlarmHistory.getRuleValue())+ " " + Utils.unitAlarm(sendAlarmHistory.getType(), gH.getTypeCul()));
        String[] sp = sendAlarmHistory.getContent().split(" - ");
        if (sp.length >= 3) {
            holder.content.setText(sp[2]);
        }
        holder.time.setText(sendAlarmHistory.getCreatedDate());

        return convertView;
    }

    static class ViewHolder {
        TextView subject;
        TextView greenHouse;
        TextView value;
        TextView valueRule;
        TextView content;
        TextView time;
    }
}
