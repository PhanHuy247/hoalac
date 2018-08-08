package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 8/13/17.
 */

public class Works {
    private Long workId;
    private String workName;
    private String staffDo;
    private String staffReviewer;
    private String startWork;
    private Integer mailStatus;
    private String sendDate;
    private List<SeasonStaffDTO> lstStaff;

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

    public String getStaffDo() {
        return staffDo;
    }

    public void setStaffDo(String staffDo) {
        this.staffDo = staffDo;
    }

    public String getStaffReviewer() {
        return staffReviewer;
    }

    public void setStaffReviewer(String staffReviewer) {
        this.staffReviewer = staffReviewer;
    }

    public String getStartWork() {
        return startWork;
    }

    public void setStartWork(String startWork) {
        this.startWork = startWork;
    }

    public Integer getMailStatus() {
        return mailStatus;
    }

    public void setMailStatus(Integer mailStatus) {
        this.mailStatus = mailStatus;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public List<SeasonStaffDTO> getLstStaff() {
        return lstStaff;
    }

    public void setLstStaff(List<SeasonStaffDTO> lstStaff) {
        this.lstStaff = lstStaff;
    }
}
