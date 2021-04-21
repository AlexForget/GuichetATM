package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import Class.GuichetATM;
import Class.Client;
import Class.Cheque;
import Class.Epargne;
import Class.Compte;

public class Test extends AppCompatActivity {
    Client c1 = new Client("Max", "Bob", "MouF", 1234);
    Compte ch1 = new Cheque(1111, "5h5h", 500);
    Compte ep1 = new Epargne(2222, "1b1b", 10000);
    GuichetATM g1 = new GuichetATM();

    //GuichetATM g2 = new GuichetATM(g1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }

    public void onClcikTest(View view) {
        TextView affichage = findViewById(R.id.affichageCompte);
        String test = "";

        test = g1.toString();

        affichage.setText(test);

        c1.setNom("aaa");
        c1.setPrenom("aaa");
        ch1.setNip(555555);
        ep1.setNip(666666);

    }

    public void onClickRetrait(View view) {
        TextView affichage = findViewById(R.id.affichageCompte);
        String test = "";

        //test = g2.toString();

        affichage.setText(test);


    }

    public void onClickDepot(View view) {
        TextView transaction = findViewById(R.id.transaction);


    }

    public void onClickInteret(View view) {
        TextView transaction = findViewById(R.id.transaction);


    }
}