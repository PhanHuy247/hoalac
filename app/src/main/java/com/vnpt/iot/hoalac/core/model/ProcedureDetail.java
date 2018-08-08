package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 7/13/17.
 */

public class ProcedureDetail {
    private Long id;
    private Long progressId;
    private Long workId;
    private String progressName;
    private String workName;
    private Integer startTime;
    private Integer endTime;
    private String notes;
    private Long procedureId;
    private Integer workStart;
    private List<ProcedureWork> procedureWorks;
    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProgressId() {
        return progressId;
    }

    public void setProgressId(Long progressId) {
        this.progressId = progressId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
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

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
    }

    public Integer getWorkStart() {
        return workStart;
    }

    public void setWorkStart(Integer workStart) {
        this.workStart = workStart;
    }

    public List<ProcedureWork> getProcedureWorks() {
        return procedureWorks;
    }

    public void setProcedureWorks(List<ProcedureWork> procedureWorks) {
        this.procedureWorks = procedureWorks;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
