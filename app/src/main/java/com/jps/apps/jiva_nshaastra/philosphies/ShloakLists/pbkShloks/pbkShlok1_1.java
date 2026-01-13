package com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.pbkShloks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jps.apps.jiva_nshaastra.HomeActivity;
import com.jps.apps.jiva_nshaastra.R;
import com.jps.apps.jiva_nshaastra.philosphies.Pbk;
import com.jps.apps.jiva_nshaastra.philosphies.Sam;
import com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.samShloks.samShlok1_1;

public class pbkShlok1_1 extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbk_shlok1_1);

        floatingActionButton = findViewById(R.id.fabHomePBK1_1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(pbkShlok1_1.this, HomeActivity.class));
                finish();
            }
        });

        textView= findViewById(R.id.pbk_shlok1_1LABEL);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(pbkShlok1_1.this, Pbk.class));
                finish();
            }
        });
    }
}