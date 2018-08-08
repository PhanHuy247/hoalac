package com.vnpt.iot.hoalac.api.network.JsonRequest;

import android.content.Context;

import com.vnpt.iot.hoalac.api.Utils.ArrayUtils;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponseJsonArray;
import com.vnpt.iot.hoalac.api.network.VolleyResourcesSingleton;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class JsonArrayReq extends JsonRequest{
    public static void makeGetRequest(final Context context,
                                          String url,
                                          HashMap<String, Object> params,
                                          final ResponseListener listener) {
        final ApiResponseJsonArray apiResponseJsArr = new ApiResponseJsonArray();

        if (params != null)
            url = url.concat("?".concat(ArrayUtils.mapToQueryString(params)));

        com.android.volley.toolbox.JsonArrayRequest jsonArrRequest = new com.android.volley.toolbox.JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                apiResponseJsArr.handleResponseSuccessed(response);
                listener.onRequestCompleted(apiResponseJsArr);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                apiResponseJsArr.handleResponseError(error);
                listener.onRequestError(apiResponseJsArr);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }

            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                return responseJsonArrayByParse(response, apiResponseJsArr);

            }
        };

        VolleyResourcesSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonArrRequest);
    }
}
