package com.vnpt.iot.hoalac.api.model;

import android.content.Context;

import com.vnpt.iot.hoalac.api.common.Constatnts;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.network.JsonRequest.JsonObjectReq;

import java.util.HashMap;

/**
 * Created by thohc on 7/26/17.
 */

public class EnvironmentApi {
    public static void getData(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.ENVIRONMENT,
                params,
                null,
                listener
        );
    }
}
