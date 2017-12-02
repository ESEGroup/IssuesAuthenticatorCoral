
package com.example.user.issuesauthenticator;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DisplayMessageActivity extends AppCompatActivity {
    private String lab_id;
    private int mInterval = 60000;
    private Handler mHandler;
    private boolean onwifi;
    String ssid;
    Intent backtolab;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        onwifi = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Bundle bundle =getIntent().getExtras();
        String labId = bundle.getString("labID");
        lab_id = bundle.getString("lab");
        String jsonString = bundle.getString("jsonPrefs");
        Log.d("foi?", jsonString);
        TextView txtlabid = (TextView) findViewById(R.id.labid);
        txtlabid.setText(labId);

        try {
            updateSpinners(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mHandler = new Handler();
        startRepeatingTask();

        backtolab = new Intent(this, LabActivity.class);

    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    public void updateSpinners(String jsonString) throws JSONException{
        JSONObject prefObject = new JSONObject(jsonString);
        int tmin = (int) Float.parseFloat(prefObject.getString("temp_min"));
        int tmax = (int) Float.parseFloat(prefObject.getString("temp_max"));
        int umin = (int) Float.parseFloat(prefObject.getString("umid_min"));
        int umax = (int) Float.parseFloat(prefObject.getString("umid_max"));
        int lmin = (int) Float.parseFloat(prefObject.getString("lum_min"));
        int lmax = (int) Float.parseFloat(prefObject.getString("lum_max"));

        Integer[] itemsTempMin = new Integer[]{16,17,18,19,20,21,22};
        ArrayAdapter<Integer> adapterTempMin = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, itemsTempMin);
        Integer[] itemsTempMax = new Integer[]{22,23,24,25,26,27,28};
        ArrayAdapter<Integer> adapterTempMax = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, itemsTempMax);
        Integer[] itemsUmiMin = new Integer[]{50, 52, 54, 56, 58, 60};
        ArrayAdapter<Integer> adapterUmiMin = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, itemsUmiMin);
        Integer[] itemsUmiMax = new Integer[]{60, 62, 64, 66, 68, 70};
        ArrayAdapter<Integer> adapterUmiMax = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, itemsUmiMax);
        Integer[] itemsLumMin = new Integer[]{500, 520, 540, 560, 580, 600};
        ArrayAdapter<Integer> adapterLumMin = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, itemsLumMin);
        Integer[] itemsLumMax = new Integer[]{600, 620, 640, 660, 680, 700};
        ArrayAdapter<Integer> adapterLumMax = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, itemsLumMax);

        Spinner mspin = (Spinner) findViewById(R.id.spinner8);
        mspin.setAdapter(adapterUmiMin);
        mspin.setSelection(adapterUmiMin.getPosition(umin));

        mspin = (Spinner) findViewById(R.id.spinner5);
        mspin.setAdapter(adapterTempMin);
        mspin.setSelection(adapterTempMin.getPosition(tmin));

        mspin = (Spinner) findViewById(R.id.spinner4);
        mspin.setAdapter(adapterTempMax);
        mspin.setSelection(adapterTempMax.getPosition(tmax));

        mspin = (Spinner) findViewById(R.id.spinner7);
        mspin.setAdapter(adapterLumMin);
        mspin.setSelection(adapterLumMin.getPosition(lmin));

        mspin = (Spinner) findViewById(R.id.spinner6);
        mspin.setAdapter(adapterLumMax);
        mspin.setSelection(adapterLumMax.getPosition(lmax));

        mspin = (Spinner) findViewById(R.id.spinner3);
        mspin.setAdapter(adapterUmiMax);
        mspin.setSelection(adapterUmiMax.getPosition(umax));
    }

    public void onSaveClicked(View v){
        final String lab1 = lab_id;
        Log.d("teste", lab1);
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://192.168.86.74:8080/salvar-preferencias";        //REQUEST DE ARMAZENAR NOVAS PREFS, NA PRÁTICA SERÁ "issuesmonitoring.com/salvar-preferencias"
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
                params.put("lab_id", lab1);
                Spinner mspin = (Spinner) findViewById(R.id.spinner5);
                params.put("temp_min", mspin.getSelectedItem().toString());
                mspin = (Spinner) findViewById(R.id.spinner4);
                params.put("temp_max", mspin.getSelectedItem().toString());
                mspin = (Spinner) findViewById(R.id.spinner8);
                params.put("umid_min", mspin.getSelectedItem().toString());
                mspin = (Spinner) findViewById(R.id.spinner3);
                params.put("umid_max", mspin.getSelectedItem().toString());
                mspin = (Spinner) findViewById(R.id.spinner7);
                params.put("lum_min", mspin.getSelectedItem().toString());
                mspin = (Spinner) findViewById(R.id.spinner6);
                params.put("lum_max", mspin.getSelectedItem().toString());
                return params;
            }};
        queue.add(stringRequest);
    }

    public void leaveLab(){
        final String lab1 = lab_id;
        Log.d("teste", lab1);
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://192.168.86.74:8080/registrar-presenca"; //REQUEST DA PRESENÇA, NA PRÁTICA SERÁ "issuesmonitoring.com/registrar-presenca"
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d("AFFE",response);
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Baaad", error.getMessage());
            }}){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("lab_id", lab1);
                params.put("evento", "OUT");
                return params;
            }};
        queue.add(stringRequest);
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                //updateStatus(); //this function can change value of mInterval.
            } finally {
                TextView errormsg = (TextView) findViewById(R.id.textView9);
                errormsg.setText("Entre no wifi do laboratório ou sua ausência será registrada nos próximos 20m");
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                Log.d("timer", "tick");
                if (WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState()) == NetworkInfo.DetailedState.CONNECTED) {
                    ssid = wifiInfo.getSSID();
                }

                ssid="b";
                if((!ssid.equals("a")) && (!onwifi)){
                    leaveLab();
                    startActivity(backtolab);
                }

                if(!ssid.equals("a")){
                    onwifi = false;
                }

                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

}
