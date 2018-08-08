package com.vnpt.iot.hoalac.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.vnpt.iot.hoalac.core.model.Camera;

import java.util.List;

/**
 * Created by thohc on 7/22/17.
 */

public class CameraAdapter implements ListAdapter {
    private List<Camera> cameraList;
    LayoutInflater inflater;
    Activity activity;

    public CameraAdapter(Activity activity, List<Camera> cameraList){
        this.cameraList = cameraList;
        this.activity = activity;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return cameraList.size();
    }

    @Override
    public Object getItem(int position) {
        return cameraList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_camera, null);

        Camera camera = cameraList.get(position);

        ImageView imgItemCamera = (ImageView)convertView.findViewById(R.id.imgItemCamera);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        if(camera.getCheckItem().equals(Constatnts.CODE_DUA_LUOI)){
            imgItemCamera.setImageResource(R.mipmap.bg_dua_luoi);
        }else if (camera.getCheckItem().equals(Constatnts.CODE_THUY_CANH)){
            imgItemCamera.setImageResource(R.mipmap.camera_view);
        }
        name.setText(camera.getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClickItem(position);
            }
        });
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return cameraList.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public interface OnClickItem {
        void onClickItem(int position);
    }

    OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

}
