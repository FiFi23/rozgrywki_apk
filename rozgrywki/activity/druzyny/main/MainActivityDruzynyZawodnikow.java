package com.example.rozgrywki.activity.druzyny.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rozgrywki.R;
import com.example.rozgrywki.activity.zawodnicy.editor.EditorActivityZawodnicy;
import com.example.rozgrywki.activity.zawodnicy.main.MainAdapterZawodnicy;
import com.example.rozgrywki.activity.zawodnicy.main.MainPresenterZawodnicy;
import com.example.rozgrywki.activity.zawodnicy.main.MainViewZawodnicy;
import com.example.rozgrywki.model.Zawodnicy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivityDruzynyZawodnikow extends AppCompatActivity implements MainViewZawodnicy {

    private static final int INTENT_EDIT = 200 ;
    private static final int INTENT_ADD = 100 ;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    String id_d, nazwa_d;

    MainPresenterZawodnicy presenter;
    MainAdapterZawodnicy adapter;
    MainAdapterZawodnicy.ItemClickListener itemClickListener;

    List<Zawodnicy> zawodnicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intentcik = getIntent();
        id_d = intentcik.getStringExtra("id_d");
        nazwa_d = intentcik.getStringExtra("nazwa_d");
        getSupportActionBar().setTitle("ZAWODNICY - " + nazwa_d);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.add);
        fab.setOnClickListener(view ->
                startActivityForResult(new Intent(this, EditorActivityZawodnicy.class),
                        INTENT_ADD)
        );

        presenter = new MainPresenterZawodnicy(this);
        presenter.getZawodnicyKonkretni(id_d);

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getZawodnicyKonkretni(id_d)
        );

        itemClickListener = ((view, position) -> {
            int id_z = zawodnicy.get(position).getId_z_Zawodnicy();
            String id_d = zawodnicy.get(position).getId_d_Zawodnicy();
            String imie = zawodnicy.get(position).getImie_Zawodnicy();

            // Przekazanie wartości do "nazw" i przekazanie do takich samych nazwa w ""
            // w editor activity na zielono, muszą być takie same
            Intent intent = new Intent(this, EditorActivityZawodnicy.class);
            intent.putExtra("id_z", id_z);
            intent.putExtra("id_d", id_d);
            intent.putExtra("imie", imie);
            startActivityForResult(intent, INTENT_EDIT);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INTENT_ADD && resultCode == RESULT_OK){
            presenter.getZawodnicyKonkretni(id_d); // reload date
        }
        else  if (requestCode == INTENT_EDIT && resultCode == RESULT_OK){
            presenter.getZawodnicyKonkretni(id_d); // reload date
        }
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Zawodnicy> zawodnicies) {
        adapter = new MainAdapterZawodnicy(this, zawodnicies, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        zawodnicy = zawodnicies;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
