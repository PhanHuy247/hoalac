package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 8/2/17.
 */

public class ScheduleTankBaste {
    private Long id;
    private String name;
    private String code;
    private String gateway;
    private String gatewayName;
    private Integer status;
    private Long farmId;
    private Long sectorId;
    private String sectorName;
    private String startTime; //format dd-mm-yyyy
    private String endTime;
    private Long progressId; //id giai doan
    private String progressName;
    private List<TimeBaste> listTime;
    //	private String checkECHour;//gio check sensor EC
//	private Integer basteWaterMinute; //so phut tuoi nc sach
    private String phValue;
    private String ecValue;
    private String ecTankValue;
    private String soilValue;
    private String soilMax;
    private String basteWaterMinuteTank; //kich hoat bom nuoc sach sau bao nhieu phut
    private String timeZone; //EX: GMT +7
    private String tankId; //ma bon
    private String timeDelay; //SetTimeDelayAfterPump
    private String numberSensor;
    private String maxTimePump;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getProgressId() {
        return progressId;
    }

    public void setProgressId(Long progressId) {
        this.progressId = progressId;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
    }

    public List<TimeBaste> getListTime() {
        return listTime;
    }

    public void setListTime(List<TimeBaste> listTime) {
        this.listTime = listTime;
    }

    public String getPhValue() {
        return phValue;
    }

    public void setPhValue(String phValue) {
        this.phValue = phValue;
    }

    public String getEcValue() {
        return ecValue;
    }

    public void setEcValue(String ecValue) {
        this.ecValue = ecValue;
    }

    public String getEcTankValue() {
        return ecTankValue;
    }

    public void setEcTankValue(String ecTankValue) {
        this.ecTankValue = ecTankValue;
    }

    public String getSoilValue() {
        return soilValue;
    }

    public void setSoilValue(String soilValue) {
        this.soilValue = soilValue;
    }

    public String getSoilMax() {
        return soilMax;
    }

    public void setSoilMax(String soilMax) {
        this.soilMax = soilMax;
    }

    public String getBasteWaterMinuteTank() {
        return basteWaterMinuteTank;
    }

    public void setBasteWaterMinuteTank(String basteWaterMinuteTank) {
        this.basteWaterMinuteTank = basteWaterMinuteTank;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getTankId() {
        return tankId;
    }

    public void setTankId(String tankId) {
        this.tankId = tankId;
    }

    public String getTimeDelay() {
        return timeDelay;
    }

    public void setTimeDelay(String timeDelay) {
        this.timeDelay = timeDelay;
    }

    public String getNumberSensor() {
        return numberSensor;
    }

    public void setNumberSensor(String numberSensor) {
        this.numberSensor = numberSensor;
    }

    public String getMaxTimePump() {
        return maxTimePump;
    }

    public void setMaxTimePump(String maxTimePump) {
        this.maxTimePump = maxTimePump;
    }
}
