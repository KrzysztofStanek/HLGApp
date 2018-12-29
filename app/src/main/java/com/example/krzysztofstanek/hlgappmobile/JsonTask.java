package com.example.krzysztofstanek.hlgappmobile;

        import android.os.AsyncTask;
        import android.util.Log;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;

        import static com.example.krzysztofstanek.hlgappmobile.API.result;

public class JsonTask extends AsyncTask<String, String, String> {
    public String responde = "";


    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("API", "preExecute");
        /*pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();*/
    }

    protected String doInBackground(String... params) {

        Log.d("API", "doInBackground");
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            Log.d("URL: ", "> " + url);

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

            }
            //result = buffer.toString();
            this.responde = buffer.toString();
            return buffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("API", "onPostExecute");
        super.onPostExecute(result);
        this.responde = result;
        //Log.d("JSON", result);
       /* if (pd.isShowing()){
            pd.dismiss();
        }*/
        //txtJson.setText(result);
    }
}