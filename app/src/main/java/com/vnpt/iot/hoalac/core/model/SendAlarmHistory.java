package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 7/26/17.
 */

public class SendAlarmHistory {
    private Long id;
    private Byte type;  //1= ph đất, 2: ec đất, 3: độ ẩm đất, 4: nhiệt độ kk, 5: độ ẩm không khí, 6:ec bồn, 7:ph bồn, 8:nhiệt độ bồn, 9:bồn cạn/đầy
    private Double overValue; //Vuot nguong canh bao
    private String createdDate;
    private String content; //Noi dung
    private Long alarmId;
    private String deviceCode; //Ma device
    private Long farmId;
    private Long sectorId;
    private String sectorName;
    private String farmName;
    private Double ruleValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Double getOverValue() {
        return overValue;
    }

    public void setOverValue(Double overValue) {
        this.overValue = overValue;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
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

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public Double getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(Double ruleValue) {
        this.ruleValue = ruleValue;
    }
}
