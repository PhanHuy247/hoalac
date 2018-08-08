package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 7/14/17.
 */

public class QRCode {
    private Long id;
    private String code;	//Code tem qr
    private Integer status;	//trang thai hoat dong
    private String createdDate;//ngay tao tem QR trong
    private Long farmId;	//id trang trai/nong ho
    private String farmName;//ten trang trai/nong ho
    private String urlImage;//duong dan chua anh
    private Long productId; //id san pham
    private String productName;//ten san pham
    private Double weigh;		//trong luong
    private String unit;	//don vi tinh
    private String activeDate; //ngay kich hoat tem QR cho san pham
    private String productDate;//ngay san xuat
    private String webLink;	   //link web
    private String describer;  //mo ta thong tin san pham
    private Long bedId;
    private String expiredDate;//ngay het han
    private String mnfDate;
    private Long sectorId;
    private String sectorName;
    private String packedDate;
    private Long phaseId; //dot
    private Long procedureId; //ma quy trinh
    private Long procedureDetailId; // ma chi tiet quy trinh
    private Long progressId; //ma giai doan
    private String startTime; //thoi gian bat dau
    private Integer type;
    private Integer active;
    private String position;
    private List<Procedure> procedure;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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

    public Double getWeigh() {
        return weigh;
    }

    public void setWeigh(Double weigh) {
        this.weigh = weigh;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getDescriber() {
        return describer;
    }

    public void setDescriber(String describer) {
        this.describer = describer;
    }

    public Long getBedId() {
        return bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getMnfDate() {
        return mnfDate;
    }

    public void setMnfDate(String mnfDate) {
        this.mnfDate = mnfDate;
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

    public String getPackedDate() {
        return packedDate;
    }

    public void setPackedDate(String packedDate) {
        this.packedDate = packedDate;
    }

    public Long getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Long phaseId) {
        this.phaseId = phaseId;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Procedure> getProcedure() {
        return procedure;
    }

    public void setProcedure(List<Procedure> procedure) {
        this.procedure = procedure;
    }
}
