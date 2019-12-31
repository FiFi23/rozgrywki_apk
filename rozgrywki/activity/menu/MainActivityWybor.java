package com.example.rozgrywki.activity.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rozgrywki.R;
import com.example.rozgrywki.activity.druzyny.main.MainActivityDruzyny;
import com.example.rozgrywki.activity.mecze.main.MainActivityMecze;
import com.example.rozgrywki.activity.zawodnicy.main.MainActivityZawodnicy;

public class MainActivityWybor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wybor);

        Button przycisk_do_druzyn = findViewById(R.id.przycisk_do_druzyn);
        Button przycisk_do_zawodnikow = findViewById(R.id.przycisk_do_zawodnikow);
        Button przycisk_do_meczy = findViewById(R.id.przycisk_do_meczy);

        //otwietanie activity z drużyną
        przycisk_do_druzyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivityDruzyny.class);
                view.getContext().startActivity(intent);}
        });

        //otwieranie activity z zawodnikami
        przycisk_do_zawodnikow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivityZawodnicy.class);
                view.getContext().startActivity(intent);}
        });

        //otwieranie activity z meczami (skopiować to wyżej)
        przycisk_do_meczy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivityMecze.class);
                view.getContext().startActivity(intent);}
        });

    }
}
