package com.vnpt.iot.hoalac.api.dto;

import java.util.List;

public class ScheduleConfigDTO {

    private String criteriaJSON;
    private String criteriaOff;
    private Integer resourceType;
    private String schedule;
    private Integer state;
    private Integer action;
    private String timeZone;
    private List<ScheduleCriteriaItemDTO> criteria;

    public String getCriteriaJSON() {
        return criteriaJSON;
    }

    public void setCriteriaJSON(String criteriaJSON) {
        this.criteriaJSON = criteriaJSON;
    }

    public String getCriteriaOff() {
        return criteriaOff;
    }

    public void setCriteriaOff(String criteriaOff) {
        this.criteriaOff = criteriaOff;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public List<ScheduleCriteriaItemDTO> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<ScheduleCriteriaItemDTO> criteria) {
        this.criteria = criteria;
    }
}
