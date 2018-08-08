package com.vnpt.iot.hoalac.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.model.ScheduleListApp;

import java.util.List;

/**
 * Created by thohc on 7/19/17.
 */

public class ScheduleAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    Activity activity;
    List<ScheduleListApp> controlLists;

    public ScheduleAdapter(Activity activity, List<ScheduleListApp> controlLists) {
        this.activity = activity;
        this.controlLists = controlLists;
    }

    @Override
    public int getCount() {
        return controlLists.size();
    }

    @Override
    public Object getItem(int position) {
        return controlLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolderItem {
        TextView txtName;
//        TextView txtDevice;
        Switch swStatus;
        Button btn;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolderItem viewHolderItem = null;

        if (row == null) {
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_schedule, parent, false);

            viewHolderItem = new ViewHolderItem();
            viewHolderItem.txtName = (TextView) row.findViewById(R.id.name);
//            viewHolderItem.txtDevice = (TextView) row.findViewById(R.id.device);
            viewHolderItem.swStatus = (Switch) row.findViewById(R.id.swStatus);
            viewHolderItem.btn = (Button) row.findViewById(R.id.btn);

            row.setTag(viewHolderItem);
        } else {
            viewHolderItem = (ViewHolderItem) row.getTag();
        }

        ScheduleListApp controlList = controlLists.get(position);

        viewHolderItem.swStatus.setId(position);
        viewHolderItem.txtName.setText(controlList.getName());
//        viewHolderItem.txtDevice.setText(controlList.getTankName());

        if (controlList.getStatus() == 1) viewHolderItem.swStatus.setChecked(true); else viewHolderItem.swStatus.setChecked(false);
        viewHolderItem.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swOnClickItem.swOnClickItem(position);
            }
        });
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClickItem(position);
            }
        });

        return row;
    }

    public void updateList(List<ScheduleListApp> controlLists){
        this.controlLists = controlLists;
        notifyDataSetChanged();
    }

    public interface OnCheckedSwitchButton {
        void onChecked(int position, boolean isChecked);
    }

    OnCheckedSwitchButton onCheckedSwitchButton;

    public void setOnCheckedSwitchButton(OnCheckedSwitchButton onCheckedSwitchButton) {
        this.onCheckedSwitchButton = onCheckedSwitchButton;
    }

    public interface OnClickItem {
        void onClickItem(int position);
    }

    OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }



    public interface SwOnClickItem {
        void swOnClickItem(int position);
    }

    SwOnClickItem swOnClickItem;

    public void setSwOnClickItem(SwOnClickItem swOnClickItem) {
        this.swOnClickItem = swOnClickItem;
    }


}
