package com.example.krzysztofstanek.hlgappmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);

        final API api = new API();

        //Button bZarejestruj = findViewById(R.id.bZarejestruj);
        Button bZaloguj = findViewById(R.id.bZaloguj);
        Button bOaplikacji = findViewById(R.id.bOaplikacji);


        /*bOaplikacji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, oaplikacji.class);
                MainActivity.this.startActivity(intent);
            }
        });*/


        /*bZarejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, rejestracjaActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });*/

        bZaloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText login_ = findViewById(R.id.login);
                String login = login_.getText().toString();

                EditText  haslo_ = findViewById(R.id.haslo);
                String haslo = haslo_.getText().toString();

                try {
                    if(api.zaloguj(login, haslo)){
                        Intent intent = new Intent(MainActivity.this, tablica.class);
                        MainActivity.this.startActivity(intent);
                        //Toast.makeText(MainActivity.this, "GRA", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Nieprawidłowy login/hasło - weź już nie pij ;)", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }
}