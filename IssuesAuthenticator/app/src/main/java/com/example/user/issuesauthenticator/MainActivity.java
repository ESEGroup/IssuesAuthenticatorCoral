package com.example.user.issuesauthenticator;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public CookieManager manager = new CookieManager();

    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(manager);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onBackPressed() {
    }

    public void preferenceOptions(View view) {
        final Intent intent = new Intent(this, LabActivity.class);
        EditText loginView = (EditText) findViewById(R.id.login);
        final String login_text = loginView.getText().toString();
        EditText senhaView = (EditText) findViewById(R.id.senha);
        final String senha_text = senhaView.getText().toString();

        //Fazendo Request HTTP
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = " http://35.199.79.158:8080/login"; //AUTENTICADOR, NA PRÁTICA SERÁ "issuesmonitoring.com/login"
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AFFE",response);
                if(response.equals("ok")){
                    Log.d("affe", "chegou");
                } else {
                    TextView errorText = (TextView) findViewById(R.id.textView8);
                    errorText.setText("Usuário inválido");
                }
                List<HttpCookie> cookieList = manager.getCookieStore().getCookies();
                if(!cookieList.isEmpty()){
                    startActivity(intent);
                }
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Baaad", error.getMessage());


            }}){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("login", login_text);
                params.put("password", senha_text);
                return params;
            }};

        queue.add(stringRequest);
    }
}
