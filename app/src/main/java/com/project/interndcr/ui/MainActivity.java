package com.project.interndcr.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;
import com.project.interndcr.R;
import com.project.interndcr.base.BaseActivity;
import com.project.interndcr.model.ResponseList;
import com.project.interndcr.utils.APIInterface;
import com.project.interndcr.utils.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    TextView boxLiterature, boxGift, boxPhysician;
    Spinner mProduct, mGift, mLiterature, mPhysician;
    APIInterface apiInterface;
    ConstraintLayout layout;
    ProgressBar progressBar;
    EditText accompinied, remarks;
    ArrayList<String> productList, literatureList, physicianList, giftList;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        //Spinner
        mProduct = findViewById(R.id.product_group);
        mGift = findViewById(R.id.gift);
        mLiterature = findViewById(R.id.literature);
        mPhysician = findViewById(R.id.Physician_sample);

        boxGift = findViewById(R.id.gift_box);
        boxLiterature = findViewById(R.id.literature_box);
        boxPhysician = findViewById(R.id.Physician_sample_box);

        //Edit text
        accompinied = findViewById(R.id.accompanied_with);
        remarks = findViewById(R.id.remarks);

        layout = findViewById(R.id.topLayout);

        //Progressbar
        progressBar = findViewById(R.id.progressBar);

        //button
        findViewById(R.id.submitBtn).setOnClickListener(v -> showToast("Done"));

        productList = new ArrayList<>(); // Received Product list
        literatureList = new ArrayList<>(); // Received Literature list
        physicianList = new ArrayList<>(); // Received Physician list
        giftList = new ArrayList<>(); // Received Gift list

        if (savedInstanceState != null) {

            progressBar.setVisibility(View.GONE);

        } else if (isConnected()) {

            getData(); //Check internet connection and make call
        } else {

            //No internet connection
            progressBar.setVisibility(View.GONE);
            showSnackbar(layout, "No Internet Connection");
        }

        spinnerOnItemSelected();
    }

    private void spinnerOnItemSelected(){
        mProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Handle item selection
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mLiterature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*String item = parent.getItemAtPosition(position).toString();
                showToast(item);*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mPhysician.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mGift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getData(){

        apiInterface = APIUtils.getAPIResponse();

        //GET method
        Call<ResponseList> call = apiInterface.getData();
        call.enqueue(new Callback<ResponseList>() {
            @Override
            public void onResponse(@NonNull Call<ResponseList> call, @NonNull Response<ResponseList> response) {
                ResponseList responseList = response.body();
                if (responseList != null) {

                    productList.add("Choose");

                    literatureList.add("Choose");

                    physicianList.add("Choose");

                    giftList.add("Choose");

                    //add data to spinner

                    for (int i = 0; i < responseList.productGroups.size(); i++){

                        productList.add(responseList.productGroups.get(i).getProductGroup());
                    }

                    for (int i = 0; i < responseList.literatureList.size(); i++){

                        literatureList.add(responseList.literatureList.get(i).getLiterature());
                    }

                    for (int i = 0; i < responseList.physicianSampleList.size(); i++){

                        physicianList.add(responseList.physicianSampleList.get(i).getSample());
                    }

                    for (int i = 0; i < responseList.giftLists.size(); i++){

                        giftList.add(responseList.giftLists.get(i).getGift());
                    }
                    setSpinnerData(mProduct, productList);
                    setSpinnerData(mLiterature, literatureList);
                    setSpinnerData(mPhysician, physicianList);
                    setSpinnerData(mGift, giftList);

                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseList> call, @NonNull Throwable t) {

                progressBar.setVisibility(View.GONE);
                showToast("Could not load data");
            }
        });
    }


    private void setSpinnerData(final Spinner spinner, List<String> items) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.spinner_text, items);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("products", productList);
        outState.putSerializable("literatures", literatureList);
        outState.putSerializable("physicians", physicianList);
        outState.putSerializable("gifts", giftList);

        outState.putInt("productSpinner", mProduct.getSelectedItemPosition());
        outState.putInt("literatureSpinner", mLiterature.getSelectedItemPosition());
        outState.putInt("physicianSpinner", mPhysician.getSelectedItemPosition());
        outState.putInt("gift", mGift.getSelectedItemPosition());

        outState.putString("accompaniedText", accompinied.getText().toString());
        outState.putString("remarksText", remarks.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null){

            productList = (ArrayList<String>) savedInstanceState.getSerializable("products");
            literatureList = (ArrayList<String>) savedInstanceState.getSerializable("literatures");
            physicianList = (ArrayList<String>) savedInstanceState.getSerializable("physicians");
            giftList = (ArrayList<String>) savedInstanceState.getSerializable("gifts");

            if (!productList.isEmpty()){
                setSpinnerData(mProduct, productList);
                mProduct.setSelection(savedInstanceState.getInt("productSpinner"));
            }
            if (!literatureList.isEmpty()){
                setSpinnerData(mLiterature, literatureList);
                mLiterature.setSelection(savedInstanceState.getInt("literatureSpinner"));
            }
            if (!physicianList.isEmpty()){
                setSpinnerData(mPhysician, physicianList);
                mPhysician.setSelection(savedInstanceState.getInt("physicianSpinner"));
            }
            if (giftList != null && !giftList.isEmpty()) {
                setSpinnerData(mGift, giftList);
                mGift.setSelection(savedInstanceState.getInt("gift"));
            }

            accompinied.setText(savedInstanceState.getString("accompaniedText"));
            remarks.setText(savedInstanceState.getString("remarksText"));
        }
    }
}
