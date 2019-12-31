package com.example.rozgrywki.activity.mecze.editor;

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

public class EditorActivityMecze extends AppCompatActivity implements EditorViewMecze {

    EditText et_wynik_gospodarz,et_wynik_gosc, et_data_meczu;
    ProgressDialog progressDialog;
    Spinner rozwijana_druzyna_gospodarza, rozwijana_druzyna_gosc;

    EditorPresenterMecze presenter;
    List<Druzyny> ListaDruzyn;

    int id_m;
    String id_gosp, id_gosc, wynik_gospodarza,wynik_gosc, data_meczu;

    Menu actionMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_mecze);
        getSupportActionBar().setTitle("Dodawanie meczu");

        et_wynik_gospodarz = findViewById(R.id.wynik_gospodarz);
        et_wynik_gosc = findViewById(R.id.wynik_gosc);
        et_data_meczu = findViewById(R.id.data_meczu);




        //Progress Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Proszę czekać...");

        presenter = new EditorPresenterMecze(this);


        Intent intent = getIntent();
        id_m = intent.getIntExtra("id_m", 0);
        id_gosp = intent.getStringExtra("id_gosp");
        id_gosc = intent.getStringExtra("id_gosc");
        wynik_gospodarza = intent.getStringExtra("wynik_gosp");
        wynik_gosc = intent.getStringExtra("wynik_gosc");
        data_meczu = intent.getStringExtra("data");

        // Lista rozwijana na sztywno
        rozwijana_druzyna_gospodarza = findViewById(R.id.rozwijana_druzyna_gospodarza);
        rozwijana_druzyna_gosc = findViewById(R.id.rozwijana_druzyna_gosc);


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
                    if (id_gosp != null){
                        int id_d_int = Integer.parseInt(id_gosp);
                        if (id_d_int == post.getId_Druzyny()){
                            rozwijana_druzyna_gospodarza.setSelection(ListaDruzyn.indexOf(aktualny));
                        }

                    }
                    if (id_gosc != null){
                        int id_d_int = Integer.parseInt(id_gosc);
                        if (id_d_int == post.getId_Druzyny()){
                            rozwijana_druzyna_gosc.setSelection(ListaDruzyn.indexOf(aktualny));
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

        rozwijana_druzyna_gospodarza.setAdapter(adapter);
        rozwijana_druzyna_gosc.setAdapter(adapter);

        setDataFromIntentExtra();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor,menu);
        actionMenu = menu;

        if (id_m != 0) {
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

        String wynik_gosp = et_wynik_gospodarz.getText().toString().trim();
        String wynik_gosci = et_wynik_gosc.getText().toString().trim();
        String data = et_data_meczu.getText().toString().trim();
        Druzyny druzyna = (Druzyny) rozwijana_druzyna_gospodarza.getSelectedItem();
        int przejsciowe = druzyna.getId_Druzyny();
        String id_gospo = Integer.toString(przejsciowe);
        Druzyny druzynka = (Druzyny) rozwijana_druzyna_gosc.getSelectedItem();
        int przejsciowee = druzynka.getId_Druzyny();
        String id_gos = Integer.toString(przejsciowee);

        switch (item.getItemId()) {
            case R.id.save:
                //Save
                if (data.isEmpty()) {
                    et_data_meczu.setError("Wpisz datę!");
                } else {
                    presenter.saveMecze(id_gospo, id_gos, wynik_gosp, wynik_gosci, data );
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

                if (data.isEmpty()) {
                    et_data_meczu.setError("Wpisz datę!");
                } else {
                    presenter.updateMecze(id_m,id_gospo, id_gos, wynik_gosp, wynik_gosci, data );
                }

                return true;

            case R.id.delete:

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("UWAGA!");
                alertDialog.setMessage("Czy chcesz usunąć wybrany mecz?");
                alertDialog.setNegativeButton("Tak", (dialog, which) -> {
                    dialog.dismiss();
                    presenter.deleteMecze(id_m);
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
        Toast.makeText(EditorActivityMecze.this,
                message,
                Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish(); //back to main activity
    }

    @Override
    public Void onRequestError(String message) {
        Toast.makeText(EditorActivityMecze.this,
                message,
                Toast.LENGTH_SHORT).show();
        return null;
    }

    private void setDataFromIntentExtra() {

        if (id_m != 0) {
            et_wynik_gospodarz.setText(wynik_gospodarza);
            et_wynik_gosc.setText(wynik_gosc);
            et_data_meczu.setText(data_meczu);

            getSupportActionBar().setTitle("Edycja meczu");
            readMode();

        } else {
            editMode();
        }
    }

    private void editMode() {
        et_wynik_gospodarz.setFocusableInTouchMode(true);
        et_wynik_gosc.setFocusableInTouchMode(true);
        et_data_meczu.setFocusableInTouchMode(true);
        rozwijana_druzyna_gospodarza.setEnabled(true);
        rozwijana_druzyna_gosc.setEnabled(true);
    }

    private void readMode() {
        et_wynik_gospodarz.setFocusableInTouchMode(false);
        et_wynik_gospodarz.setFocusable(false);
        et_wynik_gosc.setFocusableInTouchMode(false);
        et_wynik_gosc.setFocusable(false);
        et_data_meczu.setFocusableInTouchMode(false);
        et_data_meczu.setFocusable(false);
        rozwijana_druzyna_gospodarza.setEnabled(false);
        rozwijana_druzyna_gosc.setEnabled(false);
    }
}




