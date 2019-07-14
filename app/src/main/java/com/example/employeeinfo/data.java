package com.example.employeeinfo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class data extends AsyncTask< Void, Void, Void> {
    String data;
    String dataPersed="";
    String name="";
    String location2="location";
    String singleperse="";
    String doubleperse="";
    String latitude="";
    String longitude="";
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.data.setText(this.dataPersed);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url= new URL("http://anontech.info/courses/cse491/employees.json");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            String line= "";
            while(line!=null){
                line=bufferedReader.readLine();
                data= data+line;
            }
            String crappyPrefix = "null";

            if(data.startsWith(crappyPrefix)){
                data = data.substring(crappyPrefix.length(), data.length());
            }
            JSONArray ja=new JSONArray(data);
            for (int i=0;i<ja.length();i++ )
            {
                JSONObject jo=(JSONObject)ja.get(i);
                name = "Name:" + jo.get("name") + "\n";

                JSONObject location=jo.getJSONObject("location");
                if(location.isNull("latitude")) {
                    //latitude = (JSONObject)null+ "" + "\n";
                }
                else{
                    latitude = "latitude:" + location.getDouble("latitude") + "\n";
                }
                if(location.isNull("longitude")) {
                    //longitude = (JSONObject)null"" + "\n";
                }
                else{
                    longitude = "longitude:" + location.getDouble("longitude") + "\n";
                }
                singleperse = name + location2 + "\n";
                doubleperse=singleperse+ latitude + longitude +"\n";
                dataPersed = dataPersed + doubleperse + "\n";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("url", "URL er probl");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("IO", "IO te problem");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("json", "json er prbl");
        }

        return null;
    }
}
