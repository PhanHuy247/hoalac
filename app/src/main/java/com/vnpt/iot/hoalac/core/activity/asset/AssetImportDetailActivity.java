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
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.vnpt.iot.hoalac.core.model.AssetImportDetail;
import com.vnpt.iot.hoalac.core.model.MapImportMaterials;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssetImportDetailActivity extends CommonActivity {
    @BindView(R.id.code) TextView code;
    @BindView(R.id.warehouseName) TextView warehouseName;
    @BindView(R.id.codeName) TextView codeName;
    @BindView(R.id.userImport) TextView userImport;
    @BindView(R.id.total) TextView total;
    @BindView(R.id.importDate) TextView importDate;
    @BindView(R.id.state) TextView state;
    @BindView(R.id.mainContainer) LinearLayout mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_import_detail);
        commonSetting();
        ButterKnife.bind(this);
        int id = getIntent().getIntExtra("id", 1);
        getData(id);
    }

    private void getData(int id){
        HashMap<String, Object> param = new HashMap<>();
        param.put("id", id);
        kProgressHUD.show();
        AssetApi.getImportDetail(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                AssetImportDetail assetImportDetail = gson.fromJson(result.get_jsonObject().toString(), AssetImportDetail.class);
                loadView(assetImportDetail);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                toast(getApplicationContext(), R.string.loadDataErr);
            }
        });
    }

    private void loadView(AssetImportDetail assetImportDetail){
        code.setText(assetImportDetail.getCode());
        warehouseName.setText(assetImportDetail.getWarehouseName());
        codeName.setText(assetImportDetail.getCode());
        userImport.setText(assetImportDetail.getStaffName());
        Integer status = assetImportDetail.getStatus();
        if(status == Constatnts.YES){
            state.setText(getString(R.string.inputData));
        }else {
            state.setText(getString(R.string.noInputData));
        }
        total.setText(String.valueOf(assetImportDetail.getTotal()*1.0));
        importDate.setText(assetImportDetail.getImportDate());

        if ((assetImportDetail.getListProduct() != null) && (assetImportDetail.getListProduct().size() > 0)) {
            for (MapImportMaterials mapImportMaterials : assetImportDetail.getListProduct()) {
                View view = View.inflate(getApplicationContext(), R.layout.item_asset_import_product, null);
                mainContainer.addView(view);

                TextView materials = (TextView) view.findViewById(R.id.materials);
                TextView unit = (TextView) view.findViewById(R.id.unit);
                TextView quantity = (TextView) view.findViewById(R.id.quantity);
                TextView describe = (TextView) view.findViewById(R.id.describe);
                TextView price = (TextView) view.findViewById(R.id.price);
                TextView total = (TextView) view.findViewById(R.id.total);

                materials.setText(mapImportMaterials.getMaterialsName());
                unit.setText(mapImportMaterials.getUnit());
                describe.setText(mapImportMaterials.getOther());
                quantity.setText(String.valueOf(mapImportMaterials.getQuantity()));
                price.setText(String.valueOf(mapImportMaterials.getUnitPrice()*1.0));
                total.setText(String.valueOf(mapImportMaterials.getTotal()*1.0));
            }
        }
    }

    public void pressBack(View view) {onBackPressed();}
}
