package com.example.rozgrywki.api;

import com.example.rozgrywki.model.Druzyny;
import com.example.rozgrywki.model.Mecze;
import com.example.rozgrywki.model.Zawodnicy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    // \/ 1P. Odnośnie drużyn

    @FormUrlEncoded
    @POST("druzyny_dodawanie.php")
    Call<Druzyny> saveDruzyny(
        @Field("nazwa_d") String nazwa_d,
        @Field("wygrane") String wygrane,
        @Field("przegrane") String przegrane
    );

    @GET("druzyny_wyswietlanie.php")
    Call<List<Druzyny>> getDruzyny();

    @FormUrlEncoded
    @POST("druzyny_edycja.php")
    Call<Druzyny> updateDruzyny(
            @Field("id_d") int id_d,
            @Field("nazwa_d") String title,
            @Field("wygrane") String wygrane,
            @Field("przegrane") String przegrane
    );

    @FormUrlEncoded
    @POST("druzyny_usuwanie.php")
    Call<Druzyny> deleteDruzyny(@Field("id_d") int id_d );

    // /\ 1K. Odnośnie drużyn

    // \/ 2P. Odnośnie zawodników

    @FormUrlEncoded
    @POST("zawodnicy_dodawanie.php")
    Call<Zawodnicy> saveZawodnicy(
            @Field("id_d") String id_d,
            @Field("imie") String imie
    );

    @GET("zawodnicy_wyswietlanie.php")
    Call<List<Zawodnicy>> getZawodnicy();

    @FormUrlEncoded
    @POST("zawodnicy_edycja.php")
    Call<Zawodnicy> updateZawodnicy(
            @Field("id_z") int id_z,
            @Field("id_d") String id_d,
            @Field("imie") String imie
    );

    @FormUrlEncoded
    @POST("zawodnicy_usuwanie.php")
    Call<Zawodnicy> deleteZawodnicy( @Field("id_z") int id_z );

    // /\ 2K. Odnośnie zawodników

    // \/ 3P. Odnośnie meczów

    @FormUrlEncoded
    @POST("mecze_dodawanie.php")
    Call<Mecze> saveMecze(
            @Field("id_gosp") String id_gosp,
            @Field("id_gosc") String id_gosc,
            @Field("wynik_gosp") String wynik_gosp,
            @Field("wynik_gosc") String wynik_gosc,
            @Field("data") String data
    );

    @GET("mecze_wyswietlanie.php")
    Call<List<Mecze>> getMecze();

    @FormUrlEncoded
    @POST("mecze_edycja.php")
    Call<Mecze> updateMecze(
            @Field("id_m") int id_m,
            @Field("id_gosp") String id_gosp,
            @Field("id_gosc") String id_gosc,
            @Field("wynik_gosp") String wynik_gosp,
            @Field("wynik_gosc") String wynik_gosc,
            @Field("data") String data
    );

    @FormUrlEncoded
    @POST("mecze_usuwanie.php")
    Call<Mecze> deleteMecze( @Field("id_m") int id_m );

    // /\ 3K. Odnośnie meczów


    @GET("zawodnicy_druzyny_wyswietlanie.php")
    Call<List<Zawodnicy>> getZawodnicyKonkretni( @Query("id_d") String id_d );

    @GET("mecze_druzyny_wyswietlanie.php")
    Call<List<Mecze>> getMeczeKonkretne( @Query("id_d") String id_d );
}
