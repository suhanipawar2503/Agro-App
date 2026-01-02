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
import com.example.findstations.adapters.HerbicidesAdapter;
import com.example.findstations.adapters.herbicides;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HerbicidesActivity extends AppCompatActivity {

    RecyclerView rview;

    private ArrayList<herbicides> herbicidesArrayList ;
    private HerbicidesAdapter herbicidesAdapter;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herbicides);
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
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, DBClass.urlherbicides,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        Log.d("Response ", ">> " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonData = jsonObject.getJSONArray("data");
                            herbicidesArrayList = new ArrayList<>();
                            for (int i = 0; i < jsonData.length(); i++) {
                                herbicides herbicides = new herbicides();
                                JSONObject jo = jsonData.getJSONObject(i);
                                herbicides.id = jo.getInt("id");
                                herbicides.name = jo.getString("name");
                                herbicides.price = jo.getDouble("price");
                                herbicides.mrp = jo.getString("mrp");
                                herbicides.pic = jo.getString("pic");
                                herbicides.description = jo.getString("description");
                                herbicides.stock = jo.getString("stock");

                                herbicidesArrayList.add(herbicides);
                            }
                            herbicidesAdapter= new HerbicidesAdapter(getApplicationContext(), herbicidesArrayList);
                            //StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                            //rview.setLayoutManager(layoutManager);
                            rview.setLayoutManager(new GridLayoutManager(HerbicidesActivity.this, 2));
                            rview.setAdapter(herbicidesAdapter);
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