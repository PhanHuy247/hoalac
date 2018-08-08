package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 7/25/17.
 */

public class Alarm {
    private Long id;
    private Long deviceId;
    private String name;
    private Integer type; //Loai: 0: Nhiệt độ KK, 1: Độ ẩm KK, 2: Nhiệt độ đất, 3: Độ ẩm đất
    private Integer riskLevel; //0: Trung bình, 1: Nguy Hiểm, 2: Cao
    private Integer typeValue; //0: Bé hơn , 1: bằng, 2: lớn hơn
    private Double value; //Giá trị so sánh
    private String notes;
    private Integer notificationType; //Loại thông báo, 0: Web, 1: App, 2: SMS, 3: Email
    private Long parentId; //Id account chủ trang trại
    private String phone;
    private String subject; // Tieu de email (dung cho email)
    private String content; // Noi dung thong bao
    private String email;
    private Integer status; //1. Kich hoat, 2. Tam dung
    private Long farmId;
    private List<AlarmDevice> alarmDevices; // Danh sach cac device cua alarm

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Integer getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(Integer typeValue) {
        this.typeValue = typeValue;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Integer notificationType) {
        this.notificationType = notificationType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<AlarmDevice> getAlarmDevices() {
        return alarmDevices;
    }

    public void setAlarmDevices(List<AlarmDevice> alarmDevices) {
        this.alarmDevices = alarmDevices;
    }
}
