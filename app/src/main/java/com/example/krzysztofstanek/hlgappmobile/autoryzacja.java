package com.example.krzysztofstanek.hlgappmobile;

import android.app.Application;

public class autoryzacja extends Application {
    public static String nick;
    public static String user_id;
    public static String auth_id;
    public static Boolean zalogowany;


    public autoryzacja(){
        nick="";
        user_id="";
        auth_id="";
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

    public static void zaloguj(String nick_, String id_, String auth_id_) {
        zalogowany = true;
        user_id = id_;
        nick = nick_;
        auth_id = auth_id_;
    }

    public static void wyloguj() throws Exception {
        final API api = new API();
        api.wyloguj();
        nick="";
        user_id="";
        auth_id="";
        zalogowany=false;
    }

}