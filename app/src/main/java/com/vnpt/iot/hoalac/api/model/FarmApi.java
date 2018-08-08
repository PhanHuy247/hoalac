package com.vnpt.iot.hoalac.api.model;

import android.content.Context;

import com.vnpt.iot.hoalac.api.common.Constatnts;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.network.JsonRequest.JsonObjectReq;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.User;

import java.util.HashMap;

/**
 * Created by thohc on 6/22/17.
 */

public class FarmApi {
    public static void getList(Context context, ResponseListener listener) {
        User user = AppSharedPreferences.getUser(context);
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.FARM_LIST+String.valueOf(user.getId()),
                null,
                null,
                listener
        );
    }

    public static void getGreenHouse(Context context, HashMap<String, Object> params, ResponseListener listener) {
        User user = AppSharedPreferences.getUser(context);
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.GREEN_HOUSE_LIST+String.valueOf(user.getId()),
                params,
                null,
                listener
        );
    }
}
