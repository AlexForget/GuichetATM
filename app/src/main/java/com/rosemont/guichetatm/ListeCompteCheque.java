package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Class.GuichetATM;
import Class.Cheque;

public class ListeCompteCheque extends AppCompatActivity {

    GuichetATM guichet = new GuichetATM();
    double[] soldesCheques;
    double[] soldesEpargne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_layout);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        soldesCheques = extras.getDoubleArray("soldeCheques");
        soldesEpargne = extras.getDoubleArray("soldeEpargnes");

        guichet.setGuichetPourAdministrateur(soldesCheques, soldesEpargne, this);

        List<Cheque> listeCheque;
        listeCheque = guichet.getComptesCheque();

        AndroidAdapterCheque adapter = new AndroidAdapterCheque(this, R.layout.activity_liste_compte_cheque, listeCheque);

        final ListView list = findViewById(R.id.listeModele);
        final TextView quantite = findViewById(R.id.txtQuantite);
        String nombreCheque = getString(R.string.nombre_cheque) + " " + adapter.getCount();

        quantite.setText(nombreCheque);

        list.setAdapter(adapter);
    }
}