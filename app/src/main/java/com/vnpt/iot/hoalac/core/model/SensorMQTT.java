package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 4/26/17.
 */

public class SensorMQTT {
    private int errorCode;
    private String errorDesc;
    private String content;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
