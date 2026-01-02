package com.example.findstations.adapters;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.findstations.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<booking> bookingArrayList;
    Context context;
    public BookingAdapter(Context ctx, ArrayList<booking> bookingArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.bookingArrayList = bookingArrayList;
    }

    @NonNull
    @Override
    public BookingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.booking, parent, false);
        BookingAdapter.MyViewHolder holder =new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.MyViewHolder holder, int position) {

        holder.txtName.setText(bookingArrayList.get(position).name);
        holder.txtEmail.setText(bookingArrayList.get(position).email);
        holder.txtMobileno.setText(bookingArrayList.get(position).mobileno);
        holder.txtLocation.setText(bookingArrayList.get(position).location);
        holder.txtPayment.setText(bookingArrayList.get(position).payment);

        String details = bookingArrayList.get(position).name + "\n" + bookingArrayList.get(position).email + "\n" +  bookingArrayList.get(position).mobileno + "\n" + bookingArrayList.get(position).location + "\n" + bookingArrayList.get(position).payment;

        //holder.txtPrice.setText(details);
    }

    @Override
    public int getItemCount() {
        return bookingArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName,txtEmail,txtMobileno,txtLocation,txtPayment;
        LinearLayout layoutCard;
        public MyViewHolder(@NonNull View view) {
            super(view);
            context = itemView.getContext();
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtEmail = (TextView) view.findViewById(R.id.txtEmail);
            txtMobileno= (TextView) view.findViewById(R.id.txtMobileno);
            txtLocation = (TextView) view.findViewById(R.id.txtLocation);
            txtPayment = (TextView) view.findViewById(R.id.txtPayment);
            layoutCard = (LinearLayout) view.findViewById(R.id.layoutCard);


        }
    }
}
