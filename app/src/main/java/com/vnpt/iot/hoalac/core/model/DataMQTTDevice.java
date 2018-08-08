package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 8/1/17.
 */

public class DataMQTTDevice {
    private int device_type;
    private String device_id;
    private String gateway_id;
    private String device_description;
    private String hardware_address;
    private String state_hardware;
    private String state_app;
    private List<DataMQTTParam> device_values;

    public String getState_hardware() {
        return state_hardware;
    }

    public void setState_hardware(String state_hardware) {
        this.state_hardware = state_hardware;
    }

    public String getState_app() {
        return state_app;
    }

    public void setState_app(String state_app) {
        this.state_app = state_app;
    }

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getGateway_id() {
        return gateway_id;
    }

    public void setGateway_id(String gateway_id) {
        this.gateway_id = gateway_id;
    }

    public String getDevice_description() {
        return device_description;
    }

    public void setDevice_description(String device_description) {
        this.device_description = device_description;
    }

    public String getHardware_address() {
        return hardware_address;
    }

    public void setHardware_address(String hardware_address) {
        this.hardware_address = hardware_address;
    }

    public List<DataMQTTParam> getDevice_values() {
        return device_values;
    }

    public void setDevice_values(List<DataMQTTParam> device_values) {
        this.device_values = device_values;
    }
}
