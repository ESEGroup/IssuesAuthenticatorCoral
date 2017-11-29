package com.example.user.issuesauthenticator;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class LabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);
    }

    public void onLabClicked(View v){
        String ssid = "";
        String mac = "";
        Intent sendLab = new Intent(this, DisplayMessageActivity.class);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if(WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState()) == NetworkInfo.DetailedState.CONNECTED){
            ssid = wifiInfo.getSSID();
            mac = wifiInfo.getMacAddress();
        }

        switch(v.getId()){
            case R.id.button4:          //LENS1 IS CLICKED
                sendLab.putExtra("labID","LENS 1");
                if(ssid.equals("WLAN-DEL")){
                    startActivity(sendLab);
                }
                break;
            case R.id.button3:          //LENS2 IS CLICKED
                sendLab.putExtra("labID","LENS 2");
                startActivity(sendLab);
                break;
        }
        //startActivity(sendLab);
    }

}
