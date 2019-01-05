package com.example.krzysztofstanek.hlgappmobile;

import android.app.Application;

import java.util.Map;

public class autoryzacja extends Application {
    public static String nick;
    public static String user_id;
    public static String auth_id;
    public static String nazwisko;
    public static Boolean zalogowany;


    public autoryzacja(){
        nick="";
        user_id="";
        auth_id="";
        nazwisko="";
        zalogowany=false;
    }



    public static Boolean czyZalogowany() throws Exception {
        final API api = new API();
        if(api.czyZalogowany()){
            return true;
        }
        else{
            return false;
        }
    }

    public static void zaloguj(String nick_, String id_, String auth_id_) throws Exception {
        API api = new API();
        Map<String, String> dane_useraTmp = api.pobierzDaneUzytkownika(id_);
        Map<String, String> dane_usera = api.createArrayFromJSON(dane_useraTmp.get("data"));
        zalogowany = true;
        user_id = id_;
        nick = nick_;
        nazwisko = dane_usera.get("nazwisko");
        auth_id = auth_id_;
    }

    public static void wyloguj() throws Exception {
        final API api = new API();
        api.wyloguj();
        nick="";
        user_id="";
        auth_id="";
        nazwisko="";
        zalogowany=false;
    }

}