package com.vnpt.iot.hoalac.core.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.DeviceApi;
import com.vnpt.iot.hoalac.api.model.FarmApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.vnpt.iot.hoalac.core.model.Device;
import com.vnpt.iot.hoalac.core.model.Farm;
import com.vnpt.iot.hoalac.core.model.GreenHouse;
import com.vnpt.iot.hoalac.core.model.GroupDevice;
import com.vnpt.iot.hoalac.core.model.Parse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectorSelectActivity extends CommonActivity {
    @BindView(R.id.listFarm)
    Spinner listFarm;
    @BindView(R.id.listGreenHouse)
    Spinner listGreenHouse;
    private List<Farm> farms;
    private List<GreenHouse> greenHouses;
    private HashMap<String, Farm> mapFarmId = new HashMap<>();
    private HashMap<String, GreenHouse> mapGreenHouseId = new HashMap<>();
    private String nameFarmSelect;
    private String nameGreenHouseSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sector_select);
        commonSetting();
        ButterKnife.bind(this);
        initObject();
        initSpinnerFarm();

    }

    private void loadLastSelect() {
        int lastIndex = Integer.valueOf(AppSharedPreferences.getLastFarmIndex(getApplicationContext()));
        if (lastIndex == 0) return;
        if (farms == null) return;
        if (farms.size() < lastIndex) return;
        listFarm.setSelection(lastIndex);
    }

    private void initSpinnerFarm() {
        kProgressHUD.show();
        FarmApi.getList(getApplicationContext(), new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                if (result.get_jsonObject() == null) return;
                farms = Parse.getInstance().getListFarm(result);
                List<String> farmAdapter = new ArrayList<String>();
                farmAdapter.add(getStringLang(R.string.selectFarm));

                for (Farm farm : farms) {
                    farmAdapter.add(farm.getFarmName());
                    mapFarmId.put(farm.getFarmName(), farm);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner, farmAdapter);
                dataAdapter.setDropDownViewResource(R.layout.item_spinner);

                listFarm.setAdapter(dataAdapter);
                loadLastSelect();

            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                toast(getApplicationContext(), R.string.loadDataErr);
            }
        });

        listFarm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nameFarmSelect = parent.getItemAtPosition(position).toString();
                AppSharedPreferences.setLastFarmIndex(getApplication(), String.valueOf(position));
                setDefaultSpinnerGreenHouse();
                mapGreenHouseId.clear();
                if (!mapFarmId.containsKey(nameFarmSelect)) return;
                Farm farm = mapFarmId.get(nameFarmSelect);
                initSpinnerGreenHouse(farm);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerGreenHouse(Farm farm) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("farmId", farm.getId());
        kProgressHUD.show();
        FarmApi.getGreenHouse(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                if (result.get_jsonObject() == null) return;
                System.out.println(result.get_jsonObject().toString());
                greenHouses = Parse.getInstance().getListGreenHouse(result);
                List<String> greenHouseAdapter = new ArrayList<String>();
                greenHouseAdapter.add(getStringLang(R.string.selectGreenHouse));
                for (GreenHouse greenHouse : greenHouses) {
                    greenHouseAdapter.add(greenHouse.getName());
                    mapGreenHouseId.put(greenHouse.getName(), greenHouse);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner, greenHouseAdapter);
                dataAdapter.setDropDownViewResource(R.layout.item_spinner);

                listGreenHouse.setAdapter(dataAdapter);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
            }
        });
    }


    private void initObject() {
        nameFarmSelect = getStringLang(R.string.selectFarm);
        nameGreenHouseSelect = getStringLang(R.string.selectGreenHouse);
        setDefaultSpinnerGreenHouse();
        listGreenHouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nameGreenHouseSelect = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setDefaultSpinnerGreenHouse() {
        List<String> greenHouseAdapter = new ArrayList<String>();
        greenHouseAdapter.add(getStringLang(R.string.selectGreenHouse));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner, greenHouseAdapter);
        dataAdapter.setDropDownViewResource(R.layout.item_spinner);
        listGreenHouse.setAdapter(dataAdapter);
    }

    public void next(View view) {
        if (!mapFarmId.containsKey(nameFarmSelect)) {
            toast(getApplicationContext(), R.string.pleaseSelectFarm);
            return;
        }

        if (!mapGreenHouseId.containsKey(nameGreenHouseSelect)) {
            toast(getApplicationContext(), R.string.pleaseSelectGreenHouse);
            return;
        }

        AppSharedPreferences.setFarm(getApplicationContext(), mapFarmId.get(nameFarmSelect));
        AppSharedPreferences.setGreenHouse(getApplicationContext(), mapGreenHouseId.get(nameGreenHouseSelect));
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadDevice(mapGreenHouseId.get(nameGreenHouseSelect));
                loadGW(mapGreenHouseId.get(nameGreenHouseSelect));
            }
        }).start();
        startActivity(new Intent(this, MainActivity.class));
    }

    private void loadGW(GreenHouse greenHouse) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("sectorId", greenHouse.getId());
        param.put("deviceType", Constatnts.GATEWAY);
        DeviceApi.getListDeviceIP(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                List<Device> devices = Parse.getInstance().deviceList(result);
                if (devices.size() > 0) {
                    Device device = devices.get(0);
                    AppSharedPreferences.setGateway(getApplicationContext(), device.getDeviceId());
                    AppSharedPreferences.setIpBroker(getApplicationContext(), device.getIpLocal());
                    AppSharedPreferences.setTopicCMDLocal(getApplicationContext(), device.getTopicLocal());
                }
            }

            @Override
            public void onRequestError(ApiResponse error) {

            }
        });
    }

    private void loadDevice(GreenHouse greenHouse) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("sectorId", greenHouse.getId());
        param.put("deviceType", Constatnts.VAN);
        DeviceApi.getListDeviceIP(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                List<Device> devices = Parse.getInstance().deviceList(result);
                GroupDevice groupDevice = new GroupDevice(devices);
                Gson gson = new Gson();
                AppSharedPreferences.setDeviceVan(getApplicationContext(), gson.toJson(groupDevice));
            }

            @Override
            public void onRequestError(ApiResponse error) {
            }
        });
    }

    public void pressBack(View view) {
        backLogin();
    }

    @Override
    public void onBackPressed() {
        backLogin();
    }

    private void backLogin() {
        AppSharedPreferences.setRememberLoggedIn(getApplicationContext(), false);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
