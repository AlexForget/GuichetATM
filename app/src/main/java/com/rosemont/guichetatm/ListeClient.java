package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Class.GuichetATM;
import Class.Client;

public class ListeClient extends AppCompatActivity {

    GuichetATM guichet;

    // Récupère les information nécessaire et affiche la liste des clients
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_layout);

        guichet = new GuichetATM();

        List<Client> listeClient;
        listeClient = guichet.getClient();

        AndroidAdapterClient adapter = new AndroidAdapterClient(this, R.layout.activity_liste_client, listeClient);

        final ListView list = findViewById(R.id.listeModele);
        final TextView quantite = findViewById(R.id.txtQuantite);
        String nombreClient = getString(R.string.nombre_client) + " " + adapter.getCount();

        quantite.setText(nombreClient);

        list.setAdapter(adapter);
    }

}