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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_client);

        guichet = new GuichetATM();

        List<Client> listeClient = new ArrayList<Client>();
        listeClient = guichet.getClient();


        AndroidAdapter adapter = new AndroidAdapter(this, R.layout.liste_layout_client, listeClient);

        final ListView list = findViewById(R.id.listeClient);
        final TextView quantite = findViewById(R.id.txtListeClient);
        String nombreClient = getString(R.string.nombre_client) + " " + adapter.getCount();

        quantite.setText(nombreClient);

        list.setAdapter(adapter);
    }

}