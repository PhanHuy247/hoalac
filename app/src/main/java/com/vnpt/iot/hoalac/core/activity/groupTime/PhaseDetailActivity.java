package com.vnpt.iot.hoalac.core.activity.groupTime;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.PhaseApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.model.Phase;
import com.vnpt.iot.hoalac.core.model.PhaseDetail;
import com.vnpt.iot.hoalac.core.model.SeasonDetailDTO;
import com.vnpt.iot.hoalac.core.model.SeasonStaffDTO;
import com.vnpt.iot.hoalac.core.model.Works;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhaseDetailActivity extends CommonActivity {
    @BindView(R.id.name) TextView name;
    @BindView(R.id.code) TextView code;
    @BindView(R.id.processName) TextView processName;
    @BindView(R.id.nameProduct) TextView nameProduct;
    @BindView(R.id.startDate) TextView startDate;
    @BindView(R.id.endDate) TextView endDate;
    @BindView(R.id.greenHouse) TextView greenHouse;
    @BindView(R.id.area) TextView area;
    @BindView(R.id.qRQuantity) TextView qRQuantity;
    @BindView(R.id.mainContainer) LinearLayout mainContainer;
    private PhaseDetail phaseDetail;
    private Phase phase;
    private SimpleDateFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase_detail);
        commonSetting();
        ButterKnife.bind(this);
        String data = getIntent().getStringExtra("data");
        phase = gson.fromJson(data, Phase.class);
        formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        loadData();
    }

    private void loadData(){
        HashMap<String, Object> param = new HashMap<>();
        param.put("id", phase.getId());
        kProgressHUD.show();
        PhaseApi.getDetail(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                phaseDetail = gson.fromJson(result.get_jsonObject().toString(), PhaseDetail.class);
                loadView();
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
            }
        });
    }

    private void loadView(){
        name.setText(phaseDetail.getName());
        code.setText(phaseDetail.getCode());
        processName.setText(phaseDetail.getProcedureName());
        nameProduct.setText(phaseDetail.getProductName());
        startDate.setText(phaseDetail.getStartTime());
        endDate.setText(phaseDetail.getEndTime());
        greenHouse.setText(phaseDetail.getSectorName());
        area.setText(phaseDetail.getBedName());
        qRQuantity.setText(String.valueOf(phaseDetail.getQuantity()));
        Date today = new Date();

        if ((phaseDetail.getLstDetail() != null) && (phaseDetail.getLstDetail().size() > 0)) {
            for (SeasonDetailDTO seasonDetailDTO : phaseDetail.getLstDetail()) {
                View view = View.inflate(getApplicationContext(), R.layout.item_phase_progress, null);
                mainContainer.addView(view);
                TextView processName = (TextView) view.findViewById(R.id.processName);
                TextView startDate = (TextView) view.findViewById(R.id.startDate);
                TextView endDate = (TextView) view.findViewById(R.id.endDate);
                TextView startTimeReality = (TextView) view.findViewById(R.id.startTimeReality);
                TextView endTimeReality = (TextView) view.findViewById(R.id.endTimeReality);
                LinearLayout workLayout = (LinearLayout) view.findViewById(R.id.workLayout);

                processName.setText(seasonDetailDTO.getProgressName());

                try {
                    Date dateStart = formatter.parse(seasonDetailDTO.getStartTime());
                    Date dateEnd = formatter.parse(seasonDetailDTO.getEndTime());

                    if (today.after(dateStart) && today.before(dateEnd)) {
                        processName.setTextColor(getResources().getColor(R.color.red));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                startDate.setText(seasonDetailDTO.getStartTime());
                endDate.setText(seasonDetailDTO.getEndTime());
                startTimeReality.setText(seasonDetailDTO.getRealStartTime());
                endTimeReality.setText(seasonDetailDTO.getRealStopTime());

                if ((seasonDetailDTO.getListWork() != null) && (seasonDetailDTO.getListWork().size() > 0)) {
                    for (Works work : seasonDetailDTO.getListWork()) {
                        View viewWork = View.inflate(getApplicationContext(), R.layout.item_phase_progress_work, null);
                        workLayout.addView(viewWork);
                        TextView workName = (TextView) viewWork.findViewById(R.id.name);
                        TextView workSetStartDate = (TextView) viewWork.findViewById(R.id.setStartDate);
                        TextView staff = (TextView) viewWork.findViewById(R.id.staff);
                        TextView staffReviewer = (TextView) viewWork.findViewById(R.id.staffReviewer);

                        workName.setText(work.getWorkName());
                        workSetStartDate.setText(work.getStartWork());
                        String staffName = "";
                        String staffReview = "";
                        if ((work.getLstStaff() != null) && (work.getLstStaff().size() > 0)) {
                            for (SeasonStaffDTO seasonStaffDTO : work.getLstStaff()){
                                if (seasonStaffDTO.getType() == 1) staffName += seasonStaffDTO.getStaffName()+", ";
                                if (seasonStaffDTO.getType() == 2) staffReview += seasonStaffDTO.getStaffName()+", ";
                            }
                        }
                        if (!staffName.equals("")) staffName = staffName.substring(0, staffName.length() - 2);
                        if (!staffReview.equals("")) staffReview = staffReview.substring(0, staffReview.length() - 2);

                        staff.setText(staffName);
                        staffReviewer.setText(staffReview);
                    }
                }
            }
        }

    }

    public void pressBack(View view) {onBackPressed();}
}
