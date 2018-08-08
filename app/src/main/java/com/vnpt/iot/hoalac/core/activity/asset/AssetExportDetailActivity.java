package com.vnpt.iot.hoalac.core.activity.asset;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.AssetApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.model.AssetExportDetail;
import com.vnpt.iot.hoalac.core.model.MapExportMaterials;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssetExportDetailActivity extends CommonActivity {
    @BindView(R.id.code) TextView code;
    @BindView(R.id.warehouseName) TextView warehouseName;
    @BindView(R.id.codeName) TextView codeName;
    @BindView(R.id.userExport) TextView userExport;
    @BindView(R.id.exportDate) TextView exportDate;
    @BindView(R.id.receiverName) TextView receiverName;
    @BindView(R.id.receiverAddress) TextView receiverAddress;
    @BindView(R.id.mainContainer) LinearLayout mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_export_detail);
        commonSetting();
        ButterKnife.bind(this);
        getData(getIntent().getStringExtra("idAsset"));
    }

    private void getData(String id){
        HashMap<String, Object> param = new HashMap<>();
        param.put("id", id);
        kProgressHUD.show();
        AssetApi.getExportDetail(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                AssetExportDetail assetExportDetail = gson.fromJson(result.get_jsonObject().toString(), AssetExportDetail.class);
                loadView(assetExportDetail);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                toast(getApplicationContext(), R.string.loadDataErr);
            }
        });
    }

    private void loadView(AssetExportDetail assetExportDetail){
        code.setText(assetExportDetail.getCode());
        warehouseName.setText(assetExportDetail.getWarehouseName());
        codeName.setText(assetExportDetail.getCode());
        receiverName.setText(assetExportDetail.getReceiverName());
        receiverAddress.setText(assetExportDetail.getReceiverAddress());
        userExport.setText(assetExportDetail.getStaffName());
        exportDate.setText(assetExportDetail.getExportDate());

        if ((assetExportDetail.getListProduct() != null) && (assetExportDetail.getListProduct().size() > 0)) {
            for (MapExportMaterials mapExportMaterials : assetExportDetail.getListProduct()) {
                View view = View.inflate(getApplicationContext(), R.layout.item_asset_export_product, null);
                mainContainer.addView(view);

                TextView materials = (TextView) view.findViewById(R.id.materials);
                TextView quantity = (TextView) view.findViewById(R.id.quantity);
                TextView unit = (TextView) view.findViewById(R.id.unit);

                materials.setText(mapExportMaterials.getMaterialsName());
                unit.setText(mapExportMaterials.getUnit());
                quantity.setText(String.valueOf(mapExportMaterials.getQuantity()));
            }
        }
    }

    public void pressBack(View view) {onBackPressed();}
}
