package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import Class.Epargne;
import Class.GuichetATM;

public class EcranAdministrateur extends AppCompatActivity {
    GuichetATM guichet = new GuichetATM();
    double[] soldesCheques;
    double[] soldesEpargne;

    // Récupération du bundle à la création de l'activité et mise à jour de la classe guichetATM
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

    // Appel la fonction dans la class guichetATM qui permet de payer les intérêts
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

    // Permet l'affichage de la liste des comptes chèques
    public void onClickListeCheque(View view) {

        Bundle extrasPourAdministrateur = guichet.getBundlePourAdministrateur();

        Intent listeCheque = new Intent(this, ListeCompteCheque.class);
        listeCheque.putExtras(extrasPourAdministrateur);

        startActivity(listeCheque);
    }

    // Permet l'affichage de la liste des comptes épargnes
    public void onClickListeEpargne(View view) {

        Bundle extrasPourAdministrateur = guichet.getBundlePourAdministrateur();

        Intent listeEpargne = new Intent(this, ListeCompteEpargne.class);
        listeEpargne.putExtras(extrasPourAdministrateur);

        startActivity(listeEpargne);
    }

    // Permet l'affichage de la liste des clients
    public void onClickListeClient(View view) {
        Intent listeClient = new Intent(this, ListeClient.class);
        startActivity(listeClient);
    }

    // Retourne à l'activité précédente et envoi un bundle avec les modifications faits
    // dans les valeurs des comptes
    public void onClickDeconnectionAdmin(View view) {
        Bundle dataRetour = new Bundle();

        dataRetour.putDoubleArray("soldeEpargnes", soldesEpargne);
        dataRetour.putDoubleArray("soldeCheques", soldesCheques);

        Intent retourConnection = new Intent(this, Authentification.class);
        setResult(2, retourConnection);
        retourConnection.putExtras(dataRetour);
        finish();
    }

    // Fait la même chose que le bouton déconnection. J'override la fonction onBackPressed
    // pour pouvoir récupérer mon bundle si l'utilisateur appuie sur la touche retour
    @Override
    public void onBackPressed()
    {
        Bundle dataRetour = new Bundle();

        dataRetour.putDoubleArray("soldeEpargnes", soldesEpargne);
        dataRetour.putDoubleArray("soldeCheques", soldesCheques);

        Intent retourConnection = new Intent(this, Authentification.class);
        setResult(2, retourConnection);
        retourConnection.putExtras(dataRetour);
        finish();
    }
}