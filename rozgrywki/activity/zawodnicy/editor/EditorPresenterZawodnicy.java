package com.example.rozgrywki.activity.zawodnicy.editor;

import androidx.annotation.NonNull;

import com.example.rozgrywki.api.ApiClient;
import com.example.rozgrywki.api.ApiInterface;
import com.example.rozgrywki.model.Zawodnicy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenterZawodnicy {

    private EditorViewZawodnicy view;

    public EditorPresenterZawodnicy(EditorViewZawodnicy view) {
        this.view = view;
    }

    void saveZawodnicy(final String id_d, final String imie) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient()
                .create(ApiInterface.class);
        Call<Zawodnicy> call = apiInterface.saveZawodnicy(id_d, imie);

        call.enqueue(new Callback<Zawodnicy>() {
            @Override
            public void onResponse(@NonNull Call<Zawodnicy> call, @NonNull Response<Zawodnicy> response) {
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
            public void onFailure(@NonNull Call<Zawodnicy> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void updateZawodnicy(int id_z, String id_d, String imie){

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Zawodnicy> call = apiInterface.updateZawodnicy(id_z, id_d, imie);
        call.enqueue(new Callback<Zawodnicy>() {
            @Override
            public void onResponse(@NonNull Call<Zawodnicy> call, @NonNull Response<Zawodnicy> response) {
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
            public void onFailure(@NonNull Call<Zawodnicy> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void deleteZawodnicy (int id_z){

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Zawodnicy> call = apiInterface.deleteZawodnicy(id_z);
        call.enqueue(new Callback<Zawodnicy>() {
            @Override
            public void onResponse(@NonNull Call<Zawodnicy> call, @NonNull Response<Zawodnicy> response) {
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
            public void onFailure(@NonNull Call<Zawodnicy> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }
}

