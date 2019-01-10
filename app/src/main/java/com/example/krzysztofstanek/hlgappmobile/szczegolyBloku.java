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

public class szczegolyBloku extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_szczegoly_bloku);

        Intent myIntent = getIntent();
        String id = myIntent.getStringExtra("id");

        //OBSLUGA DANYCH
        API api = new API();
        Map<String, String> dane_Tmp = new HashMap<>();
        try {
            dane_Tmp = api.pobierzDaneBloku(id);
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

        TextView nazwaBloku = findViewById(R.id.nazwaBloku);
        nazwaBloku.setText(dane.get("blok"));

        TextView vlan = findViewById(R.id.vlanContent);
        vlan.setText(dane.get("vlan"));

        TextView vlanStatus = findViewById(R.id.vlanStatusContent);
        vlanStatus.setText(dane.get("vlan_status"));

        TextView dosyl = findViewById(R.id.DosylContent);
        dosyl.setText(dane.get("dosyl_blok"));

        TextView bezpieczniki = findViewById(R.id.BezpiecznikiContent);
        bezpieczniki.setText(dane.get("bezpieczniki"));

        TextView uwagi = findViewById(R.id.UwagiContent);
        uwagi.setText(dane.get("uwagi_blok"));


        Button bPowrot = findViewById(R.id.bPowrot);
        bPowrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(szczegolyBloku.this, blokiLista.class);
                szczegolyBloku.this.startActivity(intent);
            }
        });

    }
}
