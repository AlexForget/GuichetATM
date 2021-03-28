package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EcranAdministrateur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_administrateur);
    }

    public void onClickListeCheque(View view) {
        Intent listeCheque = new Intent(this, ListeCompteCheque.class);
        startActivity(listeCheque);
    }

    public void onClickListeEpargne(View view) {
        Intent listeEpargne = new Intent(this, ListeCompteEpargne.class);
        startActivity(listeEpargne);
    }

    public void onClickListeClient(View view) {
        Intent listeClient = new Intent(this, ListeClient.class);
        startActivity(listeClient);
    }
}