package com.jps.apps.jiva_nshaastra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button loginBtn, signupmotivator;

    private FirebaseAuth mAuth; // 🔥 Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance(); // 🔥 Init Firebase

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String token = prefs.getString("token", null);

        if (token != null && !token.isEmpty()) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email2);
        password = findViewById(R.id.password2);
        loginBtn = findViewById(R.id.login);
        signupmotivator = findViewById(R.id.Signupmotivator);

        loginBtn.setOnClickListener(v -> {

            String mail = email.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (mail.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            } else {
                firebaseLogin(mail, pass);
            }
        });

        signupmotivator.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // 🔥 STEP 1 — Firebase Login + Verification Check
    private void firebaseLogin(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        FirebaseUser user = mAuth.getCurrentUser();

                        if (user != null) {

                            user.reload().addOnCompleteListener(reloadTask -> {

                                if (user.isEmailVerified()) {

                                    // ✅ Verified → Call backend
                                    loginUser(email, password);

                                } else {

                                    Toast.makeText(this,
                                            "Please verify your email first.",
                                            Toast.LENGTH_LONG).show();

                                    mAuth.signOut();
                                }

                            });
                        }

                    } else {
                        Toast.makeText(this,
                                "Firebase Login Failed: " +
                                        task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    // 🔥 STEP 2 — Your original backend login (UNCHANGED)
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
                            "Login failed! " + error.toString(),
                            Toast.LENGTH_SHORT).show();
                }
        );

        request.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        queue.add(request);
    }
}