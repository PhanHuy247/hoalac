package com.vnpt.iot.hoalac.core.activity.searchSource;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.model.QRCodeDetailDTO;
import com.vnpt.iot.hoalac.core.model.QRCodeHistoryDTO;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends CommonActivity {
    @BindView(R.id.farm) TextView farmName;
    @BindView(R.id.sector) TextView sector;
    @BindView(R.id.product) TextView product;
    @BindView(R.id.code) TextView code;
    @BindView(R.id.weigh) TextView weigh;
    @BindView(R.id.productDate) TextView productDate;
    @BindView(R.id.expireDate) TextView expireDate;
    @BindView(R.id.crop) TextView crop;
    @BindView(R.id.userName) TextView userName;
    @BindView(R.id.address) TextView address;
    @BindView(R.id.phoneNumber) TextView phoneNumber;
    @BindView(R.id.main) LinearLayout main;

    private QRCodeDetailDTO qrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        String data = getIntent().getStringExtra("data");
        qrCode = gson.fromJson(data, QRCodeDetailDTO.class);
        loadView();
    }

    private void loadView() {
        farmName.setText(qrCode.getFarmName());
        sector.setText(qrCode.getSectorName());
        product.setText(qrCode.getProductName());

        HashMap<String, String> unitConvert = new HashMap<>();
        unitConvert.put("1", "Kg");
        unitConvert.put("2", "g");
        unitConvert.put("3", "l");
        unitConvert.put("4", "ml");

        String unit = "";
        if (qrCode.getUnit() != null) unit = unitConvert.get(qrCode.getUnit());
        weigh.setText(String.valueOf(qrCode.getWeigh())+" "+unit);
        code.setText(qrCode.getCode());
        productDate.setText(qrCode.getProductDate());
        expireDate.setText(qrCode.getExpireDay());
        crop.setText(qrCode.getPhaseName());
        userName.setText(qrCode.getUserName());
        address.setText(qrCode.getAddress());
        phoneNumber.setText(qrCode.getPhoneNumber());

        if ((qrCode.getLstHistory() != null) && (qrCode.getLstHistory().size() > 0)) {
            for (QRCodeHistoryDTO qrCodeHistoryDTO : qrCode.getLstHistory()) {
                View view = View.inflate(getApplicationContext(), R.layout.item_qrcode_process, null);
                main.addView(view);
                TextView processName = (TextView) view.findViewById(R.id.processName);
                TextView startDate = (TextView) view.findViewById(R.id.startDate);
                processName.setText(qrCodeHistoryDTO.getProgressName());
                startDate.setText(qrCodeHistoryDTO.getStartTime());
            }
        }
    }

    public void pressBack(View view) {
        finish();
    }
}
