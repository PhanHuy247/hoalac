package com.vnpt.iot.hoalac.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.model.AssetImport;

import java.util.List;

/**
 * Created by thohc on 7/3/17.
 */

public class AssetImportAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<AssetImport> assetImports;
    private AssetImport assetImport;

    public AssetImportAdapter(Activity activity, List<AssetImport> assetImports) {
        this.activity = activity;
        this.assetImports = assetImports;
    }

    @Override
    public int getCount() {
        return assetImports.size();
    }

    @Override
    public Object getItem(int position) {
        return assetImports.get(position);
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
        assetImport = assetImports.get(position);
        name.setText(assetImport.getCode());
        date.setText(assetImport.getImportDate());

        return convertView;
    }

    public void updateList(List<AssetImport> assetImports){
        this.assetImports = assetImports;
        notifyDataSetChanged();
    }
}
