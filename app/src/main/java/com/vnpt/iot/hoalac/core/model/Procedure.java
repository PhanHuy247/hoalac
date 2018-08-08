package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 7/3/17.
 */

public class Procedure {
    private Long id;
    private String name;
    private Long productId;
    private String productName;
    private Long categoryId;
    private String categoryName;
    private String notes;
    private String code;
    private Long accountId;
    private List<ProcedureDetail> procedureDetails;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public List<ProcedureDetail> getProcedureDetails() {
        return procedureDetails;
    }

    public void setProcedureDetails(List<ProcedureDetail> procedureDetails) {
        this.procedureDetails = procedureDetails;
    }
}
