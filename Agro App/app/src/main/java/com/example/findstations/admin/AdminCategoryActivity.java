package com.example.findstations.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.findstations.R;

public class AdminCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnSeedsClick(View view)
    {
        Intent intent = new Intent(AdminCategoryActivity.this,AdminSeedsActivity.class);
        startActivity(intent);
    }


    public void btnPesticideClick(View view) {
        Intent intent = new Intent(AdminCategoryActivity.this,AdminPesticidesActivity.class);
        startActivity(intent);
    }

    public void btnAddFertilizerClick(View view) {
        Intent intent = new Intent(AdminCategoryActivity.this,AdminFertilizersActivity.class);
        startActivity(intent);
    }

    public void btnFungicideClick(View view) {
        Intent intent = new Intent(AdminCategoryActivity.this,AdminFungicidesActivity.class);
        startActivity(intent);
    }

    public void btnHerbicidesClick(View view) {
        Intent intent = new Intent(AdminCategoryActivity.this,AdminHerbicidesActivity.class);
        startActivity(intent);

    }

    public void btnFarmmachinesClick(View view) {
        Intent intent = new Intent(AdminCategoryActivity.this,AdminFarmmachinesActivity.class);
        startActivity(intent);
    }
}