package com.rosemont.guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickAuthentifier(View view) {
        //Intent guichet = new Intent(this, Guichet.class);
        Intent test = new Intent(this, Test.class);
        startActivity(test);
    }
}