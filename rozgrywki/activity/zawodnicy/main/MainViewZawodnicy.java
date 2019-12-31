package com.example.rozgrywki.activity.zawodnicy.main;

import com.example.rozgrywki.model.Zawodnicy;

import java.util.List;

public interface MainViewZawodnicy {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Zawodnicy> zawodnicies);
    void onErrorLoading(String message);
}