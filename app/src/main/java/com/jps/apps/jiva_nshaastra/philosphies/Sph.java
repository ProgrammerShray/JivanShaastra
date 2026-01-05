package com.jps.apps.jiva_nshaastra.philosphies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jps.apps.jiva_nshaastra.R;
import com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.SphShloakList;

public class Sph extends AppCompatActivity {

    private TextView deepDive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sph);

        deepDive = findViewById(R.id.btnDeepDive);
        deepDive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sph.this, SphShloakList.class));
                finish();
            }
        });
    }
}