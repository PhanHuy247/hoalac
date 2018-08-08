package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 4/26/17.
 */

public class Device {
    private Long id;
    private String deviceId;
    private String name;
    private Integer type;
    private Long scpId;
    private Long farmId;
    private Integer status;
    private Long parentId;
    private String description;
    private String topicLocal;
    private String ipLocal;

    public String getTopicLocal() {
        return topicLocal;
    }

    public void setTopicLocal(String topicLocal) {
        this.topicLocal = topicLocal;
    }

    public String getIpLocal() {
        return ipLocal;
    }

    public void setIpLocal(String ipLocal) {
        this.ipLocal = ipLocal;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getScpId() {
        return scpId;
    }

    public void setScpId(Long scpId) {
        this.scpId = scpId;
    }

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
