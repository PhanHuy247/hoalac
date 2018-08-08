package com.vnpt.iot.hoalac.core.activity.procedureManagement;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.ProcedureApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.model.Procedure;
import com.vnpt.iot.hoalac.core.model.ProcedureDetail;
import com.vnpt.iot.hoalac.core.model.ProcedureWork;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailProcedureManagementActivity extends CommonActivity {
    @BindView(R.id.name) TextView name;
    @BindView(R.id.nameProduct) TextView nameProduct;
    @BindView(R.id.code) TextView code;
    @BindView(R.id.note) TextView note;
    @BindView(R.id.mainContainer) LinearLayout mainContainer;
    private Procedure procedure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonSetting();
        setContentView(R.layout.activity_detail_process_management);
        ButterKnife.bind(this);
        Procedure procedureData = gson.fromJson(getIntent().getStringExtra("data"), Procedure.class);
        loadData(procedureData.getId());
    }

    private void loadData(Long id){
        HashMap<String, Object> param = new HashMap<>();
        param.put("id", id);
        kProgressHUD.show();
        ProcedureApi.getDetail(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                procedure = gson.fromJson(result.get_jsonObject().toString(), Procedure.class);
                loadView();
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
            }
        });
    }

    private void loadView(){
        name.setText(procedure.getName());
        nameProduct.setText(procedure.getProductName());
        code.setText(procedure.getCode());
        note.setText(procedure.getNotes());

        if ((procedure.getProcedureDetails() != null) && (procedure.getProcedureDetails().size() > 0)) {
            for (ProcedureDetail procedureDetail : procedure.getProcedureDetails()) {
                View view = View.inflate(getApplicationContext(), R.layout.item_phase_process, null);
                mainContainer.addView(view);
                TextView processName = (TextView) view.findViewById(R.id.processName);
                TextView title3 = (TextView) view.findViewById(R.id.title3);
                TextView startDate = (TextView) view.findViewById(R.id.startDate);
                TextView title4 = (TextView) view.findViewById(R.id.title4);
                TextView endDate = (TextView) view.findViewById(R.id.endDate);
                LinearLayout workLayout = (LinearLayout) view.findViewById(R.id.workLayout);

                processName.setText(procedureDetail.getProgressName());
                title3.setText(getResources().getString(R.string.startAs));
                startDate.setText(String.valueOf(procedureDetail.getStartTime())+" "+getResources().getString(R.string.day));
                title4.setText(getResources().getString(R.string.endAs));
                endDate.setText(String.valueOf(procedureDetail.getEndTime())+" "+getResources().getString(R.string.day));

                if ((procedureDetail.getProcedureWorks() != null) && (procedureDetail.getProcedureWorks().size() > 0)) {
                    for (ProcedureWork procedureWork : procedureDetail.getProcedureWorks()) {
                        View viewWork = View.inflate(getApplicationContext(), R.layout.item_procedure_work, null);
                        workLayout.addView(viewWork);
                        TextView nameWork = (TextView) viewWork.findViewById(R.id.nameWork);
                        TextView timeWork = (TextView) viewWork.findViewById(R.id.timeWork);

                        nameWork.setText(procedureWork.getWorkName());
                        timeWork.setText(String.valueOf(procedureWork.getStartTime())+" "+getResources().getString(R.string.day));
                    }
                }
            }
        }
    }

    public void pressBack(View view){
        finish();
    }

}
