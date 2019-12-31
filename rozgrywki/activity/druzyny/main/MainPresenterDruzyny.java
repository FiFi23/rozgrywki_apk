package com.example.rozgrywki.activity.druzyny.main;

import androidx.annotation.NonNull;

import com.example.rozgrywki.api.ApiClient;
import com.example.rozgrywki.api.ApiInterface;
import com.example.rozgrywki.model.Druzyny;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterDruzyny {

    private MainViewDruzyny view;

    public MainPresenterDruzyny(MainViewDruzyny view) {
        this.view = view;
    }

    void getData() {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Druzyny>> call = apiInterface.getDruzyny();
        call.enqueue(new Callback<List<Druzyny>>() {
            @Override
            public void onResponse(@NonNull Call<List<Druzyny>> call, @NonNull Response<List<Druzyny>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Druzyny>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }
}




