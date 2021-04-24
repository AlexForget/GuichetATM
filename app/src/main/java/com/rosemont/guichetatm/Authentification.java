package com.rosemont.guichetatm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import Class.GuichetATM;

public class Authentification extends AppCompatActivity {

    public final int REQUEST_FENETRE_PRINCIPALE = 1;
    GuichetATM guichet = new GuichetATM();
    int compteurs = 0;
    int nip;
    double soldeCheque;
    double soldeEpargne;
    double[] soldeChequeAdmin;
    double[] soldeEpargneAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickAuthentifier(View view) {
        EditText nomUtilisateur = findViewById(R.id.editxtNomUtilisateur);
        EditText txtNip = findViewById(R.id.editTxtNip);

        String nomUtilisateurAValier = nomUtilisateur.getText().toString();
        String nipAValider = txtNip.getText().toString();

        // Si c'est l'admin qui se connecte, intent vers l'écran d'administrateur
        if (nomUtilisateurAValier.equals("Admin") && nipAValider.equals("D001")) {
            Bundle extrasPourAdministrateur = guichet.getBundlePourAdministrateur();

            Intent ecranAdmin = new Intent(this, EcranAdministrateur.class);
            ecranAdmin.putExtras(extrasPourAdministrateur);

            startActivityForResult(ecranAdmin, REQUEST_FENETRE_PRINCIPALE);
            compteurs = 0;

        // Si ce n'est pas l'admin et que les info de login ne sont pas valide, envoie un message
        } else if (!guichet.validerUtilisateur(nomUtilisateurAValier, nipAValider)) {
            if (compteurs < 2){
                Toast.makeText(view.getContext(), "Nom d'utilisateur ou NIP invalide", Toast.LENGTH_SHORT).show();
            }
            compteurs++;
            if (compteurs == 3) {
                Toast.makeText(view.getContext(), "Trois échecs de connection. Veuillez réessayer de nouveau plus tard.", Toast.LENGTH_SHORT).show();
                finish();
                System.exit(0);
            }

        // Si les info de login sont bonne, mets les infos du clients dans un bundle
        // qui sera envoyé à l'activité du guichet
        } else {
            compteurs = 0;
            Bundle extrasPourGuichet = guichet.getBundlePourGuichet(nomUtilisateurAValier, Integer.parseInt(nipAValider));

            Intent versGuichet = new Intent(this, Guichet.class);
            versGuichet.putExtras(extrasPourGuichet);

            startActivityForResult(versGuichet, REQUEST_FENETRE_PRINCIPALE);
        }
    }

    // Récupération des valeur modifier dans l'activité guichet ou l'écran d'administrateur
    // pour apporter les modifications et mettre à jour la classe guichet
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent dataRetour) {
        super.onActivityResult(requestCode, resultCode, dataRetour);
        if (requestCode == REQUEST_FENETRE_PRINCIPALE){
            if (resultCode == 1){
                nip = dataRetour.getIntExtra("nip", 0);
                soldeCheque = dataRetour.getDoubleExtra("sldCheque", 0);
                soldeEpargne = dataRetour.getDoubleExtra("sldEpargne", 0);
                guichet.setGuichetPourTransaction(nip, soldeCheque, soldeEpargne, this);
            }
            if (resultCode == 2){
                soldeChequeAdmin = dataRetour.getDoubleArrayExtra("soldeCheques");
                soldeEpargneAdmin = dataRetour.getDoubleArrayExtra("soldeEpargnes");

                guichet.setGuichetPourAdministrateur(soldeChequeAdmin, soldeEpargneAdmin, this);
            }
        }

    }
}





















