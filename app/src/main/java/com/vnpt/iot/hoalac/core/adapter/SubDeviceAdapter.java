package com.vnpt.iot.hoalac.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.model.CmdDevice;

import java.util.HashMap;
import java.util.List;

/**
 * Created by thohc on 7/31/17.
 */

public class SubDeviceAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<CmdDevice> devices;
    private CmdDevice device;
    private HashMap<String, String> status;

    public SubDeviceAdapter(Activity activity, List<CmdDevice> devices, HashMap<String, String> status) {
        this.activity = activity;
        this.devices = devices;
        this.status = status;
    }

    @Override
    public int getCount() {
        return devices.size();
    }

    @Override
    public Object getItem(int position) {
        return devices.get(position);
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
            convertView = inflater.inflate(R.layout.item_sub_device_control, null);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        ImageView img = (ImageView) convertView.findViewById(R.id.icon);
        TextView textStatus = (TextView) convertView.findViewById(R.id.textStatus);

        device = devices.get(position);
        name.setText(device.getName());

        if (status.containsKey(device.getDeviceId())) {
            if (status.get(device.getDeviceId()).equals("on")) {
                img.setImageResource(R.mipmap.control_red);
                textStatus.setText(activity.getResources().getString(R.string.on));
                textStatus.setTextColor(activity.getResources().getColor(R.color.red));
            } else {
                img.setImageResource(R.mipmap.control_black);
                textStatus.setTextColor(activity.getResources().getColor(R.color.black));
                textStatus.setText(activity.getResources().getString(R.string.off));
            }
        } else {
            img.setImageResource(R.mipmap.control_black);
            textStatus.setTextColor(activity.getResources().getColor(R.color.black));
            textStatus.setText(activity.getResources().getString(R.string.off));
        }

        return convertView;
    }

    public void updateList(List<CmdDevice> devices){
        this.devices = devices;
        notifyDataSetChanged();
    }

    public void updateStatus(HashMap<String, String> status){
        this.status = status;
        notifyDataSetChanged();
    }
}
