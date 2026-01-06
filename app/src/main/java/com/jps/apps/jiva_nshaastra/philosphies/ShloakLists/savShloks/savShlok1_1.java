package com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.savShloks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jps.apps.jiva_nshaastra.HomeActivity;
import com.jps.apps.jiva_nshaastra.R;
import com.jps.apps.jiva_nshaastra.philosphies.Sav;
import com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.sphShloks.sphShlok1_2;
import com.jps.apps.jiva_nshaastra.philosphies.Sph;

public class savShlok1_1 extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sav_shlok1_1);


        floatingActionButton = findViewById(R.id.fabHomeSAV1_1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(savShlok1_1.this, HomeActivity.class));
                finish();
            }
        });

        textView= findViewById(R.id.sav_shlok1_1LABEL);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(savShlok1_1.this, Sav.class));
                finish();
            }
        });
    }
}