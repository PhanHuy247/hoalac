package com.vnpt.iot.hoalac.api.model;

import android.content.Context;

import com.vnpt.iot.hoalac.api.common.Constatnts;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.network.JsonRequest.JsonObjectReq;

import java.util.HashMap;

/**
 * Created by thohc on 6/21/17.
 */

public class PhaseApi {
    public static void getList(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.PHASE_LIST,
                params,
                null,
                listener
        );
    }

    public static void getDetail(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.PHASE_DETAIL,
                params,
                null,
                listener
        );
    }
}
