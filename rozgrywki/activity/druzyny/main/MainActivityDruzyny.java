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
import com.example.rozgrywki.activity.druzyny.editor.EditorActivityDruzyny;
import com.example.rozgrywki.model.Druzyny;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivityDruzyny extends AppCompatActivity implements MainViewDruzyny {

    private static final int INTENT_EDIT = 200 ;
    private static final int INTENT_ADD = 100 ;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    MainPresenterDruzyny presenter;
    MainAdapterDruzyny adapter;
    MainAdapterDruzyny.ItemClickListener itemClickListener;

    List<Druzyny> druzyny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("DRUŻYNY");
        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.add);
        fab.setOnClickListener(view ->
            startActivityForResult(new Intent(this, EditorActivityDruzyny.class),
                    INTENT_ADD)
        );

        presenter = new MainPresenterDruzyny(this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int id_d = druzyny.get(position).getId_Druzyny();
            String nazwa_druzyny = druzyny.get(position).getNazwa_Druzyny();
            String wygrane = druzyny.get(position).getWygrane_Druzyny();
            String przegrane = druzyny.get(position).getPrzegrane_Druzyny();

            // Wpisywanie danych do edycji w pola komponenetow
            Intent intent = new Intent(this, EditorActivityDruzyny.class);
            intent.putExtra("id_d", id_d);
            intent.putExtra("nazwa_d", nazwa_druzyny);
            intent.putExtra("wygrane", wygrane);
            intent.putExtra("przegrane", przegrane);
            startActivityForResult(intent, INTENT_EDIT);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INTENT_ADD && resultCode == RESULT_OK){
            presenter.getData(); // reload date
        }
        else  if (requestCode == INTENT_EDIT && resultCode == RESULT_OK){
            presenter.getData(); // reload date
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
    public void onGetResult(List<Druzyny> druzynies) {
        adapter = new MainAdapterDruzyny(this, druzynies, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        druzyny = druzynies;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
