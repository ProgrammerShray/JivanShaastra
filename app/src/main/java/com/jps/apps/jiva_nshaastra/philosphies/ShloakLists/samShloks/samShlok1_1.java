package com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.samShloks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jps.apps.jiva_nshaastra.HomeActivity;
import com.jps.apps.jiva_nshaastra.R;
import com.jps.apps.jiva_nshaastra.philosphies.Sam;
import com.jps.apps.jiva_nshaastra.philosphies.Sat;
import com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.satShloks.satShlok1_1;

public class samShlok1_1 extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sam_shlok1_1);


        floatingActionButton = findViewById(R.id.fabHomeSAM1_1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(samShlok1_1.this, HomeActivity.class));
                finish();
            }
        });

        textView= findViewById(R.id.sam_shlok1_1LABEL);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(samShlok1_1.this, Sam.class));
                finish();
            }
        });
    }
}