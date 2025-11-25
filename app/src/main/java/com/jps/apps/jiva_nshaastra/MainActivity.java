package com.jps.apps.jiva_nshaastra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText username, email, password, dob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing the id(s)
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        dob = findViewById(R.id.dob);

        findViewById(R.id.submit).setOnClickListener(v -> {
            String name = username.getText().toString().trim();
            String mail = email.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String dobi = dob.getText().toString().trim();

            if (name.isEmpty() || mail.isEmpty() || pass.isEmpty() || dobi.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            } else {
                signUp(name, mail, pass, dobi);
            }
        });
        TextView loginmotivator = findViewById(R.id.Loginmotivator);
        loginmotivator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void signUp(String name, String email, String password, String dob){
        String url = "http://192.168.7.174:5000/auth/signup";

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject data = new JSONObject();
        try {
            data.put("name", name);
            data.put("email", email);
            data.put("password", password);
            data.put("dob", dob);
        } catch (Exception e) {}

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                data,
                response -> {
                    try {
                        boolean success = response.getBoolean("success");
                        String message = response.getString("message");

                        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

                        if (success) {
                            // move to next activity
                            Intent intent = new Intent(this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
                }
        );

        queue.add(request);


    }
}
