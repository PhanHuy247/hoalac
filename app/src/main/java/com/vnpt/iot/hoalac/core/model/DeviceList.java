package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 7/17/17.
 */

public class DeviceList {
    private Long id;
    private String deviceId;
    private String name;
    private Integer type;
    private Long farmId;
    private String farmName;
    private Integer status;
    private String createDate;
    private Long parentId;
    private List<BedsDetail> beds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
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

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<BedsDetail> getBeds() {
        return beds;
    }

    public void setBeds(List<BedsDetail> beds) {
        this.beds = beds;
    }
}
