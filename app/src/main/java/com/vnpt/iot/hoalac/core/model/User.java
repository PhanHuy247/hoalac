package com.vnpt.iot.hoalac.core.model;

import java.util.List;

/**
 * Created by thohc on 6/16/17.
 */

public class User {
    private int id;
    private String email;
    private String phoneNumber;
    private String name;
    private int parentId;
    private int type;
    private int status;
    private String address;
    private List<String> roleApps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getRoleApps() {
        return roleApps;
    }

    public void setRoleApps(List<String> roleApps) {
        this.roleApps = roleApps;
    }
}
