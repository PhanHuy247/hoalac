package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 5/10/17.
 */

public class GroupDevice {
    private List<Device> devices;

    public GroupDevice(List<Device> devices) {
        this.devices = devices;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
