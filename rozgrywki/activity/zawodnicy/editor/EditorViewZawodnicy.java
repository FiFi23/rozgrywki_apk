package com.example.rozgrywki.activity.zawodnicy.editor;

public interface EditorViewZawodnicy {

    void showProgress();
    void hideProgress();
    void onRequestSuccess(String message);
    Void onRequestError(String message);
}
