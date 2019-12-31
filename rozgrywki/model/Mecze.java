package com.example.rozgrywki.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mecze {
    @Expose
    @SerializedName("id_m") private int id_m;
    @Expose
    @SerializedName("id_gosp") private String id_gosp;
    @Expose
    @SerializedName("nazwa_gosp") private String nazwa_gosp;
    @Expose
    @SerializedName("id_gosc") private String id_gosc;
    @Expose
    @SerializedName("nazwa_gosc") private String nazwa_gosc;
    @Expose
    @SerializedName("wynik_gosp") private String wynik_gosp;
    @Expose
    @SerializedName("wynik_gosc") private String wynik_gosc;
    @Expose
    @SerializedName("data") private String data;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;



    public int getId_m_Mecze() {
        return id_m;
    }

    public void setId_m_Mecze(int id_m) {
        this.id_m = id_m;
    }

    public String getId_gosp_Mecze() { return id_gosp; }

    public void setId_gosp_Mecze(String id_gosp) { this.id_gosp = id_gosp; }

    public String getNazwa_gosp_Mecze() {
        return nazwa_gosp;
    }

    public void setNazwa_gosp_Mecze(String nazwa_gosp) { this.nazwa_gosp = nazwa_gosp; }

    public String getId_gosc_Mecze() { return id_gosc; }

    public void setId_gosc_Mecze(String id_gosc) { this.id_gosc = id_gosc; }

    public String getNazwa_gosc_Mecze() {
        return nazwa_gosc;
    }

    public void setNazwa_gosc_Mecze(String nazwa_gosc) { this.nazwa_gosc = nazwa_gosc; }

    public String getWynik_gosp_Mecze() {
        return wynik_gosp;
    }

    public void setWynik_gosp_Mecze(String wynik_gosp) {
        this.wynik_gosp = wynik_gosp;
    }

    public String getWynik_gosc_Mecze() {
        return wynik_gosc;
    }

    public void setWynik_gosc_Mecze(String wynik_gosc) {
        this.wynik_gosc = wynik_gosc;
    }

    public String getData_Mecze() {
        return data;
    }

    public void setData_Mecze(String data) {
        this.data = data;
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
}
