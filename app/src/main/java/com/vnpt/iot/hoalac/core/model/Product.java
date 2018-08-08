package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 7/4/17.
 */

public class Product {
    private int id;
    private int categoryId;	//id danh muc san pham
    private String categoryName;	//id danh muc san pham
    private String name;//ten san pham
    private String describer;//mo ta san pham
    private String status;//trang thai hoat dong cua ban ghi san pham
    private String createdDate; //ngay them ban ghi
    private Double price; //gia san pham / 1kg

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriber() {
        return describer;
    }

    public void setDescriber(String describer) {
        this.describer = describer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
