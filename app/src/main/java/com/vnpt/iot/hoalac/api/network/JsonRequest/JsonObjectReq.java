package com.vnpt.iot.hoalac.api.network.JsonRequest;

import android.content.Context;

import com.android.volley.toolbox.JsonArrayRequest;
import com.vnpt.iot.hoalac.api.Utils.ArrayUtils;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponseJsonObject;
import com.vnpt.iot.hoalac.api.network.VolleyResourcesSingleton;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonObjectReq extends JsonRequest {
    public static void makeGetRequest(final Context context,
                                      String url,
                                      HashMap<String, Object> params,
                                      JSONObject body,
                                      final ResponseListener listener) {
        final ApiResponseJsonObject apiResponse = new ApiResponseJsonObject();
        if (params != null)
            url = url.concat("?".concat(ArrayUtils.mapToQueryString(params)));
            System.out.println("URL API GET: "+url);
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                url,
                body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        apiResponse.handleResponseSuccessed(jsonObject);
                        listener.onRequestCompleted(apiResponse);
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    apiResponse.handleResponseError(error);

                    listener.onRequestError(apiResponse);
                }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", Constatnts.API_KEY);

                return params;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                return responseJsonObjectByParse(response, apiResponse);
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //It's better if the queue is obtained with an app context to keep it alive while the app is in foreground.
        VolleyResourcesSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

    public static void makePostRequest(final Context context,
                                       String url,
                                       HashMap<String, Object> params,
                                       JSONObject body,
                                       final ResponseListener listener
    ) {
        final ApiResponseJsonObject apiResponse = new ApiResponseJsonObject();
        if (params != null)
            url = url.concat("?".concat(ArrayUtils.mapToQueryString(params)));
        System.out.println("URL API POST: "+url);
        System.out.println("URL API PUT BODY: "+body);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        apiResponse.handleResponseSuccessed(jsonObject);

                        listener.onRequestCompleted(apiResponse);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                apiResponse.handleResponseError(error);

                listener.onRequestError(apiResponse);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", Constatnts.API_KEY);

                return params;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                return responseJsonObjectByParse(response, apiResponse);
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //It's better if the queue is obtained with an app context to keep it alive while the app is in foreground.
        VolleyResourcesSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

    public static void makePutRequest(final Context context,
                                      String url,
                                      HashMap<String, Object> params,
                                      JSONObject body,
                                      final ResponseListener listener) {

        final ApiResponseJsonObject apiResponse = new ApiResponseJsonObject();
        if (params != null)
            url = url.concat("?".concat(ArrayUtils.mapToQueryString(params)));
        System.out.println("URL API PUT: "+url);
        System.out.println("URL API PUT BODY: "+body);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        apiResponse.handleResponseSuccessed(jsonObject);

                        listener.onRequestCompleted(apiResponse);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                apiResponse.handleResponseError(error);

                listener.onRequestError(apiResponse);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", Constatnts.API_KEY);

                return params;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                return responseJsonObjectByParse(response, apiResponse);
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        //It's better if the queue is obtained with an app context to keep it alive while the app is in foreground.
        VolleyResourcesSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

    public static void makeDeleteRequest(final Context context,
                                         String url,
                                         HashMap<String, Object> params,
                                         JSONObject body,
                                         final ResponseListener listener) {

        final ApiResponseJsonObject apiResponse = new ApiResponseJsonObject();
        if (params != null)
            url = url.concat("?".concat(ArrayUtils.mapToQueryString(params)));
        System.out.println("URL API DELETE: "+url);
        System.out.println("URL API PUT BODY: "+body);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.DELETE,
                url,
                body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        apiResponse.handleResponseSuccessed(jsonObject);

                        listener.onRequestCompleted(apiResponse);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                apiResponse.handleResponseError(error);

                listener.onRequestError(apiResponse);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", Constatnts.API_KEY);

                return params;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                return responseJsonObjectByParse(response, apiResponse);
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //It's better if the queue is obtained with an app context to keep it alive while the app is in foreground.
        VolleyResourcesSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjReq);
    }
}
