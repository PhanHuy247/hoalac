package com.vnpt.iot.hoalac.core.model;

import java.io.Serializable;

public class FeatureHouseChicken implements Serializable {
    String overview;
    String state;
    String name;
    String value;
    String unit;
    public FeatureHouseChicken() {
    }

    public FeatureHouseChicken(String name, String state, String value) {
        this.name = name;
        this.state = state;
        this.value = value;
    }

    public FeatureHouseChicken(String state, String name, String value, String unit) {
        this.state = state;
        this.name = name;
        this.value = value;
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
