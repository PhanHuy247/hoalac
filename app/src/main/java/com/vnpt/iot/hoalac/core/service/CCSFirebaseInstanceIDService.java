package com.vnpt.iot.hoalac.core.service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;

/**
 * Created by thohc on 4/4/17.
 */

public class CCSFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        sendRegistrationToServer(refreshedToken);
        //Displaying token on logcat
        System.out.println("DeviceToken: "+refreshedToken);

    }

    private void sendRegistrationToServer(String token) {
        AppSharedPreferences.setDeviceToken(getApplicationContext(), token);
    }

}
