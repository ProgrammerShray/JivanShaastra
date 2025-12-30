package com.jps.apps.jiva_nshaastra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jps.apps.jiva_nshaastra.philosphies.*;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private TextView name;
    private TextView sph, sav, sam, sat, pbk;
    private RequestQueue requestQueue;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        requestQueue = Volley.newRequestQueue(this);

        name = findViewById(R.id.user);
        sph = findViewById(R.id.sphtext);
        sav = findViewById(R.id.savtext);
        sam = findViewById(R.id.samtext);
        sat = findViewById(R.id.sattext);
        pbk = findViewById(R.id.pbktext);

        setClickListeners();

        // Get JWT token
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        token = prefs.getString("token", null);

        if (token != null && !token.isEmpty()) {
            fetchUserProfile();
        } else {
            redirectToLogin();
        }
    }

    private void setClickListeners() {
        sph.setOnClickListener(v -> startActivity(new Intent(this, Sph.class)));
        sav.setOnClickListener(v -> startActivity(new Intent(this, Sav.class)));
        sam.setOnClickListener(v -> startActivity(new Intent(this, Sam.class)));
        sat.setOnClickListener(v -> startActivity(new Intent(this, Sat.class)));
        pbk.setOnClickListener(v -> startActivity(new Intent(this, Pbk.class)));
    }

    private void fetchUserProfile() {
        String url = "https://flaskbackendserverdb.onrender.com/auth/me";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        JSONObject user = response.getJSONObject("user");
                        String nameFromDb = user.getString("name");
                        name.setText("Welcome, " + nameFromDb);
                    } catch (Exception e) {
                        name.setText("Welcome");
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(this, "Session expired. Please login again.", Toast.LENGTH_SHORT).show();
                    redirectToLogin();
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        // Important for Render cold start
        request.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        requestQueue.add(request);
    }

    private void redirectToLogin() {
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        prefs.edit().clear().apply();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
