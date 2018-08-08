package com.vnpt.iot.hoalac.core.model;

import java.io.Serializable;

public class HouseChickenScheduleDetail implements Serializable {
    String startTime;
    String stopTime;
    String maxWeight;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }
}
