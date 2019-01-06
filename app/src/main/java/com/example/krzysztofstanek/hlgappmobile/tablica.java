package com.example.krzysztofstanek.hlgappmobile;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class tablica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //hide the title bar

        setContentView(R.layout.activity_tablica);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        TextView test_ = findViewById(R.id.zalogowanyJakoNick);
        test_.setText(autoryzacja.nazwisko);
        Button bWyloguj = findViewById(R.id.bWyloguj);
        Button bBloki = findViewById(R.id.bBloki);
/*
        Button bEdycjaProfil = findViewById(R.id.bEdycjaProfil);



        Button bRegulamin = findViewById(R.id.bRegulamin);

        Button bProfil = findViewById(R.id.bProfil);

        Button bPrywatnosc = findViewById(R.id.bPrywatnosc);

        Button bImprezy = findViewById(R.id.bImprezy);

        Button bDodajimpreze = findViewById(R.id.bDodajimpreze);
        Button bChetni = findViewById(R.id.bChetni);*/

        bBloki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(tablica.this, blokiLista.class);
                tablica.this.startActivity(intent);
            }
        });


        bWyloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    autoryzacja.wyloguj();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(tablica.this, MainActivity.class);
                tablica.this.startActivity(intent);
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }
}