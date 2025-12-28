package com.jps.apps.jiva_nshaastra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jps.apps.jiva_nshaastra.philosphies.Pbk;
import com.jps.apps.jiva_nshaastra.philosphies.Sam;
import com.jps.apps.jiva_nshaastra.philosphies.Sat;
import com.jps.apps.jiva_nshaastra.philosphies.Sav;
import com.jps.apps.jiva_nshaastra.philosphies.Sph;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    private TextView name;
    private TextView sph, sav, sam, sat, pbk;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.user);

        //fetching the name from the database
        String email = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                .getString("email", null);

        if (email != null) {
            fetchUserName(email);
        }

        //philosphies labels
        sph = findViewById(R.id.sphtext);
        sav = findViewById(R.id.savtext);
        sam = findViewById(R.id.samtext);
        sat = findViewById(R.id.sattext);
        pbk = findViewById(R.id.pbktext);

        //eventlisteners

        sph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Sph.class);
                startActivity(intent);
            }
        });

        sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Sav.class);
                startActivity(intent);

            }
        });

        sam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Sam.class);
                startActivity(intent);

            }
        });

        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Sat.class);
                startActivity(intent);

            }
        });

        pbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(HomeActivity.this, Pbk.class);
                startActivity(intent);

            }
        });
    }


    private void fetchUserName(String email) {
        String url = "https://flaskbackendserverdb.onrender.com/auth/user-name";

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject json = new JSONObject();
        try {
            json.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                json,
                response -> {
                    try {
                        String Name = response.getString("name");
                        name.setText("Welcome, " + Name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    name.setText("Welcome");
                    error.printStackTrace();
                }
        );

        queue.add(request);
    }
}