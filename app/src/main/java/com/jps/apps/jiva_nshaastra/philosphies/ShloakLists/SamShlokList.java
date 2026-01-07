package com.jps.apps.jiva_nshaastra.philosphies.ShloakLists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jps.apps.jiva_nshaastra.HomeActivity;
import com.jps.apps.jiva_nshaastra.R;
import com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.satShloks.satShlok1_1;
import com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.satShloks.satShlok1_2;
import com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.satShloks.satShlok1_3;

public class SamShlokList extends AppCompatActivity {

    private TextView shlok1_1, shlok1_2, shlok1_3;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sam_shlok_list);


        shlok1_1 = findViewById(R.id.sam_shlok1_1);
        shlok1_2 = findViewById(R.id.sam_shlok1_2);
        shlok1_3 = findViewById(R.id.sam_shlok1_3);

        shlok1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SamShlokList.this, satShlok1_1.class));
                finish();
            }
        });

        shlok1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SamShlokList.this, satShlok1_2.class));
                finish();
            }
        });

        shlok1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SamShlokList.this, satShlok1_3.class));
                finish();
            }
        });

        floatingActionButton = findViewById(R.id.fabHomeSAMshlokLIST);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SamShlokList.this, HomeActivity.class));
                finish();
            }
        });
    }
}