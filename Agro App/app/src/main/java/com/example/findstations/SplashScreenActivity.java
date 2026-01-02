package com.example.findstations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.findstations.admin.AdminHomeActivity;
import com.example.findstations.user.UserHomeActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        createDatabase();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);

                String query = "SELECT * FROM Configuration";
                if (DBClass.checkIfRecordExist(query))
                {
                    query = "SELECT CValue FROM Configuration WHERE CName = 'usertype'";
                    String usertype = DBClass.getSingleValue(query);
                    if (usertype.equals("admin"))
                    {
                        intent = new Intent(SplashScreenActivity.this, AdminHomeActivity.class);
                    }
                    else
                    {
                        intent = new Intent(SplashScreenActivity.this, UserHomeActivity.class);
                    }
                }


                startActivity(intent);
                finish();
            }
        },2000);


    }
    public void createDatabase() {
        String query;
        DBClass.database = openOrCreateDatabase(DBClass.dbname, MODE_PRIVATE, null);
        query = "CREATE TABLE IF NOT EXISTS Configuration(CName VARCHAR, CValue VARCHAR);";
        DBClass.execNonQuery(query);

    }
}