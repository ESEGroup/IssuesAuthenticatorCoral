
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
        mspin = (Spinner) findViewById(R.id.spinner5);
        mspin.setAdapter(adapterTempMin);
        mspin = (Spinner) findViewById(R.id.spinner4);
        mspin.setAdapter(adapterTempMax);
        mspin = (Spinner) findViewById(R.id.spinner7);
        mspin.setAdapter(adapterLumMin);
        mspin = (Spinner) findViewById(R.id.spinner6);
        mspin.setAdapter(adapterLumMax);
        mspin = (Spinner) findViewById(R.id.spinner3);
        mspin.setAdapter(adapterUmiMax);
    }


}
