package com.example.findstations.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.findstations.DBClass;
import com.example.findstations.R;
import com.example.findstations.SplashScreenActivity;
import com.example.findstations.user.CategoriesActivity;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);

    }

    public void onClick(View view) {
        String query = "DELETE FROM Configuration";
        DBClass.execNonQuery(query);
        Intent intent = new Intent(AdminHomeActivity.this, SplashScreenActivity.class);
        startActivity(intent);
        finish();

    }



    public void btnUsersClick(View view) {
        Intent intent = new Intent(AdminHomeActivity.this, UsersActivity.class);
        startActivity(intent);
    }

    public void btnCategoryClick(View view)
    {
        Intent intent = new Intent(AdminHomeActivity.this,AdminCategoryActivity.class);
        startActivity(intent);
    }

    public void btnBookClick(View view) {
        Intent intent = new Intent(AdminHomeActivity.this,ViewBookingActivity.class);
        startActivity(intent);
    }


//    public void btnBookClick(View view) {
//        Intent intent = new Intent(AdminHomeActivity.this, StationsActivity.class);
//        startActivity(intent);
//    }
}