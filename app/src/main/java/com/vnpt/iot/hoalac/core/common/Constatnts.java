package com.vnpt.iot.hoalac.core.common;

public class Constatnts {
//    public static final String API_KEY = "48e05bf6250951fa975f36c13b683fd1"; //Product
    public static final String API_KEY = "9a9acffb1866cce100d57be494957cb7"; //Dev
//    public static final String IP_MQTT_SERVER = "tcp://scpbroker.thingxyz.net:1883";
    public static final String IP_MQTT_SERVER = "tcp://203.162.94.38:1883";


    public static final String PATH_STEAM = "/videostream.cgi?loginuse=iot_itc&loginpas=ThingxyzNet";

    public static final String TOPIC_HOUSE_CHICKEN = "/SCPCloud/DEVICE/SMARTFARM_DELCO_2_PLC01";

//    public static final String VALVE_1_ID = "984FEE055C28VALVE1";
    public static final String VALVE_1_ID = "VALVE_1";
    public static final String VALVE_2_ID = "VALVE_2";
    public static final String VALVE_3_ID = "VALVE_3";
    public static final String VALVE_4_ID = "VALVE_4";

    public static final String VALVE_CMD_ON = "TurnOn";
    public static final String VALVE_CMD_OFF = "TurnOff";
    public static final String VALVE_CMD_REQUEST = "Request";

    public static final String SENSOR_ID = "SENSOR_1";

    public static final int BOM = 1;
    public static final int VAN = 2;
    public static final int QUAT = 3;
    public static final int DEN = 4;
    public static final int SENSOR = 5;
    public static final int GATEWAY = 6;
    public static final int CAMERA = 7;
    public static final int TANK = 8;
    public static final int AIR_COOL = 9;

    public static final int SECTOR_DUALUOI = 1;
    public static final int SECTOR_THUYCANH = 2;

    public static final String SCHEDULE_LIGHT = "SCHEDULE_LIGHT";
    public static final String SCHEDULE_TANK_BASTE = "SCHEDULE_TANK_BASTE";
    public static final String SCHEDULE_TANK_BLEND = "SCHEDULE_TANK_BLEND";
    public static final String SCHEDULE_FOG_FAN = "SCHEDULE_FOG_FAN";
    public static final String SCHEDULE_AQUAPONIC = "SCHEDULE_AQUAPONIC";

    public static final String CMD_REQUEST = "Request";
    public static final String CMD_DEVICE_GET_STATUS = "GetStatus";
    public static final String CMD_DEVICE_CHANGE_STATUS = "ChangeStateSchedule";
    public static final String CMD_DEVICE_CHANGE_STATUS_On = "ChangeStateSchedule_On";
    public static final String CMD_DEVICE_CHANGE_STATUS_Off = "ChangeStateSchedule_Off";
    public static final String CMD_GW_STATUS = "GatewayGetStatus";

    //device light
    public static final String DELCO_MELON_LIGHTING_1 = "DELCO-MELON-LIGHTING_1";

    public static final String ON_OFF = "ON/OFF";
    public static final String OVERLOAD = "OVERLOAD";
    public static final String ERROR = "ERROR";
    public static final String EMERGENCY = "EMERGENCY";
    public static final String SPEED = "";

    public static final String NO_NAME = "No name";
    public static final String OVERVIEW = "overview";
    public static final String WARNING_OVERVIEW = "WARNING_OVERVIEW";
    public static final String COOLING_SYSTEM = "coolingSystem";
    public static final String FOLLOW = "FOLLOW";
    public static final String FOOD= "FOOD";
    public static final String WATER= "WATER";
    public static final String EGG_COLLECT= "EGG COLLECT";
    public static final String DON_PHAN= "DON PHAN";
    public static final String SYSTEM = "SYSTEM";
    public static final String AUTO_MAN = "AUTO/MAN";
    public static final String CALENDAR_AUTO = "CALENDAR AUTO";
    public static final String CALENDAR_AUTO_ITEM_1 = "CALENDAR AUTO ITEM 1";
    public static final String CALENDAR_AUTO_ITEM_2 = "CALENDAR AUTO ITEM 2";
    public static final String CALENDAR_AUTO_ITEM_3 = "CALENDAR AUTO ITEM 3";
    public static final String CALENDAR_AUTO_ITEM_4 = "CALENDAR AUTO ITEM 4";

    public static final String DAY_PERIOD_TOTAL = "";
    public static final String WEEK_PERIOD_TOTAL = "";
    public static final String PLUSE = "";

    //unit
    public static final String DO_CELSIUS = "°C";
    public static final String PPM = "PPM";
    public static final String DAY = "Day";
    public static final String WEEK = "WE";
    public static final String STATE_NULL = "";
    public static final String PERCENT = "%";
    public static final String LUX = "LUX";
    public static final String PAS = "PAS";
    public static final String KG = "kg";

    public static final int ON = 1;

    //henhouseId
    public static final String HEN_HOUSE_ID = "2";

    //MainActivity to CalendarActivity
    public static final String LIST_ITEM_HOUSE_CHICKEN = "list_item_house_chicken";
    public static final String BUNDLE = "bundle";
    public static final String FEEDING_SCHEDULE = "feeding_schedule";
    public static final String COOLING_SCHEDULE = "cooling_schedule";
    public static final String LIGHT_SCHEDULE = "light_schedule";
    public static final String DRUG_SCHEDULE = "drug_schedule";

    public static final String TIME_LIST = "timeList";

    //ID_THUY_CANH
    public static final long ID_THUY_CANH_1 = 41;
    public static final long ID_DUA_LUOI_1 = 34;
    public static final long ID_DUA_LUOI_2 = 42;
    public static final long ID_DUA_LUOI_3 = 43;
    public static final long ID_DUA_LUOI_4 = 44;
    public static final String CODE_THUY_CANH = "TC";
    public static final String CODE_DUA_LUOI = "DL";

    //1: bơm, 2:van, 3:QUẠT, 4: ĐÈN, 5: SENSOR, 6: gw, 7: camera, 8: bồn, 9: Làm mát

    //status
    public static final Integer YES = 1;
    public static final Integer NO = 2;

    //water
    public static final String UNIT_WATER = "L";
    public static final String SPEED_UNIT_WATER = "L/min";
    public static final String HARD_CODE_HOUSE_CHICKEN_2 = "HARD_CODE_HOUSE_CHICKEN_2";
}
