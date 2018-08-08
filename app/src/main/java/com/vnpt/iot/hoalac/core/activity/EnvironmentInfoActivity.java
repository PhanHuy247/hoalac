package com.vnpt.iot.hoalac.core.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.EnvironmentApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.vnpt.iot.hoalac.core.model.AirCooling;
import com.vnpt.iot.hoalac.core.model.GreenHouse;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnvironmentInfoActivity extends CommonActivity {
    @BindView(R.id.temp) TextView temp;
    @BindView(R.id.doAm) TextView doAm;
    @BindView(R.id.lux) TextView lux;
    @BindView(R.id.imgCameraView)
    ImageView imgCameraView;
    private AirCooling airCooling;
    private GreenHouse sector;
    private GreenHouse greenHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_environment_info);
        ButterKnife.bind(this);
        sector = AppSharedPreferences.getGreenHouse(getApplicationContext());
        greenHouse = AppSharedPreferences.getGreenHouse(getApplicationContext());
        initView();
        loadData();
    }

    private void initView() {
        long idGreenHouse = greenHouse.getId();
        if(idGreenHouse == Constatnts.ID_DUA_LUOI_1 || idGreenHouse == Constatnts.ID_DUA_LUOI_2
                || idGreenHouse == Constatnts.ID_DUA_LUOI_3 || idGreenHouse == Constatnts.ID_DUA_LUOI_4 ){
            imgCameraView.setImageResource(R.mipmap.bg_dua_luoi);
        }else {
            if(idGreenHouse == Constatnts.ID_THUY_CANH_1) {
                imgCameraView.setImageResource(R.mipmap.camera_view);
            }
        }
    }

    private void loadData(){
        kProgressHUD.show();

        HashMap<String, Object> param = new HashMap<>();
        param.put("sectorId", sector.getId());
        EnvironmentApi.getData(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                airCooling = gson.fromJson(result.get_jsonObject().toString(), AirCooling.class);
                temp.setText(String.valueOf(airCooling.getTemp()) + "°C");
                doAm.setText(String.valueOf(airCooling.getHumi()) + "%");
                lux.setText(String.valueOf(airCooling.getLight()) + "lx");
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
            }
        });
    }

    public void pressBack(View view){
        finish();
    }

}
