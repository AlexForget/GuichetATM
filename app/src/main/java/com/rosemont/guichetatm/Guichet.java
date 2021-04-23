package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Class.GuichetATM;
import Class.Cheque;
import Class.Epargne;

public class Guichet extends AppCompatActivity {
    private static final String TAG = "Message";
    private static final String cleValue = "Value";
    String montant = "0";

    int nip;
    String prenom;
    String nom;
    String utilisateur;
    String numCptCheque;
    String numCptEpargne;
    double soldeCheque;
    double soldeEpargne;

    GuichetATM guichet = new GuichetATM();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guichet);

        if(savedInstanceState != null){
            montant = savedInstanceState.getString(cleValue);
        }

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        nip = extras.getInt("nip");
        utilisateur = extras.getString("utilisateur");
        soldeCheque = extras.getDouble("soldeChqs");
        soldeEpargne = extras.getDouble("soldeEpa");
        numCptCheque = extras.getString("numCptChqs");
        numCptEpargne = extras.getString("numCptEpa");
        prenom = extras.getString("prenom");
        nom = extras.getString("nom");

        guichet.setGuichetPourTransaction(nip, soldeCheque, soldeEpargne, this);

        TextView bonjour = findViewById(R.id.txtBonjour);
        bonjour.setText(String.format("Bonjour %s %s", prenom, nom));

        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString(cleValue, montant);

        EditText montantEntre = findViewById(R.id.edtTxtMontantEntre);
        montantEntre.setText(montant);

        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            montant = savedInstanceState.getString(cleValue);
        }
        EditText montantEntre = findViewById(R.id.edtTxtMontantEntre);
        montantEntre.setText(montant);

        Log.i(TAG, "onRestoreInstanceState");
    }

    // Cré un bundle pour retourner les valeurs modifié à l'activité précédente
    public void onClickDeconnection(View view) {
        Bundle dataRetour = new Bundle();

        dataRetour.putInt("nip", nip);
        dataRetour.putDouble("sldCheque", soldeCheque);
        dataRetour.putDouble("sldEpargne", soldeEpargne);

        Intent retourConnection = new Intent(this, MainActivity.class);
        setResult(1, retourConnection);
        retourConnection.putExtras(dataRetour);
        finish();
    }

    public void onClickEtatCompte(View view) {
        TextView soldeChqs = findViewById(R.id.txtSoldeCheque);
        TextView soldeEpa = findViewById(R.id.txtSoldeEpargne);

        soldeChqs.setText(String.format("Solde du compte chèque : %s", soldeCheque));
        soldeEpa.setText(String.format("Solde du compte épargne : %s", soldeEpargne));
    }

    public double getSoldeCheque() {
        double retour = 0;

        for (Cheque chqs : guichet.getComptesCheque()) {
            if (nip == chqs.getNip()) {
                retour = chqs.getSolde();
            }
        }
        return retour;
    }

    public double getSoldeEpargne() {
        double retour = 0;

        for (Epargne ep : guichet.getComptesEpargne()) {
            if (nip == ep.getNip()) {
                retour = ep.getSolde();
            }
        }
        return retour;
    }

    public void onClickSoumettre(View view) {
        RadioButton rdbDepot = findViewById(R.id.rdbDepot);
        RadioButton rdbRetrait = findViewById(R.id.rdbRetrait);
        RadioButton rdbVirement = findViewById(R.id.rdbVirement);
        RadioButton rdbCheque = findViewById(R.id.rdbCheque);
        RadioButton rdbEpargne = findViewById(R.id.rdbEpargne);
        EditText montantEntre = findViewById(R.id.edtTxtMontantEntre);
        TextView soldeChqs = findViewById(R.id.txtSoldeCheque);
        TextView soldeEpa = findViewById(R.id.txtSoldeEpargne);
        double montantDouble = Double.parseDouble(montant);
        String chaine = "";

        if (rdbRetrait.isChecked() && rdbCheque.isChecked() && montantDouble > 0) {
            chaine = guichet.retraitCheque(nip, montantDouble);
            soldeCheque = getSoldeCheque();
        }
        if (rdbDepot.isChecked() && rdbCheque.isChecked() && montantDouble > 0) {
            chaine = guichet.depotCheque(nip, montantDouble);
            soldeCheque = getSoldeCheque();
        }
        if (rdbDepot.isChecked() && rdbEpargne.isChecked() && montantDouble > 0) {
            chaine = guichet.depotEpargne(nip, montantDouble);
            soldeEpargne = getSoldeEpargne();
        }
        if (rdbRetrait.isChecked() && rdbEpargne.isChecked() && montantDouble > 0) {
            chaine = guichet.retraitEpargne(nip, montantDouble);
            soldeEpargne = getSoldeEpargne();
        }
        if (rdbVirement.isChecked() && rdbCheque.isChecked() && montantDouble > 0) {
            chaine = guichet.virementVersCheque(nip, montantDouble);
            soldeEpargne = getSoldeEpargne();
            soldeCheque = getSoldeCheque();
        }
        if (rdbVirement.isChecked() && rdbEpargne.isChecked() && montantDouble > 0) {
            chaine = guichet.virementVersEpargne(nip, montantDouble);
            soldeEpargne = getSoldeEpargne();
            soldeCheque = getSoldeCheque();
        }

        if (!chaine.equals("")) {
            Toast.makeText(view.getContext(), chaine, Toast.LENGTH_SHORT).show();
            montant = "0";
            montantEntre.setText("0");
            soldeChqs.setText("");
            soldeEpa.setText("");
            onClickEtatCompte(view);
        }
    }

    public void onClickZero(View view) {
        if (!montant.equals("0") && !montant.equals("")) {
            ajouterCharacter("0");
        }
    }

    public void onClickUn(View view) {
        ajouterCharacter("1");
    }

    public void onClickDeux(View view) {
        ajouterCharacter("2");
    }

    public void onClickTrois(View view) {
        ajouterCharacter("3");
    }

    public void onClickQuatre(View view) {
        ajouterCharacter("4");
    }

    public void onClickCinq(View view) {
        ajouterCharacter("5");
    }

    public void onClickSix(View view) {
        ajouterCharacter("6");
    }

    public void onClickSept(View view) {
        ajouterCharacter("7");
    }

    public void onClickHuit(View view) {
        ajouterCharacter("8");
    }

    public void onClickNeuf(View view) {
        ajouterCharacter("9");
    }

    public void onClickPoint(View view) {
        ajouterCharacter(".");
    }

    public void onClcikEffacer(View view) {
        EditText montantEntre = findViewById(R.id.edtTxtMontantEntre);

        montant = "0";
        montantEntre.setText("0");
    }

    public void ajouterCharacter(String c) {
        EditText montantEntre = findViewById(R.id.edtTxtMontantEntre);

        if (montant.equals("0") && validerMontant(montant + c)) {
            montant = c;
            montantEntre.setText(montant);
            return;
        }
        if (validerMontant(montant + c)) {
            montant += c;
            montantEntre.setText(montant);
        }
    }

    public boolean validerMontant(String aValider) {
        final String SEQUENCE;

        Pattern pattern;
        Matcher matcher;
        SEQUENCE = "^[\\d]+[.]?[\\d]{0,2}$";
        pattern = Pattern.compile(SEQUENCE);
        matcher = pattern.matcher(aValider);

        return matcher.matches();
    }


}