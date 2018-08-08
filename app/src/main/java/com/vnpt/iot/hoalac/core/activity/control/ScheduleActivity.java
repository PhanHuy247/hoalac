package com.vnpt.iot.hoalac.core.activity.control;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.DeviceApi;
import com.vnpt.iot.hoalac.api.model.GatewayAPI;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.vnpt.iot.hoalac.core.common.Utils;
import com.vnpt.iot.hoalac.core.model.Device;
import com.vnpt.iot.hoalac.core.model.DeviceSchedule;
import com.vnpt.iot.hoalac.core.model.GroupDevice;
import com.vnpt.iot.hoalac.core.model.GroupDeviceSchedule;
import com.vnpt.iot.hoalac.core.model.Parse;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.vnpt.technology.mqtt.VNPTClient;

public class ScheduleActivity extends CommonActivity {
    @BindView(R.id.spHome) Spinner spHome;
    @BindView(R.id.spDevice) Spinner spDevice;
    @BindView(R.id.spType) Spinner spType;
    @BindView(R.id.spStatus) Spinner spStatus;
    @BindView(R.id.subValue1) TextView subValue1;
    @BindView(R.id.subValue2) TextView subValue2;
    @BindView(R.id.value1) EditText value1;
    @BindView(R.id.value2) EditText value2;
    private KProgressHUD kProgressHUD;
    private List<Device> devices = new ArrayList<>();
    private DeviceSchedule deviceSchedule;
    private Gson gson;
    private Handler handler;
    private VNPTClient clientLocal;
    private String gatewayId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_schedule_old);
        ButterKnife.bind(this);
        gatewayId = AppSharedPreferences.getGateway(getApplicationContext());


        initObject();
        initSpinner();
        changeItemSpinner();
        new Thread(new Runnable() {
            @Override
            public void run() {
                createMQTT();
            }
        }).start();

    }

    private void createMQTT(){
        try {
            //String topic = AppSharedPreferences.getTopicCMDLocal(getApplicationContext());
            String brokerLocal = "tcp://"+AppSharedPreferences.getIpBroker(getApplicationContext())+":"+AppSharedPreferences.getPortBroker(getApplicationContext());
            clientLocal = new VNPTClient("client", brokerLocal);
            clientLocal.setQos(0);
            clientLocal.connect(null, null, "client1", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(View view){

        if (value1.getText().toString().isEmpty()) {
            Utils.toast(getApplicationContext(), getStringLang(R.string.ipStart));
            return;
        }

        if (value2.getText().toString().isEmpty()) {
            Utils.toast(getApplicationContext(), getStringLang(R.string.ipEnd));
            return;
        }

        Device device = devices.get(spDevice.getSelectedItemPosition());

        HashMap<String, Object> params = new HashMap<>();
        if (deviceSchedule != null) params.put("id", deviceSchedule.getId());
        params.put("deviceId", device.getDeviceId());
        params.put("scpId", device.getScpId());
        params.put("status", (spStatus.getSelectedItemPosition() +1));
        params.put("userId", 1);
        params.put("sensorId", 1);

        int type = 1;
        int conditionType = 1;
        long vl1 = 0l;
        long vl2 = 0l;

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
                Locale.getDefault());
        Date currentLocalTime = calendar.getTime();
        DateFormat date = new SimpleDateFormat("z");
        String localTime = date.format(currentLocalTime);

        params.put("timeZone", "GMT+00:00");

        switch (spType.getSelectedItemPosition()) {
            case 0:
                type = 1;
                conditionType = 1;
                vl1 = stringTimeToLong(value1.getText().toString(), localTime);
                vl2 = stringTimeToLong(value2.getText().toString(), localTime);
                break;
            case 1:
                type = 2;
                conditionType = 3;
                vl1 = Long.valueOf(value1.getText().toString());
                vl2 = Long.valueOf(value2.getText().toString());
                break;
            case 2:
                type = 2;
                conditionType = 2;
                vl1 = Long.valueOf(value1.getText().toString());
                vl2 = Long.valueOf(value2.getText().toString());
        }

        if (type == 2) {
            if (vl1 >= vl2) {
                Utils.toast(getApplicationContext(), getStringLang(R.string.ipStartEndErr));
                return;
            }
            params.put("sensorId", gatewayId+Constatnts.SENSOR_ID);
        }

        params.put("type", type);
        params.put("conditionType", conditionType);
        params.put("value1", vl1);
        params.put("value2", vl2);

        JSONObject body = new JSONObject(params);
        kProgressHUD.show();
        int finalType = type;
        int finalConditionType = conditionType;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (clientLocal.isConnected()) {
                    saveOffline(device, finalConditionType, finalType, localTime);
                } else {
                    if (isNetworkAvailable())
                        DeviceApi.saveSchedule(getApplicationContext(), null, body, new ResponseListener() {
                            @Override
                            public void onRequestCompleted(ApiResponse result) {
                                kProgressHUD.dismiss();
                                if (result.get_jsonObject() != null) {
                                    DeviceSchedule deviceSchedule = gson.fromJson(result.get_jsonObject().toString(), DeviceSchedule.class);

                                    AppSharedPreferences.addSchedule(getApplicationContext(), deviceSchedule);
                                }
                                Utils.toast(getApplicationContext(), getStringLang(R.string.saveSS));
                                finish();
                            }

                            @Override
                            public void onRequestError(ApiResponse error) {
                                Utils.toast(getApplicationContext(), getStringLang(R.string.saveErr));
                            }
                        });
                    else {
                        Utils.toast(getApplicationContext(), getStringLang(R.string.lostInternet));
                    }
                }
            }
        }).start();


    }


    private void saveOffline(Device device, int finalConditionType, int finalType, String localTime){
        if (kProgressHUD.isShowing()) kProgressHUD.dismiss();

        String cmdCt = device.getDeviceId();
        int status = spStatus.getSelectedItemPosition();
        cmdCt = cmdCt.replace(gatewayId, "");
        cmdCt += Constatnts.VALVE_CMD_ON;
        String conditionParam = "";
        switch (finalConditionType) {
            case 1:
                conditionParam = "light";
                break;
            case 2:
                conditionParam = "air_temperature";
                break;
            case 3:
                conditionParam = "air_moisture";
                break;
            default:
                break;
        }

        String name = "";
        if (finalType == 1) name = "schedule_daily_execute_"+device.getDeviceId();
        if (finalType == 2) name = "schedule_range_execute_"+device.getDeviceId();
        String cmd = GatewayAPI.generateCreateOrUpdateSchedule(name, status,
                finalType, cmdCt, device.getDeviceId(),gatewayId+Constatnts.SENSOR_ID, value1.getText().toString(),
                value2.getText().toString(), conditionParam, localTime);
        pushLocal(cmd);
        handler.post(new Runnable() {
            @Override
            public void run() {
                Utils.toast(getApplicationContext(), getStringLang(R.string.saveSS));
                finish();
            }
        });
    }

    private void pushLocal( String cmd){
        try {
            String topic = AppSharedPreferences.getTopicCMDLocal(getApplicationContext());
            System.out.println("publishMesage topic: "+topic);
            System.out.println("publishMesage cmd: "+cmd);
            clientLocal.publishMesage(topic, cmd, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Long stringTimeToLong(String timeData, String timeZone){
        //timeData = 10:30
        String time = GatewayAPI.convertToGMT0(timeZone, timeData);

        //time = 3:30
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Africa/Abidjan"));
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Timeconvert time: "+time);
        System.out.println("Timeconvert getHours: "+parsedDate.getHours());
        System.out.println("Timeconvert getMinutes: "+parsedDate.getMinutes());
        System.out.println("Timeconvert getTime: "+parsedDate.getTime());

        return parsedDate.getTime();
    }

    private void changeItemSpinner(){
        spDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((devices != null) && (devices.size() == 0)) return;
                Device device = devices.get(position);
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("deviceId", device.getDeviceId());
                kProgressHUD.show();
                if (isNetworkAvailable())
                    DeviceApi.getSchedule(getApplicationContext(), params, null, new ResponseListener() {
                        @Override
                        public void onRequestCompleted(ApiResponse result) {
                            kProgressHUD.dismiss();
                            if (result.get_jsonObject() == null) return;
                            deviceSchedule = gson.fromJson(result.get_jsonObject().toString(), DeviceSchedule.class);
                            AppSharedPreferences.addSchedule(getApplicationContext(), deviceSchedule);
                            loadSchedule();
                        }

                        @Override
                        public void onRequestError(ApiResponse error) {
                            loadScheduleOffline(device);
                        }
                    });
                else {
                    loadScheduleOffline(device);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        showValue(1,0);
                        break;
                    case 1:
                        showValue(2, 3);
                        break;
                    case 2:
                        showValue(2,2);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadScheduleOffline(Device device){
        if (kProgressHUD.isShowing()) kProgressHUD.dismiss();
        GroupDeviceSchedule groupDeviceSchedule = AppSharedPreferences.getGroupDeviceSchedule(getApplicationContext());
        HashMap<String, DeviceSchedule> deviceSchedules = groupDeviceSchedule.getDeviceSchedules();
        if (deviceSchedules.containsKey(device.getDeviceId())){
            deviceSchedule = deviceSchedules.get(device.getDeviceId());
            loadSchedule();
        } else loadScheduleErr();
    }

    private void loadSchedule(){
//        showValue(deviceSchedule.getType(), deviceSchedule.getConditionType());

        if (deviceSchedule.getType() == 1) {
            spType.setSelection(0);
        } else {
            if (deviceSchedule.getConditionType() == 2) spType.setSelection(2);
            if (deviceSchedule.getConditionType() == 3) spType.setSelection(1);
        }

        spStatus.setSelection(deviceSchedule.getStatus() - 1);

        if (deviceSchedule.getType() == 1) {

            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
                    Locale.getDefault());
            Date currentLocalTime = calendar.getTime();
            DateFormat timeZoneFM = new SimpleDateFormat("z");
            String localTime = timeZoneFM.format(currentLocalTime);

            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            df.setTimeZone(TimeZone.getTimeZone("Africa/Abidjan"));

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Date date = new Date(deviceSchedule.getValue1());
                    String var1 = GatewayAPI.regenerateWithTimeZone(df.format(date), localTime);
                    value1.setText(var1);

                    Date date2 = new Date(deviceSchedule.getValue2());
                    String var2 = GatewayAPI.regenerateWithTimeZone(df.format(date2), localTime);
                    value2.setText(var2 );
                }
            },100);



        } else {
            value1.setText(String.valueOf(deviceSchedule.getValue1()));
            value2.setText(String.valueOf(deviceSchedule.getValue2()));
        }

    }

    private void loadScheduleErr(){
        deviceSchedule = null;
        kProgressHUD.dismiss();
        value1.setText("");
        value2.setText("");
        Utils.toast(getApplicationContext(), getStringLang(R.string.noData));
    }

    private void showValue(int type, int conditionType){
        if (type == 1){
            spType.setSelection(0);
            subValue1.setText(getStringLang(R.string.from));
            subValue2.setText(getStringLang(R.string.to));
            changeTextType(value1, 1);
            changeTextType(value2, 1);
        } else {
            if (conditionType == 2) {
                spType.setSelection(2);
                subValue1.setText(getStringLang(R.string.hight));
                subValue2.setText(getStringLang(R.string.stopWhen));
            } else if (conditionType == 3) {
                spType.setSelection(1);
                subValue1.setText(getStringLang(R.string.lessThan));
                subValue2.setText(getStringLang(R.string.stopWhen));
            }
            changeTextType(value1, 2);
            changeTextType(value2, 2);
        }
    }

    private void changeTextType(EditText et, int type){
        hideKeyboard();
        et.setText("");
        if (type == 1) {
            if (deviceSchedule != null) {
                if (deviceSchedule.getType() == 1) {
                    Date date = new Date(deviceSchedule.getValue1());
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                    value1.setText(df.format(date));
                    Date date2 = new Date(deviceSchedule.getValue2());
                    value2.setText(df.format(date2));
                }
            }
            et.setFocusable(false);
            et.setInputType(InputType.TYPE_CLASS_TEXT);
            et.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(ScheduleActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            String hour = (selectedHour < 10) ? "0"+String.valueOf(selectedHour) : String.valueOf(selectedHour);
                            String minute = (selectedMinute < 10) ? "0"+String.valueOf(selectedMinute) : String.valueOf(selectedMinute);
                            et.setText( hour + ":" + minute);
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle(getStringLang(R.string.setTime));
                    mTimePicker.show();
                }
            });
        } else {
            if (deviceSchedule != null) {
                if (deviceSchedule.getType() == 2) {
                    value1.setText(String.valueOf(deviceSchedule.getValue1()));
                    value2.setText(String.valueOf(deviceSchedule.getValue2()));
                }
            }
            et.setInputType(InputType.TYPE_CLASS_NUMBER);
            et.setFocusableInTouchMode(false);
            et.setFocusable(false);
            et.setFocusableInTouchMode(true);
            et.setFocusable(true);
            et.setOnClickListener(null);
        }
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


    }

    private void initObject(){
        kProgressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false);
        gson = new Gson();
        handler = new Handler();
    }

    private void initSpinner(){
        List<String> listHome = new ArrayList<String>();
        listHome.add(getStringLang(R.string.home)+" 1");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listHome);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHome.setAdapter(dataAdapter);

        List<String> listType = new ArrayList<String>();
        listType.add(getStringLang(R.string.irrigationTimer));
        listType.add(getStringLang(R.string.humidityBase));
        listType.add(getStringLang(R.string.temperatureBase));
        ArrayAdapter<String> dataAdapterType = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listType);
        dataAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(dataAdapterType);

        List<String> listStatus = new ArrayList<String>();
        listStatus.add(getStringLang(R.string.deactivate));
        listStatus.add(getStringLang(R.string.activate));
        ArrayAdapter<String> dataAdapterStatus = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listStatus);
        dataAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(dataAdapterStatus);

        loadDevice();
    }

    private void loadDevice(){
        HashMap<String, Object> param = new HashMap<>();
        param.put("sectorId", 1);
        param.put("deviceType", Constatnts.VAN);

        kProgressHUD.show();
        if (isNetworkAvailable())
            DeviceApi.getList(getApplicationContext(), param, new ResponseListener() {
                @Override
                public void onRequestCompleted(ApiResponse result) {
                    kProgressHUD.dismiss();
                    devices = Parse.getInstance().deviceList(result);
                    GroupDevice groupDevice = new GroupDevice(devices);
                    Gson gson = new Gson();
                    AppSharedPreferences.setDeviceVan(getApplicationContext(), gson.toJson(groupDevice));
                    fillToSp(devices);
                }

                @Override
                public void onRequestError(ApiResponse error) {
                    getListDeviceOffline();
                }
            });
        else {
            getListDeviceOffline();
        }
    }

    private void getListDeviceOffline(){
        if (kProgressHUD.isShowing()) kProgressHUD.dismiss();
        GroupDevice groupDevice = AppSharedPreferences.getDeviceVan(getApplicationContext());
        if (groupDevice == null) loadDeviceErr();
        devices = groupDevice.getDevices();
        if ((devices != null) && (devices.size() == 0)) loadDeviceErr();
        fillToSp(devices);
    }

    private void loadDeviceErr(){
        Utils.toast(getApplicationContext(), getStringLang(R.string.noDevice));
        finish();
    }

    public void fillToSp(List<Device> devices){
        if ((devices != null) && (devices.size() == 0)) return;
        List<String> deviceNames = new ArrayList<String>();
        for (Device device : devices) {
            deviceNames.add(device.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ScheduleActivity.this, R.layout.item_spinner, deviceNames);
        dataAdapter.setDropDownViewResource(R.layout.item_spinner);
        spDevice.setAdapter(dataAdapter);
    }

    public void pressBack(View view) {finish();}
}
