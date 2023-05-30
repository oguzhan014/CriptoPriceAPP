package com.oguzhann.criptoappt2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oguzhann.criptoappt2.adapter.RcyclerViewAdapter;
import com.oguzhann.criptoappt2.model.CriptoModel;
import com.oguzhann.criptoappt2.service.CriptoAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PriceFrag#newInstance} factory method to
 * create an instance of this fragment.
 *
 *
 */
public class PriceFrag extends Fragment {

    ArrayList<CriptoModel> Criptomodel;
    private String BASE_URL = "https://raw.githubusercontent.com/atilsamancioglu/";
    RecyclerView recyclerView;
    RcyclerViewAdapter rcyclerViewAdapter;

    Retrofit retrofit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_price, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)

                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();

        return view;
    }

    private void loadData() {

        final CriptoAPI criptoAPI = retrofit.create(CriptoAPI.class);

        Call<List<CriptoModel>> call = criptoAPI.getData();

        call.enqueue(new Callback<List<CriptoModel>>() {
            @Override
            public void onResponse(Call<List<CriptoModel>> call, Response<List<CriptoModel>> response) {
                if (response.isSuccessful()) {
                    List<CriptoModel> responseList = response.body();
                    Criptomodel = new ArrayList<>(responseList);


                      rcyclerViewAdapter=new RcyclerViewAdapter(Criptomodel);
                    recyclerView.setAdapter(rcyclerViewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


                } else {
                    Log.d("API Response", "Error: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<CriptoModel>> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }
}