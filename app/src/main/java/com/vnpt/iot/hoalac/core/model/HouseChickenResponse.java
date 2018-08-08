package com.vnpt.iot.hoalac.core.model;

import com.google.gson.annotations.SerializedName;

public class HouseChickenResponse {
    @SerializedName("deviceId")
    String idDevice;
    @SerializedName("PLC1")
    DataHouseChicken dataHouseChicken;

    public HouseChickenResponse(String idDevice, DataHouseChicken dataHouseChicken) {
        this.idDevice = idDevice;
        this.dataHouseChicken = dataHouseChicken;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public DataHouseChicken getDataHouseChicken() {
        return dataHouseChicken;
    }

    public void setDataHouseChicken(DataHouseChicken dataHouseChicken) {
        this.dataHouseChicken = dataHouseChicken;
    }
}
