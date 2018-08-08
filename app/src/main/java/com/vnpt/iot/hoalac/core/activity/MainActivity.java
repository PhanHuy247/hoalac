package com.vnpt.iot.hoalac.core.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.niqdev.mjpeg.DisplayMode;
import com.github.niqdev.mjpeg.MjpegInputStream;
import com.google.gson.Gson;
import org.json.JSONArray;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.CalendarAutoApi;
import com.vnpt.iot.hoalac.api.model.DeviceApi;
import com.vnpt.iot.hoalac.api.model.EnvironmentApi;
import com.vnpt.iot.hoalac.api.model.UserApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.asset.AssetActivity;
import com.vnpt.iot.hoalac.core.activity.calendarAuto.CalendarAuto;
import com.vnpt.iot.hoalac.core.activity.control.ControlWaterActivity;
import com.vnpt.iot.hoalac.core.activity.dailyNoteActivity.DailyNoteActivity;
import com.vnpt.iot.hoalac.core.activity.groupTime.GrowthScheduleActivity;
import com.vnpt.iot.hoalac.core.activity.groupTime.PhaseActivity;
import com.vnpt.iot.hoalac.core.activity.procedureManagement.ProcedureManagementActivity;
import com.vnpt.iot.hoalac.core.activity.schedule.ScheduleActivity;
import com.vnpt.iot.hoalac.core.activity.searchSource.SearchSourceActivity;
import com.vnpt.iot.hoalac.core.activity.user.UserActivity;
import com.vnpt.iot.hoalac.core.adapter.CameraAdapter;
import com.vnpt.iot.hoalac.core.adapter.ListAnimalAdapter;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.vnpt.iot.hoalac.core.common.Utils;
import com.vnpt.iot.hoalac.core.model.AirCooling;
import com.vnpt.iot.hoalac.core.model.Camera;
import com.vnpt.iot.hoalac.core.model.DataHouseChicken;
import com.vnpt.iot.hoalac.core.model.DataMQTTDevice;
import com.vnpt.iot.hoalac.core.model.DataMQTTParam;
import com.vnpt.iot.hoalac.core.model.Farm;
import com.vnpt.iot.hoalac.core.model.FeatureHouseChicken;
import com.vnpt.iot.hoalac.core.model.GreenHouse;
import com.vnpt.iot.hoalac.core.model.HouseChicken;
import com.vnpt.iot.hoalac.core.model.HouseChickenResponse;
import com.vnpt.iot.hoalac.core.model.Parse;
import com.vnpt.iot.hoalac.core.model.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.lucasr.twowayview.TwoWayView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.vnpt.technology.mqtt.VNPTClient;
import vn.vnpt.technology.mqtt.VNPTClientEventHandle;

public class MainActivity extends CommonActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private boolean show = false;
    private Handler handler;
    private NavigationView navigationView;
    private String displayCurrent = Constatnts.OVERVIEW;
    @BindView(R.id.moisture)
    TextView moisture;
    @BindView(R.id.temp)
    TextView temp;
    @BindView(R.id.listImage)
    TwoWayView listImage;
    @BindView(R.id.noCamera)
    ImageView noCamera;
    @BindView(R.id.imgHomeDelco)
    ImageView imgHomeDelco;
    @BindView(R.id.content_main_vegetables)
    LinearLayout contentMainVegetables;
    @BindView(R.id.content_main_animal)
    LinearLayout contentMainAnimal;
    @BindView(R.id.lvAnimal)
    ListView lvAnimal;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.imgHome)
    ImageView imgHome;

    private AsyncTask<Void, MjpegInputStream, Void> asyncTaskCam1;
    private AsyncTask<Void, MjpegInputStream, Void> asyncTaskCam2;
    private User user;
    private DrawerLayout drawer;
    public static TextView accountName;
    public static TextView accountEmail;
    private Farm farm;
    private GreenHouse greenHouse;
    private List<Camera> cameraList;
    private ListAnimalAdapter listAnimalAdapter;
    private List<HouseChicken> houseChickenList;
    private List<HouseChicken> houseChickensCalendar;
    private FeatureHouseChicken featureHouseChicken;
    private int idNavCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        user = AppSharedPreferences.getUser(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");

        }

        farm = AppSharedPreferences.getFarm(getApplicationContext());
        greenHouse = AppSharedPreferences.getGreenHouse(getApplicationContext());

        setSupportActionBar(toolbar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        initView();
        if (greenHouse.getName().equals(Constatnts.HARD_CODE_HOUSE_CHICKEN_2)) {
            kProgressHUD.show();
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_main_drawer_2);
            toolbarTitle.setText(getString(R.string.housechicken2));
            contentMainVegetables.setVisibility(View.GONE);
            contentMainAnimal.setVisibility(View.VISIBLE);
            imgHome.setVisibility(View.GONE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    loadDataHouseChicken();
                }
            }).start();
            lvAnimal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    if(idNavCurrent == R.id.nav_calendarAuto){
                        kProgressHUD.show();
                        loadDataClendarAuto(position);
                    }
                }
            });

        } else {
            contentMainAnimal.setVisibility(View.GONE);
            contentMainVegetables.setVisibility(View.VISIBLE);

            if(checkItem().equals(Constatnts.CODE_DUA_LUOI)){
                imgHomeDelco.setImageResource(R.mipmap.bg_dua_luoi);
            }else if (checkItem().equals(Constatnts.CODE_THUY_CANH)){
                imgHomeDelco.setImageResource(R.mipmap.image_placeholder);
            }
            getCamera();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    loadDataSensor();
                }
            }).start();
            loadEnvironment();
        }
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View header = navigationView.getHeaderView(0);

        accountName = (TextView) header.findViewById(R.id.accountName);
        accountEmail = (TextView) header.findViewById(R.id.accountEmail);

        accountName.setText(user.getName());
        accountEmail.setText(user.getEmail());

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goUserInfo();
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        MenuItem menuItem = navigationView.getMenu().findItem(R.id.nav_lang);
        LinearLayout v = (LinearLayout) menuItem.getActionView();
        Switch sw = (Switch) v.findViewById(R.id.switchLang);
        if (AppSharedPreferences.getLanguage(getApplicationContext()).equals("en"))
            sw.setChecked(true);
        if (AppSharedPreferences.getLanguage(getApplicationContext()).equals("vi"))
            sw.setChecked(false);

        onChangeLanguage(sw);

    }

    private String checkItem(){
        long idGreenHouse = greenHouse.getId();
        if(idGreenHouse == Constatnts.ID_DUA_LUOI_1 || idGreenHouse == Constatnts.ID_DUA_LUOI_2
                || idGreenHouse == Constatnts.ID_DUA_LUOI_3 || idGreenHouse == Constatnts.ID_DUA_LUOI_4 ){
            return Constatnts.CODE_DUA_LUOI;
        }else {
            if(idGreenHouse == Constatnts.ID_THUY_CANH_1) {
                return Constatnts.CODE_THUY_CANH;
            }
        }
        return Constatnts.CODE_DUA_LUOI;
    }

    private void loadDataClendarAuto(int position) {
        switch(position){
            case 0:
                loadFeedingSchedule();
                break;
            case 1:
                loadCoolingSchedule();
                break;
            case 2:
                loadLightSchedule();
                break;
            case 3:
                loadDrugSchedule();
                break;
        }
    }

    private void loadDrugSchedule() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("henhouseId", Constatnts.HEN_HOUSE_ID);
        param.put("farmId", farm.getId());
        CalendarAutoApi.getDataDrugSchedule(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                JSONArray array = result.get_jsonArray();
                if(array == null || array.length() == 0) {
                    Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.NO_DATA), Toast.LENGTH_SHORT).show();
                    return;
                }
                houseChickensCalendar = new ArrayList<>();
                for(int i = 0; i < array.length(); i++){
                    try {
                        JSONObject object = (JSONObject) array.get(i);
                        List<String> listTimeList = new ArrayList<>();

                        //get Timelist
                        JSONArray arrayDrugItem = (JSONArray) object.get(Constatnts.TIME_LIST);
                        for(int index = 0; index < arrayDrugItem.length(); index++){
                            listTimeList.add((String)arrayDrugItem.get(index));
                        }
                        //remove TimeList
                        object.remove(Constatnts.TIME_LIST);
                        HouseChicken houseChicken = gson.fromJson(object.toString(),HouseChicken.class);
                        houseChicken.setNameObject(Constatnts.DRUG_SCHEDULE);
                        houseChicken.setTimeListDrug(listTimeList);
                        houseChickensCalendar.add(houseChicken);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                sendDataActivityCalendarAuto();
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.NO_DATA), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadLightSchedule() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("henhouseId", Constatnts.HEN_HOUSE_ID);
        param.put("farmId", farm.getId());
        CalendarAutoApi.getDataLightSchedule(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                JSONArray array = result.get_jsonArray();
                if(array == null || array.length() == 0) {
                    Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.NO_DATA), Toast.LENGTH_SHORT).show();
                    return;
                }
                houseChickensCalendar = new ArrayList<>();
                for(int i = 0; i < array.length(); i++){
                    try {
                        JSONObject object = (JSONObject) array.get(i);
                        HouseChicken houseChicken = gson.fromJson(object.toString(),HouseChicken.class);
                        houseChicken.setNameObject(Constatnts.LIGHT_SCHEDULE);

                        houseChickensCalendar.add(houseChicken);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                sendDataActivityCalendarAuto();
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.NO_DATA), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCoolingSchedule() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("henhouseId", Constatnts.HEN_HOUSE_ID);
        param.put("farmId", farm.getId());
        CalendarAutoApi.getDataCoolingSchedule(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                JSONArray array = result.get_jsonArray();
                if(array == null || array.length() == 0) {
                    Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.NO_DATA), Toast.LENGTH_SHORT).show();
                    return;
                }
                houseChickensCalendar = new ArrayList<>();
                for(int i = 0; i < array.length(); i++){
                    try {
                        JSONObject object = (JSONObject) array.get(i);
                        HouseChicken houseChicken = gson.fromJson(object.toString(),HouseChicken.class);
                        houseChicken.setNameObject(Constatnts.COOLING_SCHEDULE);

                        houseChickensCalendar.add(houseChicken);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                sendDataActivityCalendarAuto();
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.NO_DATA), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFeedingSchedule() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("henhouseId", Constatnts.HEN_HOUSE_ID);
        param.put("farmId", farm.getId());
        CalendarAutoApi.getDataFeedingSchedule(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                JSONArray array = result.get_jsonArray();
                if(array == null || array.length() == 0) {
                    Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.NO_DATA), Toast.LENGTH_SHORT).show();
                    return;
                }
                houseChickensCalendar = new ArrayList<>();
                for(int i = 0; i < array.length(); i++){
                    try {
                        JSONObject object = (JSONObject) array.get(i);
                        HouseChicken houseChicken = gson.fromJson(object.toString(),HouseChicken.class);
                        houseChicken.setNameObject(Constatnts.FEEDING_SCHEDULE);

                        houseChickensCalendar.add(houseChicken);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                sendDataActivityCalendarAuto();
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.NO_DATA), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void sendDataActivityCalendarAuto() {
        if(houseChickensCalendar != null && !houseChickensCalendar.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constatnts.LIST_ITEM_HOUSE_CHICKEN, (Serializable) houseChickensCalendar);
            Intent intent = new Intent(MainActivity.this, CalendarAuto.class);
            intent.putExtra(Constatnts.BUNDLE, bundle);
            startActivity(intent);
        }else {
            Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.NO_DATA), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDataHouseChicken() {
        try {
            final VNPTClient client = new VNPTClient("client", Constatnts.IP_MQTT_SERVER);
            client.setQos(0);
            client.connect(null, null, "client1", null);
            if (idNavCurrent == 0) {
                idNavCurrent = R.id.nav_overview;
            }
            client.subscribe(Constatnts.TOPIC_HOUSE_CHICKEN, new VNPTClientEventHandle() {
                @Override
                public void onMessageArrived(String topic, String message) {
                    System.out.println("MQTT message: " + message);
                    if (message == null) return;
                    HouseChickenResponse dataHouseChicken = gson.fromJson(message, HouseChickenResponse.class);
                    updateData(dataHouseChicken.getDataHouseChicken());
                    kProgressHUD.dismiss();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<HouseChicken> listOverView = createListHouseChicken(idNavCurrent, houseChickenList);
                            loadData(listOverView);

                        }
                    });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadData(List<HouseChicken> listOverView) {
        listAnimalAdapter.updateList(listOverView);
    }
    private void clearData() {
        listAnimalAdapter.clearData();
    }

    private List<HouseChicken> createListHouseChicken(int id, List<HouseChicken> houseChickenList) {
        clearData();
        List<HouseChicken> listHouseChicken = new ArrayList<>();
        if (houseChickenList == null || houseChickenList.isEmpty()) return null;
        for (HouseChicken houseChicken : houseChickenList) {
            if ((id == R.id.nav_overview) && (houseChicken.getDisplay().equals(Constatnts.OVERVIEW)
                    || houseChicken.getDisplay().equals(Constatnts.FOLLOW) || houseChicken.getDisplay().equals(Constatnts.WARNING_OVERVIEW))) {
                listHouseChicken.add(houseChicken);
            } else if ((id == R.id.nav_coolingSystem) && houseChicken.getDisplay().equals(Constatnts.COOLING_SYSTEM)) {
                listHouseChicken.add(houseChicken);
            } else if (id == R.id.nav_foodWater && (houseChicken.getDisplay().equals(Constatnts.FOOD) || houseChicken.getDisplay().equals(Constatnts.WATER))) {
                listHouseChicken.add(houseChicken);
            } else if (id == R.id.collectEggsRemoveFeces && (houseChicken.getDisplay().equals(Constatnts.EGG_COLLECT) || houseChicken.getDisplay().equals(Constatnts.DON_PHAN))) {
                listHouseChicken.add(houseChicken);
            } else if (id == R.id.nav_system && houseChicken.getDisplay().equals(Constatnts.SYSTEM)) {
                listHouseChicken.add(houseChicken);
            } else if (id == R.id.nav_automan && houseChicken.getDisplay().equals(Constatnts.AUTO_MAN)) {
                listHouseChicken.add(houseChicken);
            } else if (id == R.id.nav_calendarAuto && houseChicken.getDisplay().equals(Constatnts.CALENDAR_AUTO)) {
                lvAnimal.setBackgroundColor(Color.TRANSPARENT);
                listHouseChicken.add(houseChicken);
            }
        }
        return listHouseChicken;
    }

    private void updateData(DataHouseChicken dataHouseChicken) {
        houseChickenList = new ArrayList<>();

        addHouseChickenOverViewNoname(Constatnts.OVERVIEW, Constatnts.NO_NAME, dataHouseChicken);
        addHouseChickenOverViewSensor(Constatnts.OVERVIEW, getString(R.string.presentValue), dataHouseChicken);
        addHouseChickenOverViewAverave(Constatnts.OVERVIEW, getString(R.string.averageValue), dataHouseChicken);
        addHouseChickenFollow(Constatnts.FOLLOW, getString(R.string.THOI_GIAN_NUOI), dataHouseChicken);
        addHouseChickenOverViewPluseLamp(Constatnts.OVERVIEW, getString(R.string.DEN_CHIEU_SANG), dataHouseChicken);
        addHouseChickenEnergy(Constatnts.OVERVIEW, getString(R.string.DIEN_NANG_TIEU_THU), dataHouseChicken);
        addHouseChickenDoor(Constatnts.WARNING_OVERVIEW, getString(R.string.CUA_RA_VAO), dataHouseChicken);

        addHouseChickenCoolingFanAirFresh(Constatnts.COOLING_SYSTEM, getString(R.string.QUAT_KHI_TUOI), dataHouseChicken);
        addHouseChickenCoolingFanExhaust(Constatnts.COOLING_SYSTEM, getString(R.string.QUAT_THONG_GIO), dataHouseChicken);
        addHouseChickenCoolingFanWind(Constatnts.COOLING_SYSTEM, getString(R.string.QUAT_THONG_GIO_NGANG), dataHouseChicken);
        addHouseChickenCoolingFanCeiling(Constatnts.COOLING_SYSTEM, getString(R.string.QUAT_HUT_TRAN), dataHouseChicken);
        addHouseChickenCoolingFanRoof(Constatnts.COOLING_SYSTEM, getString(R.string.QUAT_HUT_MAI), dataHouseChicken);
        addHouseChickenCoolingDoorAir(Constatnts.COOLING_SYSTEM, getString(R.string.CUA_LAY_KHI), dataHouseChicken);
        addHouseChickenOverViewPump(Constatnts.COOLING_SYSTEM, getString(R.string.MAY_BOM), dataHouseChicken);

        addHouseChickenFood(Constatnts.FOOD, getString(R.string.FOOD), dataHouseChicken);
        addHouseChickenWater(Constatnts.WATER, getString(R.string.WATER), dataHouseChicken);

        addHouseChickenEgg(Constatnts.EGG_COLLECT, getString(R.string.MAY_THU_TRUNG), dataHouseChicken);
        addHouseChickenFeces(Constatnts.DON_PHAN, getString(R.string.BANG_TAI_PHAN), dataHouseChicken);

        addHouseChickenAutoMan(Constatnts.AUTO_MAN, getString(R.string.AUTOMAN), dataHouseChicken);

        addHouseChickenSystem(Constatnts.SYSTEM, getString(R.string.SYSTEM), dataHouseChicken);

        addHouseChickenCalendarAuto(Constatnts.CALENDAR_AUTO, getString(R.string.CALENDAR_AUTO));
    }

    private void addHouseChickenAutoMan(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_KHI_TUOI), "", "", dataHouseChicken.getaUTOMAN1());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_KHI_TRAN), "", "", dataHouseChicken.getaUTOMAN2());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_KHI_NGANG), "", "", dataHouseChicken.getaUTOMAN3());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_GIO_CUOI_CHUONG), "", "", dataHouseChicken.getaUTOMAN4());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_GIO_MAI), "", "", dataHouseChicken.getaUTOMAN5());
        addDataHouseChicken(houseChicken, getString(R.string.CUA_LAY_KHI_NGANG), "", "", dataHouseChicken.getaUTOMAN3N());
        addDataHouseChicken(houseChicken, getString(R.string.BOM_NUOC_LAM_MAT), "", "", dataHouseChicken.getaUTOMAN6());
        addDataHouseChicken(houseChicken, getString(R.string.BOM_KHU_TRUNG_2), "", "", dataHouseChicken.getaUTOMAN7());
        addDataHouseChicken(houseChicken, getString(R.string.HE_THONG_TAI_CAM_SILO), "", "", dataHouseChicken.getaUTOMAN8A());
        addDataHouseChicken(houseChicken, getString(R.string.HE_THONG_TAI_CAM), "", "", dataHouseChicken.getaUTOMAN8B());
        addDataHouseChicken(houseChicken, getString(R.string.HE_THONG_CHIEU_SANG), "", "", dataHouseChicken.getaUTOMAN9());
//        addDataHouseChicken(houseChicken, getString(R.string.HE_THONG_PHA_THUOC), "", "", dataHouseChicken.getaUTOMAN10());
        addDataHouseChicken(houseChicken, getString(R.string.HE_THONG_TAI_PHAN), "", "", dataHouseChicken.getaUTOMAN11());
    }

    private void addHouseChickenDoor(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);
        addDataHouseChicken(houseChicken, getString(R.string.CUA_RA_VAO_1), "", "", dataHouseChicken.getdOOR1());
        addDataHouseChicken(houseChicken, getString(R.string.CUA_RA_VAO_2), "", "", dataHouseChicken.getdOOR2());
        addDataHouseChicken(houseChicken, getString(R.string.CUA_RA_VAO_3), "", "", dataHouseChicken.getdOOR3());
        addDataHouseChicken(houseChicken, getString(R.string.CUA_RA_VAO_4), "", "", dataHouseChicken.getdOOR4());
        addDataHouseChicken(houseChicken, getString(R.string.CUA_RA_VAO_5), "", "", dataHouseChicken.getdOOR5());
    }

    private void addHouseChickenOverViewNoname(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
//        addDataHouseChicken(houseChicken, getString(R.string.tempoutdoor), "", Constatnts.DO_CELSIUS, dataHouseChicken.getaITEMPOUTDOOR());
        addDataHouseChicken(houseChicken, getString(R.string.sensorLoadcell), "", Constatnts.KG, dataHouseChicken.getaILOACELL());
    }

    private void addHouseChickenOverViewSensor(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);
        addDataHouseChicken(houseChicken, getString(R.string.sensorConcentration1), "", Constatnts.PPM, dataHouseChicken.getaICO21());
        addDataHouseChicken(houseChicken, getString(R.string.sensorConcentration2), "", Constatnts.PPM, dataHouseChicken.getaICO22());

        addDataHouseChicken(houseChicken, getString(R.string.tempSensor1), "", Constatnts.DO_CELSIUS, dataHouseChicken.getaITEMP1());
        addDataHouseChicken(houseChicken, getString(R.string.tempSensor2), "", Constatnts.DO_CELSIUS, dataHouseChicken.getaITEMP2());
        addDataHouseChicken(houseChicken, getString(R.string.tempSensor3), "", Constatnts.DO_CELSIUS, dataHouseChicken.getaITEMP3());
        addDataHouseChicken(houseChicken, getString(R.string.tempSensor4), "", Constatnts.DO_CELSIUS, dataHouseChicken.getaITEMP4());
        addDataHouseChicken(houseChicken, getString(R.string.tempSensor5), "", Constatnts.DO_CELSIUS, dataHouseChicken.getaITEMP5());

        addDataHouseChicken(houseChicken, getString(R.string.humiditySensor1), "", Constatnts.PERCENT, dataHouseChicken.getaIHUM1());
        addDataHouseChicken(houseChicken, getString(R.string.humiditySensor2), "", Constatnts.PERCENT, dataHouseChicken.getaIHUM2());
        addDataHouseChicken(houseChicken, getString(R.string.humiditySensor3), "", Constatnts.PERCENT, dataHouseChicken.getaIHUM3());
        addDataHouseChicken(houseChicken, getString(R.string.humiditySensor4), "", Constatnts.PERCENT, dataHouseChicken.getaIHUM4());

        addDataHouseChicken(houseChicken, getString(R.string.luxSensor), "", Constatnts.LUX, dataHouseChicken.getaILUX());
        addDataHouseChicken(houseChicken, getString(R.string.pressureSensor), "", Constatnts.PAS, dataHouseChicken.getaIPRES());

    }

    private void addHouseChickenOverViewAverave(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChickenEnvironment = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChickenEnvironment, null, null, null, null);
        addDataHouseChicken(houseChickenEnvironment, getString(R.string.consentrationCO2), "", Constatnts.PPM, dataHouseChicken.getaICO2AVERAGE());
        addDataHouseChicken(houseChickenEnvironment, getString(R.string.tempSensorWaterTank), "", "", dataHouseChicken.getaITEMPWATER());

        addDataHouseChicken(houseChickenEnvironment, getString(R.string.sensorAngleMeasureTheWindOpenLEFT), "", Constatnts.PERCENT, dataHouseChicken.getdOORANGLE1());
        addDataHouseChicken(houseChickenEnvironment, getString(R.string.sensorAngleMeasureTheWindOpenRIGHT), "", Constatnts.PERCENT, dataHouseChicken.getdOORANGLE2());

        addDataHouseChicken(houseChickenEnvironment, getString(R.string.huminity), "", Constatnts.PERCENT, dataHouseChicken.getaIHUMAVERAGE());
        addDataHouseChicken(houseChickenEnvironment, getString(R.string.tempAverage), "", Constatnts.DO_CELSIUS, dataHouseChicken.getaITEMPAVERAGE());

    }


    private void addHouseChickenCoolingFanExhaust(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_1), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO1ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_1), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO1OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_1), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO1ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_1), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO1EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_2), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO2ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_2), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO2OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_2), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO2ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_2), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO2EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_3), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO3ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_3), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO3OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_3), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO3ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_3), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO3EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_4), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO4ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_4), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO4OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_4), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO4ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_4), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO4EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_5), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO5ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_5), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO5OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_5), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO5ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_5), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO5EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_6), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO6ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_6), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO6OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_6), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO6ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_6), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO6EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_7), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO7ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_7), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO7OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_7), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO7ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_7), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO7EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_8), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO8ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_8), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO8OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_8), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO8ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_8), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO8EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_9), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO9ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_9), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO9OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_9), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO9ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_9), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO9EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_10), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO10ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_10), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO10OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_10), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO10ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_10), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO10EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_11), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO11ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_11), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO11OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_11), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO11ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_11), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO11EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_12), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO12ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_12), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO12OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_12), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO12ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_12), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO12EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_13), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO13ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_13), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO13OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_13), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO13ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_13), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO13EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_14), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO14ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_14), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO14OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_14), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO14ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_14), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO14EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_15), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO15ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_15), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO15OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_15), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO15ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_15), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO15EM());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_16), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTGIO16ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_16), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTGIO16OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_16), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTGIO16ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_16), Constatnts.EMERGENCY, "", dataHouseChicken.getqUATHUTGIO16EM());

    }

    private void addHouseChickenCoolingFanWind(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_1), Constatnts.ON_OFF, "", dataHouseChicken.getqUATGIONGANG1ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_1), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATGIONGANG1OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_1), Constatnts.ERROR, "", dataHouseChicken.getqUATGIONGANG1ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_1), Constatnts.SPEED, Constatnts.PERCENT, dataHouseChicken.getqUATGIONGANG1SP());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_2), Constatnts.ON_OFF, "", dataHouseChicken.getqUATGIONGANG2ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_2), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATGIONGANG2OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_2), Constatnts.ERROR, "", dataHouseChicken.getqUATGIONGANG2ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_2), Constatnts.SPEED, Constatnts.PERCENT, dataHouseChicken.getqUATGIONGANG2SP());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_3), Constatnts.ON_OFF, "", dataHouseChicken.getqUATGIONGANG3ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_3), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATGIONGANG3OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_3), Constatnts.ERROR, "", dataHouseChicken.getqUATGIONGANG3ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_3), Constatnts.SPEED, Constatnts.PERCENT, dataHouseChicken.getqUATGIONGANG3SP());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_4), Constatnts.ON_OFF, "", dataHouseChicken.getqUATGIONGANG4ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_4), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATGIONGANG4OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_4), Constatnts.ERROR, "", dataHouseChicken.getqUATGIONGANG4ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_4), Constatnts.SPEED, Constatnts.PERCENT, dataHouseChicken.getqUATGIONGANG4SP());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_5), Constatnts.ON_OFF, "", dataHouseChicken.getqUATGIONGANG5ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_5), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATGIONGANG5OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_5), Constatnts.ERROR, "", dataHouseChicken.getqUATGIONGANG5ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_5), Constatnts.SPEED, Constatnts.PERCENT, dataHouseChicken.getqUATGIONGANG5SP());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_6), Constatnts.ON_OFF, "", dataHouseChicken.getqUATGIONGANG6ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_6), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATGIONGANG6OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_6), Constatnts.ERROR, "", dataHouseChicken.getqUATGIONGANG6ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_THONG_GIO_NGANG_6), Constatnts.SPEED, Constatnts.PERCENT, dataHouseChicken.getqUATGIONGANG6SP());

    }

    private void addHouseChickenCoolingDoorAir(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.CUA_LAY_KHI_1), Constatnts.ON_OFF, "", dataHouseChicken.getcUAKHINGANG1ON());
        addDataHouseChicken(houseChicken, getString(R.string.CUA_LAY_KHI_1), Constatnts.OVERLOAD, "", dataHouseChicken.getcUAKHINGANG1OVL());
        addDataHouseChicken(houseChicken, getString(R.string.CUA_LAY_KHI_1), Constatnts.ERROR, "", dataHouseChicken.getcUAKHINGANG1ERR());
        addDataHouseChicken(houseChicken, getString(R.string.CUA_LAY_KHI_1), "", Constatnts.PERCENT, dataHouseChicken.getcUAKHINGANG1ANGLE());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.CUA_LAY_KHI_2), Constatnts.ON_OFF, "", dataHouseChicken.getcUAKHINGANG2ON());
        addDataHouseChicken(houseChicken, getString(R.string.CUA_LAY_KHI_2), Constatnts.OVERLOAD, "", dataHouseChicken.getcUAKHINGANG2OVL());
        addDataHouseChicken(houseChicken, getString(R.string.CUA_LAY_KHI_2), Constatnts.ERROR, "", dataHouseChicken.getcUAKHINGANG2ERR());
        addDataHouseChicken(houseChicken, getString(R.string.CUA_LAY_KHI_2), "", Constatnts.PERCENT, dataHouseChicken.getcUAKHINGANG2ANGLE());

    }

    private void addHouseChickenCoolingFanAirFresh(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);
        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_KHI_TUOI_1), Constatnts.ON_OFF, "", dataHouseChicken.getqUATKHITUOI1ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_KHI_TUOI_1), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATKHITUOI1OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_KHI_TUOI_1), Constatnts.ERROR, "", dataHouseChicken.getqUATKHITUOI1ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_KHI_TUOI_1), Constatnts.SPEED, Constatnts.PERCENT, dataHouseChicken.getqUATKHITUOI1SP());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_KHI_TUOI_2), Constatnts.ON_OFF, "", dataHouseChicken.getqUATKHITUOI2ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_KHI_TUOI_2), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATKHITUOI2OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_KHI_TUOI_2), Constatnts.ERROR, "", dataHouseChicken.getqUATKHITUOI2ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_KHI_TUOI_2), Constatnts.SPEED, Constatnts.PERCENT, dataHouseChicken.getqUATKHITUOI2SP());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_KHI_TUOI_3), Constatnts.ON_OFF, "", dataHouseChicken.getqUATKHITUOI3ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_KHI_TUOI_3), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATKHITUOI3OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_KHI_TUOI_3), Constatnts.ERROR, "", dataHouseChicken.getqUATKHITUOI3ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_KHI_TUOI_3), Constatnts.SPEED, Constatnts.PERCENT, dataHouseChicken.getqUATKHITUOI3SP());
    }

    private void addHouseChickenCoolingFanCeiling(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_TRAN_1), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTTRAN1ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_TRAN_1), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTTRAN1OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_TRAN_1), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTTRAN1ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_TRAN_1), Constatnts.SPEED, Constatnts.PERCENT, dataHouseChicken.getqUATHUTTRAN1SP());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_TRAN_2), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTTRAN2ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_TRAN_2), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTTRAN2OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_TRAN_2), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTTRAN2ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_TRAN_2), Constatnts.SPEED, Constatnts.PERCENT, dataHouseChicken.getqUATHUTTRAN2SP());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_TRAN_3), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTTRAN3ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_TRAN_3), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTTRAN3OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_TRAN_3), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTTRAN3ERR());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_TRAN_3), Constatnts.SPEED, Constatnts.PERCENT, dataHouseChicken.getqUATHUTTRAN3SP());

    }

    private void addHouseChickenCoolingFanRoof(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_MAI_1), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTMAI1ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_MAI_1), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTMAI1OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_MAI_1), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTMAI1ERR());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_MAI_2), Constatnts.ON_OFF, "", dataHouseChicken.getqUATHUTMAI2ON());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_MAI_2), Constatnts.OVERLOAD, "", dataHouseChicken.getqUATHUTMAI2OVL());
        addDataHouseChicken(houseChicken, getString(R.string.QUAT_HUT_MAI_2), Constatnts.ERROR, "", dataHouseChicken.getqUATHUTMAI2ERR());
    }

    private void addHouseChickenOverViewPluseLamp(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);
        addDataHouseChicken(houseChicken, getString(R.string.DEN_CHIEU_SANG), Constatnts.PLUSE, Constatnts.PERCENT, dataHouseChicken.getpLUSELAMP());
    }

    private void addHouseChickenFollow(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);
        addDataHouseChicken(houseChicken, getString(R.string.SO_NGAY_TRONG_CHU_KI_NUOI), Constatnts.DAY_PERIOD_TOTAL, Constatnts.DAY, dataHouseChicken.getdAYPERIODTOTAL());
        addDataHouseChicken(houseChicken, getString(R.string.SO_TUAN_TRONG_CHU_KI_NUOI), Constatnts.WEEK_PERIOD_TOTAL, Constatnts.WEEK, dataHouseChicken.getwEEKPERIODTOTAL());
    }

    private void addHouseChickenEnergy(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);
        addDataHouseChicken(houseChicken, getString(R.string.VOLT_AB), "", getString(R.string.VOLT), dataHouseChicken.getvOLTAB());
        addDataHouseChicken(houseChicken, getString(R.string.VOLT_BC), "", getString(R.string.VOLT), dataHouseChicken.getvOLTBC());
        addDataHouseChicken(houseChicken, getString(R.string.VOLT_CA), "", getString(R.string.VOLT), dataHouseChicken.getvOLTCA());
        addDataHouseChicken(houseChicken, getString(R.string.AMPE_A), "", getString(R.string.AMPE), dataHouseChicken.getaMPEA());
        addDataHouseChicken(houseChicken, getString(R.string.AMPE_B), "", getString(R.string.AMPE), dataHouseChicken.getaMPEB());
        addDataHouseChicken(houseChicken, getString(R.string.AMPE_C), "", getString(R.string.AMPE), dataHouseChicken.getaMPEC());
        addDataHouseChicken(houseChicken, getString(R.string.SUM_P_DAY), "", getString(R.string.KWH), dataHouseChicken.getsUMPDAY());
        addDataHouseChicken(houseChicken, getString(R.string.SUM_P_POWER_TOTAL), "", getString(R.string.KWH), dataHouseChicken.getsUMPPOWERTOTAL());
    }

    private void addHouseChickenOverViewPump(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.MAY_BOM_NUOC_MAT_1), Constatnts.ON_OFF, "", dataHouseChicken.getbOMCOOLINGON());
        addDataHouseChicken(houseChicken, getString(R.string.MAY_BOM_NUOC_MAT_1), Constatnts.OVERLOAD, "", dataHouseChicken.getbOMCOOLINGOVL());
        addDataHouseChicken(houseChicken, getString(R.string.MAY_BOM_NUOC_MAT_1), Constatnts.ERROR, "", dataHouseChicken.getbOMCOOLINGERR());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.DONG_CO_KEO_BAT_COOLINGPAD), Constatnts.ON_OFF, "", dataHouseChicken.getkEOBATCOOLINGPADON());
        addDataHouseChicken(houseChicken, getString(R.string.DONG_CO_KEO_BAT_COOLINGPAD), Constatnts.OVERLOAD, "", dataHouseChicken.getkEOBATCOOLINGPADOVL());
    }

    private void addHouseChickenFood(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);
        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.DONG_CO_RAI_CAM_LINE1), Constatnts.ON_OFF, "", dataHouseChicken.getrAICAMLINE1ON());
        addDataHouseChicken(houseChicken, getString(R.string.DONG_CO_RAI_CAM_LINE1), Constatnts.OVERLOAD, "", dataHouseChicken.getrAICAMLINE1OVL());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.DONG_CO_RAI_CAM_LINE2), Constatnts.ON_OFF, "", dataHouseChicken.getrAICAMLINE2ON());
        addDataHouseChicken(houseChicken, getString(R.string.DONG_CO_RAI_CAM_LINE2), Constatnts.OVERLOAD, "", dataHouseChicken.getrAICAMLINE2OVL());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.DONG_CO_RAI_CAM_LINE3), Constatnts.ON_OFF, "", dataHouseChicken.getrAICAMLINE3ON());
        addDataHouseChicken(houseChicken, getString(R.string.DONG_CO_RAI_CAM_LINE3), Constatnts.OVERLOAD, "", dataHouseChicken.getrAICAMLINE3OVL());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.DONG_CO_RAI_CAM_LINE4), Constatnts.ON_OFF, "", dataHouseChicken.getrAICAMLINE4ON());
        addDataHouseChicken(houseChicken, getString(R.string.DONG_CO_RAI_CAM_LINE4), Constatnts.OVERLOAD, "", dataHouseChicken.getrAICAMLINE4OVL());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_CAM_TU_SILO), Constatnts.ON_OFF, "", dataHouseChicken.gettAICAMSILOON());
        addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_CAM_TU_SILO), Constatnts.OVERLOAD, "", dataHouseChicken.gettAICAMSILOOVL());

        houseChicken = addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_CAM_NGANG), Constatnts.ON_OFF, "", dataHouseChicken.gettAICAMON());
        addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_CAM_NGANG), Constatnts.OVERLOAD, "", dataHouseChicken.gettAICAMOVL());

        addDataHouseChicken(houseChicken, getString(R.string.CAM_BIEN_BAO_DAY_BANG_TAI_CAM_SILO), Constatnts.ON_OFF, "", dataHouseChicken.gettAICAMLEVERFULL());
        addDataHouseChicken(houseChicken, getString(R.string.CAM_BIEN_BAO_DAY_BANG_TAI_CAM_NGANG), Constatnts.ON_OFF, "", dataHouseChicken.gettAICAMLEVERFULLB());
        addDataHouseChicken(houseChicken, getString(R.string.SILO_CAM_MUC_THAP), Constatnts.ON_OFF, "", dataHouseChicken.gettAICAMSILOLEVER());
    }

    private void addHouseChickenWater(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);
        addDataHouseChicken(houseChicken, getString(R.string.BE_CAP_NUOC), Constatnts.ON_OFF, "", dataHouseChicken.getwATERLEVEL());
        addDataHouseChicken(houseChicken, getString(R.string.HE_THONG_LAY_NUOC), Constatnts.ON_OFF, "", dataHouseChicken.getwATERON());
        addDataHouseChicken(houseChicken, getString(R.string.BO_BAO_NUOC), Constatnts.ON_OFF, "", dataHouseChicken.getwATERWARMING());

        addDataHouseChicken(houseChicken, getString(R.string.VAN_TOC_CAP_NUOC), "", Constatnts.SPEED_UNIT_WATER, dataHouseChicken.getwATERSPEED());
        addDataHouseChicken(houseChicken, getString(R.string.LUONG_NUOC_DINH_MUC_TRONG_NGAY), "", Constatnts.UNIT_WATER, dataHouseChicken.getwATERLEVERDAY());
        int sumerwater1 = 0;
        int sumerwater2 = 0;
        if(dataHouseChicken.getsUMWATERN1() != null && !dataHouseChicken.getsUMWATERN1().isEmpty()) sumerwater1 = Integer.valueOf(dataHouseChicken.getsUMWATERN1());
        if(dataHouseChicken.getsUMWATERN2() != null && !dataHouseChicken.getsUMWATERN2().isEmpty()) sumerwater2 = Integer.valueOf(dataHouseChicken.getsUMWATERN2());
        addDataHouseChicken(houseChicken, getString(R.string.TONG_LUONG_NUOC_GA_UONG), "", Constatnts.UNIT_WATER, String.valueOf(sumerwater1+sumerwater2));
        addDataHouseChicken(houseChicken, getString(R.string.TONG_LUONG_NUOC_CAP), "", Constatnts.UNIT_WATER, dataHouseChicken.getsUMWATERSUPPLY());
    }

    private void addHouseChickenEgg(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);
        addDataHouseChicken(houseChicken, getString(R.string.MAY_THU_TRUNG_1), Constatnts.ON_OFF, "", dataHouseChicken.gettHUTRUNGLINE1ON());
        addDataHouseChicken(houseChicken, getString(R.string.MAY_THU_TRUNG_1), Constatnts.OVERLOAD, "", dataHouseChicken.gettHUTRUNGLINE1OVL());

        addDataHouseChicken(houseChicken, getString(R.string.MAY_THU_TRUNG_2), Constatnts.ON_OFF, "", dataHouseChicken.gettHUTRUNGLINE2ON());
        addDataHouseChicken(houseChicken, getString(R.string.MAY_THU_TRUNG_2), Constatnts.OVERLOAD, "", dataHouseChicken.gettHUTRUNGLINE2OVL());

        addDataHouseChicken(houseChicken, getString(R.string.MAY_THU_TRUNG_3), Constatnts.ON_OFF, "", dataHouseChicken.gettHUTRUNGLINE3ON());
        addDataHouseChicken(houseChicken, getString(R.string.MAY_THU_TRUNG_3), Constatnts.OVERLOAD, "", dataHouseChicken.gettHUTRUNGLINE3OVL());

        addDataHouseChicken(houseChicken, getString(R.string.MAY_THU_TRUNG_4), Constatnts.ON_OFF, "", dataHouseChicken.gettHUTRUNGLINE4ON());
        addDataHouseChicken(houseChicken, getString(R.string.MAY_THU_TRUNG_4), Constatnts.OVERLOAD, "", dataHouseChicken.gettHUTRUNGLINE4OVL());

        addDataHouseChicken(houseChicken, getString(R.string.KIEM_TRA_BAN_THU_TRUNG_LINE_1), Constatnts.ON_OFF, "", dataHouseChicken.gettHUTRUNGLINE1CAMCHECK());
        addDataHouseChicken(houseChicken, getString(R.string.KIEM_TRA_BAN_THU_TRUNG_LINE_2), Constatnts.ON_OFF, "", dataHouseChicken.gettHUTRUNGLINE2CAMCHECK());
        addDataHouseChicken(houseChicken, getString(R.string.KIEM_TRA_BAN_THU_TRUNG_LINE_3), Constatnts.ON_OFF, "", dataHouseChicken.gettHUTRUNGLINE3CAMCHECK());
        addDataHouseChicken(houseChicken, getString(R.string.KIEM_TRA_BAN_THU_TRUNG_LINE_4), Constatnts.ON_OFF, "", dataHouseChicken.gettHUTRUNGLINE4CAMCHECK());
    }

    private void addHouseChickenFeces(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);
        addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_PHAN_LINE_1), Constatnts.ON_OFF, "", dataHouseChicken.gettAIPHANLINE1ON());
        addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_PHAN_LINE_1), Constatnts.OVERLOAD, "", dataHouseChicken.gettAIPHANLINE1OVL());

        addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_PHAN_LINE_2), Constatnts.ON_OFF, "", dataHouseChicken.gettAIPHANLINE2ON());
        addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_PHAN_LINE_2), Constatnts.OVERLOAD, "", dataHouseChicken.gettAIPHANLINE2OVL());

        addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_PHAN_LINE_3), Constatnts.ON_OFF, "", dataHouseChicken.gettAIPHANLINE3ON());
        addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_PHAN_LINE_3), Constatnts.OVERLOAD, "", dataHouseChicken.gettAIPHANLINE3OVL());

        addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_PHAN_LINE_4), Constatnts.ON_OFF, "", dataHouseChicken.gettAIPHANLINE4ON());
        addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_PHAN_LINE_4), Constatnts.OVERLOAD, "", dataHouseChicken.gettAIPHANLINE4OVL());

        addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_PHAN_NGANG), Constatnts.ON_OFF, "", dataHouseChicken.gettAIPHANNGANGON());
        addDataHouseChicken(houseChicken, getString(R.string.BANG_TAI_PHAN_NGANG), Constatnts.OVERLOAD, "", dataHouseChicken.gettAIPHANNGANGOVL());
        addDataHouseChicken(houseChicken, getString(R.string.BOM_KHU_TRUNG), Constatnts.ON_OFF, "", dataHouseChicken.getbOMKHUTRUNGON());
        addDataHouseChicken(houseChicken, getString(R.string.BOM_KHU_TRUNG), Constatnts.OVERLOAD, "", dataHouseChicken.getbOMKHUTRUNGOVL());
    }

    private void addHouseChickenSystem(String display, String groupObject, DataHouseChicken dataHouseChicken) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, null, null, null, null);
        addDataHouseChicken(houseChicken, getString(R.string.DUNG_KHAN_BI_KICH_HOAT), Constatnts.ON_OFF, "", dataHouseChicken.geteSTOP());
        addDataHouseChicken(houseChicken, getString(R.string.RESET_CAC_CANH_BAO_TREN_TU_DIEU_KHIEN), Constatnts.ON_OFF, "", dataHouseChicken.getrESETALARM());
        addDataHouseChicken(houseChicken, getString(R.string.COI_BAO_BI_VO_HIEU_HOA), Constatnts.ON_OFF, "", dataHouseChicken.getsPEAKOFF());
        addDataHouseChicken(houseChicken, getString(R.string.MAT_KET_NOI_PLC1_PLC2), Constatnts.ON_OFF, "", dataHouseChicken.getcONNECTPLC1PLC2STATUS());
        addDataHouseChicken(houseChicken, getString(R.string.MAT_KET_NOI_PLC1_IOTGATEWAY), Constatnts.ON_OFF, "", dataHouseChicken.getcONNECTPLC1IOTSTATUS());
    }

    private void addHouseChickenCalendarAuto(String display, String groupObject) {
        HouseChicken houseChicken = new HouseChicken(display, groupObject);
        addDataHouseChicken(houseChicken, Constatnts.CALENDAR_AUTO_ITEM_1, "", "", getString(R.string.LICH_CHO_GA_AN));
        addDataHouseChicken(houseChicken, Constatnts.CALENDAR_AUTO_ITEM_2, "", "", getString(R.string.LICH_LAM_MAT));
        addDataHouseChicken(houseChicken, Constatnts.CALENDAR_AUTO_ITEM_3, "", "", getString(R.string.LICH_CHIEU_SANG));
        addDataHouseChicken(houseChicken, Constatnts.CALENDAR_AUTO_ITEM_4, "", "", getString(R.string.LICH_UONG_NUOC));
    }

    private HouseChicken addDataHouseChicken(HouseChicken houseChicken, String name, String state, String unit, String value) {
        if (name == null) {
            houseChicken = new HouseChicken(houseChicken.getDisplay(), houseChicken.getGroupObject());
            houseChicken.setHeader(true);
            houseChickenList.add(houseChicken);
            return houseChicken;
        }
        if (featureHouseChicken == null || !featureHouseChicken.getName().equals(name)) {
            houseChicken = new HouseChicken(houseChicken.getDisplay(), houseChicken.getGroupObject());
            featureHouseChicken = new FeatureHouseChicken(state, name, value, unit);
            houseChicken.getFeatureHouseChickenList().add(featureHouseChicken);
            houseChicken.setName(name);
            houseChicken.setHeader(false);
            houseChickenList.add(houseChicken);
        } else {
            featureHouseChicken = new FeatureHouseChicken(state, name, value, unit);
            houseChicken.getFeatureHouseChickenList().add(featureHouseChicken);
        }
        return houseChicken;
    }

    private void loadEnvironment() {
        GreenHouse sector = AppSharedPreferences.getGreenHouse(getApplicationContext());
        HashMap<String, Object> param = new HashMap<>();
        param.put("sectorId", sector.getId());
        EnvironmentApi.getData(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                AirCooling airCooling = gson.fromJson(result.get_jsonObject().toString(), AirCooling.class);
                temp.setText(String.valueOf(airCooling.getTemp()));
                moisture.setText(String.valueOf(airCooling.getHumi()));
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
            }
        });
    }

    private void getCamera() {

        HashMap<String, Object> param = new HashMap<>();
        param.put("farmId", farm.getId());
        param.put("sectorId", greenHouse.getId());
        if(!kProgressHUD.isShowing())
            kProgressHUD.show();
        DeviceApi.getListCamera(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                cameraList = Parse.getInstance().camera(result);
                addItemForCameraList(cameraList);
                initViewCam();
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                noCamera.setVisibility(View.VISIBLE);
                listImage.setVisibility(View.GONE);
            }
        });
    }

    private void addItemForCameraList(List<Camera> cameraList) {
        if(cameraList != null && !cameraList.isEmpty()){
            for(Camera camera : cameraList){
                if(checkItem().equals(Constatnts.CODE_DUA_LUOI)){
                    camera.setCheckItem(Constatnts.CODE_DUA_LUOI);
                }else if (checkItem().equals(Constatnts.CODE_THUY_CANH)){
                    camera.setCheckItem(Constatnts.CODE_THUY_CANH);
                }
            }
        }
    }

    private void initViewCam() {
        if (cameraList.size() == 0) return;
        noCamera.setVisibility(View.GONE);
        listImage.setVisibility(View.VISIBLE);
        CameraAdapter cameraAdapter = new CameraAdapter(this, cameraList);
        listImage.setAdapter(cameraAdapter);
        cameraAdapter.setOnClickItem(new CameraAdapter.OnClickItem() {
            @Override
            public void onClickItem(int position) {
                Camera camera = cameraList.get(position);
                if (camera.getUrlHDView() != null) {
                    String url = camera.getUrlHDView();
                    if(Utils.validateURI(url)){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }

                } else if (camera.getUrlView() != null && !camera.getUrlView().isEmpty()) {
                    String url = camera.getUrlView();
                    if(Utils.validateURI(url)){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                }

            }
        });
    }

    private void goUserInfo() {
        startActivity(new Intent(this, UserActivity.class));
    }

    private DisplayMode calculateDisplayMode() {
        int orientation = getResources().getConfiguration().orientation;
        return orientation == Configuration.ORIENTATION_LANDSCAPE ?
                DisplayMode.FULLSCREEN : DisplayMode.BEST_FIT;
    }

    private void onChangeLanguage(Switch sw) {

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppSharedPreferences.setLanguage(getApplicationContext(), "en");
                    reloadActivity();
                } else {
                    AppSharedPreferences.setLanguage(getApplicationContext(), "vi");
                    reloadActivity();
                }
            }
        });
    }

    private void reloadActivity() {
        finish();
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sw", true);
        startActivity(intent);
    }

    private void loadDataSensor() {
        String topicGW = "/SCPCloud/DEVICE/" + AppSharedPreferences.getGateway(getApplicationContext());
        System.out.println("MQTT topicGW: " + topicGW);
        try {
            final VNPTClient client = new VNPTClient("client", Constatnts.IP_MQTT_SERVER);
            client.setQos(0);
            client.connect(null, null, "client1", null);
            client.subscribe(topicGW, new VNPTClientEventHandle() {
                @Override
                public void onMessageArrived(String topic, String message) {
                    System.out.println("MQTT message: " + message);
                    if (message == null) return;
                    DataMQTTDevice dataMQTTDevice = gson.fromJson(message, DataMQTTDevice.class);
                    setDataView(dataMQTTDevice);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        String ipBrokerLocal = "tcp://" + AppSharedPreferences.getIpBroker(getApplicationContext()) + ":" + AppSharedPreferences.getPortBroker(getApplicationContext());
        try {
            final VNPTClient clientLocal = new VNPTClient("client", ipBrokerLocal);
            clientLocal.setQos(0);
            clientLocal.connect(null, null, "client1", null);
            clientLocal.subscribe(topicGW, new VNPTClientEventHandle() {
                @Override
                public void onMessageArrived(String topic, String message) {
                    System.out.println("NhietDoDoAm MSG: " + message);
                    if (message == null) return;
                    DataMQTTDevice dataMQTTDevice = gson.fromJson(message, DataMQTTDevice.class);
                    setDataView(dataMQTTDevice);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void setDataView(DataMQTTDevice dataMQTTDevice) {
        handler.post(new Runnable() {
            public void run() {
                if (dataMQTTDevice == null) return;
                if ((dataMQTTDevice.getDevice_values() != null) && (dataMQTTDevice.getDevice_values().size() > 0)) {
                    for (DataMQTTParam dataMQTTParam : dataMQTTDevice.getDevice_values()) {
                        if (dataMQTTParam.getParam().equals("air_temperature")) {
                            temp.setText(String.valueOf(dataMQTTParam.getValue()));
                        }
                        if ((dataMQTTParam.getParam().equals("air_humidity"))) {
                            moisture.setText(String.valueOf(dataMQTTParam.getValue()));
                        }
                    }
                }
            }
        });
    }

    public void goNotification(View view) {
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }

    private void initView() {
        gson = new Gson();
        handler = new Handler();
        lvAnimal.setDivider(null);
        lvAnimal.setDividerHeight(0);
        listAnimalAdapter = new ListAnimalAdapter(this, new ArrayList<>());
        lvAnimal.setAdapter(listAnimalAdapter);
    }

    public void goControlWater(View view) {
        Intent intent = new Intent(this, ControlWaterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            return;
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        idNavCurrent = id;
        boolean result;

        naviPlant(id);
        naviAnimal(id);
        result = naviGeneral(id);

        drawer.closeDrawer(GravityCompat.START);
        return result;
    }

    private boolean naviGeneral(int id) {
        if (id == R.id.nav_log_out) {
            logout();
        } else if (id == R.id.nav_lang) {
            return false;
        } else if (id == R.id.selectFarm) {
            finish();
        }
        return true;
    }

    private void naviAnimal(int id) {
        List<HouseChicken> listHouseChicken = createListHouseChicken(id, houseChickenList);
        loadDataNavi(listHouseChicken);
    }

    private void loadDataNavi(List<HouseChicken> listHouseChicken) {
        if (listHouseChicken == null || listHouseChicken.isEmpty()) return;
        kProgressHUD.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData(listHouseChicken);
                kProgressHUD.dismiss();
            }
        }, 300);
    }

    private void naviPlant(int id) {
        if (id == R.id.nav_schedule) {
            startActivity(new Intent(this, ScheduleActivity.class));
        } else if (id == R.id.nav_dailyGrowthReport) {
            startActivity(new Intent(this, GrowthScheduleActivity.class));
        } else if (id == R.id.nav_group_time) {
            startActivity(new Intent(this, PhaseActivity.class));
        } else if (id == R.id.nav_evi_info) {
            startActivity(new Intent(this, EnvironmentInfoActivity.class));
        } else if (id == R.id.nav_follow) {
            startActivity(new Intent(this, ProcedureManagementActivity.class));
        } else if (id == R.id.nav_make) {
            goSearchResource();
        } else if (id == R.id.nav_money) {
            startActivity(new Intent(this, AssetActivity.class));
        } else if (id == R.id.nav_account) {
            startActivity(new Intent(this, DailyNoteActivity.class));
        }
    }

    private void goSearchResource() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 321);
        } else {
            startActivity(new Intent(this, SearchSourceActivity.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 321) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity(new Intent(this, SearchSourceActivity.class));
            }
        }
    }

    private void logout() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("token", AppSharedPreferences.getDeviceToken(getApplicationContext()));

        UserApi.deleteToken(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {

            }

            @Override
            public void onRequestError(ApiResponse error) {

            }
        });

        AppSharedPreferences.setRememberLoggedIn(getApplicationContext(), false);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (greenHouse.getName().equals(Constatnts.HARD_CODE_HOUSE_CHICKEN_2)) {
            if(idNavCurrent == 0) {
                navigationView.setCheckedItem(R.id.nav_overview);
            }else {
                navigationView.setCheckedItem(idNavCurrent);
            }
        } else {
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

}
