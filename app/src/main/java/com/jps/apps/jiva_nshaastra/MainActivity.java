package com.jps.apps.jiva_nshaastra;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText username, email, password, dob;
    private Button loginmotivator, signup;

    private FirebaseAuth mAuth; // 🔥 Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance(); // 🔥 Initialize Firebase

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        dob = findViewById(R.id.dob);

        Calendar calendar = Calendar.getInstance();

        dob.setOnClickListener(v -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {

                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(selectedYear, selectedMonth, selectedDay);

                        SimpleDateFormat sdf =
                                new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

                        dob.setText(sdf.format(selectedDate.getTime()));
                    },
                    year, month, day
            );

            datePickerDialog.getDatePicker()
                    .setMaxDate(System.currentTimeMillis());

            datePickerDialog.show();
        });

        loginmotivator = findViewById(R.id.Loginmotivator);
        signup = findViewById(R.id.submit);

        signup.setOnClickListener(view -> {
            String name = username.getText().toString().trim();
            String mail = email.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String dobi = dob.getText().toString().trim();

            if (name.isEmpty() || mail.isEmpty() || pass.isEmpty() || dobi.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            } else if (pass.length() < 6) {
                Toast.makeText(MainActivity.this, "Password must be at least 6 characters!", Toast.LENGTH_SHORT).show();
            } else {
                registerWithFirebase(name, mail, pass, dobi);
            }
        });

        loginmotivator.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // 🔥 Step 1: Create Firebase user and send verification
    private void registerWithFirebase(String name, String email, String password, String dob) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        FirebaseUser user = mAuth.getCurrentUser();

                        if (user != null) {
                            user.sendEmailVerification()
                                    .addOnCompleteListener(verifyTask -> {

                                        if (verifyTask.isSuccessful()) {

                                            Toast.makeText(this,
                                                    "Verifying your details....",
                                                    Toast.LENGTH_LONG).show();

                                            // 🔥 Now call backend signup
                                            signUp(name, email, password, dob);

                                        } else {
                                            Toast.makeText(this,
                                                    "Failed to verify the details. Please enter valid credentials!",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }

                    } else {
                        Toast.makeText(this,
                                "Firebase Error: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    // 🔥 Step 2: Your original backend signup (UNCHANGED)
    private void signUp(String name, String email, String password, String dob) {

        String url = "https://flaskbackendserverdb.onrender.com/auth/signup";

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject data = new JSONObject();
        try {
            data.put("name", name);
            data.put("email", email);
            data.put("password", password);
            data.put("dob", dob);
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
                        String message = response.getString("message");

                        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

                        if (success) {
                            Intent intent = new Intent(this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
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