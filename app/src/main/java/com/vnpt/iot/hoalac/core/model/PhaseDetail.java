package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 7/13/17.
 */

public class PhaseDetail {
    private Long id;
    private String code;
    private String name;
    private Long productId;
    private String productName;
    private Long procedureId;
    private String procedureName;
    private String startTime;
    private String endTime;
    private String bedsList;
    private String bedsName;
    private Long farmId;
    private Long sectorId;
    private String sectorName;
    private Long bedId;
    private String bedName;
    private Integer quantity;
    private QRCodeRes qrcode;
    private String position;
    private Integer rowFrom;
    private Integer rowTo;
    private List<SeasonDetailDTO> lstDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getBedsList() {
        return bedsList;
    }

    public void setBedsList(String bedsList) {
        this.bedsList = bedsList;
    }

    public String getBedsName() {
        return bedsName;
    }

    public void setBedsName(String bedsName) {
        this.bedsName = bedsName;
    }

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public Long getBedId() {
        return bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }

    public String getBedName() {
        return bedName;
    }

    public void setBedName(String bedName) {
        this.bedName = bedName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public QRCodeRes getQrcode() {
        return qrcode;
    }

    public void setQrcode(QRCodeRes qrcode) {
        this.qrcode = qrcode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getRowFrom() {
        return rowFrom;
    }

    public void setRowFrom(Integer rowFrom) {
        this.rowFrom = rowFrom;
    }

    public Integer getRowTo() {
        return rowTo;
    }

    public void setRowTo(Integer rowTo) {
        this.rowTo = rowTo;
    }

    public List<SeasonDetailDTO> getLstDetail() {
        return lstDetail;
    }

    public void setLstDetail(List<SeasonDetailDTO> lstDetail) {
        this.lstDetail = lstDetail;
    }
}
