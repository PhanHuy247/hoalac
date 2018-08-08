package com.vnpt.iot.hoalac.api.model;

import android.content.Context;

import com.vnpt.iot.hoalac.api.common.Constatnts;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.network.JsonRequest.JsonObjectReq;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.User;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by thohc on 4/26/17.
 */

public class DeviceApi {
    public static void getList(Context context, HashMap<String, Object> params, ResponseListener listener) {
        User user = AppSharedPreferences.getUser(context);
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.DEVICE_LIST+String.valueOf(user.getId()),
                params,
                null,
                listener
        );
    }

    public static void getListDeviceIP(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.DEVICE_LIST_IP,
                params,
                null,
                listener
        );
    }

    public static void getSchedule(Context context, HashMap<String, Object> params, JSONObject body, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.DEVICE_SCHEDULE,
                params,
                body,
                listener
        );
    }

    public static void getListCamera(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.DEVICE_LIST_CAMERA,
                params,
                null,
                listener
        );
    }

    public static void saveSchedule(Context context, HashMap<String, Object> params, JSONObject body, ResponseListener listener) {
        JsonObjectReq.makePostRequest(
                context,
                Constatnts.DEVICE_SAVE_SCHEDULE,
                params,
                body,
                listener
        );
    }
}
