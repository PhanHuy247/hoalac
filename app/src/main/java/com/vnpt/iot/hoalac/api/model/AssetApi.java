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

public class AssetApi {

    public static void getListImport(Context context, HashMap<String, Object> params, ResponseListener listener) {
        User user = AppSharedPreferences.getUser(context);
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.ASSET_IMPORT_LIST+String.valueOf(user.getId()),
                params,
                null,
                listener
        );
    }

    public static void getListExport(Context context, HashMap<String, Object> params, ResponseListener listener) {
        User user = AppSharedPreferences.getUser(context);
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.ASSET_EXPORT_LIST+String.valueOf(user.getId()),
                params,
                null,
                listener
        );
    }

    public static void getImportDetail(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.ASSET_IMPORT_DETAIL,
                params,
                null,
                listener
        );
    }

    public static void getExportDetail(Context context, HashMap<String, Object> params, ResponseListener listener) {
        JsonObjectReq.makeGetRequest(
                context,
                Constatnts.ASSET_EXPORT_DETAIL,
                params,
                null,
                listener
        );
    }
}
