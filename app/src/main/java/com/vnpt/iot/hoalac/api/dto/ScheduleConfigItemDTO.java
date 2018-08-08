package com.vnpt.iot.hoalac.api.dto;

/**
 * Created by thohc on 7/14/17.
 */

public class ScheduleConfigItemDTO {
    private String dependDeviceId;
    private String conditionParam;
    private String valueAbove;
    private String valueBelow;
    private Integer filterOperation;

    public String getDependDeviceId() {
        return dependDeviceId;
    }

    public void setDependDeviceId(String dependDeviceId) {
        this.dependDeviceId = dependDeviceId;
    }

    public String getConditionParam() {
        return conditionParam;
    }

    public void setConditionParam(String conditionParam) {
        this.conditionParam = conditionParam;
    }

    public String getValueAbove() {
        return valueAbove;
    }

    public void setValueAbove(String valueAbove) {
        this.valueAbove = valueAbove;
    }

    public String getValueBelow() {
        return valueBelow;
    }

    public void setValueBelow(String valueBelow) {
        this.valueBelow = valueBelow;
    }

    public Integer getFilterOperation() {
        return filterOperation;
    }

    public void setFilterOperation(Integer filterOperation) {
        this.filterOperation = filterOperation;
    }
}
