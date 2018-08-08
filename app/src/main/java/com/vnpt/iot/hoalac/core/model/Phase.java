package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 6/21/17.
 */

public class Phase {
    private Long id;
    private Long procedureId;
    private String procedureName;
    private String name;
    private String code;
    private String startTime;
    private String endTime;
    private Long productId;
    private String productName;
    private String listBeds;
    private List<AccListSmall> lstBeds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getListBeds() {
        return listBeds;
    }

    public void setListBeds(String listBeds) {
        this.listBeds = listBeds;
    }

    public List<AccListSmall> getLstBeds() {
        return lstBeds;
    }

    public void setLstBeds(List<AccListSmall> lstBeds) {
        this.lstBeds = lstBeds;
    }
}
