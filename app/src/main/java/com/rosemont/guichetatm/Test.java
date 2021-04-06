package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import Class.*;

public class Test extends AppCompatActivity {
    Client c1 = new Client("aa", "aa", "MouF", 1234);
    Client c2 = new Client("aa", "bb", "MouF", 1234);
    Client c3 = new Client(c1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }

    public void onClcikTest(View view) {
        TextView affichage = findViewById(R.id.affichageCompte);
        String test = "";




        affichage.setText(Integer.toString(c2.compareTo(c1)));




    }

    public void onClickRetrait(View view) {
        TextView transaction = findViewById(R.id.transaction);


    }

    public void onClickDepot(View view) {
        TextView transaction = findViewById(R.id.transaction);


    }

    public void onClickInteret(View view) {
        TextView transaction = findViewById(R.id.transaction);


    }
}