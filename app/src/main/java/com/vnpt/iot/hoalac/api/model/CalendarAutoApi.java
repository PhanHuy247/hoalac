package com.vnpt.iot.hoalac.api.model;

import android.content.Context;

import com.vnpt.iot.hoalac.api.common.Constatnts;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.network.JsonRequest.JsonArrayReq;

import java.util.HashMap;

public class CalendarAutoApi {
    public static void getDataFeedingSchedule(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonArrayReq.makeGetRequest(
                context,
                Constatnts.FEEDING_SCHEDULE,
                params,
                listener
        );
    }
    public static void getDataCoolingSchedule(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonArrayReq.makeGetRequest(
                context,
                Constatnts.COOLING_SCHEDULE,
                params,
                listener
        );
    }
    public static void getDataLightSchedule(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonArrayReq.makeGetRequest(
                context,
                Constatnts.LIGHT_SCHEDULE,
                params,
                listener
        );
    }
    public static void getDataDrugSchedule(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonArrayReq.makeGetRequest(
                context,
                Constatnts.DRUG_SCHEDULE,
                params,
                listener
        );
    }
}
