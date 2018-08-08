package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 8/1/17.
 */

public class AquaticConfiguration {
    private Integer id;
    private String name;
    private Integer status;

    private Integer sector;
    private String gateway;
    private Integer progress;
    private String date_start;
    private String date_end;
    private String tank;
    private Float volume;
    private Long time_exec;
    private Float stop_temperature;
    private Float ec_below;
    private Float ec_above;
    private Float ph_below;
    private Float ph_above;
    private Integer created_by;
    private String created_date;
    private String scheduleCode;
    private Integer farmId;

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

    public Integer getSector() {
        return sector;
    }

    public void setSector(Integer sector) {
        this.sector = sector;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
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

    public String getTank() {
        return tank;
    }

    public void setTank(String tank) {
        this.tank = tank;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Long getTime_exec() {
        return time_exec;
    }

    public void setTime_exec(Long time_exec) {
        this.time_exec = time_exec;
    }

    public Float getStop_temperature() {
        return stop_temperature;
    }

    public void setStop_temperature(Float stop_temperature) {
        this.stop_temperature = stop_temperature;
    }

    public Float getEc_below() {
        return ec_below;
    }

    public void setEc_below(Float ec_below) {
        this.ec_below = ec_below;
    }

    public Float getEc_above() {
        return ec_above;
    }

    public void setEc_above(Float ec_above) {
        this.ec_above = ec_above;
    }

    public Float getPh_below() {
        return ph_below;
    }

    public void setPh_below(Float ph_below) {
        this.ph_below = ph_below;
    }

    public Float getPh_above() {
        return ph_above;
    }

    public void setPh_above(Float ph_above) {
        this.ph_above = ph_above;
    }

    public Integer getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Integer created_by) {
        this.created_by = created_by;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getScheduleCode() {
        return scheduleCode;
    }

    public void setScheduleCode(String scheduleCode) {
        this.scheduleCode = scheduleCode;
    }

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        this.farmId = farmId;
    }
}
