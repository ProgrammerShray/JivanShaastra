package com.jps.apps.jiva_nshaastra.philosphies.ShloakLists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jps.apps.jiva_nshaastra.HomeActivity;
import com.jps.apps.jiva_nshaastra.R;
import com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.savShloks.savShlok1_1;
import com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.savShloks.savShlok1_2;

public class SavShlokList extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private TextView shlok1_1,shlok1_2,shlok1_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sav_shlok_list);

        shlok1_1 = findViewById(R.id.sav_shlok1_1);
        shlok1_2 = findViewById(R.id.sav_shlok1_2);
        shlok1_3 = findViewById(R.id.sav_shlok1_3);

        shlok1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SavShlokList.this, savShlok1_1.class));
                finish();
            }
        });

        shlok1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SavShlokList.this, savShlok1_2.class));
                finish();
            }
        });

        floatingActionButton = findViewById(R.id.fabHomeSAVshlokLIST);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SavShlokList.this, HomeActivity.class));
                finish();
            }
        });

    }
}