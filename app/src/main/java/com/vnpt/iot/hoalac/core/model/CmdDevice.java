package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 7/31/17.
 */

public class CmdDevice {
    private String name;
    private String parentId;
    private String deviceId;
    private String cmdOn;
    private String cmdOff;

    public CmdDevice(String name, String parentId, String deviceId, String cmdOn, String cmdOff) {
        this.name = name;
        this.parentId = parentId;
        this.deviceId = deviceId;
        this.cmdOn = cmdOn;
        this.cmdOff = cmdOff;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCmdOn() {
        return cmdOn;
    }

    public void setCmdOn(String cmdOn) {
        this.cmdOn = cmdOn;
    }

    public String getCmdOff() {
        return cmdOff;
    }

    public void setCmdOff(String cmdOff) {
        this.cmdOff = cmdOff;
    }
}
