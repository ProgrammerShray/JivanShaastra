package com.jps.apps.jiva_nshaastra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                            String name = response.getString("name");

                            // 🔐 Save JWT token securely
                            SharedPreferences prefs =
                                    getSharedPreferences("UserPrefs", MODE_PRIVATE);

                            prefs.edit()
                                    .putString("token", token)
                                    .putString("name", name)
                                    .apply();

                            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(this, "Invalid server response", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show();
                }
        );

        queue.add(request);
    }
}
