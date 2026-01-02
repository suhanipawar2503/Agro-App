package com.example.findstations.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.findstations.DBClass;
import com.example.findstations.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserBookingActivity extends AppCompatActivity {

    EditText etxtName, etxtEmail, etxtLocation, etxtMobileno;

    String name="", email="", location="", mobileno="",  payment="";

    RadioButton online,delivery;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_booking);

        etxtName = findViewById(R.id.etxtName);
        etxtEmail = findViewById(R.id.etxtEmail);
        etxtLocation = findViewById(R.id.etxtLocation);
        etxtMobileno = findViewById(R.id.etxtMobileno);
        online = findViewById(R.id.online);
        delivery = findViewById(R.id.delivery);


    }

    public void btnBuyClick(View view) {
        name = etxtName.getText().toString();
        if (name.equals(""))
        {
            etxtName.setError("Please enter name");
            etxtName.requestFocus();
            return;
        }
        email = etxtEmail.getText().toString();
        if (email.equals(""))
        {
            etxtEmail.setError("Please enter city");
            etxtEmail.requestFocus();
            return;
        }
        location = etxtLocation.getText().toString();
        if (location.equals(""))
        {
            etxtLocation.setError("Please enter email");
            etxtLocation.requestFocus();
            return;
        }
        mobileno = etxtMobileno.getText().toString();
        if (mobileno.equals(""))
        {
            etxtMobileno.setError("Please enter mobileno");
            etxtMobileno.requestFocus();
            return;
        }

        if (online.isChecked())
        {
            payment = online.getText().toString();
        }
        if (delivery.isChecked())
        {
            payment = delivery.getText().toString();
        }

        progressDialog = new ProgressDialog(UserBookingActivity.this);
        progressDialog.setMessage("validating your details, please wait...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DBClass.urlBooking, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    progressDialog.dismiss();

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getString("status").equals("success"))
                    {

                        Toast.makeText(UserBookingActivity.this, "Add Product Successfully", Toast.LENGTH_SHORT).show();

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
                params.put("email", email);
                params.put("location", location );
                params.put("mobileno", mobileno);
                params.put("payment", payment);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}