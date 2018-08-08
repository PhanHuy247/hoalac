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
 * Created by thohc on 6/16/17.
 */

public class UserApi {
    public static void login(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makePostRequest(
                context,
                Constatnts.LOGIN,
                params,
                null,
                listener
        );
    }

    public static void update(Context context, HashMap<String, Object> params, ResponseListener listener) {
        User user = AppSharedPreferences.getUser(context);
        JsonObjectReq.makePutRequest(
                context,
                Constatnts.UPDATE+String.valueOf(user.getId()),
                params,
                null,
                listener
        );
    }

    public static void createToken(Context context, JSONObject body, ResponseListener listener) {
        User user = AppSharedPreferences.getUser(context);
        JsonObjectReq.makePutRequest(
                context,
                Constatnts.REGISTER_TOKEN+String.valueOf(user.getId()),
                null,
                body,
                listener
        );
    }

    public static void deleteToken(Context context, HashMap<String, Object> params, ResponseListener listener) {
        User user = AppSharedPreferences.getUser(context);
        JsonObjectReq.makeDeleteRequest(
                context,
                Constatnts.REMOVE_TOKEN+String.valueOf(user.getId()),
                params,
                null,
                listener
        );
    }
}
