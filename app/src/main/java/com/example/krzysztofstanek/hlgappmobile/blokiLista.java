package com.example.krzysztofstanek.hlgappmobile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.krzysztofstanek.hlgappmobile.R;

public class blokiLista extends AppCompatActivity {

    private ListView list ;
    private BlokiListAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_bloki_lista);


        Button bPowrot = findViewById(R.id.bPowrot);
        bPowrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(blokiLista.this, tablica.class);
                blokiLista.this.startActivity(intent);
            }
        });

        list = (ListView) findViewById(R.id.listView1);


        Map<String, String> dane_tmp = new HashMap<>();
        API api = new API();
        try {
            dane_tmp = api.pobierzListeBlokow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> dane = new HashMap<>();;
        String d = dane_tmp.get("data");
        Log.d("blokiListaDATA", d);
        String[] keyvalue = d.split("\\;");
        Log.d("blokiListaLength", String.valueOf(keyvalue.length));
        String bloki[];
        bloki = new String[keyvalue.length];
        int i=0;
        ArrayList<BlokModel> blokiList = new ArrayList<>();
        for (String kv : keyvalue) {
            String[] temp = kv.split("\\#");
            String t0 = temp[0]; t0 = t0.replace("\"",""); t0 = t0.replace("[{",""); t0 = t0.replace("}]","");
            String t1 = temp[1]; t1 = t1.replace("\"",""); t1 = t1.replace("[{",""); t1 = t1.replace("}]","");
            Log.d("blokiLista", t1+" = "+t0);
            blokiList.add(new BlokModel(t0 , t1));
            //bloki[i]=t0;

            i++;
        }



        //ArrayList<String> blokiL = new ArrayList<String>();
        //blokiL.addAll( Arrays.asList(bloki) );
        adapter = new BlokiListAdapter(this,blokiList);

                //adapter = new ArrayAdapter<String>(this, R.layout.bloklistelement, blokiL);

        list.setAdapter(adapter);

    }

    public void onClickListaBlokow(View view) {
        String id = (String) view.getTag();
        Intent intent = new Intent(blokiLista.this, szczegolyBloku.class);
        intent.putExtra("id", id);
        blokiLista.this.startActivity(intent);

        //Toast.makeText(blokiLista.this, name, Toast.LENGTH_SHORT).show();
    }
}