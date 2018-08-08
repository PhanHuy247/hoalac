package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 8/3/17.
 */

public class ScheduleTankBlend {
    private Long id;
    private String name;
    private String code;
    private String gateway;
    private Integer status;
    private Long farmId;
    private Long sectorId;
    private String sectorName;
    private String startTime; //format dd-mm-yyyy
    private String endTime;
    private Long progressId; //id giai doan
    private String progressName;
    private Float ecTankFrom; //gia tri sensor ec bon nguong duoi
    private Float ecTankTo; //gia tri sensor ec bon nguong tren
    private Float ecTankMin; //gia tri sensor ec bon nho nhat
    private String timeZone; //EX: GMT +7
    private String tankId; //ma bon
    private String gatewayName;
    private String tankName;
    private Integer aerationStart;
    private Integer aerationStop;

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getTankName() {
        return tankName;
    }

    public void setTankName(String tankName) {
        this.tankName = tankName;
    }

    public Integer getAerationStart() {
        return aerationStart;
    }

    public void setAerationStart(Integer aerationStart) {
        this.aerationStart = aerationStart;
    }

    public Integer getAerationStop() {
        return aerationStop;
    }

    public void setAerationStop(Integer aerationStop) {
        this.aerationStop = aerationStop;
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

    public String getTankId() {
        return tankId;
    }

    public void setTankId(String tankId) {
        this.tankId = tankId;
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

    public Float getEcTankFrom() {
        return ecTankFrom;
    }

    public void setEcTankFrom(Float ecTankFrom) {
        this.ecTankFrom = ecTankFrom;
    }

    public Float getEcTankTo() {
        return ecTankTo;
    }

    public void setEcTankTo(Float ecTankTo) {
        this.ecTankTo = ecTankTo;
    }

    public Float getEcTankMin() {
        return ecTankMin;
    }

    public void setEcTankMin(Float ecTankMin) {
        this.ecTankMin = ecTankMin;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
