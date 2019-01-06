package com.example.krzysztofstanek.hlgappmobile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.krzysztofstanek.hlgappmobile.R;

public class blokiLista extends AppCompatActivity {

    private ListView list ;
    private ArrayAdapter<String> adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloki_lista);

        list = (ListView) findViewById(R.id.listView1);


        Map<String, String> dane_tmp = new HashMap<>();;
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
        for (String kv : keyvalue) {
            String[] temp = kv.split("\\#");
            String t0 = temp[0]; t0 = t0.replace("\"",""); t0 = t0.replace("[{",""); t0 = t0.replace("}]","");
            String t1 = temp[1]; t1 = t1.replace("\"",""); t1 = t1.replace("[{",""); t1 = t1.replace("}]","");
            Log.d("blokiLista", t1+" = "+t0);
            bloki[i]=t0;
            i++;
        }



        ArrayList<String> blokiL = new ArrayList<String>();
        blokiL.addAll( Arrays.asList(bloki) );

        adapter = new ArrayAdapter<String>(this, R.layout.bloklistelement, blokiL);

        list.setAdapter(adapter);
    }
}