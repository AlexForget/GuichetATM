package com.rosemont.guichetatm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import Class.GuichetATM;

public class MainActivity extends AppCompatActivity {

    public final int REQUEST_FENETRE_PRINCIPALE = 1;
    GuichetATM guichet = new GuichetATM();
    int nip;
    String utilisateur;
    double soldeCheque;
    double soldeEpargne;

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
        if (nomUtilisateurAValier.equals("Admin") && nipAValider.equals("111")) {
            Intent ecranAdmin = new Intent(this, EcranAdministrateur.class);
            startActivity(ecranAdmin);

        // Si ce n'est pas l'admin et que les info de login ne sont pas valide, envoie un message
        } else if (!guichet.validerUtilisateur(nomUtilisateurAValier, nipAValider)) {
            Toast.makeText(view.getContext(), "Nom d'utilisateur ou NIP invalide", Toast.LENGTH_SHORT).show();

        // Si les info de login sont bonne, mets les infos du clients dans un bundle
        // qui sera envoyé à l'activité du guichet
        } else {
            Bundle extras = guichet.getBundle(nomUtilisateurAValier, Integer.parseInt(nipAValider));

            Intent versGuichet = new Intent(this, Guichet.class);
            versGuichet.putExtras(extras);

            startActivityForResult(versGuichet, REQUEST_FENETRE_PRINCIPALE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent dataRetour) {
        super.onActivityResult(requestCode, resultCode, dataRetour);
        if (requestCode == REQUEST_FENETRE_PRINCIPALE){
            if (resultCode == 1){
                nip = dataRetour.getIntExtra("nip", 0);
                soldeCheque = dataRetour.getDoubleExtra("sldCheque", 0);
                soldeEpargne = dataRetour.getDoubleExtra("sldEpargne", 0);
                guichet.setGuichet(nip, soldeCheque, soldeEpargne, this);
            }
        }
    }
}





















