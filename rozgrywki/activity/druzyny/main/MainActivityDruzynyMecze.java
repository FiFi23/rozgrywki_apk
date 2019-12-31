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
import com.example.rozgrywki.activity.mecze.editor.EditorActivityMecze;
import com.example.rozgrywki.activity.mecze.main.MainAdapterMecze;
import com.example.rozgrywki.activity.mecze.main.MainPresenterMecze;
import com.example.rozgrywki.activity.mecze.main.MainViewMecze;
import com.example.rozgrywki.model.Mecze;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivityDruzynyMecze extends AppCompatActivity implements MainViewMecze {

    private static final int INTENT_EDIT = 200 ;
    private static final int INTENT_ADD = 100 ;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    String id_d, nazwa_d;

    MainPresenterMecze presenter;
    MainAdapterMecze adapter;
    MainAdapterMecze.ItemClickListener itemClickListener;

    List<Mecze> mecze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intentcik = getIntent();
        id_d = intentcik.getStringExtra("id_d");
        nazwa_d = intentcik.getStringExtra("nazwa_d");
        getSupportActionBar().setTitle("MECZE - " + nazwa_d);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.add);
        fab.setOnClickListener(view ->
                startActivityForResult(new Intent(this, EditorActivityMecze.class),
                        INTENT_ADD)
        );

        presenter = new MainPresenterMecze(this);
        presenter.getMeczeKonkretne(id_d);

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getMeczeKonkretne(id_d)
        );

        itemClickListener = ((view, position) -> {
            int id_m = mecze.get(position).getId_m_Mecze();
            String id_gosp = mecze.get(position).getId_gosp_Mecze();
            String id_gosc = mecze.get(position).getId_gosc_Mecze();
            String wynik_gosp = mecze.get(position).getWynik_gosp_Mecze();
            String wynik_gosc = mecze.get(position).getWynik_gosc_Mecze();
            String data = mecze.get(position).getData_Mecze();

            // Przekazanie wartości do "nazw" i przekazanie do takich samych nazwa w ""
            // w editor activity na zielono, muszą być takie same
            Intent intent = new Intent(this, EditorActivityMecze.class);
            intent.putExtra("id_m", id_m);
            intent.putExtra("id_gosp", id_gosp);
            intent.putExtra("id_gosc", id_gosc);
            intent.putExtra("wynik_gosp", wynik_gosp);
            intent.putExtra("wynik_gosc", wynik_gosc);
            intent.putExtra("data", data);
            startActivityForResult(intent, INTENT_EDIT);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INTENT_ADD && resultCode == RESULT_OK){
            presenter.getMeczeKonkretne(id_d); // reload date
        }
        else  if (requestCode == INTENT_EDIT && resultCode == RESULT_OK){
            presenter.getMeczeKonkretne(id_d); // reload date
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
    public void onGetResult(List<Mecze> meczes) {
        adapter = new MainAdapterMecze(this, meczes, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        mecze = meczes;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
