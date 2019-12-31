package com.example.rozgrywki.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Zawodnicy {

    @Expose
    @SerializedName("id_z") private int id_z;
    @Expose
    @SerializedName("id_d") private String id_d;
    @Expose
    @SerializedName("nazwa_d") private String nazwa_d;
    @Expose
    @SerializedName("imie") private String imie;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;



    public int getId_z_Zawodnicy() {
        return id_z;
    }

    public void setId_z_Zawodnicy(int id_z) {
        this.id_z = id_z;
    }

    public String getId_d_Zawodnicy() {
        return id_d;
    }

    public void setId_d_Zawodnicy(String id_d) { this.id_d = id_d; }

    public String getNazwa_d_Zawodnicy() {
        return nazwa_d;
    }

    public void setNazwa_d_Zawodnicy(String nazwa_d) { this.nazwa_d = nazwa_d; }

    public String getImie_Zawodnicy() {
        return imie;
    }

    public void setImie_Zawodnicy(String imie) {
        this.imie = imie;
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
