package com.vnpt.iot.hoalac.api.common;

public class Constatnts {

//    public static final String API_BASE_URL = "http://scpapigw.thingxyz.net:8086/SCPAPI";
//    public static final String API_BASE_URL = "http://wtpapi.thingxyz.net";
    public static final String API_BASE_URL = "http://203.162.94.42:8080/SCPAPI";

//    public static final String API_ORGANIC_URL = "http://delcoapi.thingxyz.net/Delco";
//    public static final String API_ORGANIC_URL = "http://wtpapidelco.thingxyz.net/Delco";
    public static final String API_ORGANIC_URL = "http://203.162.94.41:9898/Delco";
//    public static final String API_ORGANIC_URL = "http://10.15.12.247:9898/Delco";//local

    // Control Device
//    public static final String CONTROL_DEVICE = API_BASE_URL.concat("/devices/controlDevice/1126"); //Pro
    public static final String CONTROL_DEVICE = API_BASE_URL.concat("/devices/controlDevice/801"); //Dev

    //Login
    public static final String LOGIN = API_ORGANIC_URL.concat("/loginApp");
    public static final String UPDATE = API_ORGANIC_URL.concat("/update-");
    public static final String REGISTER_TOKEN = API_ORGANIC_URL.concat("/createToken-");
    public static final String REMOVE_TOKEN = API_ORGANIC_URL.concat("/deleteToken-");

    //Phase
    public static final String PHASE_LIST = API_ORGANIC_URL.concat("/phase/getListApp");
    public static final String PHASE_DETAIL = API_ORGANIC_URL.concat("/phase/detail");

    //QRCode
    public static final String QRCODE_DETAIL = API_ORGANIC_URL.concat("/qrcode/getPhaseQRCodeDetail");

    //QRCode
    public static final String ENVIRONMENT = API_ORGANIC_URL.concat("/devices/getDataAirCooling");

    //Journal
    public static final String JOURNAL_LIST = API_ORGANIC_URL.concat("/journal/getListByApp-");

    //Schedule
    public static final String SCHEDULE_LIST = API_ORGANIC_URL.concat("/devices/getScheduleListApp");
    public static final String SCHEDULE_CHANGESTATUS = API_ORGANIC_URL.concat("/devices/updateStatusByIdApp");
    public static final String SCHEDULE_DETAIL = API_ORGANIC_URL.concat("/control/detail");
    public static final String SCHEDULE_DETAIL_APP = API_ORGANIC_URL.concat("/devices/getDetailFromListAllApp");
    public static final String SCHEDULE_CHANGE_STATUS_BY_DEVICE = API_ORGANIC_URL.concat("/devices/changeStatusScheduleAppByDevice");
    public static final String SCHEDULE_COUNT = API_ORGANIC_URL.concat("/devices/countScheduleAppByDevice");

    //Procedure
    public static final String PROCEDURE_LIST = API_ORGANIC_URL.concat("/procedure/getList-");
    public static final String PROCEDURE_DETAIL = API_ORGANIC_URL.concat("/procedure/detailGroupBy");

    //Common
    public static final String CATEGORY_LIST = API_ORGANIC_URL.concat("/common/getListCategory-");
    public static final String PRODUCT_LIST = API_ORGANIC_URL.concat("/common/listProductByCategory");

    //Asset
    public static final String ASSET_IMPORT_LIST = API_ORGANIC_URL.concat("/materials/getListImport-");
    public static final String ASSET_IMPORT_DETAIL = API_ORGANIC_URL.concat("/materials/detailImportCoupon");
    public static final String ASSET_EXPORT_LIST = API_ORGANIC_URL.concat("/materials/getListExport-");
    public static final String ASSET_EXPORT_DETAIL = API_ORGANIC_URL.concat("/materials/detailExportCoupon");

    //Farm
    public static final String FARM_LIST = API_ORGANIC_URL.concat("/farm/getList-");
    public static final String GREEN_HOUSE_LIST = API_ORGANIC_URL.concat("/sector/getList-");

    //Alarm
    public static final String ALARM_LIST = API_ORGANIC_URL.concat("/alarm/getListHistorySendAlarm-");

    // Get List Device
    public static final String DEVICE_LIST = API_ORGANIC_URL.concat("/devices/getList-");
    public static final String DEVICE_LIST_CAMERA = API_ORGANIC_URL.concat("/devices/getListCamera");
    public static final String DEVICE_LIST_IP = API_ORGANIC_URL.concat("/devices/getList");
    public static final String DEVICE_SCHEDULE = API_ORGANIC_URL.concat("/devices/getSchedule");
    public static final String DEVICE_SAVE_SCHEDULE = API_ORGANIC_URL.concat("/devices/schedule");

    //Calendar Auto
    public static final String FEEDING_SCHEDULE = API_ORGANIC_URL.concat("/henhouseSchedule/getFeedingScheduleList");
    public static final String COOLING_SCHEDULE = API_ORGANIC_URL.concat("/henhouseSchedule/getCoolingScheduleList");
    public static final String LIGHT_SCHEDULE = API_ORGANIC_URL.concat("/henhouseSchedule/getLightScheduleList");
    public static final String DRUG_SCHEDULE = API_ORGANIC_URL.concat("/henhouseSchedule/getDrugScheduleList");



}
