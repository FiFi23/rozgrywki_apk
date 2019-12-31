package com.example.rozgrywki.activity.druzyny.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rozgrywki.R;
import com.example.rozgrywki.activity.druzyny.main.MainActivityDruzynyMecze;
import com.example.rozgrywki.activity.druzyny.main.MainActivityDruzynyZawodnikow;

public class EditorActivityDruzyny extends AppCompatActivity implements EditorViewDruzyny {

    EditText et_nazwa_druzyny, et_wygrane, et_przegrane;
    ProgressDialog progressDialog;
    Button przycisk, przycisk2;

    EditorPresenterDruzyny presenter;

    int id_d;
    String nazwa_d, wygrane, przegrane;

    Menu actionMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_druzyny);
        getSupportActionBar().setTitle("Dodawanie drużyny");

        et_nazwa_druzyny = findViewById(R.id.pole_nazwa_druzyny);
        et_wygrane = findViewById(R.id.pole_wygrane);
        et_przegrane = findViewById(R.id.pole_przegrane);
        przycisk = findViewById(R.id.przycisk_do_przenoszenia);
        przycisk.setVisibility(View.GONE);
        przycisk2 = findViewById(R.id.przycisk_do_przenoszenia2);
        przycisk2.setVisibility(View.GONE);




        //Progress Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Proszę czekać...");

        presenter = new EditorPresenterDruzyny(this);

        Intent intent = getIntent();
        id_d = intent.getIntExtra("id_d", 0);
        nazwa_d = intent.getStringExtra("nazwa_d");
        wygrane = intent.getStringExtra("wygrane");
        przegrane = intent.getStringExtra("przegrane");

        setDataFromIntentExtra();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor,menu);
        actionMenu = menu;

        if (id_d != 0) {
            actionMenu.findItem(R.id.edit).setVisible(true);
            actionMenu.findItem(R.id.delete).setVisible(true);
            actionMenu.findItem(R.id.save).setVisible(false);
            actionMenu.findItem(R.id.update).setVisible(false);
        } else {
            actionMenu.findItem(R.id.edit).setVisible(false);
            actionMenu.findItem(R.id.delete).setVisible(false);
            actionMenu.findItem(R.id.save).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String nazwa_druzyny = et_nazwa_druzyny.getText().toString().trim();
        String wygrane = et_wygrane.getText().toString().trim();
        String przegrane = et_przegrane.getText().toString().trim();

        switch (item.getItemId()) {
            case R.id.save:
                if (nazwa_druzyny.isEmpty()) {
                    et_nazwa_druzyny.setError("Wpisz nazwę drużyny!");
                } else if (wygrane.isEmpty()) {
                    et_wygrane.setError("Wpisz ilość wygranych!");
                } else if (przegrane.isEmpty()){
                    {
                     et_przegrane.setError("Wpisz ilość przegranych!");
                    }}
                    else{
                        presenter.saveDruzyny(nazwa_druzyny, wygrane, przegrane);
                    }
                return true;

            case R.id.edit:

                editMode();
                actionMenu.findItem(R.id.edit).setVisible(false);
                actionMenu.findItem(R.id.delete).setVisible(false);
                actionMenu.findItem(R.id.save).setVisible(false);
                actionMenu.findItem(R.id.update).setVisible(true);

                return true;
            case R.id.update:
                //Update !!! TUTAJ MOŻNA DODAC sprawdzanie jeszcze walidacji przegrane

                if (nazwa_druzyny.isEmpty()) {
                    et_nazwa_druzyny.setError("Wpisz nazwę drużyny!");
                } else if (wygrane.isEmpty()) {
                    et_wygrane.setError("Wpisz ilość wygranych!");
                } else if (przegrane.isEmpty()){
                    {
                        et_przegrane.setError("Wpisz ilość przegranych!");
                    }}
                else{
                    presenter.updateDruzyny(id_d, nazwa_druzyny, wygrane, przegrane);
                }

                return true;

            case R.id.delete:

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("UWAGA!");
                alertDialog.setMessage("Czy chcesz usunąć " + nazwa_druzyny + "?");
                alertDialog.setNegativeButton("Tak", (dialog, which) -> {
                    dialog.dismiss();
                    presenter.deleteDruzyny(id_d);
                });
                alertDialog.setPositiveButton("Nie",
                        (dialog, which) -> dialog.dismiss());

                alertDialog.show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(EditorActivityDruzyny.this,
                message,
                Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish(); //back to main activity
    }

    @Override
    public Void onRequestError(String message) {
        Toast.makeText(EditorActivityDruzyny.this,
                message,
                Toast.LENGTH_SHORT).show();
        return null;
    }

    private void setDataFromIntentExtra() {

        if (id_d != 0) {
            et_nazwa_druzyny.setText(nazwa_d);
            et_wygrane.setText(wygrane);
            et_przegrane.setText(przegrane);
            String id_d_string = Integer.toString(id_d);
            przycisk.setVisibility(View.VISIBLE);
            przycisk.setText("Zawodnicy");
            przycisk2.setVisibility(View.VISIBLE);
            przycisk2.setText("Mecze");

            //otwieranie activity z zawodnikami
            przycisk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MainActivityDruzynyZawodnikow.class);
                    intent.putExtra("id_d", id_d_string);
                    intent.putExtra("nazwa_d", nazwa_d);
                    view.getContext().startActivity(intent);}
            });

            //otwieranie activity z meczemi
            przycisk2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MainActivityDruzynyMecze.class);
                    intent.putExtra("id_d", id_d_string);
                    intent.putExtra("nazwa_d", nazwa_d);
                    view.getContext().startActivity(intent);}
            });


            getSupportActionBar().setTitle("Edycja drużyny");
            readMode();

        } else {
            editMode();
        }
    }

    private void editMode() {
        et_nazwa_druzyny.setFocusableInTouchMode(true);
        et_wygrane.setFocusableInTouchMode(true);
        et_przegrane.setFocusableInTouchMode(true);
    }

    private void readMode() {
        et_nazwa_druzyny.setFocusableInTouchMode(false);
        et_wygrane.setFocusableInTouchMode(false);
        et_przegrane.setFocusableInTouchMode(false);
        et_przegrane.setFocusable(false);
        et_nazwa_druzyny.setFocusable(false);
        et_wygrane.setFocusable(false);
    }
}




