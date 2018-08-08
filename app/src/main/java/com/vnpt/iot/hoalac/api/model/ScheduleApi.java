package com.vnpt.iot.hoalac.api.model;

import android.content.Context;

import com.vnpt.iot.hoalac.api.common.Constatnts;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.network.JsonRequest.JsonObjectReq;

import java.util.HashMap;

/**
 * Created by thohc on 7/19/17.
 */

public class ScheduleApi {
    public static void getList(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.SCHEDULE_LIST,
                params,
                null,
                listener
        );
    }

    public static void getDetailFromListAllApp(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.SCHEDULE_DETAIL_APP,
                params,
                null,
                listener
        );
    }

    public static void changeStatus(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makePutRequest(
                context,
                Constatnts.SCHEDULE_CHANGESTATUS,
                params,
                null,
                listener
        );
    }

    public static void changeStatusByDevice(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.SCHEDULE_CHANGE_STATUS_BY_DEVICE,
                params,
                null,
                listener
        );
    }

    public static void getCountScheduleByDevice(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.SCHEDULE_COUNT,
                params,
                null,
                listener
        );
    }
}
