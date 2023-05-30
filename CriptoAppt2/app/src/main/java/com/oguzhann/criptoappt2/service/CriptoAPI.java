package com.oguzhann.criptoappt2.service;

import com.oguzhann.criptoappt2.model.CriptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CriptoAPI {

    @GET("K21-JSONDataSet/master/crypto.json")
    Call<List<CriptoModel>> getData();
}
