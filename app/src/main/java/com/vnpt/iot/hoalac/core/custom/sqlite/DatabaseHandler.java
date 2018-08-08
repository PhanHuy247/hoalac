package com.vnpt.iot.hoalac.core.custom.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vnpt.iot.hoalac.core.model.Device;
import com.vnpt.iot.hoalac.core.model.DeviceSchedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thohc on 5/11/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "organic4u";
    private static final String TABLE_DEVICE = "device";
    private static final String TABLE_DEVICE_SCHEDULE = "deviceSchedule";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_DEVICE + "(id INTEGER PRIMARY KEY, deviceId TEXT, name TEXT, scpId INTEGER, farmId INTEGER, status INTEGER, parentId INTEGER )");
        db.execSQL("CREATE TABLE " + TABLE_DEVICE_SCHEDULE + "(id INTEGER PRIMARY KEY, deviceId TEXT, scpId INTEGER, status INTEGER, type INTEGER, conditionType INTEGER, value1 INTEGER, value2 INTEGER, sensorId TEXT, userId INTEGER )");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEVICE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEVICE_SCHEDULE);
        // Create tables again
        onCreate(db);
    }

    public void addListDevice(List<Device> devices) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (devices.size() == 0) return;
        for (Device device : devices) {
            ContentValues values = new ContentValues();
            values.put("id", device.getId());
            values.put("deviceId", device.getDeviceId());
            values.put("name", device.getName());
            values.put("scpId", device.getScpId());
            values.put("farmId", device.getFarmId());
            values.put("status", device.getStatus());
            values.put("parentId", device.getParentId());
            db.insert(TABLE_DEVICE, null, values);
        }
        db.close();
    }

    public List<Device> getAllDevice() {
        List<Device> devices = new ArrayList<Device>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DEVICE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Device device = new Device();
                device.setId(Long.valueOf(cursor.getString(0)));
                device.setDeviceId(cursor.getString(1));
                device.setName(cursor.getString(2));
                device.setScpId(Long.valueOf(cursor.getString(3)));
                device.setFarmId(Long.valueOf(cursor.getString(4)));
                device.setStatus(Integer.valueOf(cursor.getString(5)));
                device.setFarmId(Long.valueOf(cursor.getString(6)));
                // Adding contact to list
                devices.add(device);
            } while (cursor.moveToNext());
        }
        return devices;
    }

    public DeviceSchedule getDeviceScheduleByDevice(String deviceId){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DEVICE_SCHEDULE, new String[] {"id", "deviceId", "scpId", "status", "type", "conditionType", "value1", "value2", "sensorId", "userId"}, "deviceId = ?", new String[]{deviceId}, null, null, null, null);
        DeviceSchedule deviceSchedule = null;
        if (cursor != null) {
            cursor.moveToFirst();
            deviceSchedule = new DeviceSchedule(cursor.getLong(0), cursor.getString(1), cursor.getLong(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getLong(6), cursor.getLong(7), cursor.getString(8), cursor.getLong(9));
        }

        return deviceSchedule;
    }
}
