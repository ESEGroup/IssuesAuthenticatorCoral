package com.example.user.issuesauthenticator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void preferenceOptions(View view) {
        Intent intent = new Intent(this, LabActivity.class);
        EditText loginView = (EditText) findViewById(R.id.login);
        final String login_text = loginView.getText().toString();
        EditText senhaView = (EditText) findViewById(R.id.senha);
        final String senha_text = loginView.getText().toString();


        //Fazendo Request HTTP
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://requestb.in/1c4rl0z1";

        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Debug",response);
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.getMessage());
            }}){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("login", login_text);
                params.put("password", senha_text);
                return params;
            }};

        queue.add(stringRequest);

        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
