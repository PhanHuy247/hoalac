package com.vnpt.iot.hoalac.core.common;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.model.CmdDevice;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static void toast(Context c, String text){
        Toast.makeText(c, text, Toast.LENGTH_SHORT).show();
    }

    public static ArrayList<String> languageApp(){
        ArrayList<String> listName = new ArrayList<>();
        listName.add("vi");
        listName.add("en");

        return listName;
    }

    public static String getCriteria(Activity activity, String key) {
        String text = "";
        switch (key) {
            case "1":
                text = activity.getResources().getString(R.string.hour);
                break;
            case "air_temperature":
                text = activity.getResources().getString(R.string.airTemperature);
                break;
            case "air_moisture":
                text = activity.getResources().getString(R.string.airMoisture);
                break;
            case "soil_temperature":
                text = activity.getResources().getString(R.string.soilTemperature);
                break;
            case "soil_moisture":
                text = activity.getResources().getString(R.string.soilMoisture);
                break;
        }
        return text;
    }

    public static String typeAlarm(Activity activity, byte type) {
        String data = "";
        switch (type) {
            case 1:
                data = activity.getResources().getString(R.string.soilpH);
                break;
            case 2:
                data = activity.getResources().getString(R.string.soilEC);
                break;
            case 3:
                data = activity.getResources().getString(R.string.soilMoisture);
                break;
            case 4:
                data = activity.getResources().getString(R.string.environmentTemperature);
                break;
            case 5:
                data = activity.getResources().getString(R.string.environmentHumidity);
                break;
            case 6:
                data = activity.getResources().getString(R.string.tankEC);
                break;
            case 7:
                data = activity.getResources().getString(R.string.tankPH);
                break;
            case 8:
                data = activity.getResources().getString(R.string.tankTemperature);
                break;
            case 9:
                data = activity.getResources().getString(R.string.tankFullEmpty);
                break;
        }
        return data;
    }

    public static String unitAlarm(byte type, int typeSector) {
        String data = "";
        switch (type) {
            case 2:
                data = "ms/cm";
                break;
            case 3:
                data = "%";
                break;
            case 4:
                data = "°C";
                break;
            case 5:
                data = "%";
                break;
            case 6:
                if (typeSector == 1) {
                    data = "Ds";
                } else {
                    data = "ms/cm";
                }

                break;
            case 8:
                data = "°C";
                break;
        }
        return data;
    }


    public static List<CmdDevice> getListSubDevice(Activity activity,String gatewayId,String parentId, int type, int typeSector){
        String deviceId = parentId.replace(gatewayId, "");
        List<CmdDevice> cmdDevices = new ArrayList<>();
        switch (type) {
            case Constatnts.TANK:
                if (typeSector == Constatnts.SECTOR_DUALUOI) {
                    cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.cleanWaterPump), parentId, "fresh_water_pump", deviceId+"FreshWaterPumpTurnOn", deviceId+"FreshWaterPumpTurnOff"));
                    cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.cleanWaterValve), parentId, "water_recharge_valve", deviceId+"WaterRechargePumpTurnOn", deviceId+"WaterRechargePumpTurnOff"));
                    cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.venturi)+" A", parentId, "fertilizer_A",deviceId+"FertilizerAPumpTurnOn", deviceId+"FertilizerAPumpTurnOff"));
                    cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.venturi)+" B", parentId, "fertilizer_B",deviceId+"FertilizerBPumpTurnOn", deviceId+"FertilizerBPumpTurnOff"));
                    cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.aerationMachine), parentId, "aerator_status", deviceId+"AeratorPumpTurnOn", deviceId+"AeratorPumpTurnOff"));
                    cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.nutritionPump), parentId, "ec_pump",deviceId+"ECWaterPumpTurnOn", deviceId+"ECWaterPumpTurnOff"));
                }

                if (typeSector == Constatnts.SECTOR_THUYCANH) {
                    cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.cleanWaterPump), parentId, "water_recharge_valve", deviceId+"FreshWaterValveTurnOn", deviceId+"FreshWaterValveTurnOff"));
                    cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.venturi)+" A", parentId, "fertilizer_A", deviceId+"ECPumpATurnOn", deviceId+"ECPumpATurnOff"));
                    cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.venturi)+" B", parentId, "fertilizer_B", deviceId+"ECPumpBTurnOn", deviceId+"ECPumpBTurnOff"));
                    cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.aerationMachine), parentId, "aerator_status", deviceId+"AeratorTurnOn", deviceId+"AeratorTurnOff"));
                    cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.circulatingPump)+" 1", parentId, "circulator_pump1", deviceId+"CircularPump1TurnOn", deviceId+"CircularPump1TurnOff"));
                    cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.circulatingPump)+" 2", parentId, "circulator_pump2", deviceId+"CircularPump2TurnOn", deviceId+"CircularPump2TurnOff"));
                }

                break;
            case Constatnts.AIR_COOL:
                cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.blower), parentId, "fan1", deviceId+"FanTurnOn", deviceId+"FanTurnOff"));
                cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.mistSprinkle), parentId, "spray_pump", deviceId+"SprayTurnOn", deviceId+"SprayTurnOff"));
                if (typeSector == Constatnts.SECTOR_THUYCANH) {
                    cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.sunshadeNet), parentId, "sunshade", deviceId+"SunShadeOpen", deviceId+"SunShadeClose"));
                }
                break;
            case Constatnts.DEN:
                cmdDevices.add(new CmdDevice(activity.getResources().getString(R.string.light), parentId, "light", deviceId+"TurnOn", deviceId+"TurnOff"));
                break;
        }

        return cmdDevices;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static boolean checkTablet(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
        if (diagonalInches>=6.5){
            return true;
        }else{
            return false;
        }
    }

    public static boolean validateURI(String uri) {
        final URL url;
        try {
            url = new URL(uri);
        } catch (Exception e1) {
            return false;
        }
        return "http".equals(url.getProtocol()) || "https".equals(url.getProtocol());
    }
}
