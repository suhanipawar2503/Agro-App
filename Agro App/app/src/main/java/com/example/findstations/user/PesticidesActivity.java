package com.example.findstations.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.findstations.DBClass;
import com.example.findstations.R;
import com.example.findstations.adapters.PesticidesAdapter;
import com.example.findstations.adapters.pesticides;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PesticidesActivity extends AppCompatActivity {

    RecyclerView rview;

    private ArrayList<pesticides> pesticidesArrayList ;
    private PesticidesAdapter pesticidesAdapter;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesticides);
        rview =  findViewById(R.id.rview);
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
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, DBClass.urlpesticides,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        Log.d("Response ", ">> " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonData = jsonObject.getJSONArray("data");
                            pesticidesArrayList = new ArrayList<>();
                            for (int i = 0; i < jsonData.length(); i++) {
                               pesticides pesticides = new pesticides();
                                JSONObject jo = jsonData.getJSONObject(i);
                                pesticides.id = jo.getInt("id");
                                pesticides.name = jo.getString("name");
                                pesticides.price = jo.getDouble("price");
                                pesticides.mrp = jo.getString("mrp");
                                pesticides.pic = jo.getString("pic");
                                pesticides.description = jo.getString("description");
                                pesticides.stock = jo.getString("stock");

                                pesticidesArrayList.add(pesticides);
                            }
                            pesticidesAdapter = new PesticidesAdapter(getApplicationContext(), pesticidesArrayList);
                            //StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                            //rview.setLayoutManager(layoutManager);
                            rview.setLayoutManager(new GridLayoutManager(PesticidesActivity.this, 2));
                            rview.setAdapter(pesticidesAdapter);
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