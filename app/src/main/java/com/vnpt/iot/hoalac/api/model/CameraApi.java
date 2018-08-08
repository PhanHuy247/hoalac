package com.vnpt.iot.hoalac.api.model;

import android.content.Context;

import com.vnpt.iot.hoalac.api.common.Constatnts;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.network.JsonRequest.JsonObjectReq;

import org.json.JSONObject;

import java.util.HashMap;

public class CameraApi {
    public static void cmd(Context context, HashMap<String, Object> params, JSONObject body, ResponseListener listener) {
        JsonObjectReq.makePutRequest(
                context,
                Constatnts.CONTROL_DEVICE,
                params,
                body,
                listener
        );
    }

    public static void cmdOffline(Context context, String url, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                url,
                null,
                null,
                listener
        );
    }
}
