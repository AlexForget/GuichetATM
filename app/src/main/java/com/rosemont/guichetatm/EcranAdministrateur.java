package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Class.Epargne;
import Class.GuichetATM;
import Class.Client;

public class EcranAdministrateur extends AppCompatActivity {
    GuichetATM guichet = new GuichetATM();
    double[] soldesCheques;
    double[] soldesEpargne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_administrateur);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        soldesCheques = extras.getDoubleArray("soldeCheques");
        soldesEpargne = extras.getDoubleArray("soldeEpargnes");

        guichet.setGuichetPourAdministrateur(soldesCheques, soldesEpargne, this);
    }

    public void onClickPaiementInterets(View view) {
        double interet = guichet.paiementInterets() * 100;
        soldesEpargne = getSoldesEparnge();

        Toast.makeText(view.getContext(), "Les intérêts de " + interet + "% ont été payées.", Toast.LENGTH_SHORT).show();
    }

    public double[] getSoldesEparnge() {
        List<Epargne> listEpargne = guichet.getComptesEpargne();
        double[] sldEpargne = new double[listEpargne.size()];

        for (int i = 0; i < listEpargne.size(); i++) {
            sldEpargne[i] = listEpargne.get(i).getSolde();
        }
        return sldEpargne;
    }

    public void onClickListeCheque(View view) {

        Bundle extrasPourAdministrateur = guichet.getBundlePourAdministrateur();

        Intent listeCheque = new Intent(this, ListeCompteCheque.class);
        listeCheque.putExtras(extrasPourAdministrateur);

        startActivity(listeCheque);
    }

    public void onClickListeEpargne(View view) {

        Bundle extrasPourAdministrateur = guichet.getBundlePourAdministrateur();

        Intent listeEpargne = new Intent(this, ListeCompteEpargne.class);
        listeEpargne.putExtras(extrasPourAdministrateur);

        startActivity(listeEpargne);
    }

    public void onClickListeClient(View view) {
        Intent listeClient = new Intent(this, ListeClient.class);
        startActivity(listeClient);
    }

    public void onClickDeconnectionAdmin(View view) {
        Bundle dataRetour = new Bundle();

        dataRetour.putDoubleArray("soldeEpargnes", soldesEpargne);
        dataRetour.putDoubleArray("soldeCheques", soldesCheques);

        Intent retourConnection = new Intent(this, MainActivity.class);
        setResult(2, retourConnection);
        retourConnection.putExtras(dataRetour);
        finish();
    }
}