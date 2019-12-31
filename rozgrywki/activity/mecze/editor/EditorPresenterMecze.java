package com.example.rozgrywki.activity.mecze.editor;

import androidx.annotation.NonNull;

import com.example.rozgrywki.api.ApiClient;
import com.example.rozgrywki.api.ApiInterface;
import com.example.rozgrywki.model.Mecze;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenterMecze {

    private EditorViewMecze view;

    public EditorPresenterMecze(EditorViewMecze view) {
        this.view = view;
    }

    void saveMecze(final String id_gosp, final String id_gosc, final String wynik_gosp, final String wynik_gosc, final String data) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient()
                .create(ApiInterface.class);
        Call<Mecze> call = apiInterface.saveMecze(id_gosp, id_gosc, wynik_gosp, wynik_gosp, data);

        call.enqueue(new Callback<Mecze>() {
            @Override
            public void onResponse(@NonNull Call<Mecze> call, @NonNull Response<Mecze> response) {
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
            public void onFailure(@NonNull Call<Mecze> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void updateMecze(int id_m, String id_gosp, String id_gosc, String wynik_gosp, String wynik_gosc, String data){

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Mecze> call = apiInterface.updateMecze(id_m, id_gosp, id_gosc, wynik_gosp, wynik_gosc, data);
        call.enqueue(new Callback<Mecze>() {
            @Override
            public void onResponse(@NonNull Call<Mecze> call, @NonNull Response<Mecze> response) {
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
            public void onFailure(@NonNull Call<Mecze> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void deleteMecze (int id_m){

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mecze> call = apiInterface.deleteMecze(id_m);
        call.enqueue(new Callback<Mecze>() {
            @Override
            public void onResponse(@NonNull Call<Mecze> call, @NonNull Response<Mecze> response) {
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
            public void onFailure(@NonNull Call<Mecze> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }
}

