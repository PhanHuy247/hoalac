package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 8/3/17.
 */

public class ScheduleLight {
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
    private Integer lux;//cuong do sang
    private String hourStartCheck; //gio bat dau kiem tra
    private String hourStopCheck; //gio ket thuc kiem tra
    private Integer inMinute; //bat trong bao nhieu phut
    private String timeZone; //EX: GMT+7:00

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
    }

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

    public Integer getLux() {
        return lux;
    }

    public void setLux(Integer lux) {
        this.lux = lux;
    }

    public String getHourStartCheck() {
        return hourStartCheck;
    }

    public void setHourStartCheck(String hourStartCheck) {
        this.hourStartCheck = hourStartCheck;
    }

    public String getHourStopCheck() {
        return hourStopCheck;
    }

    public void setHourStopCheck(String hourStopCheck) {
        this.hourStopCheck = hourStopCheck;
    }

    public Integer getInMinute() {
        return inMinute;
    }

    public void setInMinute(Integer inMinute) {
        this.inMinute = inMinute;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
