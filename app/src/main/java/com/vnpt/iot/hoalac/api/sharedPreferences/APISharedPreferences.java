package com.vnpt.iot.hoalac.api.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class APISharedPreferences {
    private static final String TAG = APISharedPreferences.class.getSimpleName();
    public final static String CCS_SHARED_PREFERENCES = "CCSSharedPreferences";

    public final static String SHARED_PREFERENCES_TOKEN_KEY = "ApiKey";
    public final static String SHARED_PREFERENCES_USER_ID = "UserId";

    public static SharedPreferences sharedPreferences = null;

    /**
     * Method to save the Token
     */
    public static void setToken(Context context, String token) {
        sharedPreferences = context.getSharedPreferences(CCS_SHARED_PREFERENCES, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_PREFERENCES_TOKEN_KEY, token);
        editor.apply();
    }

    /**
     * Method to get the Token from shared preferences
     *
     * @return String
     */
    public static String getToken(Context context) {
        String token = "";
        try {
            sharedPreferences = context.getSharedPreferences(CCS_SHARED_PREFERENCES, 0);
            if (sharedPreferences != null) {
                token = sharedPreferences.getString(SHARED_PREFERENCES_TOKEN_KEY, "");
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return token;
    }

    /**
     * Method to save the Key
     */

    public static void setUserId(Context context, int userId) {
        sharedPreferences = context.getSharedPreferences(CCS_SHARED_PREFERENCES, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREFERENCES_USER_ID, userId);
        editor.apply();
    }

    /**
     * Method to get the Key from shared preferences
     *
     * @return String
     */
    public static int getUserId(Context context) {
        int userId = 0;
        try {
            sharedPreferences = context.getSharedPreferences(CCS_SHARED_PREFERENCES, 0);
            if (sharedPreferences != null) {
                userId = sharedPreferences.getInt(SHARED_PREFERENCES_USER_ID, 0);
            }
        } catch (Exception e) {
            return 0;
        }

        return userId;
    }
}
