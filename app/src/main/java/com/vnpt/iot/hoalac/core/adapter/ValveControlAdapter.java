package com.vnpt.iot.hoalac.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.model.BedsDetail;
import com.vnpt.iot.hoalac.core.model.DeviceList;

import java.util.List;

/**
 * Created by thohc on 7/17/17.
 */

public class ValveControlAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DeviceList> devices;
    private DeviceList device;


    public ValveControlAdapter(Activity activity, List<DeviceList> devices) {
        this.activity = activity;
        this.devices = devices;
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
            convertView = inflater.inflate(R.layout.item_device_control, null);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView bed = (TextView) convertView.findViewById(R.id.bed);
        RelativeLayout control = (RelativeLayout) convertView.findViewById(R.id.control);
        ImageView img = (ImageView) convertView.findViewById(R.id.icon);
        TextView textStatus = (TextView) convertView.findViewById(R.id.textStatus);

        device = devices.get(position);
        name.setText(device.getName());

        if ((device.getBeds() != null) && (device.getBeds().size() > 0)) {
            String bedName = "";
            for (BedsDetail bedsDetail :  device.getBeds()) {
                bedName += bedsDetail.getName()+", ";
            }
            bedName = bedName.substring(0, bedName.length() - 2);
            bed.setText(bedName);
        }

        return convertView;
    }

    public void updateList(List<DeviceList> devices){
        this.devices = devices;
        notifyDataSetChanged();
    }
}
