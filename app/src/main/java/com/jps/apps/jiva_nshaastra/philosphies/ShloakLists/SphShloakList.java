package com.jps.apps.jiva_nshaastra.philosphies.ShloakLists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jps.apps.jiva_nshaastra.HomeActivity;
import com.jps.apps.jiva_nshaastra.R;
import com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.sphShloks.sphShlok1_1;
import com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.sphShloks.sphShlok1_2;
import com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.sphShloks.sphShlok1_3;

public class SphShloakList extends AppCompatActivity {

    private TextView shlok1, shlok2, shlok3;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sph_shloak_list);

        //components
        shlok1 = findViewById(R.id.sph_shlok1_1);
        shlok2 = findViewById(R.id.sph_shlok1_2);
        shlok3 = findViewById(R.id.sph_shlok1_3);

        shlok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SphShloakList.this, sphShlok1_1.class));
            }
        });
        shlok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SphShloakList.this, sphShlok1_2.class));
            }
        });
        shlok3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SphShloakList.this, sphShlok1_3.class));
            }
        });

        floatingActionButton = findViewById(R.id.fabHomeSPHshlokLIST);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SphShloakList.this, HomeActivity.class));
                finish();
            }
        });

    }
}