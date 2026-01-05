package com.jps.apps.jiva_nshaastra.philosphies.ShloakLists;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jps.apps.jiva_nshaastra.R;

public class SphShloakList extends AppCompatActivity {

    private TextView shlok1, shlok2, shlok3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sph_shloak_list);

        //components
        shlok1 = findViewById(R.id.sph_shlok1_1);
        shlok2 = findViewById(R.id.sph_shlok1_2);
        shlok3 = findViewById(R.id.sph_shlok1_3);


    }
}