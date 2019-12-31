package com.example.rozgrywki.activity.druzyny.editor;

import androidx.annotation.NonNull;

import com.example.rozgrywki.api.ApiClient;
import com.example.rozgrywki.api.ApiInterface;
import com.example.rozgrywki.model.Druzyny;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenterDruzyny {

    private EditorViewDruzyny view;

    public EditorPresenterDruzyny(EditorViewDruzyny view) {
        this.view = view;
    }

    void saveDruzyny(final String nazwa_druzyny, final String wygrane, final String przegrane) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient()
                .create(ApiInterface.class);
        Call<Druzyny> call = apiInterface.saveDruzyny(nazwa_druzyny, wygrane, przegrane);

        call.enqueue(new Callback<Druzyny>() {
            @Override
            public void onResponse(@NonNull Call<Druzyny> call, @NonNull Response<Druzyny> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {

                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    }

                    else {
                        view.onRequestError(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<Druzyny> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void updateDruzyny(int id_d, String nazwa_druzyny, String wygrane, String przegrane){

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Druzyny> call = apiInterface.updateDruzyny(id_d, nazwa_druzyny, wygrane, przegrane);
        call.enqueue(new Callback<Druzyny>() {
            @Override
            public void onResponse(@NonNull Call<Druzyny> call, @NonNull Response<Druzyny> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null) {

                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<Druzyny> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void deleteDruzyny (int id_d){

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Druzyny> call = apiInterface.deleteDruzyny(id_d);
        call.enqueue(new Callback<Druzyny>() {
            @Override
            public void onResponse(@NonNull Call<Druzyny> call, @NonNull Response<Druzyny> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null){

                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Druzyny> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }
}
