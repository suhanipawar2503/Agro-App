package com.example.findstations.admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.findstations.DBClass;
import com.example.findstations.R;
import com.example.findstations.adapters.BookingAdapter;
import com.example.findstations.adapters.booking;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewBookingActivity extends AppCompatActivity {

    private ArrayList<booking> bookingArrayList;
    private BookingAdapter bookingAdapter;
    RecyclerView rview;
    ProgressDialog pDialog;
    Context context = null;
    String userid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_view_booking);

        context = getApplicationContext();
        rview =  findViewById(R.id.rview);
        String query = "SELECT CValue FROM Configuration WHERE CName = 'id'";
        userid = DBClass.getSingleValue(query);
        list();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("My Orders");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void list()
    {
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

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, DBClass.urlViewbooking,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        Log.d("Response ", ">> " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonData = jsonObject.getJSONArray("data");
                            bookingArrayList = new ArrayList<>();
                            for (int i = 0; i < jsonData.length(); i++) {
                                booking booking = new booking();
                                JSONObject jo = jsonData.getJSONObject(i);
                                booking.id = jo.getString("id");
                                booking.name = jo.getString("name");
                                booking.email = jo.getString("email");
                                booking.mobileno = jo.getString("mobileno");
                                booking.location = jo.getString("location");
                                booking.payment = jo.getString("payment");

                                booking.from="user";

                                bookingArrayList.add(booking);
                            }
                            bookingAdapter = new BookingAdapter(getApplicationContext(), bookingArrayList);
                            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                            rview.setLayoutManager(layoutManager);
                            rview.setAdapter(bookingAdapter);
                        } catch (Exception e) {
                            Log.e("Exception ", ">> " + e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error ", ">> " + error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userid", userid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}