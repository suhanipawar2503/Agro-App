package com.example.findstations.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.findstations.DBClass;
import com.example.findstations.R;
import com.example.findstations.SplashScreenActivity;

public class UserHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_home);

    }
    public void btnProfileClick(View view) {
        Intent intent = new Intent(UserHomeActivity.this,ProfileActivity.class);
        startActivity(intent);
    }

    public void btnLogClick(View view) {
        String query = "DELETE FROM Configuration";
        DBClass.execNonQuery(query);
        Intent intent = new Intent(UserHomeActivity.this, SplashScreenActivity.class);
        startActivity(intent);
        finish();
    }

    public void btncatgoryClick(View view) {
        Intent intent = new Intent(UserHomeActivity.this, CategoriesActivity.class);
        startActivity(intent);
    }

    public void btnBookingClick(View view) {
        Intent intent = new Intent(UserHomeActivity.this, MyBookingActivity.class);
        startActivity(intent);
    }
}