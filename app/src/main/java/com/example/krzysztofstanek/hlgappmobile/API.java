package com.example.krzysztofstanek.hlgappmobile;

        import android.os.AsyncTask;
        import android.util.Log;

        import java.io.BufferedOutputStream;
        import java.io.IOException;
        import java.io.OutputStream;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.SocketTimeoutException;
        import java.net.URL;
        import java.util.HashMap;
        import java.util.Map;
        import java.util.concurrent.ExecutionException;

        import org.json.*;

public class API {
    public static String result="";

    public String url_base = "http://159.69.245.224/hlg-bloki/API/JHGSDAG1254sgd1tfs113R/";
    public JSONObject response;

    public String request(Map<String, String> params) throws ExecutionException, InterruptedException {
        String params_txt="";
        String action = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            params_txt += ""+entry.getKey()+"="+entry.getValue()+"|";
        }
        String request_url = this.url_base+params_txt;
        Log.d("URL API", request_url);
        JsonTask task = new JsonTask();
        task.execute(request_url).get();
        return task.responde;

    }

    public Map<String, String> createData(String json) throws JSONException {

        Map<String, String> data = new HashMap<>();
        Log.d("create", json);
        JSONObject obj = new JSONObject(json);
        response = obj;
        data.put("status",obj.optString("status") );
        data.put("desc",obj.optString("desc") );
        data.put("data",obj.optString("data") );


        data.put("auth_id",obj.optString("auth_id") );
        data.put("user_id",obj.optString("user_id") );
        data.put("nick",obj.optString("nick") );



        return data;
    }

    public Map<String, String> createArrayFromJSON(String json) throws JSONException {

        Map<String, String> data = new HashMap<>();
        //JSONObject obj = new JSONObject(json);
        //response = obj;

        String[] keyvalue = json.split("\\,");

        for (String kv : keyvalue) {
            String[] temp = kv.split("\\:");
            String t0 = temp[0]; t0 = t0.replace("\"",""); t0 = t0.replace("[{",""); t0 = t0.replace("}]","");
            String t1 = temp[1]; t1 = t1.replace("\"",""); t1 = t1.replace("[{",""); t1 = t1.replace("}]","");

            t0.replace("\\/", "\\"); t1.replace("\\/", "\\");
            t0.replace("\\r\\n", " "); t1.replace("\\r\\n", " ");
            data.put(t0,t1);
            Log.d("parseJSONbyKSTANEK", t0+" = "+t1);
        }




        return data;
    }


    ///////////////////////////////////////////////////////////////////////////

    //LOGOWNANIE
    public Boolean zaloguj(String nick, String haslo) throws Exception {
        Map<String, String> parametr = new HashMap<>();

        String pass_hash = Password.hash(haslo);
        parametr.put("action", "loguj");

        parametr.put("nick", nick);
        parametr.put("haslo", pass_hash);

        Map<String, String> data = new HashMap<>();
        Log.d("TEST", "ZALOGUJ FUNKCJA");

        try {
            String responde = this.request(parametr);
            data = this.createData(responde);
            Log.d("TEST", "DATA: "+data.get("data"));
            if(data.get("data").equals("TRUE")){
                String nick_ = data.get("nick"); Log.d("TEST", "NICK: "+nick_);
                String id_ = data.get("user_id"); Log.d("TEST", "ID: "+id_);
                String auth_id_=data.get("auth_id"); Log.d("TEST", "AUTH_ID: "+auth_id_);
                autoryzacja.zaloguj(nick_, id_, auth_id_);
                return true;
            }
            else{
                //nie loguj
                Log.d("TEST", "ZALOGUJ - nie TRUE");
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("CATCH", e.toString());
        }
        Log.d("TEST", "ZALOGUJ - nie TRY");
        return false;
    }

    //POBIERANIE DANYCH UZYTKOWNIKA

    public Map<String, String> pobierzDaneUzytkownika(String id) throws Exception {

        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "pobierzDaneUzytkownika");

        parametr.put("id", id);


        Map<String, String> data = new HashMap<>();


        try {
            String responde = this.request(parametr);
            data = this.createData(responde);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    //POBIERANIE DANYCH UZYTKOWNIKA

    public Map<String, String> pobierzListeBlokow() throws Exception {

        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "pobierzListeBlokow");



        Map<String, String> data = new HashMap<>();


        try {
            String responde = this.request(parametr);
            data = this.createData(responde);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    public Map<String, String> pobierzDaneBloku(String id) throws Exception {

        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "pobierzDaneBloku");

        parametr.put("id", id);


        Map<String, String> data = new HashMap<>();


        try {
            String responde = this.request(parametr);
            data = this.createData(responde);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }







    //REJESTRACJA

    public Map<String, String> rejestruj(String nick, String imie, String haslo, String miejscowosc, String opis, String data_urodzenia, String woj) throws Exception {

        String pass_hash = Password.hash(haslo);

        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "register_user");

        parametr.put("nick", nick);
        parametr.put("imie", imie);
        parametr.put("haslo", pass_hash);
        parametr.put("miejscowosc", miejscowosc);
        parametr.put("opis", opis);
        parametr.put("data_urodzenia", data_urodzenia);
        parametr.put("wojewodztwo", woj);

        Map<String, String> data = new HashMap<>();


        try {
            String responde = this.request(parametr);
            data = this.createData(responde);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    //DODAJ IMPREZE

    public Map<String, String> dodaj_impreze(String nazwa, String data_imrezy, String miejsce, String wojewodztwo, String opis) throws Exception {

        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "dodaj_impreze");

        parametr.put("nazwa", nazwa);
        parametr.put("data", data_imrezy);
        parametr.put("miejsce", miejsce);
        parametr.put("wojewodztwo", wojewodztwo);
        parametr.put("opis", opis);

        Map<String, String> data = new HashMap<>();

        try {
            String responde = this.request(parametr);
            data = this.createData(responde);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }



    public Map<String, String> pobierzImpreze(String wojewodztwo) throws Exception {

        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "pobierz_impreze");

        parametr.put("wojewodztwo", wojewodztwo);


        Map<String, String> data = new HashMap<>();


        try {
            String responde = this.request(parametr);
            data = this.createData(responde);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    public Map<String, String> dolaczDoImprezy(String idImprezy, String idUsera) throws Exception {

        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "dolaczDoImprezy");

        parametr.put("idImprezy", idImprezy);
        parametr.put("idUsera", idUsera);


        Map<String, String> data = new HashMap<>();


        try {
            String responde = this.request(parametr);
            data = this.createData(responde);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    public Map<String, String> bierzeUdzial(String idImprezy, String idUsera) throws Exception {

        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "bierzeUdzial");

        parametr.put("idImprezy", idImprezy);
        parametr.put("idUsera", idUsera);


        Map<String, String> data = new HashMap<>();


        try {
            String responde = this.request(parametr);
            data = this.createData(responde);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    public Map<String, String> ileWezmieUdzial(String idImprezy) throws Exception {

        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "ileWezmieUdzial");

        parametr.put("idImprezy", idImprezy);


        Map<String, String> data = new HashMap<>();


        try {
            String responde = this.request(parametr);
            data = this.createData(responde);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    //EDYCJA

    public Map<String, String> edycja_profilu(String id, String imie, String miejscowosc, String opis, String woj) throws Exception {

        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "edit_user");

        parametr.put("id", id);
        parametr.put("imie", imie);
        parametr.put("miejscowosc", miejscowosc);
        parametr.put("opis", opis);
        parametr.put("wojewodztwo", woj);

        Map<String, String> data = new HashMap<>();


        try {
            String responde = this.request(parametr);
            data = this.createData(responde);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    public Boolean czyKontoIstnieje(String nick) throws JSONException, ExecutionException, InterruptedException {
        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "account_exist");

        parametr.put("nick", nick);

        Map<String, String> data = new HashMap<>();

        try {
            String responde = this.request(parametr);
            data = this.createData(responde);
            if(data.get("data").equals("TRUE")){
                Log.d("czyKontoIstnieje", "istnieje");
                return true;
            }
            else{
                Log.d("czyKontoIstnieje", "nie istnieje"+data.get("data"));
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }




    public Boolean czyZalogowany() throws Exception {

        if(autoryzacja.zalogowany){
            Map<String, String> parametr = new HashMap<>();

            parametr.put("action", "isLogged");

            parametr.put("id", autoryzacja.user_id);
            parametr.put("auth_id", autoryzacja.auth_id);

            Map<String, String> data = new HashMap<>();

            try {
                String responde = this.request(parametr);
                data = this.createData(responde);
                if(data.get("data").equals("TRUE")){
                    autoryzacja.zalogowany=true;
                    return true;
                }
                else{
                    autoryzacja.zalogowany = false;
                    return false;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }
        else{
            return false;
        }

    }


    public Boolean wyloguj() throws Exception {

        if(autoryzacja.zalogowany){
            Map<String, String> parametr = new HashMap<>();

            parametr.put("action", "logout");

            parametr.put("id", autoryzacja.user_id);
            parametr.put("auth_id", autoryzacja.auth_id);

            Map<String, String> data = new HashMap<>();

            try {
                String responde = this.request(parametr);
                data = this.createData(responde);
                if(data.get("data").equals("TRUE")){
                    autoryzacja.zalogowany=false;
                    return true;
                }
                else{
                    autoryzacja.zalogowany = false;
                    return false;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }
        else{
            return false;
        }

    }


    public Map<String, String> pobierzChętnych() throws Exception {

        Map<String, String> parametr = new HashMap<>();
        parametr.put("action", "pobierz_chetnych");


        Map<String, String> data = new HashMap<>();


        try {
            String responde = this.request(parametr);
            data = this.createData(responde);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

}
