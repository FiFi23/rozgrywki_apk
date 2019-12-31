package com.example.rozgrywki.activity.druzyny.editor;

public interface EditorViewDruzyny {

    void showProgress();
    void hideProgress();
    void onRequestSuccess(String message);
    Void onRequestError(String message);

}
