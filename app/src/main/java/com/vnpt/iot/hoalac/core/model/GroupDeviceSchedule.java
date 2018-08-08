package com.vnpt.iot.hoalac.core.model;

import java.util.HashMap;

/**
 * Created by thohc on 5/18/17.
 */

public class GroupDeviceSchedule {

    private HashMap<String, DeviceSchedule> deviceSchedules;

    public HashMap<String, DeviceSchedule> getDeviceSchedules() {
        return deviceSchedules;
    }

    public void setDeviceSchedules(HashMap<String, DeviceSchedule> deviceSchedules) {
        this.deviceSchedules = deviceSchedules;
    }
}
