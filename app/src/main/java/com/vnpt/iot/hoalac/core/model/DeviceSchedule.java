package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 4/26/17.
 */

public class DeviceSchedule {
    private Long id;
    private String deviceId;
    private Long scpId;
    private Integer status;
    private Integer type;
    private Integer conditionType;
    private Long value1;
    private Long value2;
    private String sensorId;
    private Long userId;

    public DeviceSchedule(Long id, String deviceId, Long scpId, Integer status, Integer type, Integer conditionType, Long value1, Long value2, String sensorId, Long userId) {
        this.id = id;
        this.deviceId = deviceId;
        this.scpId = scpId;
        this.status = status;
        this.type = type;
        this.conditionType = conditionType;
        this.value1 = value1;
        this.value2 = value2;
        this.sensorId = sensorId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Long getScpId() {
        return scpId;
    }

    public void setScpId(Long scpId) {
        this.scpId = scpId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getConditionType() {
        return conditionType;
    }

    public void setConditionType(Integer conditionType) {
        this.conditionType = conditionType;
    }

    public Long getValue1() {
        return value1;
    }

    public void setValue1(Long value1) {
        this.value1 = value1;
    }

    public Long getValue2() {
        return value2;
    }

    public void setValue2(Long value2) {
        this.value2 = value2;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
