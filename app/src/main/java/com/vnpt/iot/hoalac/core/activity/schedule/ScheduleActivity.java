package com.vnpt.iot.hoalac.core.activity.schedule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.ScheduleApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.activity.schedule.detail.ScheduleAquaponicDetailActivity;
import com.vnpt.iot.hoalac.core.activity.schedule.detail.ScheduleFogFanDetail;
import com.vnpt.iot.hoalac.core.activity.schedule.detail.ScheduleLightDetail;
import com.vnpt.iot.hoalac.core.activity.schedule.detail.SchedulePlanNursingActivity;
import com.vnpt.iot.hoalac.core.activity.schedule.detail.ScheduleSunshadeNetDetail;
import com.vnpt.iot.hoalac.core.activity.schedule.detail.ScheduleTankBasteActivity;
import com.vnpt.iot.hoalac.core.activity.schedule.detail.ScheduleTankBlendActivity;
import com.vnpt.iot.hoalac.core.adapter.ScheduleAdapter;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.common.Constatnts;
import com.vnpt.iot.hoalac.core.model.AquaticConfigurationDetail;
import com.vnpt.iot.hoalac.core.model.Farm;
import com.vnpt.iot.hoalac.core.model.FogFanConfigurationDetail;
import com.vnpt.iot.hoalac.core.model.GreenHouse;
import com.vnpt.iot.hoalac.core.model.Parse;
import com.vnpt.iot.hoalac.core.model.ScheduleListApp;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vnpt.iot.hoalac.R.string.confirmChangeStatus;

public class ScheduleActivity extends CommonActivity {
    @BindView(R.id.listSchedule) ListView listSchedule;
    private List<ScheduleListApp> controlLists;
    private ScheduleAdapter scheduleAdapter;
    private Integer TYPE_LUX_CUT = 3;
    private Integer TYPE_PLAN_NURSING = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        commonSetting();
        ButterKnife.bind(this);
        initList();
        loadData();
    }

    private void initList(){
        listSchedule.setDivider(null);
        listSchedule.setDividerHeight(0);
    }

    private void loadData(){
        Farm farm = AppSharedPreferences.getFarm(getApplicationContext());
        GreenHouse greenHouse = AppSharedPreferences.getGreenHouse(getApplicationContext());
        HashMap<String , Object> param = new HashMap<>();
        param.put("farmId", farm.getId());
        param.put("sectorId", greenHouse.getId());
        kProgressHUD.show();
        ScheduleApi.getList(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                controlLists = Parse.getInstance().scheduleList(result);
                scheduleAdapter = new ScheduleAdapter(ScheduleActivity.this, controlLists);
                listSchedule.setAdapter(scheduleAdapter);
//                scheduleAdapter.setOnCheckedSwitchButton(new ScheduleAdapter.OnCheckedSwitchButton() {
//                    @Override
//                    public void onChecked(int position, boolean isChecked) {
//                        ScheduleListApp controlList = controlLists.get(position);
//                        String status = isChecked ? "1" : "0";
//
//                        confirmChangeStatus(controlList, status, position);
//                    }
//                });

                scheduleAdapter.setSwOnClickItem(new ScheduleAdapter.SwOnClickItem() {
                    @Override
                    public void swOnClickItem(int position) {
                        System.out.println("Test DL: "+position);
                        confirmChangeStatus(position);
                    }
                });

                scheduleAdapter.setOnClickItem(new ScheduleAdapter.OnClickItem() {
                    @Override
                    public void onClickItem(int position) {
                        ScheduleListApp controlList = controlLists.get(position);
                        Intent intent = null;
                        switch (controlList.getTblName()) {
                            case Constatnts.SCHEDULE_LIGHT:
                                intent = new Intent(ScheduleActivity.this, ScheduleLightDetail.class);
                                break;
                            case Constatnts.SCHEDULE_TANK_BASTE:
                                intent = new Intent(ScheduleActivity.this, ScheduleTankBasteActivity.class);
                                break;
                            case Constatnts.SCHEDULE_TANK_BLEND:
                                intent = new Intent(ScheduleActivity.this, ScheduleTankBlendActivity.class);
                                break;
                            case Constatnts.SCHEDULE_FOG_FAN:
                                loadFogFan(controlList);
                                intent = null;
                                break;
                            case Constatnts.SCHEDULE_AQUAPONIC:
                                loadAqua(controlList);
                                break;

                        }
                        if (intent == null) return;

                        intent.putExtra("data", gson.toJson(controlList));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
            }
        });
    }

    private void loadAqua(ScheduleListApp scheduleListApp){
        HashMap<String, Object> param = new HashMap<>();
        param.put("id", scheduleListApp.getId());
        param.put("tblName", scheduleListApp.getTblName());
        kProgressHUD.show();
        ScheduleApi.getDetailFromListAllApp(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                AquaticConfigurationDetail aquaticConfigurationDetail = gson.fromJson(result.get_jsonObject().toString(), AquaticConfigurationDetail.class);
                Intent intent;
                if (aquaticConfigurationDetail.getType() == TYPE_PLAN_NURSING) {
                    intent = new Intent(ScheduleActivity.this, SchedulePlanNursingActivity.class);
                } else {
                    intent = new Intent(ScheduleActivity.this, ScheduleAquaponicDetailActivity.class);
                }
                intent.putExtra("data", gson.toJson(aquaticConfigurationDetail));
                startActivity(intent);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
            }
        });
    }

    private void confirmChangeStatus(int position){
        ScheduleListApp controlList = controlLists.get(position);
        byte status = 1;
        if (controlList.getStatus() == 1) status = 0;

        byte finalStatus = status;
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage(getStringLang(confirmChangeStatus))
                .setPositiveButton(getStringLang(R.string.no), null)
                .setNegativeButton(getStringLang(R.string.accept), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeStatus(controlList, finalStatus, position);
                    }

                })
                .show();
    }

    private void loadFogFan(ScheduleListApp scheduleListApp){
        HashMap<String, Object> param = new HashMap<>();
        param.put("id", scheduleListApp.getId());
        param.put("tblName", scheduleListApp.getTblName());
        kProgressHUD.show();
        ScheduleApi.getDetailFromListAllApp(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                FogFanConfigurationDetail fogFanConfigurationDetail = gson.fromJson(result.get_jsonObject().toString(), FogFanConfigurationDetail.class);
                Intent intent;
                if (fogFanConfigurationDetail.getType() == TYPE_LUX_CUT) {
                    intent = new Intent(ScheduleActivity.this, ScheduleSunshadeNetDetail.class);
                } else {
                    intent = new Intent(ScheduleActivity.this, ScheduleFogFanDetail.class);
                }
                intent.putExtra("data", gson.toJson(fogFanConfigurationDetail));
                startActivity(intent);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
            }
        });
    }

    private void changeStatus(ScheduleListApp controlList, byte status, int position){
        HashMap<String, Object> param = new HashMap<>();
        param.put("status", status);
        param.put("id", controlList.getId());
        param.put("tblName", controlList.getTblName());
        kProgressHUD.show();
        ScheduleApi.changeStatus(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                toast(getApplicationContext(), R.string.saveSS);
                controlLists.get(position).setStatus(status);
                scheduleAdapter.updateList(controlLists);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                toast(getApplicationContext(), R.string.saveErr);
            }
        });
    }

    public void pressBack(View view) {finish();}

}
