package com.vnpt.iot.hoalac.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.vnpt.iot.hoalac.core.common.Utils;
import com.vnpt.iot.hoalac.core.model.HouseChicken;

import java.util.ArrayList;
import java.util.List;

public class CalendarAutoAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    List<HouseChicken> houseChickenList;
    private HouseChicken houseChicken;
    private CalendarItemAutoAdapter calendarItemAutoAdapter;

    public CalendarAutoAdapter(Activity activity, List<HouseChicken> houseChickenList) {
        this.activity = activity;
        this.houseChickenList = houseChickenList;

    }

    @Override
    public int getCount() {
        return houseChickenList.size();
    }

    @Override
    public Object getItem(int position) {
        return houseChickenList.get(position);
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
            convertView = inflater.inflate(R.layout.item_list_calendar_auto, null);
            holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            holder.txtHouseChicken = (TextView) convertView.findViewById(R.id.txtHouseChicken);
            holder.txtCode = (TextView) convertView.findViewById(R.id.txtCode);
            holder.txtState = (TextView) convertView.findViewById(R.id.txtState);

            holder.txtNameValue = (TextView) convertView.findViewById(R.id.txtNameValue);
            holder.txtHouseChickenValue = (TextView) convertView.findViewById(R.id.txtHouseChickenValue);
            holder.txtCodeValue = (TextView) convertView.findViewById(R.id.txtCodeValue);
            holder.txtStateValue = (TextView) convertView.findViewById(R.id.txtStateValue);
            holder.txtSoLuongQuatChayToiThieuValue = (TextView) convertView.findViewById(R.id.txtSoLuongQuatChayToiThieuValue);
            holder.txtThoiGianDaoQuatThongGioValue = (TextView) convertView.findViewById(R.id.txtThoiGianDaoQuatThongGioValue);
            holder.txtQUATKHITUOIValue = (TextView) convertView.findViewById(R.id.txtQUATKHITUOIValue);
            holder.txtQuatKhiTranValue = (TextView) convertView.findViewById(R.id.txtQuatKhiTranValue);
            holder.txtQuatKhiNgangValue = (TextView) convertView.findViewById(R.id.txtQuatKhiNgangValue);
            holder.txtNhietdotrongchuongTuValue = (TextView) convertView.findViewById(R.id.txtNhietdotrongchuongTuValue);
            holder.txtNhietdotrongchuongDenValue = (TextView) convertView.findViewById(R.id.txtNhietdotrongchuongDenValue);
            holder.txtNhietdongoaichuongDenValue = (TextView) convertView.findViewById(R.id.txtNhietdongoaichuongDenValue);
            holder.txtNhietdongoaichuongTuValue = (TextView) convertView.findViewById(R.id.txtNhietdongoaichuongTuValue);
            holder.txtDOAMchuongTuValue = (TextView) convertView.findViewById(R.id.txtDOAMchuongTuValue);
            holder.txtDOAMchuongDenValue = (TextView) convertView.findViewById(R.id.txtDOAMchuongDenValue);
            holder.txtPPMTUValue = (TextView) convertView.findViewById(R.id.txtPPMTUValue);
            holder.txtPPMDenValue = (TextView) convertView.findViewById(R.id.txtPPMDenValue);

            holder.lvListItem = (ListView) convertView.findViewById(R.id.lvListItem);

            holder.llItemInside = (LinearLayout) convertView.findViewById(R.id.llItemInside);
            holder.llListView = (LinearLayout) convertView.findViewById(R.id.llListItem);
            calendarItemAutoAdapter = new CalendarItemAutoAdapter(activity,new ArrayList<>());
            holder.lvListItem.setAdapter(calendarItemAutoAdapter);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        if(houseChickenList == null || houseChickenList.isEmpty()) return null;

        //check getNameObject item calendar auto
        houseChicken = houseChickenList.get(0);
        switch (houseChicken.getNameObject()){
            case Constatnts.FEEDING_SCHEDULE:
                handleDataFeedingSchedule(holder);
                break;
            case Constatnts.COOLING_SCHEDULE:
                handleDataCoolingSchedule(holder);
                break;
            case Constatnts.LIGHT_SCHEDULE:
                handleDataLightSchedule(holder);
                break;
            case Constatnts.DRUG_SCHEDULE:
                handleDataDrugSchedule(holder);
                break;
        }
        return convertView;
    }

    private void handleDataDrugSchedule(ViewHolder holder) {
        for(HouseChicken houseChicken :houseChickenList){
            holder.txtName.setText(activity.getString(R.string.NAME));
            holder.txtHouseChicken.setText(activity.getString(R.string.HOUSE_CHICKEN));
            holder.txtCode.setText(activity.getString(R.string.CODE));
            holder.txtState.setText(activity.getString(R.string.STATE));

            holder.txtNameValue.setText(houseChicken.getName());
            holder.txtHouseChickenValue.setText(houseChicken.getHenhouseName());
            holder.txtCodeValue.setText(houseChicken.getCode());

            Integer status = Integer.valueOf(houseChicken.getStatusProcess());
            switch (status){
                case 1:
                    holder.txtStateValue.setText(activity.getString(R.string.Success));
                    break;
                case 2:
                    holder.txtStateValue.setText(activity.getString(R.string.InProcess));
                    break;
                case 3:
                    holder.txtStateValue.setText(activity.getString(R.string.Fail));
                    break;
                case 4:
                    holder.txtStateValue.setText(activity.getString(R.string.Test));
                    break;
                case 5:
                    holder.txtStateValue.setText(activity.getString(R.string.ExpiredTest));
                    break;
                default:
                    holder.txtStateValue.setText(activity.getString(R.string.InProcess));
                    break;
            }
            calendarItemAutoAdapter.updateListDrug(houseChicken.getTimeListDrug());
            Utils.setListViewHeightBasedOnChildren(holder.lvListItem);
            holder.llItemInside.setVisibility(View.GONE);
        }
    }

    private void handleDataLightSchedule(ViewHolder holder) {
        for(HouseChicken houseChicken :houseChickenList){
            holder.txtName.setText(activity.getString(R.string.NAME));
            holder.txtHouseChicken.setText(activity.getString(R.string.HOUSE_CHICKEN));
            holder.txtCode.setText(activity.getString(R.string.CODE));
            holder.txtState.setText(activity.getString(R.string.STATE));

            holder.txtNameValue.setText(houseChicken.getName());
            holder.txtHouseChickenValue.setText(houseChicken.getHenhouseName());
            holder.txtCodeValue.setText(houseChicken.getCode());
            holder.txtStateValue.setText(houseChicken.getStatusProcess());

            calendarItemAutoAdapter.updateList(houseChicken.getTimeList());
            Utils.setListViewHeightBasedOnChildren(holder.lvListItem);
            holder.llItemInside.setVisibility(View.GONE);
        }
    }

    private void handleDataCoolingSchedule(ViewHolder holder) {
        for(HouseChicken houseChicken :houseChickenList){
            holder.txtName.setText(activity.getString(R.string.NAME));
            holder.txtHouseChicken.setText(activity.getString(R.string.HOUSE_CHICKEN));
            holder.txtCode.setText(activity.getString(R.string.CODE));
            holder.txtState.setText(activity.getString(R.string.STATE));

            holder.txtNameValue.setText(houseChicken.getName());
            holder.txtHouseChickenValue.setText(houseChicken.getHenhouseName());
            holder.txtCodeValue.setText(houseChicken.getCode());
            holder.txtStateValue.setText(houseChicken.getStatusProcess());

            holder.llListView.setVisibility(View.GONE);

            holder.txtSoLuongQuatChayToiThieuValue.setText(houseChicken.getMinFanNumber());
            holder.txtThoiGianDaoQuatThongGioValue.setText(houseChicken.getReverseTime());

            holder.txtQUATKHITUOIValue.setText(houseChicken.getMinFreshFanSpeed());
            holder.txtQuatKhiTranValue.setText(houseChicken.getMinCeilingFanSpeed());
            holder.txtQuatKhiNgangValue.setText(houseChicken.getMinHorizontalFanSpeed());

            holder.txtNhietdotrongchuongTuValue.setText(houseChicken.getTempIn_From());
            holder.txtNhietdotrongchuongDenValue.setText(houseChicken.getTempIn_To());

            holder.txtNhietdongoaichuongTuValue.setText(houseChicken.getTempOut_From());
            holder.txtNhietdongoaichuongDenValue.setText(houseChicken.getTempOut_To());

            holder.txtDOAMchuongTuValue.setText(houseChicken.getHumidity_From());
            holder.txtDOAMchuongDenValue.setText(houseChicken.getHumidity_To());

            holder.txtPPMTUValue.setText(houseChicken.getCO2_From());
            holder.txtPPMDenValue.setText(houseChicken.getCO2_To());
        }
    }

    private void handleDataFeedingSchedule(ViewHolder holder) {
        for(HouseChicken houseChicken :houseChickenList){
            holder.txtName.setText(activity.getString(R.string.NAME));
            holder.txtHouseChicken.setText(activity.getString(R.string.HOUSE_CHICKEN));
            holder.txtCode.setText(activity.getString(R.string.CODE));
            holder.txtState.setText(activity.getString(R.string.STATE));

            holder.txtNameValue.setText(houseChicken.getName());
            holder.txtHouseChickenValue.setText(houseChicken.getHenhouseName());
            holder.txtCodeValue.setText(houseChicken.getCode());
            holder.txtStateValue.setText(houseChicken.getStatusProcess());

            calendarItemAutoAdapter.updateList(houseChicken.getFeedingInfoList());
            Utils.setListViewHeightBasedOnChildren(holder.lvListItem);
            holder.llItemInside.setVisibility(View.GONE);
        }
    }

    public void updateList(List<HouseChicken> houseChickenList){
        this.houseChickenList = houseChickenList;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        TextView txtName ;
        TextView txtHouseChicken ;
        TextView txtCode ;
        TextView txtState ;

        TextView txtNameValue;
        TextView txtHouseChickenValue;
        TextView txtCodeValue;
        TextView txtStateValue;
        TextView txtSoLuongQuatChayToiThieuValue;
        TextView txtThoiGianDaoQuatThongGioValue;
        TextView txtQUATKHITUOIValue;
        TextView txtQuatKhiTranValue;
        TextView txtQuatKhiNgangValue;
        TextView txtNhietdotrongchuongTuValue;
        TextView txtNhietdotrongchuongDenValue;
        TextView txtNhietdongoaichuongTuValue;
        TextView txtNhietdongoaichuongDenValue;
        TextView txtDOAMchuongTuValue;
        TextView txtDOAMchuongDenValue;
        TextView txtPPMDenValue;
        TextView txtPPMTUValue;

        LinearLayout llListView;
        ListView lvListItem;
        LinearLayout llItemInside;
    }
}
