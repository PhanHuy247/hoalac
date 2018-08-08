package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 7/4/17.
 */

public class Journal {
    private Long id;
    private Long staffId;
    private String fromDate;
    private String toDate;
    private String workName;
    private String staffName;
    private Integer status;
    private String idBeds;
    private List<AccListSmall> lstBeds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIdBeds() {
        return idBeds;
    }

    public void setIdBeds(String idBeds) {
        this.idBeds = idBeds;
    }

    public List<AccListSmall> getLstBeds() {
        return lstBeds;
    }

    public void setLstBeds(List<AccListSmall> lstBeds) {
        this.lstBeds = lstBeds;
    }
}
