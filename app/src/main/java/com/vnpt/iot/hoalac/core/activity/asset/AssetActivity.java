package com.vnpt.iot.hoalac.core.activity.asset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.AssetApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.adapter.AssetExportAdapter;
import com.vnpt.iot.hoalac.core.adapter.AssetImportAdapter;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.AssetExport;
import com.vnpt.iot.hoalac.core.model.AssetImport;
import com.vnpt.iot.hoalac.core.model.Farm;
import com.vnpt.iot.hoalac.core.model.Parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

public class AssetActivity extends CommonActivity {
    private ListView listImport, listExport;
    private TabHost tabHost;
    private AssetImportAdapter assetImportAdapter;
    private AssetExportAdapter assetExportAdapter;
    private List<AssetImport> assetImports;
    private List<AssetExport> assetExports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        ButterKnife.bind(this);
        setContentView(R.layout.activity_asset);
        loadTab();
        initObject();
        loadData();
    }

    private void initObject(){
        listImport = (ListView) tabHost.getTabContentView().getChildAt(0).findViewById(R.id.listImport);
        listImport.setDivider(null);
        listImport.setDividerHeight(0);
        assetImportAdapter = new AssetImportAdapter(this, new ArrayList<>());
        listImport.setAdapter(assetImportAdapter);
        listImport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AssetImport assetImport = assetImports.get(position);
                Intent intent = new Intent(AssetActivity.this, AssetImportDetailActivity.class);
                intent.putExtra("id", assetImport.getId());
                startActivity(intent);
            }
        });


        listExport = (ListView) tabHost.getTabContentView().getChildAt(1).findViewById(R.id.listExport);
        listExport.setDivider(null);
        listExport.setDividerHeight(0);
        assetExportAdapter = new AssetExportAdapter(this, new ArrayList<>());
        listExport.setAdapter(assetExportAdapter);
        listExport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AssetExport assetExport = assetExports.get(position);
                Intent intentExport = new Intent(AssetActivity.this, AssetExportDetailActivity.class);
                intentExport.putExtra("idAsset", String.valueOf(assetExport.getId()));
                startActivity(intentExport);
            }
        });
    }

    private void loadData() {
        Farm farm = AppSharedPreferences.getFarm(getApplicationContext());
        HashMap<String, Object> param = new HashMap<>();
        param.put("farmId", farm.getId());
        AssetApi.getListImport(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                assetImports = Parse.getInstance().assetImport(result);
                assetImportAdapter.updateList(assetImports);
            }

            @Override
            public void onRequestError(ApiResponse error) {

            }
        });

        AssetApi.getListExport(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                assetExports = Parse.getInstance().assetExport(result);
                assetExportAdapter.updateList(assetExports);
            }

            @Override
            public void onRequestError(ApiResponse error) {

            }
        });
    }

    public void pressBack(View view) {
        finish();
    }

    private void loadTab(){
        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec spec;

        spec = tabHost.newTabSpec("t1");
        spec.setContent(R.id.tab1);
        spec.setIndicator(getStringLang(R.string.input));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("t2");
        spec.setContent(R.id.tab2);
        spec.setIndicator(getStringLang(R.string.output));
        tabHost.addTab(spec);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                resetColorTab(tabHost);
                int tab = tabHost.getCurrentTab();
                TextView tv = (TextView) tabHost.getTabWidget().getChildAt(tab).findViewById(android.R.id.title);
                tv.setTextColor(getResources().getColor(R.color.green));

            }
        });
        TextView tv = (TextView) tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).findViewById(android.R.id.title);
        tv.setTextColor(getResources().getColor(R.color.green));
    }

    private void resetColorTab(TabHost tabHost){
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.textNormal));
        }
    }

    public void goAdd(View view) {
        if (tabHost.getCurrentTab() == 0) {
            //startActivity(new Intent(this, AddAssetActivity.class));
        } else {
            //startActivity(new Intent(this, ExportAssetActivity.class));
        }

    }
}
