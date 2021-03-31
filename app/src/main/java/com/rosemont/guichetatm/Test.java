package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import Class.*;

public class Test extends AppCompatActivity {
    Epargne e1 = new Epargne(111, "abc", 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }

    public void onClcikTest(View view) {
        TextView affichage = findViewById(R.id.affichageCompte);


        affichage.setText(e1.toString());


    }

    public void onClickRetrait(View view) {
        TextView transaction = findViewById(R.id.transaction);

        transaction.setText(e1.retrait(1000));
    }

    public void onClickDepot(View view) {
        TextView transaction = findViewById(R.id.transaction);

        transaction.setText(e1.depot(2000));
    }

    public void onClickInteret(View view) {
        TextView transaction = findViewById(R.id.transaction);

        transaction.setText(e1.payerInteret());
    }
}