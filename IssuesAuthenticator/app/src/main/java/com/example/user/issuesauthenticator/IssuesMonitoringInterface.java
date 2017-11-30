package com.example.user.issuesauthenticator;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class IssuesMonitoringInterface {
    public boolean Autenticar(String user, String password){

        return true;
    }

    public boolean RegistrarPresenca(String user, int id_lab){
        return true;
    }

    public boolean RegistrarPreferencias(String user, int id_lab, int maxtemp, int mintemp, int maxum, int minum, int maxluz, int minluz){
        return true;
    }
}
