package com.example.krzysztofstanek.hlgappmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class klatkiLista extends AppCompatActivity {

    private ListView list ;
    private KlatkiListAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_klatki_lista);

        Intent myIntent = getIntent();
        final String id = myIntent.getStringExtra("id");

        Button bPowrot = findViewById(R.id.bPowrot);
        bPowrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(klatkiLista.this, szczegolyBloku.class);
                intent.putExtra("id", id);
                klatkiLista.this.startActivity(intent);
            }
        });




        list = (ListView) findViewById(R.id.listView1);


        Map<String, String> dane_tmp = new HashMap<>();
        API api = new API();
        try {
            dane_tmp = api.pobierzListeKlatek(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> dane = new HashMap<>();;
        String d = dane_tmp.get("data");
        Log.d("diagnostyka",d);
        if(d==""){
            Log.d("diagnostyka","Warunek");
            Toast.makeText(klatkiLista.this, "BRAK SWITCHY", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(klatkiLista.this, szczegolyBloku.class);
            intent.putExtra("id", id);
            klatkiLista.this.startActivity(intent);
        }
        else {
            Log.d("KlatkiDATA", d);
            String[] keyvalue = d.split("\\;");

            String klatki[];
            klatki = new String[keyvalue.length];
            int i = 0;
            ArrayList<KlatkiModel> klatkiList = new ArrayList<>();
            for (String kv : keyvalue) {
                String[] temp = kv.split("\\#");
                String t0 = temp[0];
                t0 = t0.replace("\"", "");
                t0 = t0.replace("[{", "");
                t0 = t0.replace("}]", "");
                String t1 = temp[1];
                t1 = t1.replace("\"", "");
                t1 = t1.replace("[{", "");
                t1 = t1.replace("}]", "");
                Log.d("klatkiLista", t1 + " = " + t0);
                klatkiList.add(new KlatkiModel(t0, t1));
                //bloki[i]=t0;

                i++;
            }


            //ArrayList<String> blokiL = new ArrayList<String>();
            //blokiL.addAll( Arrays.asList(bloki) );
            adapter = new KlatkiListAdapter(this, klatkiList);

            //adapter = new ArrayAdapter<String>(this, R.layout.bloklistelement, blokiL);

            list.setAdapter(adapter);
        }
    }

    public void onClickListaKlatek(View view) {
        String id = (String) view.getTag();
        Intent intent = new Intent(klatkiLista.this, szczegolyKlatki.class);
        intent.putExtra("id", id);
        klatkiLista.this.startActivity(intent);

        //Toast.makeText(blokiLista.this, name, Toast.LENGTH_SHORT).show();
    //}
    }
}
