package com.jps.apps.jiva_nshaastra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import android.app.DatePickerDialog;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private EditText username, email, password, dob;
    private Button loginmotivator;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing the id(s)
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

            // OPTIONAL: prevent future dates
            datePickerDialog.getDatePicker()
                    .setMaxDate(System.currentTimeMillis());

            datePickerDialog.show();
        });

        loginmotivator= findViewById(R.id.Loginmotivator);
        signup = findViewById(R.id.submit);

        if (signup == null) {
            Toast.makeText(this, "Submit button not found!", Toast.LENGTH_LONG).show();
            return;
        }


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String dobi = dob.getText().toString().trim();

                if (name.isEmpty() || mail.isEmpty() || pass.isEmpty() || dobi.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    signUp(name, mail, pass, dobi);
                }
            }
        });

        if (loginmotivator == null) {
            Toast.makeText(this, "Login button not found!", Toast.LENGTH_LONG).show();
            return;
        }

        loginmotivator.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }
    private void signUp(String name, String email, String password, String dob){
//        String url = "http://192.168.178.159:5000/auth/signup"; --> Use when your device is using real mobile
//        String url = "http://10.0.2.2:5000/auth/signup"; //it will work with avd or any virtual device

        String url = "https://flaskbackendserverdb.onrender.com/auth/signup"; //it will work with avd and mobile

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
                        else{
                            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
                    System.out.println(error.toString());
                }
        );

        queue.add(request);


    }
}
