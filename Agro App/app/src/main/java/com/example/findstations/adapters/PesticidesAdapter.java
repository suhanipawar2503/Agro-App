package com.example.findstations.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.findstations.DBClass;
import com.example.findstations.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PesticidesAdapter extends RecyclerView.Adapter<PesticidesAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<pesticides> pesticidesArrayList;
    Context context;

    public PesticidesAdapter(Context ctx, ArrayList<pesticides> pesticidesArrayList){

        inflater = LayoutInflater.from(ctx);
        this.pesticidesArrayList = pesticidesArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pesticides, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.txtTitle.setText(pesticidesArrayList.get(position).name);
        holder.txtMrp.setText("\u20B9 "+pesticidesArrayList.get(position).mrp);
        holder.txtPrice.setText("\u20B9 "+pesticidesArrayList.get(position).price);
        if (pesticidesArrayList.get(position).stock.equals("Out_Of_Stock"))
        {
            holder.txtStock.setVisibility(View.VISIBLE);
            holder.txtStock.setText("Out of Stock");
        }
        final Uri uri = Uri.parse(DBClass.url+"pesticidespics/"+pesticidesArrayList.get(position).pic);
        Picasso.get().load(uri).into(holder.imageView);

        holder.layoutProduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    Log.e("id", "onClick: " + pesticidesArrayList.get(position).id);
                    Toast.makeText(context, "You Clicked on product and id is "+ pesticidesArrayList.get(position).id, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(context, ProductDetailsActivity.class);
//                    intent.putExtra("id", productArrayList.get(position).id);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
                } catch (Exception ex) {
                    Log.e("Ex", "onClick: " + ex);
                }
            }
        });






    }

    @Override
    public int getItemCount() {
        return pesticidesArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtMrp, txtPrice, txtStock;
        ImageView imageView;
        LinearLayout layoutProduct;

        public MyViewHolder(View view) {
            super(view);
            context = itemView.getContext();
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            txtMrp = (TextView) view.findViewById(R.id.txtMrp);
            txtStock = (TextView) view.findViewById(R.id.txtStock);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            imageView = (ImageView) view.findViewById(R.id.imgIcon);
            layoutProduct = (LinearLayout) view.findViewById(R.id.layoutProduct);

        }
    }
}
