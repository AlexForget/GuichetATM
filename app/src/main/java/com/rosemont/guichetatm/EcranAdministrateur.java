package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class EcranAdministrateur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_administrateur);
    }

    public void onClickTemporaire(View view) {
        setResult(RESULT_OK);
        finish();
    }
}