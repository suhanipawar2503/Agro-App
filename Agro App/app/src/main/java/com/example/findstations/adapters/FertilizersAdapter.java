package com.example.findstations.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findstations.DBClass;
import com.example.findstations.R;

import java.util.ArrayList;

public class FertilizersAdapter extends RecyclerView.Adapter<FertilizersAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<fertilizers> fertilizersArrayList;
    Context context;

    public FertilizersAdapter(Context ctx, ArrayList<fertilizers> fertilizersArrayList){

        inflater = LayoutInflater.from(ctx);
        this.fertilizersArrayList = fertilizersArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fertilizers, parent, false);
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

        holder.txtTitle.setText(fertilizersArrayList.get(position).name);
        holder.txtMrp.setText("\u20B9 "+fertilizersArrayList.get(position).mrp);
        holder.txtPrice.setText("\u20B9 "+fertilizersArrayList.get(position).price);
        if (fertilizersArrayList.get(position).stock.equals("Out_Of_Stock"))
        {
            holder.txtStock.setVisibility(View.VISIBLE);
            holder.txtStock.setText("Out of Stock");
        }
        final Uri uri = Uri.parse(DBClass.url+"fertilizerspics/"+fertilizersArrayList.get(position).pic);
        Picasso.get().load(uri).into(holder.imageView);

        holder.layoutProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Log.e("id", "onClick: " + fertilizersArrayList.get(position).id);
                    Toast.makeText(context, "You Clicked on product and id is "+ fertilizersArrayList.get(position).id, Toast.LENGTH_SHORT).show();
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
        return fertilizersArrayList.size();
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
