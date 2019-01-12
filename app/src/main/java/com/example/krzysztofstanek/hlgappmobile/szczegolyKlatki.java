package com.example.krzysztofstanek.hlgappmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class szczegolyKlatki extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_szczegoly_klatki);

        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("id");

        //OBSLUGA DANYCH
        API api = new API();
        Map<String, String> dane_Tmp = new HashMap<>();
        try {
            dane_Tmp = api.pobierzDaneKlatki(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
        Map<String, String> dane = new HashMap<>();
        try {
            dane = api.createArrayFromJSON(dane_Tmp.get("data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*TextView nazwaBloku = findViewById(R.id.nazwaBloku);
        nazwaBloku.setText(dane.get("blok"));*/

        TextView vlan = findViewById(R.id.nrKlatkiContent);
        vlan.setText(dane.get("nr_klatki"));

        TextView vlanStatus = findViewById(R.id.nrMieszkaniaContent);
        vlanStatus.setText(dane.get("nr_mieszkan"));

        TextView dosyl = findViewById(R.id.KluczeContent);
        dosyl.setText(dane.get("klucze_piwnica_klatka"));

        TextView uwagi = findViewById(R.id.UwagiContent);
        uwagi.setText(dane.get("uwagi_klatka"));

        Button bSwitche = findViewById(R.id.bSwitche);
        final Map<String, String> finalDane = dane;
        bSwitche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(szczegolyKlatki.this, switcheLista.class);
                intent.putExtra("id", finalDane.get("nr_klatki"));
                szczegolyKlatki.this.startActivity(intent);
            }
        });


        Button bPowrot = findViewById(R.id.bPowrot);
        bPowrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(szczegolyKlatki.this, klatkiLista.class);
                intent.putExtra("id", id);
                szczegolyKlatki.this.startActivity(intent);
            }
        });


    }

}
