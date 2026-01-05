package com.jps.apps.jiva_nshaastra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button loginBtn, signupmotivator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String token = prefs.getString("token", null);

        if (token != null && !token.isEmpty()) {
            // Auto-login
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email2);
        password = findViewById(R.id.password2);
        loginBtn = findViewById(R.id.login);
        signupmotivator = findViewById(R.id.Signupmotivator);

        // Login button
        loginBtn.setOnClickListener(v -> {
            String mail = email.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (mail.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(mail, pass);
            }
        });

        // Go to Signup
        signupmotivator.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void loginUser(String email, String password) {

        String url = "https://flaskbackendserverdb.onrender.com/auth/login";

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject data = new JSONObject();
        try {
            data.put("email", email);
            data.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                data,
                response -> {
                    try {
                        boolean success = response.getBoolean("success");

                        if (success) {
                            String token = response.getString("token");

                            JSONObject user = response.getJSONObject("user");
                            String name = user.getString("name");
                            String userId = user.getString("id");

                            // 🔐 Save JWT + user info
                            SharedPreferences prefs =
                                    getSharedPreferences("UserPrefs", MODE_PRIVATE);

                            prefs.edit()
                                    .putString("token", token)
                                    .putString("user_id", userId)
                                    .putString("name", name)
                                    .apply();

                            Toast.makeText(this,
                                    "Welcome " + name,
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(this, HomeActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(this,
                                    response.getString("message"),
                                    Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this,
                                "Invalid server response",
                                Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(this,
                            "Login failed!"+ error.toString(),
                            Toast.LENGTH_SHORT).show();
                }
        );
        request.setRetryPolicy(new DefaultRetryPolicy(
                20000, // ⏱ 20 seconds (important for Render cold start)
                1,     // retry once
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));


        queue.add(request);
    }

}
