package com.example.rozgrywki.activity.mecze.editor;

public interface EditorViewMecze {

    void showProgress();
    void hideProgress();
    void onRequestSuccess(String message);
    Void onRequestError(String message);
}
