package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 8/2/17.
 */

public class FogFanConfigurationDetail {
    private Integer id;
    private String name;
    private Integer status;
    private String sector;
    private String gateway;
    private String gatewayName;
    private String progress;
    private String date_start;
    private String date_end;

    private String fog_stop_in;
    private String fog_start_when_temp_gt;
    private String fog_stop_when_temp_lt;

    private String fog_start_when_humid_lt;
    private String fog_stop_when_humid_gt;

    private String fan_start_in;
    private String fan_start_when_humid_gt;
    private String fan_stop_when_humid_lt;
    private String created_by;
    private String scheduleCode;
    private String farmId;
    private String bedName;
    private Integer type;
    //shadow config
    private String shadow_temp_below;
    private String shadow_temp_above;
    private String shadow_time_below;
    private String shadow_time_above;
    private String valid_time_below;
    private String valid_time_above;

    public String getValid_time_below() {
        return valid_time_below;
    }

    public void setValid_time_below(String valid_time_below) {
        this.valid_time_below = valid_time_below;
    }

    public String getValid_time_above() {
        return valid_time_above;
    }

    public void setValid_time_above(String valid_time_above) {
        this.valid_time_above = valid_time_above;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getFog_stop_in() {
        return fog_stop_in;
    }

    public void setFog_stop_in(String fog_stop_in) {
        this.fog_stop_in = fog_stop_in;
    }

    public String getFog_start_when_temp_gt() {
        return fog_start_when_temp_gt;
    }

    public void setFog_start_when_temp_gt(String fog_start_when_temp_gt) {
        this.fog_start_when_temp_gt = fog_start_when_temp_gt;
    }

    public String getFog_stop_when_temp_lt() {
        return fog_stop_when_temp_lt;
    }

    public void setFog_stop_when_temp_lt(String fog_stop_when_temp_lt) {
        this.fog_stop_when_temp_lt = fog_stop_when_temp_lt;
    }

    public String getFog_start_when_humid_lt() {
        return fog_start_when_humid_lt;
    }

    public void setFog_start_when_humid_lt(String fog_start_when_humid_lt) {
        this.fog_start_when_humid_lt = fog_start_when_humid_lt;
    }

    public String getFog_stop_when_humid_gt() {
        return fog_stop_when_humid_gt;
    }

    public void setFog_stop_when_humid_gt(String fog_stop_when_humid_gt) {
        this.fog_stop_when_humid_gt = fog_stop_when_humid_gt;
    }

    public String getFan_start_in() {
        return fan_start_in;
    }

    public void setFan_start_in(String fan_start_in) {
        this.fan_start_in = fan_start_in;
    }

    public String getFan_start_when_humid_gt() {
        return fan_start_when_humid_gt;
    }

    public void setFan_start_when_humid_gt(String fan_start_when_humid_gt) {
        this.fan_start_when_humid_gt = fan_start_when_humid_gt;
    }

    public String getFan_stop_when_humid_lt() {
        return fan_stop_when_humid_lt;
    }

    public void setFan_stop_when_humid_lt(String fan_stop_when_humid_lt) {
        this.fan_stop_when_humid_lt = fan_stop_when_humid_lt;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getScheduleCode() {
        return scheduleCode;
    }

    public void setScheduleCode(String scheduleCode) {
        this.scheduleCode = scheduleCode;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getBedName() {
        return bedName;
    }

    public void setBedName(String bedName) {
        this.bedName = bedName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getShadow_temp_below() {
        return shadow_temp_below;
    }

    public void setShadow_temp_below(String shadow_temp_below) {
        this.shadow_temp_below = shadow_temp_below;
    }

    public String getShadow_temp_above() {
        return shadow_temp_above;
    }

    public void setShadow_temp_above(String shadow_temp_above) {
        this.shadow_temp_above = shadow_temp_above;
    }

    public String getShadow_time_below() {
        return shadow_time_below;
    }

    public void setShadow_time_below(String shadow_time_below) {
        this.shadow_time_below = shadow_time_below;
    }

    public String getShadow_time_above() {
        return shadow_time_above;
    }

    public void setShadow_time_above(String shadow_time_above) {
        this.shadow_time_above = shadow_time_above;
    }
}
