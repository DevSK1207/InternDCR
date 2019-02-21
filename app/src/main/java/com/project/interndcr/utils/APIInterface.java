package com.project.interndcr.utils;

import com.project.interndcr.model.ResponseList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("intern-dcr-data/master/data.json")
    Call<ResponseList> getData();
}
