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

public class samShlok1_2 extends AppCompatActivity {

    private TextView textView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sam_shlok1_2);


        floatingActionButton = findViewById(R.id.fabHomeSAM1_1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(samShlok1_2.this, HomeActivity.class));
                finish();
            }
        });

        textView = findViewById(R.id.sam_shlok1_1LABEL);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(samShlok1_2.this, Sam.class));
                finish();
            }
        });
    }
}