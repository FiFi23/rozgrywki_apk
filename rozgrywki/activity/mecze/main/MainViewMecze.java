package com.example.rozgrywki.activity.mecze.main;

import com.example.rozgrywki.model.Mecze;

import java.util.List;

public interface MainViewMecze {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Mecze> meczes);
    void onErrorLoading(String message);
}
