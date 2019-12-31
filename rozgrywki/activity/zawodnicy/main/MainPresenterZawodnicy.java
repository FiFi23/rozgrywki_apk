package com.example.rozgrywki.activity.zawodnicy.main;

import androidx.annotation.NonNull;

import com.example.rozgrywki.api.ApiClient;
import com.example.rozgrywki.api.ApiInterface;
import com.example.rozgrywki.model.Zawodnicy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterZawodnicy {

    public MainViewZawodnicy view;

    public MainPresenterZawodnicy(MainViewZawodnicy view) {
        this.view = view;
    }

    void getData() {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Zawodnicy>> call = apiInterface.getZawodnicy();
        call.enqueue(new Callback<List<Zawodnicy>>() {
            @Override
            public void onResponse(@NonNull Call<List<Zawodnicy>> call, @NonNull Response<List<Zawodnicy>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Zawodnicy>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

    public void getZawodnicyKonkretni(String id_d) {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Zawodnicy>> call = apiInterface.getZawodnicyKonkretni(id_d);
        call.enqueue(new Callback<List<Zawodnicy>>() {
            @Override
            public void onResponse(@NonNull Call<List<Zawodnicy>> call, @NonNull Response<List<Zawodnicy>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Zawodnicy>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }
}




