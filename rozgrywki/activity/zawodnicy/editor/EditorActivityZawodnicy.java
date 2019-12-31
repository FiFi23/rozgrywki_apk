package com.example.rozgrywki.activity.zawodnicy.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rozgrywki.R;
import com.example.rozgrywki.api.ApiClient;
import com.example.rozgrywki.api.ApiInterface;
import com.example.rozgrywki.model.Druzyny;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivityZawodnicy extends AppCompatActivity implements EditorViewZawodnicy {

    EditText et_imie;
    ProgressDialog progressDialog;
    Spinner lista_rozwijana;

    EditorPresenterZawodnicy presenter;
    List<Druzyny> ListaDruzyn;

    int id_z;
    String id_d, imie;

    Menu actionMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_zawodnicy);
        getSupportActionBar().setTitle("Dodawanie zawodnika");

        et_imie = findViewById(R.id.pole_imie);




        //Progress Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Proszę czekać...");

        presenter = new EditorPresenterZawodnicy(this);

        // Odebranie wartości do "nazw" znajdujących się w  takich samych nazwa w ""
        // w main activity na zielono, muszą być takie same
        Intent intent = getIntent();
        id_z = intent.getIntExtra("id_z", 0);
        id_d = intent.getStringExtra("id_d");
        imie = intent.getStringExtra("imie");




        // Lista rozwijana na sztywno
        lista_rozwijana = findViewById(R.id.lista_rozwijana);

        //Stworzenie tabeli z druzynami
        ListaDruzyn = new ArrayList<>();
        Druzyny pierwszy = new Druzyny(0, "Wybierz drużynę:");
        ListaDruzyn.add(pierwszy);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Druzyny>> call = apiInterface.getDruzyny();

        call.enqueue(new Callback<List<Druzyny>>() {
            @Override
            public void onResponse(Call<List<Druzyny>> call, Response<List<Druzyny>> response) {

                List<Druzyny> posts = response.body();

                for (Druzyny post : posts) {
                    Druzyny aktualny = new Druzyny(post.getId_Druzyny(), post.getNazwa_Druzyny());
                    ListaDruzyn.add(aktualny);
                    if (id_d != null){
                        int id_d_int = Integer.parseInt(id_d);
                        if (id_d_int == post.getId_Druzyny()){
                            lista_rozwijana.setSelection(ListaDruzyn.indexOf(aktualny));
                        }

                    }


                }
            }

            @Override
            public void onFailure(Call<List<Druzyny>> call, Throwable t) {
            }
        });

        ArrayAdapter<Druzyny> adapter = new ArrayAdapter<Druzyny>(this, android.R.layout.simple_spinner_item, ListaDruzyn);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        lista_rozwijana.setAdapter(adapter);

        setDataFromIntentExtra();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor,menu);
        actionMenu = menu;

        if (id_z != 0) {
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

        String imie = et_imie.getText().toString().trim();
        // Stworzenie obiektu "drużyna", która jest przypisana do wybranej pozycji
        Druzyny druzyna = (Druzyny) lista_rozwijana.getSelectedItem();
        //Pobranie id metodą i zapisanie jej do inta
        int przejsciowe = druzyna.getId_Druzyny();
        //konwersja inta na stringa
        String id_d = Integer.toString(przejsciowe);

        switch (item.getItemId()) {
            case R.id.save:
                //Save
                if (imie.isEmpty()) {
                    et_imie.setError("Wpisz imię zawodnika!");
                } else {
                    presenter.saveZawodnicy(id_d, imie);
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
                //Update

                if (imie.isEmpty()) {
                    et_imie.setError("Wpisz dane zawodnika!");
                } else {
                    presenter.updateZawodnicy(id_z, id_d, imie);
                }

                return true;

            case R.id.delete:

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("UWAGA!");
                alertDialog.setMessage("Czy chcesz usunąć " + imie + "?");
                alertDialog.setNegativeButton("Tak", (dialog, which) -> {
                    dialog.dismiss();
                    presenter.deleteZawodnicy(id_z);
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
        Toast.makeText(EditorActivityZawodnicy.this,
                message,
                Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish(); //back to main activity
    }

    @Override
    public Void onRequestError(String message) {
        Toast.makeText(EditorActivityZawodnicy.this,
                message,
                Toast.LENGTH_SHORT).show();
        return null;
    }

    private void setDataFromIntentExtra() {

        if (id_z != 0) {
            et_imie.setText(imie);

            getSupportActionBar().setTitle("Edycja zawodnika");
            readMode();

        } else {
            editMode();
        }
    }

    private void editMode() {

        et_imie.setFocusableInTouchMode(true);
        lista_rozwijana.setEnabled(true);
    }

    private void readMode() {
        et_imie.setFocusableInTouchMode(false);
        et_imie.setFocusable(false);
        lista_rozwijana.setEnabled(false);
    }
}




