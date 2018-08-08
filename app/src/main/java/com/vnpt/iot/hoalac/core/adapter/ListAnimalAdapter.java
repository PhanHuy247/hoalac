package com.vnpt.iot.hoalac.core.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.vnpt.iot.hoalac.core.common.Utils;
import com.vnpt.iot.hoalac.core.model.FeatureHouseChicken;
import com.vnpt.iot.hoalac.core.model.HouseChicken;

import java.util.List;

public class ListAnimalAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<HouseChicken> houseChickens;
    private HouseChicken houseChicken;

    public ListAnimalAdapter(Activity activity, List<HouseChicken> houseChickens) {
        this.activity = activity;
        this.houseChickens = houseChickens;
    }

    @Override
    public int getCount() {
        return houseChickens.size();
    }

    @Override
    public Object getItem(int position) {
        return houseChickens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        houseChicken = houseChickens.get(position);

        if (houseChicken.getHeader()) {
            convertView = inflater.inflate(R.layout.item_header_list_animal, null);
            TextView txtHeader = (TextView) convertView.findViewById(R.id.txtHeader);

            txtHeader.setText(houseChicken.getGroupObject());
        } else if (houseChicken.getDisplay().equals(Constatnts.CALENDAR_AUTO)) {
            convertView = inflater.inflate(R.layout.item_calendar_auto_list_animal, null);
            TextView txtAutoCalendar = (TextView) convertView.findViewById(R.id.txtAutoCalendar);
            displayCalendarAuto(houseChicken,txtAutoCalendar);
        } else {
            convertView = inflater.inflate(R.layout.item_list_animal, null);
            TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
            TextView txtValue = (TextView) convertView.findViewById(R.id.txtValue);
            TextView txtValueOnOff = (TextView) convertView.findViewById(R.id.txtValueOnOff);
            TextView txtValueOverLoad = (TextView) convertView.findViewById(R.id.txtValueOverLoad);
            TextView txtValueWarning = (TextView) convertView.findViewById(R.id.txtValueWarning);
            TextView txtValueError = (TextView) convertView.findViewById(R.id.txtValueError);
            TextView txtUnit = (TextView) convertView.findViewById(R.id.txtUnit);
            ImageView imgValue = (ImageView) convertView.findViewById(R.id.imgValue);
            ImageView imgOverload = (ImageView) convertView.findViewById(R.id.imgOverLoad);
            ImageView imgWarning = (ImageView) convertView.findViewById(R.id.imgWarning);
            ImageView imgError = (ImageView) convertView.findViewById(R.id.imgError);
//            changeSizeTextTablet(txtName,txtValue,txtValueOnOff,txtValueOverLoad,txtValueWarning,txtUnit,imgValue,imgOverload,imgWarning,imgError);

            switch (houseChicken.getDisplay()) {
                case Constatnts.OVERVIEW:
                case Constatnts.FOLLOW:
                    displayOverview(houseChicken, txtName, txtValue, txtUnit);
                    break;
                case Constatnts.WARNING_OVERVIEW:
                    displayOverviewWarning(houseChicken, txtName, txtUnit, imgValue);
                    break;
                case Constatnts.COOLING_SYSTEM:
                    String groupObject = houseChicken.getGroupObject();
                    if (groupObject.equals(activity.getString(R.string.QUAT_KHI_TUOI)) || groupObject.equals(activity.getString(R.string.QUAT_THONG_GIO_NGANG))
                            || groupObject.equals(activity.getString(R.string.QUAT_HUT_TRAN)) || groupObject.equals(activity.getString(R.string.CUA_LAY_KHI))) {
                        displayCoolingSystem(houseChicken, txtName, txtUnit, txtValueOnOff, txtValueOverLoad, txtValueWarning, imgValue, imgOverload, imgWarning);
                    } else if (groupObject.equals(activity.getString(R.string.QUAT_THONG_GIO))) {
                        displayCoolingSystemVentilator(houseChicken, txtName, txtUnit, imgValue, imgOverload, imgWarning, imgError);
                    } else if (groupObject.equals(activity.getString(R.string.QUAT_HUT_MAI))) {
                        displayCoolingSystemRoofFans(houseChicken, txtName, txtUnit, imgValue, imgOverload, imgWarning);
                    } else if (groupObject.equals(activity.getString(R.string.MAY_BOM))) {
                        displayCoolingSystemPump(houseChicken, txtName, txtUnit, imgValue, imgOverload, imgWarning);
                    }
                    break;
                case Constatnts.FOOD:
                    displayFood(houseChicken, txtName, txtUnit, imgValue, imgOverload);
                    break;
                case Constatnts.WATER:
                    displayWater(houseChicken, txtName, txtUnit, txtValue, imgValue);
                    break;
                case Constatnts.EGG_COLLECT:
                    displayCollect(houseChicken, txtName, txtUnit, imgValue, imgOverload);
                    break;
                case Constatnts.DON_PHAN:
                    displayClearDung(houseChicken, txtName, txtUnit, imgValue, imgOverload);
                    break;
                case Constatnts.AUTO_MAN:
                    displayAutoMan(houseChicken, txtName, txtUnit, imgValue, imgOverload);
                    break;
                case Constatnts.SYSTEM:
                    displaySystem(houseChicken, txtName, txtUnit, imgValue, imgOverload);
                    break;
            }
        }

        return convertView;
    }

    private void changeSizeTextTablet(TextView txtName, TextView txtValue, TextView txtValueOnOff, TextView txtValueOverLoad, TextView txtValueWarning, TextView txtUnit, ImageView imgValue, ImageView imgOverload, ImageView imgWarning, ImageView imgError) {
                if(Utils.checkTablet(activity)){
            txtName.setTextSize(20);
            txtValue.setTextSize(20);
            txtValueOnOff.setTextSize(20);
            txtValueOverLoad.setTextSize(20);
            txtValueWarning.setTextSize(20);
            txtUnit.setTextSize(20);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,10,10,10);
        }
    }


    private void displayCalendarAuto(HouseChicken houseChicken, TextView txtAutoCalendar) {
        List<FeatureHouseChicken> featureHouseChickenList = houseChicken.getFeatureHouseChickenList();
        FeatureHouseChicken houseChickenValue = featureHouseChickenList.get(0);
        txtAutoCalendar.setText(houseChickenValue.getValue());
    }

    private void displaySystem(HouseChicken houseChicken, TextView txtName, TextView txtUnit
            , ImageView imgValue, ImageView imgOverload) {
        List<FeatureHouseChicken> featureHouseChickenList = houseChicken.getFeatureHouseChickenList();
        FeatureHouseChicken houseChickenOnOff = featureHouseChickenList.get(0);
        FeatureHouseChicken houseChickenOverLoad = null;
        if (featureHouseChickenList.size() > 1) {
            houseChickenOverLoad = featureHouseChickenList.get(1);
        }

        int isValueOnOff = 0;
        if (houseChickenOnOff.getValue() != null) {
            isValueOnOff = Integer.valueOf(houseChickenOnOff.getValue());
        }
        int isValueOverLoad = 0;
        if (houseChickenOverLoad != null) {
            isValueOverLoad = Integer.valueOf(houseChickenOverLoad.getValue());
        }
        txtName.setText(houseChickenOnOff.getName());

        if (isValueOnOff == Constatnts.ON) {
            imgValue.setImageResource(R.mipmap.turn_on_ic);
            txtUnit.setText(activity.getString(R.string.SWITCH_ON));
        } else {
            imgValue.setImageResource(R.mipmap.ic_turn_off);
            txtUnit.setText(activity.getString(R.string.SWITCH_OFF));
        }
        if (isValueOverLoad == Constatnts.ON) {
            imgOverload.setImageResource(R.drawable.circle_shape_overload);
        }
    }

    private void displayAutoMan(HouseChicken houseChicken, TextView txtName, TextView txtUnit
            , ImageView imgValue, ImageView imgOverload) {
        List<FeatureHouseChicken> featureHouseChickenList = houseChicken.getFeatureHouseChickenList();
        FeatureHouseChicken houseChickenOnOff = featureHouseChickenList.get(0);
        FeatureHouseChicken houseChickenOverLoad = null;
        if (featureHouseChickenList.size() > 1) {
            houseChickenOverLoad = featureHouseChickenList.get(1);
        }

        int isValueOnOff = 0;
        if (houseChickenOnOff.getValue() != null) {
            isValueOnOff = Integer.valueOf(houseChickenOnOff.getValue());
        }
        int isValueOverLoad = 0;
        if (houseChickenOverLoad != null) {
            isValueOverLoad = Integer.valueOf(houseChickenOverLoad.getValue());
        }
        txtName.setText(houseChickenOnOff.getName());

        if (isValueOnOff == Constatnts.ON) {
            imgValue.setImageResource(R.mipmap.turn_on_ic);
            txtUnit.setText(activity.getString(R.string.AUTO));
        } else {
            imgValue.setImageResource(R.mipmap.ic_turn_off);
            txtUnit.setText(activity.getString(R.string.MAKE_HAND));
        }
        if (isValueOverLoad == Constatnts.ON) {
            imgOverload.setImageResource(R.drawable.circle_shape_overload);
        }
    }

    private void displayClearDung(HouseChicken houseChicken, TextView txtName, TextView txtUnit
            , ImageView imgValue, ImageView imgOverload) {
        List<FeatureHouseChicken> featureHouseChickenList = houseChicken.getFeatureHouseChickenList();
        FeatureHouseChicken houseChickenOnOff = featureHouseChickenList.get(0);
        FeatureHouseChicken houseChickenOverLoad = null;
        if (featureHouseChickenList.size() > 1) {
            houseChickenOverLoad = featureHouseChickenList.get(1);
        }

        int isValueOnOff = 0;
        if (houseChickenOnOff.getValue() != null) {
            isValueOnOff = Integer.valueOf(houseChickenOnOff.getValue());
        }
        int isValueOverLoad = 0;
        if (houseChickenOverLoad != null) {
            isValueOverLoad = Integer.valueOf(houseChickenOverLoad.getValue());
        }
        txtName.setText(houseChickenOnOff.getName());

        if (isValueOnOff == Constatnts.ON) {
            imgValue.setImageResource(R.mipmap.turn_on_ic);
            txtUnit.setText(activity.getString(R.string.SWITCH_ON));
        } else {
            imgValue.setImageResource(R.mipmap.ic_turn_off);
            txtUnit.setText(activity.getString(R.string.SWITCH_OFF));
        }
        if (isValueOverLoad == Constatnts.ON) {
            imgOverload.setImageResource(R.drawable.circle_shape_overload);
        }
    }

    private void displayCollect(HouseChicken houseChicken, TextView txtName, TextView txtUnit
            , ImageView imgValue, ImageView imgOverload) {
        List<FeatureHouseChicken> featureHouseChickenList = houseChicken.getFeatureHouseChickenList();
        FeatureHouseChicken houseChickenOnOff = featureHouseChickenList.get(0);
        FeatureHouseChicken houseChickenOverLoad = null;
        if (featureHouseChickenList.size() > 1) {
            houseChickenOverLoad = featureHouseChickenList.get(1);
        }

        int isValueOnOff = 0;
        if (houseChickenOnOff.getValue() != null) {
            isValueOnOff = Integer.valueOf(houseChickenOnOff.getValue());
        }
        int isValueOverLoad = 0;
        if (houseChickenOverLoad != null) {
            isValueOverLoad = Integer.valueOf(houseChickenOverLoad.getValue());
        }
        txtName.setText(houseChickenOnOff.getName());

        if (isValueOnOff == Constatnts.ON) {
            imgValue.setImageResource(R.mipmap.turn_on_ic);
            txtUnit.setText(activity.getString(R.string.SWITCH_ON));
        } else {
            imgValue.setImageResource(R.mipmap.ic_turn_off);
            txtUnit.setText(activity.getString(R.string.SWITCH_OFF));
        }
        if (isValueOverLoad == Constatnts.ON) {
            imgOverload.setImageResource(R.drawable.circle_shape_overload);
        }
    }

    private void displayWater(HouseChicken houseChicken, TextView txtName, TextView txtUnit
            , TextView txtValue, ImageView imgValue) {
        List<FeatureHouseChicken> featureHouseChickenList = houseChicken.getFeatureHouseChickenList();
        FeatureHouseChicken houseChickenOnOff = featureHouseChickenList.get(0);
        String name = houseChickenOnOff.getName();
        txtName.setText(name);
        String valueWater = houseChickenOnOff.getValue();
        String unitWater = houseChickenOnOff.getUnit();
        if (name.equals(activity.getString(R.string.VAN_TOC_CAP_NUOC)) || name.equals(activity.getString(R.string.LUONG_NUOC_DINH_MUC_TRONG_NGAY))) {
            txtValue.setText(valueWater);
            txtUnit.setText(unitWater);
            return;
        } else if (name.equals(activity.getString(R.string.TONG_LUONG_NUOC_GA_UONG)) || name.equals(activity.getString(R.string.TONG_LUONG_NUOC_CAP))) {
            txtValue.setText(valueWater);
            txtUnit.setText(unitWater);
            return;
        }

        int isValueOnOff = 0;
        if (houseChickenOnOff.getValue() != null) {
            isValueOnOff = Integer.valueOf(houseChickenOnOff.getValue());
        }
        if (name.equals(activity.getString(R.string.BE_CAP_NUOC))) {
            if (isValueOnOff == Constatnts.ON) {
                txtUnit.setText(activity.getString(R.string.DAY_BE));
            } else {
                txtUnit.setText(activity.getString(R.string.CAN));
            }
        } else if (name.equals(activity.getString(R.string.HE_THONG_LAY_NUOC))) {
            if (isValueOnOff == Constatnts.ON) {
                imgValue.setImageResource(R.mipmap.turn_on_ic);
                txtUnit.setText(activity.getString(R.string.SWITCH_ON));
            } else {
                imgValue.setImageResource(R.mipmap.ic_turn_off);
                txtUnit.setText(activity.getString(R.string.SWITCH_OFF));
            }
        } else if (name.equals(activity.getString(R.string.BO_BAO_NUOC))) {
            if (isValueOnOff == Constatnts.ON) {
                txtUnit.setText(activity.getString(R.string.MAT_NUOC));
            } else {
                txtUnit.setText(activity.getString(R.string.CO_NUOC));
            }
        }

    }

    private void displayFood(HouseChicken houseChicken, TextView txtName, TextView txtUnit
            , ImageView imgValue, ImageView imgOverload) {
        List<FeatureHouseChicken> featureHouseChickenList = houseChicken.getFeatureHouseChickenList();
        FeatureHouseChicken houseChickenOnOff = featureHouseChickenList.get(0);
        FeatureHouseChicken houseChickenOverLoad = null;
        if (featureHouseChickenList.size() > 1) {
            houseChickenOverLoad = featureHouseChickenList.get(1);
        }

        int isValueOnOff = 0;
        if (houseChickenOnOff.getValue() != null) {
            isValueOnOff = Integer.valueOf(houseChickenOnOff.getValue());
        }
        int isValueOverLoad = 0;
        if (houseChickenOverLoad != null) {
            isValueOverLoad = Integer.valueOf(houseChickenOverLoad.getValue());
        }
        txtName.setText(houseChickenOnOff.getName());

        if (isValueOnOff == Constatnts.ON) {
            imgValue.setImageResource(R.mipmap.turn_on_ic);
            txtUnit.setText(activity.getString(R.string.SWITCH_ON));
        } else {
            imgValue.setImageResource(R.mipmap.ic_turn_off);
            txtUnit.setText(activity.getString(R.string.SWITCH_OFF));
        }
        if (isValueOverLoad == Constatnts.ON) {
            imgOverload.setImageResource(R.drawable.circle_shape_overload);
        }
    }

    private void displayCoolingSystemPump(HouseChicken houseChicken, TextView txtName, TextView txtUnit
            , ImageView imgValue, ImageView imgOverload, ImageView imgWarning) {
        List<FeatureHouseChicken> featureHouseChickenList = houseChicken.getFeatureHouseChickenList();
        FeatureHouseChicken houseChickenOnOff = featureHouseChickenList.get(0);
        FeatureHouseChicken houseChickenOverLoad = featureHouseChickenList.get(1);
        FeatureHouseChicken houseChickenError = null;
        if (featureHouseChickenList.size() > 2) {
            houseChickenError = featureHouseChickenList.get(2);
        }

        int isValueOnOff = 0;
        if (houseChickenOnOff.getValue() != null) {
            isValueOnOff = Integer.valueOf(houseChickenOnOff.getValue());
        }
        int isValueOverLoad = Integer.valueOf(houseChickenOverLoad.getValue());
        int isValueError = 0;
        if (houseChickenError != null) {
            isValueError = Integer.valueOf(houseChickenError.getValue());
        }
        txtName.setText(houseChickenOnOff.getName());

        if (isValueOnOff == Constatnts.ON) {
            imgValue.setImageResource(R.mipmap.turn_on_ic);
            txtUnit.setText(activity.getString(R.string.SWITCH_ON));
        } else {
            imgValue.setImageResource(R.mipmap.ic_turn_off);
            txtUnit.setText(activity.getString(R.string.SWITCH_OFF));
        }
        if (isValueOverLoad == Constatnts.ON) {
            imgOverload.setImageResource(R.drawable.circle_shape_overload);
            if (isValueError == Constatnts.ON) {
                imgWarning.setImageResource(R.drawable.circle_shape_error);
            }
        } else {
            if (isValueError == Constatnts.ON) {
                imgOverload.setImageResource(R.drawable.circle_shape_error);
            }
        }

    }

    private void displayCoolingSystemRoofFans(HouseChicken houseChicken, TextView txtName, TextView txtUnit
            , ImageView imgValue, ImageView imgOverload, ImageView imgWarning) {
        List<FeatureHouseChicken> featureHouseChickenList = houseChicken.getFeatureHouseChickenList();
        FeatureHouseChicken houseChickenOnOff = featureHouseChickenList.get(0);
        FeatureHouseChicken houseChickenOverLoad = featureHouseChickenList.get(1);
        FeatureHouseChicken houseChickenError = featureHouseChickenList.get(2);
        int isValueOnOff = 0;
        if (houseChickenOnOff.getValue() != null) {
            isValueOnOff = Integer.valueOf(houseChickenOnOff.getValue());
        }

        int isValueOverLoad = Integer.valueOf(houseChickenOverLoad.getValue());
        int isValueError = Integer.valueOf(houseChickenError.getValue());
        txtName.setText(houseChickenOnOff.getName());

        if (isValueOnOff == Constatnts.ON) {
            imgValue.setImageResource(R.mipmap.turn_on_ic);
            txtUnit.setText(activity.getString(R.string.SWITCH_ON));
        } else {
            imgValue.setImageResource(R.mipmap.ic_turn_off);
            txtUnit.setText(activity.getString(R.string.SWITCH_OFF));
        }
        if (isValueOverLoad == Constatnts.ON) {
            imgOverload.setImageResource(R.drawable.circle_shape_overload);
            if (isValueError == Constatnts.ON) {
                imgWarning.setImageResource(R.drawable.circle_shape_error);
            }
        } else {
            if (isValueError == Constatnts.ON) {
                imgOverload.setImageResource(R.drawable.circle_shape_error);
            }
        }

    }

    private void displayCoolingSystemVentilator(HouseChicken houseChicken, TextView txtName, TextView txtUnit,
                                                ImageView imgValue, ImageView imgOverload, ImageView imgWarning, ImageView imgError) {
        List<FeatureHouseChicken> featureHouseChickenList = houseChicken.getFeatureHouseChickenList();
        FeatureHouseChicken houseChickenOnOff = featureHouseChickenList.get(0);
        FeatureHouseChicken houseChickenOverLoad = featureHouseChickenList.get(1);
        FeatureHouseChicken houseChickenError = featureHouseChickenList.get(2);
        FeatureHouseChicken houseChickenEmergency = featureHouseChickenList.get(3);

        int isValueOnOff = 0;
        if (houseChickenOnOff.getValue() != null) {
            isValueOnOff = Integer.valueOf(houseChickenOnOff.getValue());
        }
        int isValueOverLoad = Integer.valueOf(houseChickenOverLoad.getValue());
        int isValueError = Integer.valueOf(houseChickenError.getValue());
        int isValueEmergency = Integer.valueOf(houseChickenEmergency.getValue());
        txtName.setText(houseChickenOnOff.getName());
        if (isValueOnOff == Constatnts.ON) {
            imgValue.setImageResource(R.mipmap.turn_on_ic);
            txtUnit.setText(activity.getString(R.string.SWITCH_ON));
        } else {
            imgValue.setImageResource(R.mipmap.ic_turn_off);
            txtUnit.setText(activity.getString(R.string.SWITCH_OFF));
        }
        if (isValueOverLoad == Constatnts.ON) {
            imgOverload.setImageResource(R.drawable.circle_shape_overload);
            if (isValueEmergency == Constatnts.ON) {
                imgWarning.setImageResource(R.drawable.circle_shape_warning);
                if (isValueError == Constatnts.ON) {
                    imgError.setImageResource(R.drawable.circle_shape_error);
                }
            } else {
                if (isValueError == Constatnts.ON) {
                    imgWarning.setImageResource(R.drawable.circle_shape_error);
                }
            }
        } else {
            if (isValueEmergency == Constatnts.ON) {
                imgOverload.setImageResource(R.drawable.circle_shape_warning);
                if (isValueError == Constatnts.ON) {
                    imgWarning.setImageResource(R.drawable.circle_shape_error);
                }
            } else {
                if (isValueError == Constatnts.ON) {
                    imgOverload.setImageResource(R.drawable.circle_shape_error);
                }
            }
        }

    }

    private void displayCoolingSystem(HouseChicken houseChicken, TextView txtName, TextView txtUnit, TextView txtValueOnOff, TextView txtValueOverLoad,
                                      TextView txtValueWarning, ImageView imgValue, ImageView imgOverload, ImageView imgWarning) {
        List<FeatureHouseChicken> featureHouseChickenList = houseChicken.getFeatureHouseChickenList();
        FeatureHouseChicken houseChickenOnOff = featureHouseChickenList.get(0);
        FeatureHouseChicken houseChickenOverLoad = featureHouseChickenList.get(1);
        FeatureHouseChicken houseChickenError = featureHouseChickenList.get(2);
        FeatureHouseChicken houseChickenSpeed = featureHouseChickenList.get(3);

        int isValueOnOff = 0;
        if (houseChickenOnOff.getValue() != null) {
            isValueOnOff = Integer.valueOf(houseChickenOnOff.getValue());
        }
        int isValueOverLoad = Integer.valueOf(houseChickenOverLoad.getValue());
        int isValueError = Integer.valueOf(houseChickenError.getValue());
        String valueSpeed = houseChickenSpeed.getValue();
        String unit = houseChickenSpeed.getUnit();
        if (unit == null || unit.isEmpty()) {
            txtName.setText(houseChickenOnOff.getName());
        } else {
            txtName.setText(houseChickenOnOff.getName() + " (" + unit + ")");
        }

        if (isValueOnOff == Constatnts.ON) {
            imgValue.setImageResource(R.mipmap.turn_on_ic);
            txtUnit.setText(activity.getString(R.string.SWITCH_ON));
        } else {
            imgValue.setImageResource(R.mipmap.ic_turn_off);
            txtUnit.setText(activity.getString(R.string.SWITCH_OFF));
        }
        if (isValueOverLoad == Constatnts.ON) {
            imgOverload.setImageResource(R.drawable.circle_shape_overload);
            if (isValueError == Constatnts.ON) {
                imgWarning.setImageResource(R.drawable.circle_shape_error);
                txtValueWarning.setText(valueSpeed);
            } else {
                txtValueOverLoad.setText(valueSpeed);
            }
        } else {
            if (isValueError == Constatnts.ON) {
                imgOverload.setImageResource(R.drawable.circle_shape_error);
                txtValueOverLoad.setText(valueSpeed);
            } else {
                txtValueOnOff.setText(valueSpeed);
            }
        }

    }

    private void displayOverviewWarning(HouseChicken houseChicken, TextView txtName, TextView txtUnit, ImageView imgValue) {
        List<FeatureHouseChicken> featureHouseChickenList = houseChicken.getFeatureHouseChickenList();
        int isValueOpenDoor = 0;
        if (featureHouseChickenList != null && !featureHouseChickenList.isEmpty()) {
            isValueOpenDoor = Integer.valueOf(featureHouseChickenList.get(0).getValue());
        }
        txtName.setText(featureHouseChickenList.get(0).getName());
        if (isValueOpenDoor == Constatnts.ON) {
            imgValue.setImageResource(R.mipmap.turn_on_ic);
            txtUnit.setText(activity.getString(R.string.OPEN));
        } else {
            imgValue.setImageResource(R.mipmap.ic_turn_off);
            txtUnit.setText(activity.getString(R.string.CLOSE));
        }
    }

    private void displayOverview(HouseChicken houseChicken, TextView txtName, TextView txtValue, TextView txtUnit) {
        List<FeatureHouseChicken> featureHouseChickenList = houseChicken.getFeatureHouseChickenList();
        txtName.setText(featureHouseChickenList.get(0).getName());
        txtValue.setText(featureHouseChickenList.get(0).getValue());
        txtUnit.setText(featureHouseChickenList.get(0).getUnit());
    }

    static class ViewHolder {
        TextView txtName;
        TextView txtValue;
        TextView txtUnit;
        ImageView imgValue;

        public ViewHolder(TextView txtName, TextView txtValue, TextView txtUnit) {
            this.txtName = txtName;
            this.txtValue = txtValue;
            this.txtUnit = txtUnit;
        }
    }

    public void updateList(List<HouseChicken> houseChickens) {
        this.houseChickens = houseChickens;
        notifyDataSetChanged();
    }

    public void clearData() {
        // clear the data
        houseChickens.clear();
        notifyDataSetChanged();
    }
}
