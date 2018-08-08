package com.vnpt.iot.hoalac.core.model;

/**
 * Created by thohc on 7/22/17.
 */

public class Camera {
    private Long id;
    private String name;
    private String deviceId;
    private String urlView;
    private String urlHDView;
    private String checkItem;

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUrlView() {
        return urlView;
    }

    public void setUrlView(String urlView) {
        this.urlView = urlView;
    }

    public String getUrlHDView() {
        return urlHDView;
    }

    public void setUrlHDView(String urlHDView) {
        this.urlHDView = urlHDView;
    }

    public String getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem;
    }
}
