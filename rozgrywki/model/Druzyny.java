package com.example.rozgrywki.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Druzyny {

    @Expose
    @SerializedName("id_d") private int id_d;
    @Expose
    @SerializedName("nazwa_d") private String nazwa_d;
    @Expose
    @SerializedName("wygrane") private String wygrane;
    @Expose
    @SerializedName("przegrane") private String przegrane;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;



    public int getId_Druzyny() {
        return id_d;
    }

    public void setId_Druzyny(int id_d) {
        this.id_d = id_d;
    }

    public String getNazwa_Druzyny() {
        return nazwa_d;
    }

    public void setNazwa_Druzyny(String nazwa_d) { this.nazwa_d = nazwa_d; }

    public String getWygrane_Druzyny() {
        return wygrane;
    }

    public void setWygrane_Druzyny(String wygrane) {
        this.wygrane = wygrane;
    }

    public String getPrzegrane_Druzyny() {
        return przegrane;
    }

    public void setPrzegrane_Druzyny(String przegrane) {
        this.przegrane = przegrane;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Druzyny(int id_d, String nazwa_d) {
        this.id_d = id_d;
        this.nazwa_d = nazwa_d;
    }

    @NonNull
    @Override
    public String toString() {
        return nazwa_d;
    }
}
