package com.vnpt.iot.hoalac.core.activity.procedureManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.vnpt.iot.hoalac.R;
import com.vnpt.iot.hoalac.api.listeners.ResponseListener;
import com.vnpt.iot.hoalac.api.model.CommonApi;
import com.vnpt.iot.hoalac.api.model.ProcedureApi;
import com.vnpt.iot.hoalac.api.network.ApiResponse.ApiResponse;
import com.vnpt.iot.hoalac.core.activity.CommonActivity;
import com.vnpt.iot.hoalac.core.adapter.ProcedureAdapter;
import com.vnpt.iot.hoalac.core.common.AppSharedPreferences;
import com.vnpt.iot.hoalac.core.model.Category;
import com.vnpt.iot.hoalac.core.model.Farm;
import com.vnpt.iot.hoalac.core.model.Parse;
import com.vnpt.iot.hoalac.core.model.Procedure;
import com.vnpt.iot.hoalac.core.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProcedureManagementActivity extends CommonActivity {
    @BindView(R.id.listProcess) ListView listProcess;
    @BindView(R.id.spCategory) Spinner spCategory;
    @BindView(R.id.spProduct) Spinner spProduct;
    private ProcedureAdapter procedureAdapter;
    private List<Procedure> procedures;
    private String categoryName, productName;
    private List<Category> categories;
    private List<Product> products;
    private HashMap<String, Category> categoryMap = new HashMap<>();
    private HashMap<String, Product> productMap = new HashMap<>();
    private Farm farm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_management);
        commonSetting();
        ButterKnife.bind(this);
        farm = AppSharedPreferences.getFarm(getApplicationContext());

        initObject();

        HashMap<String, Object> param = new HashMap<>();
        param.put("farmId",farm.getId());
        loadData(param);
        loadFilter();
    }

    private void loadFilter(){
        HashMap<String, Object> param = new HashMap<>();
        param.put("farmId", farm.getId());
        CommonApi.getListCategory(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                categories = Parse.getInstance().category(result);
                List<String> categoryAdapter = new ArrayList<String>();
                categoryAdapter.add(getStringLang(R.string.category));

                for (Category category : categories) {
                    categoryAdapter.add(category.getName());
                    categoryMap.put(category.getName(), category);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner, categoryAdapter);
                dataAdapter.setDropDownViewResource(R.layout.item_spinner);

                spCategory.setAdapter(dataAdapter);
            }

            @Override
            public void onRequestError(ApiResponse error) {

            }
        });

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryName = parent.getItemAtPosition(position).toString();
                setDefaultSpinnerProduct();
                productMap.clear();
                if (!categoryMap.containsKey(categoryName)) return;
                Category category = categoryMap.get(categoryName);
                initSpinnerProduct(category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void filter(View view){
        HashMap<String, Object> param = new HashMap<>();
        param.put("farmId", farm.getId());

        if (categoryName.equals(getStringLang(R.string.category))) {
            loadData(param);
            return;
        }

        Category category = categoryMap.get(categoryName);
        if (productName.equals(getStringLang(R.string.product))) {
            param.clear();
            param.put("categoryType",category.getId());
            loadData(param);
        } else {
            param.clear();
            Product product = productMap.get(productName);
            param.put("categoryType",category.getId());
            param.put("productId", product.getId());
            loadData(param);
        }
    }

    private void initSpinnerProduct(Category category) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("categoryId", category.getId());
        CommonApi.getListProduct(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                products = Parse.getInstance().product(result);
                List<String> productAdapter = new ArrayList<String>();
                productAdapter.add(getStringLang(R.string.product));

                for (Product product : products) {
                    productAdapter.add(product.getName());
                    productMap.put(product.getName(), product);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner, productAdapter);
                dataAdapter.setDropDownViewResource(R.layout.item_spinner);

                spProduct.setAdapter(dataAdapter);
            }

            @Override
            public void onRequestError(ApiResponse error) {

            }
        });
    }

    private void setDefaultSpinnerProduct(){
        List<String> greenHouseAdapter = new ArrayList<String>();
        greenHouseAdapter.add(getStringLang(R.string.product));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner, greenHouseAdapter);
        dataAdapter.setDropDownViewResource(R.layout.item_spinner);
        spProduct.setAdapter(dataAdapter);
    }

    private void loadData(HashMap<String, Object> param){
        kProgressHUD.show();
        ProcedureApi.getList(getApplicationContext(), param, new ResponseListener() {
            @Override
            public void onRequestCompleted(ApiResponse result) {
                kProgressHUD.dismiss();
                if (result.get_jsonObject() == null) {
                    procedureAdapter.updateList(new ArrayList<>());
                    return;
                }
                procedures = Parse.getInstance().getListProcedure(result);
                procedureAdapter.updateList(procedures);
            }

            @Override
            public void onRequestError(ApiResponse error) {
                kProgressHUD.dismiss();
                procedureAdapter.updateList(new ArrayList<>());
            }
        });
    }

    private void initObject(){
        listProcess.setDivider(null);
        listProcess.setDividerHeight(0);
        procedureAdapter = new ProcedureAdapter(this, new ArrayList<Procedure>());
        listProcess.setAdapter(procedureAdapter);

        categoryName = getStringLang(R.string.category);
        productName = getStringLang(R.string.product);
        setDefaultSpinnerProduct();
        spProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                productName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listProcess.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Procedure procedure = procedures.get(position);
                String data = gson.toJson(procedure);
                Intent intent = new Intent(ProcedureManagementActivity.this, DetailProcedureManagementActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });


    }

    public void pressBack(View view){
        finish();
    }

    public void goDetail(View view){
        startActivity(new Intent(this, DetailProcedureManagementActivity.class));
    }
}
