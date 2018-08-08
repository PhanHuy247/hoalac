package com.vnpt.iot.hoalac.core.activity.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.CameraApi;
import com.vnpt.iot.hoalac.api.model.GatewayAPI;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.adapter.SubDeviceAdapter;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.vnpt.iot.hoalac.core.common.Utils;
import com.vnpt.iot.hoalac.core.model.AquaticConfiguration;
import com.vnpt.iot.hoalac.core.model.CmdDevice;
import com.vnpt.iot.hoalac.core.model.CountActive;
import com.vnpt.iot.hoalac.core.model.DataMQTTDevice;
import com.vnpt.iot.hoalac.core.model.DataMQTTParam;
import com.vnpt.iot.hoalac.core.model.DeviceList;
import com.vnpt.iot.hoalac.core.model.Farm;
import com.vnpt.iot.hoalac.core.model.GreenHouse;
import com.vnpt.iot.hoalac.core.model.User;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.vnpt.technology.mqtt.VNPTClient;
import vn.vnpt.technology.mqtt.VNPTClientEventHandle;

public class ControlSubDeviceActivity extends CommonActivity {
    @BindView(R.id.deviceName) TextView deviceName;
    @BindView(R.id.listDeviceControl) ListView listDeviceControl;
    @BindView(R.id.txtGwStatus) TextView txtGwStatus;
    @BindView(R.id.btnSchedule) Button btnSchedule;
    @BindView(R.id.swAutoStatus) Switch swAutoStatus;

    private DeviceList device;
    private String gatewayId;
    private String topicGateway;
    private List<CmdDevice> cmdDevices;
    private SubDeviceAdapter subDeviceAdapter;
    private GreenHouse sector;
    private HashMap<String, String> deviceStatus = new HashMap<>();
    private VNPTClient client;
    private VNPTClient clientLocal;
    private Handler handler = new Handler();
    private Farm farm;
    private AquaticConfiguration aquaticConfiguration;
    private CountActive countActiveSchedule;
    private boolean gwStatus = false;  //0:manual; 1:auto
    private boolean initSw = false;

    private boolean statusCo = false;
    private boolean statusAuto = false;
    private boolean statusRq = false;
    private Activity activity;
    private Runnable runnableLoad;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_sub_device);
        commonSetting();
        ButterKnife.bind(this);
        activity = this;

        device = gson.fromJson(getIntent().getStringExtra("data"), DeviceList.class);
        gatewayId = getIntent().getStringExtra("gw");
        topicGateway = "/SCPCloud/DEVICE/"+gatewayId;
        sector = AppSharedPreferences.getGreenHouse(getApplicationContext());
        farm = AppSharedPreferences.getFarm(getApplicationContext());

        kProgressHUD.setLabel(getStringLang(R.string.connectSystem)).show();
        user = AppSharedPreferences.getUser(getApplicationContext());
        connectMQTT();
        initView();
        runnableLoad = new Runnable() {
            @Override
            public void run() {
                if (!statusAuto && !statusCo && !statusRq) {
                    if (kProgressHUD.isShowing()) kProgressHUD.dismiss();
                    toast(getApplicationContext(), R.string.lostSystem);
                    activity.finish();
                }
            }
        };
        handler.postDelayed(runnableLoad, 30000);

//        loadSchedule();
    }

    private void checkCloseLoading(){
        if (statusRq && statusAuto && statusCo) {
            if (kProgressHUD.isShowing()) kProgressHUD.dismiss();
        }
    }

    private void loadStatus(VNPTClient client){
        String deviceId = device.getDeviceId().replace(gatewayId, "");
        String cmdRequest = GatewayAPI.generateGWCommand(deviceId+Constatnts.CMD_REQUEST, deviceId+Constatnts.CMD_REQUEST);
        System.out.println("PUT MQTT: "+cmdRequest);
        try {
            client.publishMesage(gatewayId, cmdRequest, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeSchedule(View view) {
        String deviceId = device.getDeviceId().replace(gatewayId, "");
        String cmd = deviceId+Constatnts.CMD_DEVICE_CHANGE_STATUS;
        if(deviceId.contains(Constatnts.DELCO_MELON_LIGHTING_1)){
            if(swAutoStatus.isChecked()){
                cmd = deviceId+Constatnts.CMD_DEVICE_CHANGE_STATUS_Off;
            }else {
                cmd = deviceId+Constatnts.CMD_DEVICE_CHANGE_STATUS_On;
            }
        }

        String content = GatewayAPI.generateGWCommand(cmd, cmd);

        try {
            if (clientLocal.isConnected()) {
                clientLocal.publishMesage(gatewayId, content, true);
            } else {
                if (client.isConnected()) {
                    client.publishMesage(gatewayId, content, true);
                }
            }
            toast(getApplicationContext(), R.string.sendRequestSchedule);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView(){
        sector = AppSharedPreferences.getGreenHouse(getApplicationContext());

        if (device.getName().length() > 20) {
            deviceName.setText(device.getName().substring(0, 20)+"...");
        } else {
            deviceName.setText(device.getName());
        }

        listDeviceControl.setDividerHeight(0);
        listDeviceControl.setDivider(null);

        cmdDevices = Utils.getListSubDevice(ControlSubDeviceActivity.this,gatewayId ,device.getDeviceId(), device.getType(), sector.getTypeCul());
        subDeviceAdapter = new SubDeviceAdapter(this, cmdDevices, deviceStatus);
        listDeviceControl.setAdapter(subDeviceAdapter);

        listDeviceControl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CmdDevice cmdDevice = cmdDevices.get(position);
                String cmd = cmdDevice.getCmdOn();
                if ((deviceStatus.containsKey(cmdDevice.getDeviceId())) && (deviceStatus.get(cmdDevice.getDeviceId()).equals("on"))) {
                    cmd = cmdDevice.getCmdOff();
                }

                if (!checkRole()) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            ControlSubDeviceActivity.this);
                    alertDialogBuilder
                            .setMessage(R.string.noPermission)
                            .setCancelable(false)
                            .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    return;
                }

                if (!gwStatus) {
                    toast(getApplicationContext(), R.string.errControl);
                    return;
                }

                if ((gwStatus) && (swAutoStatus.isChecked())) {
                    toast(getApplicationContext(), R.string.turnOffAutomatic);
                    return;
                }

                waterControl(cmd);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (kProgressHUD.isShowing()) activity.finish(); else activity.finish();
            handler.removeCallbacks(runnableLoad);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void connectMQTT(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                connectLocal();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                connectInternet();
            }
        }).start();
    }

    private void connectLocal(){
        try {
            String brokerLocal = "tcp://"+AppSharedPreferences.getIpBroker(getApplicationContext())+":"+AppSharedPreferences.getPortBroker(getApplicationContext());
            clientLocal = new VNPTClient("client", brokerLocal);
            clientLocal.setQos(0);
            clientLocal.connect(null, null, "client1", null);
            if (clientLocal.isConnected()) {
                subLocal();
                loadStatus(clientLocal);
            }
        } catch (Exception e) {

        }

        while (!clientLocal.isConnected()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            connectLocal();
        }
    }

    private void connectInternet(){
        try {
            String broker = Constatnts.IP_MQTT_SERVER;
            client = new VNPTClient("client", broker);
            client.setQos(0);
            client.connect(null, null, "client1", null);
            if (client.isConnected()) {
                subInternet();
                loadStatus(client);
            }
        } catch (Exception e2) {

        }

        while (!client.isConnected()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            connectInternet();
        }
    }

    private void subLocal(){
        try {
            clientLocal.subscribe(topicGateway, new VNPTClientEventHandle() {
                @Override
                public void onMessageArrived(String topic, String message) {
                    System.out.println("Data MQTT Local: "+message);
                    DataMQTTDevice dataMQTTDevice = gson.fromJson(message, DataMQTTDevice.class);
                    loadToView(dataMQTTDevice);
                }
            });

        } catch (Exception e) {

        }
    }

    private void subInternet(){
        try {
            client.subscribe(topicGateway, new VNPTClientEventHandle() {
                @Override
                public void onMessageArrived(String topic, String message) {
                    System.out.println("Data MQTT Inter: "+message);
                    DataMQTTDevice dataMQTTDevice = gson.fromJson(message, DataMQTTDevice.class);
                    loadToView(dataMQTTDevice);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void loadToView(DataMQTTDevice dataMQTTDevice) {
        if (dataMQTTDevice == null) return;
        if (dataMQTTDevice.getDevice_id() == null) return;
        String deviceIdNonPar = device.getDeviceId().replace(gatewayId, "");
        if (!dataMQTTDevice.getDevice_id().equals(deviceIdNonPar)) return;;
        if (dataMQTTDevice.getState_hardware() != null) {
            statusCo = true;
            checkCloseLoading();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (dataMQTTDevice.getState_hardware().equals("0")) {
                        txtGwStatus.setText(getStringLang(R.string.manual));
                        txtGwStatus.setTextColor(getResources().getColor(R.color.green));
                        btnSchedule.setEnabled(false);
                        swAutoStatus.setEnabled(false);
                        gwStatus = false;
                    } else {
                        txtGwStatus.setText(getStringLang(R.string.automatic));
                        txtGwStatus.setTextColor(getResources().getColor(R.color.red));
                        btnSchedule.setEnabled(true);
                        swAutoStatus.setEnabled(true);
                        gwStatus = true;
                    }
                }
            });
        }


        if (dataMQTTDevice.getState_app() != null) changeSw(dataMQTTDevice.getState_app());

        if ((dataMQTTDevice.getDevice_values() != null) && (dataMQTTDevice.getDevice_values().size() > 0)) {
            for (DataMQTTParam dataMQTTParam : dataMQTTDevice.getDevice_values()) {
                deviceStatus.put(dataMQTTParam.getParam(), dataMQTTParam.getValue().toLowerCase());
            }
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (subDeviceAdapter == null) return;
                statusRq = true;
                checkCloseLoading();
                subDeviceAdapter.updateStatus(deviceStatus);
            }
        });
    }

    private void changeSw(String value){
        statusAuto = true;
        checkCloseLoading();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (value.equals("0")) swAutoStatus.setChecked(false);
                else swAutoStatus.setChecked(true);
            }
        });

    }

    private boolean checkRole(){
        if (user.getRoleApps().contains("fullcontrol")) return true;
        else return false;
    }

    private void waterControl(String cmd){

        String content = GatewayAPI.generateGWCommand(cmd, cmd);
        System.out.println("CMD LOAL: "+content);

        HashMap<String, Object> params = new HashMap<>();
        params.put("deviceId", gatewayId);
        params.put("cmdType", 1);

        Map<String, String> map1 = new HashMap<>();
        map1.put("name", cmd);
        map1.put("value", cmd);

        Map<String, List<Map<String, String>>> map2 = new HashMap<>();
        map2.put("anyArg", Arrays.asList(map1));

        Map<String, List<Map<String, List<Map<String, String>>>>> map3 = new HashMap<>();
        map3.put("reset", Arrays.asList(map2));

        if (clientLocal.isConnected()) {
            controlOffline(cmd);
            return;
        }

        JSONObject body = new JSONObject(map3);
        CameraApi.cmd(getApplicationContext(), params, body, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {

            }

            @Override
            public void onRequestError(ApiResponse error) {
                Utils.toast(getApplicationContext(), getStringLang(R.string.controlErr));
            }
        });
    }

    private void controlOffline( String cmd){
        try {
            String content = GatewayAPI.generateGWCommand(cmd, cmd);
            System.out.println("publishMesage topic: "+gatewayId);
            System.out.println("publishMesage content: "+content);
            clientLocal.publishMesage(gatewayId, content, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pressBack(View view){
        finish();
    }
}
