package com.example.user.issuesauthenticator;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);
    }

    public void onLabClicked(View v) {
        boolean onwifi = false;
        String ssid = "";
        String mac = "";
        final Intent sendLab = new Intent(this, DisplayMessageActivity.class);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState()) == NetworkInfo.DetailedState.CONNECTED) {
            ssid = wifiInfo.getSSID();
            mac = wifiInfo.getMacAddress();
        }
        String lab_id_aux = "0";
        switch (v.getId()) {
            case R.id.button4:          //LENS1 IS CLICKED
                sendLab.putExtra("labID", "LENS 1");
                if (ssid.equals("Padang2.0")) {
                    onwifi = true;
                    lab_id_aux = "1";
                }
                break;
            case R.id.button3:          //LENS2 IS CLICKED
                sendLab.putExtra("labID", "LENS 2");
                lab_id_aux = "2";
                onwifi = true;
                break;
        }

        if (onwifi) {
            final String lab_id = lab_id_aux;
            RequestQueue queue = Volley.newRequestQueue(this);
            final String url = "https://requestb.in/15urlgk1"; //REQUEST DA PRESENÇA, NA PRÁTICA SERÁ "issuesmonitoring.com/registrar-presenca"
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("AFFE",response);
                }}, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Baaad", error.getMessage());
                }}){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("lab_id", lab_id);
                    params.put("evento", "IN");
                    return params;
                }};
            queue.add(stringRequest);

            final String url_get_pref = "a"; // REQUEST DAS PREFERENCIAS, NA PRÁTICA SERÁ "issuesmonitoring.com/pegar-preferencias"
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url_get_pref, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("response", response.toString());
                    sendLab.putExtra("jsonPrefs", response.toString());
                }},
                new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Baaad", error.getMessage());
                }}){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("lab_id", lab_id);
                    return params;
                }};

            queue.add(jsonRequest);
            startActivity(sendLab);
        }
    }
}
