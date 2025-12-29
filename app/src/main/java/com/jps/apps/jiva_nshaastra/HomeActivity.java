package com.jps.apps.jiva_nshaastra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.DefaultRetryPolicy;

import com.jps.apps.jiva_nshaastra.philosphies.*;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    private TextView name;
    private TextView sph, sav, sam, sat, pbk;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize RequestQueue once
        requestQueue = Volley.newRequestQueue(this);

        name = findViewById(R.id.user);
        if (name == null) {
            Toast.makeText(this, "User TextView not found!", Toast.LENGTH_LONG).show();
            return;
        }

        // Fetch email from SharedPreferences
        String email = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                .getString("email", null);

        if (email != null && !email.isEmpty()) {
            fetchUserName(email);
        } else {
            name.setText("Welcome");
        }

        // Philosophy labels
        sph = findViewById(R.id.sphtext);
        sav = findViewById(R.id.savtext);
        sam = findViewById(R.id.samtext);
        sat = findViewById(R.id.sattext);
        pbk = findViewById(R.id.pbktext);

        setClickListeners();
    }

    private void setClickListeners() {
        sph.setOnClickListener(v -> startActivity(new Intent(this, Sph.class)));
        sav.setOnClickListener(v -> startActivity(new Intent(this, Sav.class)));
        sam.setOnClickListener(v -> startActivity(new Intent(this, Sam.class)));
        sat.setOnClickListener(v -> startActivity(new Intent(this, Sat.class)));
        pbk.setOnClickListener(v -> startActivity(new Intent(this, Pbk.class)));
    }

    private void fetchUserName(String email) {
        String url = "https://flaskbackendserverdb.onrender.com/auth/user-name";

        JSONObject json = new JSONObject();
        try {
            json.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                json,
                response -> {
                    try {
                        String nameFromDb = response.getString("name");
                        name.setText("Welcome, " + nameFromDb);
                    } catch (JSONException e) {
                        name.setText("Welcome");
                        e.printStackTrace();
                    }
                },
                error -> {
                    name.setText("Welcome");
                    Toast.makeText(this,
                            "Unable to fetch name",
                            Toast.LENGTH_SHORT).show();
                }
        );

        // IMPORTANT for Render (cold starts)
        request.setRetryPolicy(new DefaultRetryPolicy(
                15000, // 15 seconds
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        requestQueue.add(request);
    }
}
