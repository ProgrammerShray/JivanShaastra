package com.jps.apps.jiva_nshaastra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jps.apps.jiva_nshaastra.philosphies.Pbk;
import com.jps.apps.jiva_nshaastra.philosphies.Sam;
import com.jps.apps.jiva_nshaastra.philosphies.Sat;
import com.jps.apps.jiva_nshaastra.philosphies.Sav;
import com.jps.apps.jiva_nshaastra.philosphies.Sph;

public class HomeActivity extends AppCompatActivity {

    private TextView name;
    private TextView sph, sav, sam, sat, pbk;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.user);

        //philosphies labels
        sph = findViewById(R.id.sphtext);
        sav = findViewById(R.id.savtext);
        sam = findViewById(R.id.samtext);
        sav = findViewById(R.id.sattext);
        pbk = findViewById(R.id.pbktext);

        //evenlisteners

        sph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Sph.class);
            }
        });

        sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Sav.class);
            }
        });

        sam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Sam.class);
            }
        });

        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Sat.class);
            }
        });

        pbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Pbk.class);
            }
        });
    }
}