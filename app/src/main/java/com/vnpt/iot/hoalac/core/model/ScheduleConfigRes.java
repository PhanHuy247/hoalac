package com.vnpt.iot.hoalac.core.model;

import com.vnpt.iot.hoalac.api.dto.ScheduleConfigDTO;

import java.util.List;

/**
 * Created by thohc on 7/19/17.
 */

public class ScheduleConfigRes {
    private Long id;
    private String name;
    private String code;
    private Long deviceId;
    private String deviceName;
    private Byte status;
    private Long userId; //ma chu cac trang trai
    private Integer type;
    private String editGatewayName;
    private Long editGateway;
    private List<ScheduleConfigDTO> list;

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

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEditGatewayName() {
        return editGatewayName;
    }

    public void setEditGatewayName(String editGatewayName) {
        this.editGatewayName = editGatewayName;
    }

    public Long getEditGateway() {
        return editGateway;
    }

    public void setEditGateway(Long editGateway) {
        this.editGateway = editGateway;
    }

    public List<ScheduleConfigDTO> getList() {
        return list;
    }

    public void setList(List<ScheduleConfigDTO> list) {
        this.list = list;
    }
}
