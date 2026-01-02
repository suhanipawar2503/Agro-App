package com.example.findstations.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.findstations.R;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnSeedsClick(View view)
    {
        Intent intent = new Intent(CategoriesActivity.this, SeedsActivity.class);
        startActivity(intent);
    }

    public void btnPesticidesClick(View view)
    {
        Intent intent = new Intent(CategoriesActivity.this, PesticidesActivity.class);
        startActivity(intent);
    }

    public void btnFertilizerClick(View view)
    {
        Intent intent = new Intent(CategoriesActivity.this, FertilizersActivity.class);
        startActivity(intent);
    }

    public void btnFungicideClick(View view)
    {
        Intent intent = new Intent(CategoriesActivity.this, FungicidesActivity.class);
        startActivity(intent);
    }

    public void btnHerbicidesClick(View view)
    {
        Intent intent = new Intent(CategoriesActivity.this, HerbicidesActivity.class);
        startActivity(intent);
    }

    public void btnFarmmachinesClick(View view)
    {
        Intent intent = new Intent(CategoriesActivity.this, FarmmachinesActivity.class);
        startActivity(intent);
    }
}