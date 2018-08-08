package com.vnpt.iot.hoalac.core.activity.control;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.DeviceApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.adapter.ValveControlAdapter;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.vnpt.iot.hoalac.core.model.Device;
import com.vnpt.iot.hoalac.core.model.DeviceList;
import com.vnpt.iot.hoalac.core.model.Farm;
import com.vnpt.iot.hoalac.core.model.GreenHouse;
import com.vnpt.iot.hoalac.core.model.Parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ControlWaterActivity extends CommonActivity {

    @BindView(R.id.listGateway) Spinner listGateway;
    @BindView(R.id.listVan) ListView listVan;

    private String gatewayId;
    private HashMap<String , Device> mapDevice = new HashMap<>();
    private HashMap<String, String> deviceStatus = new HashMap<>();
    private ValveControlAdapter valveControlAdapter;
    private Farm farm;
    private List<DeviceList> listValve = new ArrayList<>();
    private Device gateWay;
    private GreenHouse greenHouse;
    private DeviceList deviceSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_control_device);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ButterKnife.bind(this);
        gatewayId = AppSharedPreferences.getGateway(getApplicationContext());
        farm = AppSharedPreferences.getFarm(getApplicationContext());
        greenHouse = AppSharedPreferences.getGreenHouse(getApplicationContext());

        initObject();
        initSpinner();
        initListDeviceControl();

    }

    private void initListDeviceControl(){
        listVan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deviceSelect = listValve.get(position);
                String data = gson.toJson(deviceSelect);
                Intent intent = new Intent(ControlWaterActivity.this, ControlSubDeviceActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("gw", gatewayId);
                startActivity(intent);
                //dialogControl();
            }
        });
    }

    private void initSpinner() {
        loadGW();
        listGateway.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mapDevice.isEmpty()) return;
                gateWay = mapDevice.get(parent.getItemAtPosition(position).toString());
                gatewayId = gateWay.getDeviceId();
                AppSharedPreferences.setIpBroker(getApplicationContext(), gateWay.getIpLocal());
                loadListDeviceControl(gateWay);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadListDeviceControl(Device gateWay){
        HashMap<String, Object> param = new HashMap<>();
        //param.put("typeId", Constatnts.VAN);
        param.put("parentId", gateWay.getId());

        kProgressHUD.show();
        DeviceApi.getList(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                List<DeviceList> listData = Parse.getInstance().getDeviceList(result);
                listValve.clear();
                for (DeviceList deviceList: listData) {
                    if (deviceList.getType() != 5) {
                        listValve.add(deviceList);
                    }

                }

                valveControlAdapter = new ValveControlAdapter(ControlWaterActivity.this, listValve);
                listVan.setAdapter(valveControlAdapter);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
            }
        });
    }

    private void loadGW(){
        HashMap<String, Object> param = new HashMap<>();
        param.put("sectorId", greenHouse.getId());
        param.put("farmId", farm.getId());
        param.put("deviceType", Constatnts.GATEWAY);
        kProgressHUD.show();
        DeviceApi.getListDeviceIP(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                List<Device> devices = Parse.getInstance().deviceList(result);

                List<String> gateWayAdapter = new ArrayList<String>();

                for (Device device : devices) {
                    gateWayAdapter.add(device.getName());
                    mapDevice.put(device.getName(), device);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner, gateWayAdapter);
                dataAdapter.setDropDownViewResource(R.layout.item_spinner);
                listGateway.setAdapter(dataAdapter);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                toast(getApplicationContext(), R.string.loadDataErr);
            }
        });
    }

    private void initObject(){
        listVan.setDivider(null);
        listVan.setDividerHeight(0);
    }

    public void pressBack(View view) {
        finish();
    }

}
