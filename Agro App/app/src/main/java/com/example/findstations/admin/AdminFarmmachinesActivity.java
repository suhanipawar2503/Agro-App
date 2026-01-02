package com.example.findstations.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.findstations.DBClass;
import com.example.findstations.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.findstations.adapters.farmmachines;
import com.example.findstations.adapters.FarmmachinesAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminFarmmachinesActivity extends AppCompatActivity {

    RecyclerView rview;
    FloatingActionButton fab;
    private ArrayList<farmmachines> farmmachinesArrayList;
    private FarmmachinesAdapter farmmachinesAdapter;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_farmmachines);
        rview =  findViewById(R.id.rview);
        fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(AdminFarmmachinesActivity.this, AddFarmmachinesActivity.class);
               startActivity(intent);
            }
        });

        list();

    }
    public void list(){

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("downloading, please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
        pDialog.show();
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, DBClass.urlfarmmachines,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        Log.d("Response ", ">> " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonData = jsonObject.getJSONArray("data");
                            farmmachinesArrayList = new ArrayList<>();
                            for (int i = 0; i < jsonData.length(); i++) {
                               farmmachines farmmachines= new farmmachines();
                                JSONObject jo = jsonData.getJSONObject(i);
                                farmmachines.id = jo.getInt("id");
                                farmmachines.name = jo.getString("name");
                                farmmachines.price = jo.getDouble("price");
                                farmmachines.mrp = jo.getString("mrp");
                                farmmachines.pic = jo.getString("pic");
                                farmmachines.description = jo.getString("description");
                                farmmachines.stock = jo.getString("stock");

                                farmmachinesArrayList.add(farmmachines);
                            }
                            farmmachinesAdapter = new FarmmachinesAdapter(getApplicationContext(), farmmachinesArrayList);
                            //StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                            //rview.setLayoutManager(layoutManager);
                            rview.setLayoutManager(new GridLayoutManager(AdminFarmmachinesActivity.this, 2));
                            rview.setAdapter(farmmachinesAdapter);
                        } catch (Exception e) {
                            Log.e("Exception>> ", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error>> ", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}