package com.vnpt.iot.hoalac.core.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.vnpt.iot.hoalac.core.model.DeviceSchedule;
import com.vnpt.iot.hoalac.core.model.Farm;
import com.vnpt.iot.hoalac.core.model.GreenHouse;
import com.vnpt.iot.hoalac.core.model.GroupDevice;
import com.vnpt.iot.hoalac.core.model.GroupDeviceSchedule;
import com.vnpt.iot.hoalac.core.model.User;
import com.google.gson.Gson;

import java.util.HashMap;

public class AppSharedPreferences {
    public final static String ORGANIC_SHARED_PREFERENCES = "OrganicSharedPreferences";
    public final static String SHARED_PREFERENCES_DATE_VALVE_1 = "DATE_VALVE_1";
    public final static String SHARED_PREFERENCES_DATE_VALVE_2 = "DATE_VALVE_2";
    public final static String SHARED_PREFERENCES_DATE_VALVE_3 = "DATE_VALVE_3";
    public final static String SHARED_PREFERENCES_IP_BROKER = "IP_BROKER";
    public final static String SHARED_PREFERENCES_PORT_BROKER = "PORT_BROKER";
    public final static String SHARED_PREFERENCES_IP_CAM_1 = "IP_CAM_1";
    public final static String SHARED_PREFERENCES_IP_CAM_2 = "IP_CAM_2";
    public final static String SHARED_PREFERENCES_ID_CAM_1 = "ID_CAM_1";
    public final static String SHARED_PREFERENCES_ID_CAM_2 = "ID_CAM_2";
    public final static String SHARED_PREFERENCES_DEVICE_VAN = "DEVICE_VAN";
    public final static String SHARED_PREFERENCES_DEVICE_GATEWAY = "DEVICE_GATEWAY";
    public final static String SHARED_PREFERENCES_DEVICE_SCHEDULE = "DEVICE_SCHEDULE";
    public final static String SHARED_PREFERENCES_LANGUAGE = "LANGUAGE";
    public final static String SHARED_PREFERENCES_TOPIC_CMD = "TOPIC_CMD";
    public final static String SHARED_PREFERENCES_USERNAME = "USERNAME";
    public final static String SHARED_PREFERENCES_PASSWORD = "PASSWORD";
    public final static String SHARED_PREFERENCES_LOGGED = "LOGGED";
    public final static String SHARED_PREFERENCES_USER = "USER";
    public final static String SHARED_PREFERENCES_FARM = "FARM";
    public final static String SHARED_PREFERENCES_GREENHOUSE = "GREENHOUSE";
    public final static String SHARED_PREFERENCES_LAST_FARM_INDEX = "LAST_FARM_INDEX";
    public final static String SHARED_PREFERENCES_DEVICE_TOKEN = "DEVICE_TOKEN";

    public static SharedPreferences sharedPreferences = null;


    public static void setRememberLoggedIn(Context context, boolean isRemember) {
        sharedPreferences = context.getSharedPreferences(ORGANIC_SHARED_PREFERENCES, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SHARED_PREFERENCES_LOGGED, isRemember);
        editor.apply();
    }

    public static boolean getRememberLoggedIn(Context context) {
        boolean isRemember = false;
        try {
            sharedPreferences = context.getSharedPreferences(ORGANIC_SHARED_PREFERENCES, 0);
            if (sharedPreferences != null) {
                isRemember = sharedPreferences.getBoolean(SHARED_PREFERENCES_LOGGED, false);
            }
        } catch (Exception e) {
            Log.e("SharedPreferences", e.getMessage());
        }

        return isRemember;
    }

    private static void setValue(Context context, String value, String valueConstans){
        sharedPreferences = context.getSharedPreferences(ORGANIC_SHARED_PREFERENCES, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(valueConstans, value);
        editor.apply();
    }


    private static String getValue(Context context, String constans, String defaul){
        String value = "";
        try {
            sharedPreferences = context.getSharedPreferences(ORGANIC_SHARED_PREFERENCES, 0);
            if (sharedPreferences != null) {
                value = sharedPreferences.getString(constans, defaul);
            }
        } catch (Exception e) {
            Log.e("SharedPreferences", e.getMessage());
        }
        return value;
    }

    public static void setUser(Context context, User user) {
        Gson gson = new Gson();
        String data = gson.toJson(user);
        setValue(context, data, SHARED_PREFERENCES_USER);
    }

    public static User getUser(Context context) {
        Gson gson = new Gson();
        String data = getValue(context, SHARED_PREFERENCES_USER, "");
        User user = gson.fromJson(data, User.class);
        return user;
    }

    public static void setFarm(Context context, Farm farm) {
        Gson gson = new Gson();
        String data = gson.toJson(farm);
        setValue(context, data, SHARED_PREFERENCES_FARM);
    }

    public static Farm getFarm(Context context) {
        Gson gson = new Gson();
        String data = getValue(context, SHARED_PREFERENCES_FARM, "");
        Farm farm = gson.fromJson(data, Farm.class);
        return farm;
    }

    public static void setDeviceToken(Context context, String token) {
        setValue(context, token, SHARED_PREFERENCES_DEVICE_TOKEN);
    }

    public static String getDeviceToken(Context context) {
        return getValue(context, SHARED_PREFERENCES_DEVICE_TOKEN, null);
    }

    public static void setGreenHouse(Context context, GreenHouse greenHouse) {
        Gson gson = new Gson();
        String data = gson.toJson(greenHouse);
        setValue(context, data, SHARED_PREFERENCES_GREENHOUSE);
    }

    public static GreenHouse getGreenHouse(Context context) {
        Gson gson = new Gson();
        String data = getValue(context, SHARED_PREFERENCES_GREENHOUSE, "");
        GreenHouse greenHouse = gson.fromJson(data, GreenHouse.class);
        return greenHouse;
    }

    public static void setLastFarmIndex(Context context, String username) {
        setValue(context, username, SHARED_PREFERENCES_LAST_FARM_INDEX);
    }

    public static String getLastFarmIndex(Context context) {
        return getValue(context, SHARED_PREFERENCES_LAST_FARM_INDEX, "0");
    }

    public static void setUsername(Context context, String username) {
        setValue(context, username, SHARED_PREFERENCES_USERNAME);
    }

    public static String getUsername(Context context) {
        return getValue(context, SHARED_PREFERENCES_USERNAME, "");
    }

    public static void setPassword(Context context, String text) {
        setValue(context, text, SHARED_PREFERENCES_PASSWORD);
    }

    public static String getPassword(Context context) {
        return getValue(context, SHARED_PREFERENCES_PASSWORD, "");
    }

    public static void setDateValve1(Context context, String date) {
        setValue(context, date, SHARED_PREFERENCES_DATE_VALVE_1);
    }

    public static String getDateValve1(Context context) {
        return getValue(context, SHARED_PREFERENCES_DATE_VALVE_1, "");
    }

    public static void setLanguage(Context context, String language) {
        setValue(context, language, SHARED_PREFERENCES_LANGUAGE);
    }

    public static String getLanguage(Context context) {
        return getValue(context, SHARED_PREFERENCES_LANGUAGE, null);
    }

    public static void setIpBroker(Context context, String ip) {
        setValue(context, ip, SHARED_PREFERENCES_IP_BROKER);
    }

    public static String getIpBroker(Context context) {
        return getValue(context, SHARED_PREFERENCES_IP_BROKER, "192.168.0.139");
    }

    public static void setTopicCMDLocal(Context context, String ip) {
        setValue(context, ip, SHARED_PREFERENCES_TOPIC_CMD);
    }

    public static String getTopicCMDLocal(Context context) {
        return getValue(context, SHARED_PREFERENCES_TOPIC_CMD, "");
    }

    public static void setPortBroker(Context context, String ip) {
        setValue(context, ip, SHARED_PREFERENCES_PORT_BROKER);
    }

    public static String getPortBroker(Context context) {
        return getValue(context, SHARED_PREFERENCES_PORT_BROKER, "1883");
    }

    public static void setIpCam1(Context context, String ip) {
        setValue(context, ip, SHARED_PREFERENCES_IP_CAM_1);
    }

    public static String getIpCam1(Context context) {
        return getValue(context, SHARED_PREFERENCES_IP_CAM_1, "camera.thingxyz.net");
    }

    public static void setIdCam1(Context context, String ip) {
        setValue(context, ip, SHARED_PREFERENCES_ID_CAM_1);
    }

    public static String getIdCam1(Context context) {
        return getValue(context, SHARED_PREFERENCES_ID_CAM_1, "PPCN475552VBXZK");
    }

    public static void setIpCam2(Context context, String ip) {
        setValue(context, ip, SHARED_PREFERENCES_IP_CAM_2);
    }

    public static String getIpCam2(Context context) {
        return getValue(context, SHARED_PREFERENCES_IP_CAM_2, "192.168.0.112");
    }

    public static void setIdCam2(Context context, String ip) {
        setValue(context, ip, SHARED_PREFERENCES_ID_CAM_2);
    }

    public static String getIdCam2(Context context) {
        return getValue(context, SHARED_PREFERENCES_ID_CAM_2, "");
    }

    public static void setGateway(Context context, String ip) {
        setValue(context, ip, SHARED_PREFERENCES_DEVICE_GATEWAY);
    }

    public static String getGateway(Context context) {
        return getValue(context, SHARED_PREFERENCES_DEVICE_GATEWAY, "");
    }

    public static void setDeviceVan(Context context, String listDevice) {
        setValue(context, listDevice, SHARED_PREFERENCES_DEVICE_VAN);
    }

    public static GroupDevice getDeviceVan(Context context) {
        String json = getValue(context, SHARED_PREFERENCES_DEVICE_VAN, null);
        if (json == null) return null;
        Gson gson = new Gson();
        GroupDevice groupDevice = gson.fromJson(json, GroupDevice.class);
        return groupDevice;
    }

    public static void addSchedule(Context context, DeviceSchedule deviceSchedule) {
        Gson gson = new Gson();
        GroupDeviceSchedule groupDeviceSchedule = getGroupDeviceSchedule(context);
        HashMap<String, DeviceSchedule> deviceSchedules = groupDeviceSchedule.getDeviceSchedules();
        if (deviceSchedules.size() == 0) {
            deviceSchedules.put(deviceSchedule.getDeviceId(), deviceSchedule);
            groupDeviceSchedule.setDeviceSchedules(deviceSchedules);
            setGroupDeviceSchedule(context, gson.toJson(groupDeviceSchedule));
            return;
        } else {
            if (deviceSchedules.containsKey(deviceSchedule.getDeviceId())) deviceSchedules.remove(deviceSchedule.getDeviceId());
            deviceSchedules.put(deviceSchedule.getDeviceId(), deviceSchedule);
            groupDeviceSchedule.setDeviceSchedules(deviceSchedules);
            AppSharedPreferences.setGroupDeviceSchedule(context, gson.toJson(groupDeviceSchedule));
        }

    }

    private static void setGroupDeviceSchedule(Context context, String listDevice) {
        setValue(context, listDevice, SHARED_PREFERENCES_DEVICE_SCHEDULE);
    }

    public static GroupDeviceSchedule getGroupDeviceSchedule(Context context) {
        GroupDeviceSchedule groupDeviceSchedule = new GroupDeviceSchedule();
        HashMap<String, DeviceSchedule> deviceSchedules = new HashMap<>();
        groupDeviceSchedule.setDeviceSchedules(deviceSchedules);
        String json = getValue(context, SHARED_PREFERENCES_DEVICE_SCHEDULE, null);
        if (json == null) return groupDeviceSchedule;
        Gson gson = new Gson();
        groupDeviceSchedule = gson.fromJson(json, GroupDeviceSchedule.class);
        return groupDeviceSchedule;
    }

    public static void setDateValve2(Context context, String date) {
        setValue(context, date, SHARED_PREFERENCES_DATE_VALVE_2);
    }

    public static String getDateValve2(Context context) {
        return getValue(context, SHARED_PREFERENCES_DATE_VALVE_2, "");
    }

    public static void setDateValve3(Context context, String date) {
        setValue(context, date, SHARED_PREFERENCES_DATE_VALVE_3);
    }

    public static String getDateValve3(Context context) {
        return getValue(context, SHARED_PREFERENCES_DATE_VALVE_3, "");
    }

}
