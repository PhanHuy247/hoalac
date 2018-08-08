package com.vnpt.iot.hoalac.core.activity.searchSource;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.QRCodeApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.barcode.Barcode;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.Farm;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;


public class SearchSourceActivity extends CommonActivity implements BarcodeRetriever{
    @BindView(R.id.edCode) EditText edCode;
    private Farm farm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_source);
        commonSetting();
        ButterKnife.bind(this);
        farm = AppSharedPreferences.getFarm(getApplicationContext());
        BarcodeCapture barcodeCapture = (BarcodeCapture) getSupportFragmentManager().findFragmentById(R.id.barcode);
        barcodeCapture.setRetrieval(this);
    }

    public void pressBack(View view) {
        finish();
    }

    public void goResult(View view) {
        if (edCode.getText().toString().isEmpty()) {
            toast(getApplicationContext(), R.string.loadDataErr);
            return;
        }
        HashMap<String, Object> param = new HashMap<>();
        param.put("farmId", farm.getId());
        param.put("code", edCode.getText().toString());

        kProgressHUD.show();
        QRCodeApi.detail(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                Intent intent = new Intent(SearchSourceActivity.this, SearchResultActivity.class);
                intent.putExtra("data", result.get_jsonObject().toString());
                startActivity(intent);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                toast(getApplicationContext(), R.string.noData);
            }
        });
    }

    @Override
    public void onRetrieved(Barcode barcode) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String code[] = barcode.displayValue.split("=");
                String dataCode = code[code.length-1];
                edCode.setText(dataCode);
            }
        });
    }

    @Override
    public void onRetrievedMultiple(Barcode barcode, List<BarcodeGraphic> list) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onRetrievedFailed(String s) {

    }
}
