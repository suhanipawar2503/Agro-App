package com.example.findstations.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.findstations.DBClass;
import com.example.findstations.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddPesticidesActivity extends AppCompatActivity {

    EditText etxtName, etxtPrice, etxtMRP, etxtDescription;
    String name="", price="", mrp="", description="",  stock="";
    RadioButton rdInstock, rdOutofstock;
    ProgressDialog pDialog;
    JSONArray jsonData = null;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pesticides);
        etxtName = findViewById(R.id.etxtName);
        etxtMRP = findViewById(R.id.etxtMRP);
        etxtPrice = findViewById(R.id.etxtPrice);
        etxtDescription = findViewById(R.id.etxtDescription);
        imageView = findViewById(R.id.imageView);
        rdInstock = findViewById(R.id.rdInstock);
        rdOutofstock = findViewById(R.id.rdOutofstock);

    }

    public void btnSubmitClick(View view) {
        name = etxtName.getText().toString();
        if(name.equals("")) {
            etxtName.setError("Please enter product Name");
            etxtName.requestFocus();
            return;
        }
        price = etxtPrice.getText().toString();
        if(price.equals("")) {
            etxtPrice.setError("Please enter product price");
            etxtPrice.requestFocus();
            return;
        }
        mrp = etxtMRP.getText().toString();
        if(mrp.equals("")) {
            etxtMRP.setError("Please enter product mrp");
            etxtMRP.requestFocus();
            return;
        }
        description = etxtDescription.getText().toString();
        if(description.equals("")) {
            etxtDescription.setError("Please enter product Description");
            etxtDescription.requestFocus();
            return;
        }
        if (bitmap == null) {
            Toast.makeText(this, "Select Iimage", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rdInstock.isChecked())
        {
            stock = rdInstock.getText().toString();
        }
        if (rdOutofstock.isChecked())
        {
            stock = rdOutofstock.getText().toString();
        }

        final String imageString = convertBitmapToBase64(bitmap);
        pDialog = new ProgressDialog(AddPesticidesActivity.this);
        pDialog.setMessage("validating your details, please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DBClass.urlAddPesticides,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        Log.d("Response", ">> "+response);
                        pDialog.dismiss();
                        try {
                            jsonObject = new JSONObject(response);

                            Log.e( "onResponse: ", jsonObject.getString("status") );

                            if(jsonObject.getString("status").equals("success")) {

                                Intent intent = new Intent(getApplicationContext(), AdminPesticidesActivity.class);
                                startActivity(intent);
                                finish();

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Product Not Added ...Failed...", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Check Internet Connection...", Toast.LENGTH_LONG).show();
                            Log.e("Exception", ">> "+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        Log.e("Exception", error.toString());
                        Toast.makeText(getApplicationContext(), "Check Internet Connection...", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("price", price);
                params.put("mrp", mrp);
                params.put("description", description);
                params.put("image", imageString);
                params.put("stock", stock);
                Log.e("Params", params.toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    public void btnSelectFileClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                InputStream imageStream = getContentResolver().openInputStream(imageUri);
                bitmap = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}