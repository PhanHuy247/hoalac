package com.vnpt.iot.hoalac.core.model;

import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Parse {
    //create an object of SingleObject
    private static Parse instance = new Parse();

    //make the constructor private so that this class cannot be
    //instantiated
    private Parse() {
    }

    //Get the only object available
    public static Parse getInstance() {
        return instance;
    }



    public List<Device> deviceList(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<Device> deviceList = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    Device device = gson.fromJson(jsAeList, Device.class);
                    deviceList.add(device);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return deviceList;
    }

    public ArrayList<SendAlarmHistory> alarmList(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        ArrayList<SendAlarmHistory> alarms = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    SendAlarmHistory alarm = gson.fromJson(jsAeList, SendAlarmHistory.class);
                    alarms.add(alarm);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return alarms;
    }

    public List<Camera> camera(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<Camera> cameras = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    Camera camera = gson.fromJson(jsAeList, Camera.class);
                    cameras.add(camera);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return cameras;
    }

    public List<DeviceList> getDeviceList(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<DeviceList> deviceList = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    DeviceList device = gson.fromJson(jsAeList, DeviceList.class);
                    deviceList.add(device);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return deviceList;
    }

    public List<AssetImport> assetImport(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<AssetImport> assetImports = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    AssetImport assetImport = gson.fromJson(jsAeList, AssetImport.class);
                    assetImports.add(assetImport);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return assetImports;
    }

    public List<AquaticConfiguration> aquaticConfiguration(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<AquaticConfiguration> aquaticConfigurations = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    AquaticConfiguration aquaticConfiguration = gson.fromJson(jsAeList, AquaticConfiguration.class);
                    aquaticConfigurations.add(aquaticConfiguration);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return aquaticConfigurations;
    }

    public List<ScheduleListApp> scheduleList(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<ScheduleListApp> controlLists = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    ScheduleListApp controlList = gson.fromJson(jsAeList, ScheduleListApp.class);
                    controlLists.add(controlList);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return controlLists;
    }

    public List<AssetExport> assetExport(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<AssetExport> assetExports = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    AssetExport assetExport = gson.fromJson(jsAeList, AssetExport.class);
                    assetExports.add(assetExport);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return assetExports;
    }

    public List<Journal> journal(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<Journal> journals = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    Journal journal = gson.fromJson(jsAeList, Journal.class);
                    journals.add(journal);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return journals;
    }

    public List<Category> category(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<Category> categories = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    Category category = gson.fromJson(jsAeList, Category.class);
                    categories.add(category);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return categories;
    }

    public List<Product> product(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<Product> products = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    Product product = gson.fromJson(jsAeList, Product.class);
                    products.add(product);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return products;
    }

    public List<Farm> getListFarm(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<Farm> farms = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    Farm farm = gson.fromJson(jsAeList, Farm.class);
                    farms.add(farm);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return farms;
    }

    public List<Procedure> getListProcedure(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<Procedure> procedures = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    Procedure procedure = gson.fromJson(jsAeList, Procedure.class);
                    procedures.add(procedure);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return procedures;
    }

    public List<Phase> getListPhase(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<Phase> phases = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    Phase phase = gson.fromJson(jsAeList, Phase.class);
                    phases.add(phase);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return phases;
    }

    public List<GreenHouse> getListGreenHouse(ApiResponse apiResponse) {
        JSONObject jsonObject = apiResponse.get_jsonObject();
        Gson gson = new Gson();
        List<GreenHouse> greenHouses = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            if (jsonArray != null && jsonArray.length() > 0)
                for (int idx = 0; idx < jsonArray.length(); idx++) {
                    String jsAeList = jsonArray.getString(idx);
                    GreenHouse greenHouse = gson.fromJson(jsAeList, GreenHouse.class);
                    greenHouses.add(greenHouse);
                }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return greenHouses;
    }


}
