package com.example.rozgrywki.activity.druzyny.main;

import com.example.rozgrywki.model.Druzyny;

import java.util.List;

public interface MainViewDruzyny {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Druzyny> druzynies);
    void onErrorLoading(String message);
}
