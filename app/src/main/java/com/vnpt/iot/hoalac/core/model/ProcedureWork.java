package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 8/11/17.
 */

public class ProcedureWork {
    private Long workId;
    private String workName;
    private Integer startTime;

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }
}
