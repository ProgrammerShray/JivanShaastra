package com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.sphShloks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jps.apps.jiva_nshaastra.HomeActivity;
import com.jps.apps.jiva_nshaastra.R;
import com.jps.apps.jiva_nshaastra.philosphies.Sph;

public class sphShlok1_3 extends AppCompatActivity {


    private FloatingActionButton floatingActionButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sph_shlok1_3);


        floatingActionButton = findViewById(R.id.fabHomeSPH1_3);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sphShlok1_3.this, HomeActivity.class));
            }
        });

        textView= findViewById(R.id.sph_shlok1_3LABEL);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sphShlok1_3.this, Sph.class));
            }
        });
    }
}