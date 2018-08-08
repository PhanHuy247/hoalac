package com.vnpt.iot.hoalac.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HouseChicken implements Serializable{
    String display;
    String groupObject;
    String name;
    Boolean isHeader = false;
    List<FeatureHouseChicken> featureHouseChickenList;

    //FeedingSchedule
    String id;
    String code;
    String henhouseId;
    String henhouseName;
    String henhouseName_EN;
    String action;
    String topicName;
    String requestId;
    String timeZone;
    String statusProcess;
    String nameObject;

    //CoolingSchedule
    String minFanNumber;
    String reverseTime;
    String minFreshFanSpeed;
    String minCeilingFanSpeed;
    String minHorizontalFanSpeed;
    String tempIn_From;
    String tempIn_To;
    String tempOut_From;
    String tempOut_To;
    String humidity_From;
    String humidity_To;
    String CO2_From;
    String CO2_To;
    String pressure_From;
    String pressure_To;

    List<HouseChickenScheduleDetail> feedingInfoList;
    List<HouseChickenScheduleDetail> timeList;
    List<String> timeListDrug;

    public HouseChicken() {
    }

    public HouseChicken(String display) {
        this.display = display;
        featureHouseChickenList = new ArrayList<>();
    }

    public HouseChicken(String display, String groupObject) {
        this.display = display;
        this.groupObject = groupObject;
        featureHouseChickenList = new ArrayList<>();
    }

    public List<String> getTimeListDrug() {
        return timeListDrug;
    }

    public void setTimeListDrug(List<String> timeListDrug) {
        this.timeListDrug = timeListDrug;
    }

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getGroupObject() {
        return groupObject;
    }

    public void setGroupObject(String groupObject) {
        this.groupObject = groupObject;
    }

    public List<FeatureHouseChicken> getFeatureHouseChickenList() {
        return featureHouseChickenList;
    }

    public void setFeatureHouseChickenList(List<FeatureHouseChicken> featureHouseChickenList) {
        this.featureHouseChickenList = featureHouseChickenList;
    }

    public Boolean getHeader() {
        return isHeader;
    }

    public void setHeader(Boolean header) {
        isHeader = header;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHenhouseId() {
        return henhouseId;
    }

    public void setHenhouseId(String henhouseId) {
        this.henhouseId = henhouseId;
    }

    public String getHenhouseName() {
        return henhouseName;
    }

    public void setHenhouseName(String henhouseName) {
        this.henhouseName = henhouseName;
    }

    public String getHenhouseName_EN() {
        return henhouseName_EN;
    }

    public void setHenhouseName_EN(String henhouseName_EN) {
        this.henhouseName_EN = henhouseName_EN;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getStatusProcess() {
        return statusProcess;
    }

    public void setStatusProcess(String statusProcess) {
        this.statusProcess = statusProcess;
    }

    public List<HouseChickenScheduleDetail> getFeedingInfoList() {
        return feedingInfoList;
    }

    public void setFeedingInfoList(List<HouseChickenScheduleDetail> feedingInfoList) {
        this.feedingInfoList = feedingInfoList;
    }

    public String getMinFanNumber() {
        return minFanNumber;
    }

    public void setMinFanNumber(String minFanNumber) {
        this.minFanNumber = minFanNumber;
    }

    public String getReverseTime() {
        return reverseTime;
    }

    public void setReverseTime(String reverseTime) {
        this.reverseTime = reverseTime;
    }

    public String getMinFreshFanSpeed() {
        return minFreshFanSpeed;
    }

    public void setMinFreshFanSpeed(String minFreshFanSpeed) {
        this.minFreshFanSpeed = minFreshFanSpeed;
    }

    public String getMinCeilingFanSpeed() {
        return minCeilingFanSpeed;
    }

    public void setMinCeilingFanSpeed(String minCeilingFanSpeed) {
        this.minCeilingFanSpeed = minCeilingFanSpeed;
    }

    public String getMinHorizontalFanSpeed() {
        return minHorizontalFanSpeed;
    }

    public void setMinHorizontalFanSpeed(String minHorizontalFanSpeed) {
        this.minHorizontalFanSpeed = minHorizontalFanSpeed;
    }

    public String getTempIn_From() {
        return tempIn_From;
    }

    public void setTempIn_From(String tempIn_From) {
        this.tempIn_From = tempIn_From;
    }

    public String getTempIn_To() {
        return tempIn_To;
    }

    public void setTempIn_To(String tempIn_To) {
        this.tempIn_To = tempIn_To;
    }

    public String getTempOut_From() {
        return tempOut_From;
    }

    public void setTempOut_From(String tempOut_From) {
        this.tempOut_From = tempOut_From;
    }

    public String getTempOut_To() {
        return tempOut_To;
    }

    public void setTempOut_To(String tempOut_To) {
        this.tempOut_To = tempOut_To;
    }

    public String getHumidity_From() {
        return humidity_From;
    }

    public void setHumidity_From(String humidity_From) {
        this.humidity_From = humidity_From;
    }

    public String getHumidity_To() {
        return humidity_To;
    }

    public void setHumidity_To(String humidity_To) {
        this.humidity_To = humidity_To;
    }

    public String getCO2_From() {
        return CO2_From;
    }

    public void setCO2_From(String CO2_From) {
        this.CO2_From = CO2_From;
    }

    public String getCO2_To() {
        return CO2_To;
    }

    public void setCO2_To(String CO2_To) {
        this.CO2_To = CO2_To;
    }

    public String getPressure_From() {
        return pressure_From;
    }

    public void setPressure_From(String pressure_From) {
        this.pressure_From = pressure_From;
    }

    public String getPressure_To() {
        return pressure_To;
    }

    public void setPressure_To(String pressure_To) {
        this.pressure_To = pressure_To;
    }

    public List<HouseChickenScheduleDetail> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<HouseChickenScheduleDetail> timeList) {
        this.timeList = timeList;
    }
}
