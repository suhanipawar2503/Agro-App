package com.example.findstations.admin;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.findstations.DBClass;
import com.example.findstations.R;
import com.example.findstations.adapters.UserAdapter;
import com.example.findstations.adapters.user;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UsersActivity extends AppCompatActivity {

    RecyclerView rview;

    private ArrayList<com.example.findstations.adapters.user> UserArrayList;
    private com.example.findstations.adapters.UserAdapter UserAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_users);
        context=getApplicationContext();
        rview = findViewById(R.id.rview);
        getData();
    }
    public void getData()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DBClass.urlUsers, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject= new JSONObject(response);
                    JSONArray jsonData = jsonObject.getJSONArray("data");
                    Log.e("DATA>>",String.valueOf(jsonData));

                    UserArrayList = new ArrayList<>();
                    for(int i=0;i<jsonData.length();i++)
                    {
                        user user = new user();
                        JSONObject jo = jsonData.getJSONObject(i);
                        user.id = jo.getString("id");
                        user.name = jo.getString("name");
                        user.city = jo.getString("city");
                        user.mobileno = jo.getString("mobileno");


                        UserArrayList.add(user);


                    }
                    UserAdapter = new UserAdapter(getApplicationContext(),UserArrayList);
                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                    rview.setLayoutManager(layoutManager);
                    rview.setAdapter(UserAdapter);

                }
                catch(Exception e){

                    Log.e("Exception",e.toString());

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error ", error.toString());
            }
        }){
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}