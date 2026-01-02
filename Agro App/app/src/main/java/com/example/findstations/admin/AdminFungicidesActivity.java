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
import com.example.findstations.adapters.fungicides;
import com.example.findstations.adapters.FungicidesAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminFungicidesActivity extends AppCompatActivity {

    RecyclerView rview;
    FloatingActionButton fab;
    private ArrayList<fungicides> fungicidesArrayList;
    private FungicidesAdapter fungicidesAdapter;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_fungicides);
        rview =  findViewById(R.id.rview);
        fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(AdminFungicidesActivity.this, AddFungicidesActivity.class);
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
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, DBClass.urlfungicides,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        Log.d("Response ", ">> " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonData = jsonObject.getJSONArray("data");
                            fungicidesArrayList = new ArrayList<>();
                            for (int i = 0; i < jsonData.length(); i++) {
                                fungicides fungicides = new fungicides();
                                JSONObject jo = jsonData.getJSONObject(i);
                                fungicides.id = jo.getInt("id");
                                fungicides.name = jo.getString("name");
                                fungicides.price = jo.getDouble("price");
                                fungicides.mrp = jo.getString("mrp");
                                fungicides.pic = jo.getString("pic");
                                fungicides.description = jo.getString("description");
                                fungicides.stock = jo.getString("stock");

                                fungicidesArrayList.add(fungicides);
                            }
                            fungicidesAdapter = new FungicidesAdapter(getApplicationContext(), fungicidesArrayList);
                            //StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                            //rview.setLayoutManager(layoutManager);
                            rview.setLayoutManager(new GridLayoutManager(AdminFungicidesActivity.this, 2));
                            rview.setAdapter(fungicidesAdapter);
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