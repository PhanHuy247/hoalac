package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 4/26/17.
 */

public class SensorMQTTContent {
    private String deviceid;
    private String gatewayid;
    private String air_temperature;
    private String air_moisture;
    private String soil_temperature;
    private String soil_moisture;
    private String status;

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getGatewayid() {
        return gatewayid;
    }

    public void setGatewayid(String gatewayid) {
        this.gatewayid = gatewayid;
    }

    public String getAir_temperature() {
        return air_temperature;
    }

    public void setAir_temperature(String air_temperature) {
        this.air_temperature = air_temperature;
    }

    public String getAir_moisture() {
        return air_moisture;
    }

    public void setAir_moisture(String air_moisture) {
        this.air_moisture = air_moisture;
    }

    public String getSoil_temperature() {
        return soil_temperature;
    }

    public void setSoil_temperature(String soil_temperature) {
        this.soil_temperature = soil_temperature;
    }

    public String getSoil_moisture() {
        return soil_moisture;
    }

    public void setSoil_moisture(String soil_moisture) {
        this.soil_moisture = soil_moisture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
