package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 8/3/17.
 */

public class AquaticConfigurationDetail {
    private Integer id;
    private String name;
    private Integer status;
    private String sector;
    private String gateway;
    private String gatewayName;
    private String progress;
    private String date_start;
    private String date_end;
    private String tank;
    private String time_exec;
    private String stop_temperature;
    private String ec_below;
    private String ec_above;
    private String ph_below;
    private String ph_above;
    private String created_by;
    private String scheduleCode;
    private String farmName;
    private String time_exec_val_A;
    private String time_exec_val_B;
    private List<EcJson> listEc;
    private String bedName;

    //new requirement 07/09/2017
    private Integer type;
    private String pre_exec_in;
    private String time_pump_start;
    private String pump_exec_duration;
    private String pump_stop_duration;

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

    public String getTank() {
        return tank;
    }

    public void setTank(String tank) {
        this.tank = tank;
    }

    public String getTime_exec() {
        return time_exec;
    }

    public void setTime_exec(String time_exec) {
        this.time_exec = time_exec;
    }

    public String getStop_temperature() {
        return stop_temperature;
    }

    public void setStop_temperature(String stop_temperature) {
        this.stop_temperature = stop_temperature;
    }

    public String getEc_below() {
        return ec_below;
    }

    public void setEc_below(String ec_below) {
        this.ec_below = ec_below;
    }

    public String getEc_above() {
        return ec_above;
    }

    public void setEc_above(String ec_above) {
        this.ec_above = ec_above;
    }

    public String getPh_below() {
        return ph_below;
    }

    public void setPh_below(String ph_below) {
        this.ph_below = ph_below;
    }

    public String getPh_above() {
        return ph_above;
    }

    public void setPh_above(String ph_above) {
        this.ph_above = ph_above;
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

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getTime_exec_val_A() {
        return time_exec_val_A;
    }

    public void setTime_exec_val_A(String time_exec_val_A) {
        this.time_exec_val_A = time_exec_val_A;
    }

    public String getTime_exec_val_B() {
        return time_exec_val_B;
    }

    public void setTime_exec_val_B(String time_exec_val_B) {
        this.time_exec_val_B = time_exec_val_B;
    }

    public List<EcJson> getListEc() {
        return listEc;
    }

    public void setListEc(List<EcJson> listEc) {
        this.listEc = listEc;
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

    public String getPre_exec_in() {
        return pre_exec_in;
    }

    public void setPre_exec_in(String pre_exec_in) {
        this.pre_exec_in = pre_exec_in;
    }

    public String getTime_pump_start() {
        return time_pump_start;
    }

    public void setTime_pump_start(String time_pump_start) {
        this.time_pump_start = time_pump_start;
    }

    public String getPump_exec_duration() {
        return pump_exec_duration;
    }

    public void setPump_exec_duration(String pump_exec_duration) {
        this.pump_exec_duration = pump_exec_duration;
    }

    public String getPump_stop_duration() {
        return pump_stop_duration;
    }

    public void setPump_stop_duration(String pump_stop_duration) {
        this.pump_stop_duration = pump_stop_duration;
    }
}
