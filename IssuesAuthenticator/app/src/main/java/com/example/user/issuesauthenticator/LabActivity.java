package com.example.user.issuesauthenticator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Tuniks on 11/15/2017.
 */

public class LabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);
    }

    public void onLabClicked(View v){
        Intent sendLab = new Intent(this, DisplayMessageActivity.class);
        switch(v.getId()){
            case R.id.button4:          //LENS1 IS CLICKED
                sendLab.putExtra("labID","LENS 1");
                //do something
                break;
            case R.id.button3:          //LENS2 IS CLICKED
                sendLab.putExtra("labID","LENS 2");
                break;
        }
        startActivity(sendLab);
    }

}
