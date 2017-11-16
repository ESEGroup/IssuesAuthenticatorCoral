
package com.example.user.issuesauthenticator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Bundle bundle =getIntent().getExtras();
        String labID = bundle.getString("labID");
        TextView txtlabid = (TextView) findViewById(R.id.labid);
        txtlabid.setText(labID);

        Integer[] items = new Integer[]{16,17,18,19,20,21,22,23,24,25,26,27,28};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);

        Spinner mspin = (Spinner) findViewById(R.id.spinner3);
        mspin.setAdapter(adapter);
        mspin = (Spinner) findViewById(R.id.spinner4);
        mspin.setAdapter(adapter);
        mspin = (Spinner) findViewById(R.id.spinner5);
        mspin.setAdapter(adapter);
        mspin = (Spinner) findViewById(R.id.spinner6);
        mspin.setAdapter(adapter);
        mspin = (Spinner) findViewById(R.id.spinner7);
        mspin.setAdapter(adapter);
        mspin = (Spinner) findViewById(R.id.spinner8);
        mspin.setAdapter(adapter);
    }


}
