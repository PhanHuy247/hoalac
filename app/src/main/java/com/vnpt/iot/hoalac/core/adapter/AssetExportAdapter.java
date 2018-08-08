package com.vnpt.iot.hoalac.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.model.AssetExport;

import java.util.List;

/**
 * Created by thohc on 7/4/17.
 */

public class AssetExportAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<AssetExport> assetExports;
    private AssetExport assetExport;

    public AssetExportAdapter(Activity activity, List<AssetExport> assetExports) {
        this.activity = activity;
        this.assetExports = assetExports;
    }

    @Override
    public int getCount() {
        return assetExports.size();
    }

    @Override
    public Object getItem(int position) {
        return assetExports.get(position);
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
        TextView date = (TextView) convertView.findViewById(R.id.dateStart);
        TextView dateEnd = (TextView) convertView.findViewById(R.id.dateEnd);
        assetExport = assetExports.get(position);
        name.setText(assetExport.getCode());
        date.setText(assetExport.getExportDate());

        return convertView;
    }

    public void updateList(List<AssetExport> assetExports){
        this.assetExports = assetExports;
        notifyDataSetChanged();
    }
}
