package com.example.findstations;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.findstations.admin.AdminHomeActivity;
import com.example.findstations.user.UserHomeActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText etxtEmail, etxtPassword;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etxtEmail = findViewById(R.id.etxtEmail);
        etxtPassword = findViewById(R.id.etxtPassword);
    }


    public void btnLoginClick(View view) {

        String email = etxtEmail.getText().toString();
        String password = etxtPassword.getText().toString();

        if (email.equals(""))
        {
            etxtEmail.setError("Please Enter Email");
            etxtEmail.requestFocus();
            return;
        }
        if (password.equals(""))
        {
            etxtPassword.setError("Please enter password");
            etxtPassword.requestFocus();
            return;
        }

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Validating your details, Please Wait..");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DBClass.urlLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    progressDialog.dismiss();

                    JSONObject jsonObject = new JSONObject(response);

                    Boolean loginSuccess = false;
                    if (jsonObject.getString("status").equals("success"))
                    {
                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String usertype = jsonObject.getString("usertype");
                        loginSuccess = true;
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                        String query = "DELETE FROM Configuration";
                        DBClass.execNonQuery(query);

                        query = "INSERT INTO Configuration(CName, CValue) ";
                        query += "VALUES('id', '" + id.replace("'", "''") + "')";
                        DBClass.execNonQuery(query);

                        query = "INSERT INTO Configuration(CName, CValue) ";
                        query += "VALUES('usertype', '" + usertype.replace("'", "''") + "')";
                        DBClass.execNonQuery(query);

                        query = "INSERT INTO Configuration(CName, CValue) ";
                        query += "VALUES('name', '" + name.replace("'", "''") + "')";
                        DBClass.execNonQuery(query);

                        if (loginSuccess)
                        {
                            if (usertype.equals("admin"))
                            {
                                Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Intent intent = new Intent(LoginActivity.this, UserHomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
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

                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }


    public void btnRegistrationClick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
}