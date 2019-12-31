package com.example.rozgrywki.activity.mecze.main;

import androidx.annotation.NonNull;

import com.example.rozgrywki.api.ApiClient;
import com.example.rozgrywki.api.ApiInterface;
import com.example.rozgrywki.model.Mecze;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterMecze {

    private MainViewMecze view;

    public MainPresenterMecze(MainViewMecze view) {
        this.view = view;
    }

    void getData() {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Mecze>> call = apiInterface.getMecze();
        call.enqueue(new Callback<List<Mecze>>() {
            @Override
            public void onResponse(@NonNull Call<List<Mecze>> call, @NonNull Response<List<Mecze>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Mecze>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

    public void getMeczeKonkretne(String id_d) {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Mecze>> call = apiInterface.getMeczeKonkretne(id_d);
        call.enqueue(new Callback<List<Mecze>>() {
            @Override
            public void onResponse(@NonNull Call<List<Mecze>> call, @NonNull Response<List<Mecze>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Mecze>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }
}




