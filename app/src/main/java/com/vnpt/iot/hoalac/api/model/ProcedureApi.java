package com.vnpt.iot.hoalac.api.model;

import android.content.Context;

import com.vnpt.iot.hoalac.api.common.Constatnts;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.network.JsonRequest.JsonObjectReq;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.User;

import java.util.HashMap;

/**
 * Created by thohc on 7/3/17.
 */

public class ProcedureApi {
    public static void getList(Context context, HashMap<String, Object> params, ResponseListener listener) {
        User user = AppSharedPreferences.getUser(context);
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.PROCEDURE_LIST+String.valueOf(user.getId()),
                params,
                null,
                listener
        );
    }

    public static void getDetail(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.PROCEDURE_DETAIL,
                params,
                null,
                listener
        );
    }
}
