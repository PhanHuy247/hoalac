package com.vnpt.iot.hoalac.api.dto;

import java.util.List;

/**
 * Created by thohc on 7/14/17.
 */

public class ScheduleCriteriaItemDTO {
    private String criteriaName;
    private Integer mode;
    private Integer priority;
    private Integer rulesRelationship;
    private String originalDeviceId;
    private List<SimpleCommandInstanceDTO> command;
    private List<ScheduleConfigItemDTO> criteriaList;

    private String ScheduleConfigItemDTOJSON;

    public String getScheduleConfigItemDTOJSON() {
        return ScheduleConfigItemDTOJSON;
    }

    public void setScheduleConfigItemDTOJSON(String scheduleConfigItemDTOJSON) {
        ScheduleConfigItemDTOJSON = scheduleConfigItemDTOJSON;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getRulesRelationship() {
        return rulesRelationship;
    }

    public void setRulesRelationship(Integer rulesRelationship) {
        this.rulesRelationship = rulesRelationship;
    }

    public String getOriginalDeviceId() {
        return originalDeviceId;
    }

    public void setOriginalDeviceId(String originalDeviceId) {
        this.originalDeviceId = originalDeviceId;
    }

    public List<SimpleCommandInstanceDTO> getCommand() {
        return command;
    }

    public void setCommand(List<SimpleCommandInstanceDTO> command) {
        this.command = command;
    }

    public List<ScheduleConfigItemDTO> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<ScheduleConfigItemDTO> criteriaList) {
        this.criteriaList = criteriaList;
    }
}
