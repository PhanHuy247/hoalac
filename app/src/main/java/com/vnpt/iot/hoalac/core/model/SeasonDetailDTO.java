package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 7/13/17.
 */

public class SeasonDetailDTO {
    private Long id;
    private Long phaseId;
    private String phaseName;
    private Long procedureId;
    private String procedureName;
    private Long procedureDetailId;
    private Long progressId;
    private String progressName;
    private String startTime; //ngay bat dau du kien
    private String endTime; // ngay bat dau thuc te
    private String realStartTime; // ngay ket thuc du kien
    private String realStopTime; // ngay ket thuc thuc te
    private List<Works> listWork;

    public List<Works> getListWork() {
        return listWork;
    }

    public void setListWork(List<Works> listWork) {
        this.listWork = listWork;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Long phaseId) {
        this.phaseId = phaseId;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Long getProcedureDetailId() {
        return procedureDetailId;
    }

    public void setProcedureDetailId(Long procedureDetailId) {
        this.procedureDetailId = procedureDetailId;
    }

    public Long getProgressId() {
        return progressId;
    }

    public void setProgressId(Long progressId) {
        this.progressId = progressId;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRealStartTime() {
        return realStartTime;
    }

    public void setRealStartTime(String realStartTime) {
        this.realStartTime = realStartTime;
    }

    public String getRealStopTime() {
        return realStopTime;
    }

    public void setRealStopTime(String realStopTime) {
        this.realStopTime = realStopTime;
    }
}
