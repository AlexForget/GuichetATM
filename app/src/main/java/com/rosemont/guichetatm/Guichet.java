package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Guichet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guichet);
    }

    public void onClickDeconnection(View view) {
        setResult(RESULT_OK);
        finish();
    }

    public void onClickZero(View view) {
        EditText montant = findViewById(R.id.edtTxtMontantEntre);
        montant.setText("0");  // Pour test!!!
    }

    public void onClickEtatCompte(View view) {
        Intent ecranAdministrateur = new Intent(this, EcranAdministrateur.class);
        startActivity(ecranAdministrateur);
    }
}