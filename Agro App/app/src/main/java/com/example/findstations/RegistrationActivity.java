package com.example.findstations;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    EditText etxtName, etxtEmail, etxtCity, etxtMobileno, etxtPassword;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etxtName = findViewById(R.id.etxtName);
        etxtEmail = findViewById(R.id.etxtEmail);
        etxtCity = findViewById(R.id.etxtCity);
        etxtMobileno = findViewById(R.id.etxtMobileno);
        etxtPassword = findViewById(R.id.etxtPassword);
    }

    public void btnRegisterClick(View view) {
        String name = etxtName.getText().toString();
        String email = etxtEmail.getText().toString();
        String city = etxtCity.getText().toString();
        String mobileno = etxtMobileno.getText().toString();
        String password = etxtPassword.getText().toString();
        if (name.equals(""))
        {
            etxtName.setError("Please enter name");
            etxtName.requestFocus();
            return;
        }
        if (city.equals(""))
        {
            etxtCity.setError("Please enter city");
            etxtCity.requestFocus();
            return;
        }
        if (email.equals(""))
        {
            etxtEmail.setError("Please enter email");
            etxtEmail.requestFocus();
            return;
        }
        if (mobileno.equals(""))
        {
            etxtMobileno.setError("Please enter mobileno");
            etxtMobileno.requestFocus();
            return;
        }
        if (password.equals(""))
        {
            etxtPassword.setError("Please enter password");
            etxtPassword.requestFocus();
            return;
        }

        progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setMessage("validating your details, please wait...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DBClass.urlRegistration, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    progressDialog.dismiss();

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getString("status").equals("success"))
                    {

                        Toast.makeText(RegistrationActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }

                }catch(Exception e)
                {
                    Log.e("Exception>> ", e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Error>> ", error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("city", city);
                params.put("email", email);
                params.put("mobileno", mobileno);
                params.put("password", password);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}