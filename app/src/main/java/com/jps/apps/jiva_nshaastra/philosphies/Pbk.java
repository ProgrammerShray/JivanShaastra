package com.jps.apps.jiva_nshaastra.philosphies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jps.apps.jiva_nshaastra.HomeActivity;
import com.jps.apps.jiva_nshaastra.R;
import com.jps.apps.jiva_nshaastra.philosphies.ShloakLists.PbkShlokList;

public class Pbk extends AppCompatActivity {
    private TextView textView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbk);

        textView = findViewById(R.id.btnDeepDivePBK);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pbk.this, PbkShlokList.class));
                finish();
            }
        });

        floatingActionButton = findViewById(R.id.fabHomePBK);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pbk.this, HomeActivity.class));
            }
        });
    }
}