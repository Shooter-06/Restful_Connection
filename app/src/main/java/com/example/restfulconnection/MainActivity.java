package com.example.restfulconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button submit;
    String responseText;
    StringBuffer response;
    URL url;
    Activity activity;
    ArrayList<Country> countries= new ArrayList<>();
    ListView listView;

    //restful server
    private String path = "https://cdn.jsdelivr.net/gh/arpitmandliya/AndroidRestJSONExample@master/countries.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        submit =findViewById(R.id.button);
        listView= findViewById(R.id.listView);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetServerData().execute();
            }
        });
    }
    class GetServerData extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            return getWebServiceResponsableData();
        }

        private Object getWebServiceResponsableData() {
            try{
                url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");

                int responseCode =conn.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK){
                    //reading response from inout stream
                    BufferedReader in= new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String output;
                    response = new StringBuffer();
                    while((output = in.readLine()) !=null){
                        response.append(output);
                    }
                    in.close();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            responseText = response.toString();

            try {
                JSONArray jsonArray = new JSONArray(responseText);
                for (int i=0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("id");
                    String country =jsonObject.getString("countryName");
                    Country countryobj= new Country(id, country);
                    countries.add(countryobj);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            //for populating the data to the list view
            CustomCountryList customCountryList =new CustomCountryList(activity, countries);
            listView.setAdapter(customCountryList);

        }
    }
}