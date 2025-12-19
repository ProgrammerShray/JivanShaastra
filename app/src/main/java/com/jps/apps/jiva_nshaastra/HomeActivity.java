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
        sat = findViewById(R.id.sattext);
        pbk = findViewById(R.id.pbktext);

        //eventlisteners

        sph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Sph.class);
                startActivity(intent);
            }
        });

        sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Sav.class);
                startActivity(intent);

            }
        });

        sam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Sam.class);
                startActivity(intent);

            }
        });

        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Sat.class);
                startActivity(intent);

            }
        });

        pbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Pbk.class);
                startActivity(intent);

            }
        });
    }
}