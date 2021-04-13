package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Guichet extends AppCompatActivity {
    private static final String TAG = "Message";
    private static final String cleValue = "Value";
    String montant = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guichet);

        if(savedInstanceState != null){
            montant = savedInstanceState.getString(cleValue);
        }

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




    public void onClickDeconnection(View view) {
        setResult(RESULT_OK);
        finish();
    }

    public void onClickEtatCompte(View view) {
        Intent ecranAdministrateur = new Intent(this, EcranAdministrateur.class);
        startActivity(ecranAdministrateur);
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

        montant = "";
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
            montant = montant + c;
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