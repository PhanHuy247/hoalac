package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 6/22/17.
 */

public class GreenHouse {
    private Long id;
    private Long farmId;
    private String name;
    private String farmName;
    private String code;
    private Integer type;
    private Long area;
    private Integer typeCul;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Integer getTypeCul() {
        return typeCul;
    }

    public void setTypeCul(Integer typeCul) {
        this.typeCul = typeCul;
    }
}
