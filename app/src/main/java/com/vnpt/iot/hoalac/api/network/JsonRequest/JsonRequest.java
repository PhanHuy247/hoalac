package com.vnpt.iot.hoalac.api.network.JsonRequest;

import com.vnpt.iot.hoalac.api.common.Utils;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponseJsonArray;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponseJsonObject;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public abstract class JsonRequest {
    protected static Response<JSONObject> responseJsonObjectByParse(NetworkResponse response, ApiResponseJsonObject apiResponseJsObj) {
        try {
            apiResponseJsObj.handleNetworkResponse(new String(response.data, "utf-8"), String.valueOf(response.statusCode), response.headers.toString());
            String raw = apiResponseJsObj.get_raw();

            if (!Utils.isExist(raw) && response.statusCode < 400) {
                return Response.success(
                        null,
                        HttpHeaderParser.parseCacheHeaders(response));
            } else if (Utils.isExist(raw) && Utils.isInteger(raw) && response.statusCode < 400) {
                // response là một kí tự type number
                // fake json response
                String rawFake = "{" + "errorCode:" + raw + "}";

                return Response.success(new JSONObject(rawFake), HttpHeaderParser.parseCacheHeaders(response));

            } else if (Utils.isExist(raw) && Utils.isBoolean(raw) && response.statusCode < 400) {
                // response là một chuỗi kí tự true or false
                // fake json response
                String rawFake = "{errorSuccess:" + raw;
                if (Boolean.valueOf(raw)) {
                    rawFake = rawFake + ", errorCode:1";
                } else {
                    rawFake = rawFake + ", errorCode:2";
                }
                rawFake = rawFake + "}";

                return Response.success(new JSONObject(rawFake), HttpHeaderParser.parseCacheHeaders(response));
            } else {
                return Response.success(
                        new JSONObject(raw),
                        HttpHeaderParser.parseCacheHeaders(response));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }
    }

    protected static Response<JSONArray> responseJsonArrayByParse(NetworkResponse response, ApiResponseJsonArray apiResponseJsArr) {
        try {
            apiResponseJsArr.handleNetworkResponse(new String(response.data, "utf-8"), String.valueOf(response.statusCode), response.headers.toString());
            String raw = apiResponseJsArr.get_raw();

            if (!Utils.isExist(raw) && response.statusCode < 400) {
                return Response.success(
                        null,
                        HttpHeaderParser.parseCacheHeaders(response));
            } else if (Utils.isExist(raw)
                    && Utils.isInteger(raw)
                    && response.statusCode < 400) {
                // response là một kí tự type number
                // fake json response
                String rawFake = "[{" + "errorCode:" + raw + "}]";

                return Response.success(new JSONArray(rawFake), HttpHeaderParser.parseCacheHeaders(response));

            } else if (Utils.isExist(raw) && Utils.isBoolean(raw) && response.statusCode < 400) {
                // response là một boolean true or false
                // fake json response
                String rawFake = "[{errorSuccess:" + raw;
                if (Boolean.valueOf(raw)) {
                    rawFake = rawFake + ", errorCode:1";
                } else {
                    rawFake = rawFake + ", errorCode:2";
                }
                rawFake = rawFake + "}]";

                return Response.success(new JSONArray(rawFake), HttpHeaderParser.parseCacheHeaders(response));
            } else {
                return Response.success(
                        new JSONArray(raw),
                        HttpHeaderParser.parseCacheHeaders(response));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }
}
