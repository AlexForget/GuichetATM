package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Class.GuichetATM;
import Class.Epargne;

public class ListeCompteEpargne extends AppCompatActivity {

    GuichetATM guichet = new GuichetATM();
    double[] soldesCheques;
    double[] soldesEpargne;

    // Récupère les information nécessaire et affiche la liste des comptes épargnes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_layout);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        soldesCheques = extras.getDoubleArray("soldeCheques");
        soldesEpargne = extras.getDoubleArray("soldeEpargnes");

        guichet.setGuichetPourAdministrateur(soldesCheques, soldesEpargne, this);

        List<Epargne> listeEpargne;
        listeEpargne = guichet.getComptesEpargne();

        AndroidAdapterEpargne adapter = new AndroidAdapterEpargne(this, R.layout.activity_liste_compte_epargne, listeEpargne);

        final ListView list = findViewById(R.id.listeModele);
        final TextView quantite = findViewById(R.id.txtQuantite);
        String nombreEpargne = getString(R.string.nombre_epargne) + " " + adapter.getCount();

        quantite.setText(nombreEpargne);

        list.setAdapter(adapter);
    }
}