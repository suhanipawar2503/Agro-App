package com.example.findstations.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.findstations.R;

import java.util.ArrayList;



public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<user> UserArrayList;
    Context context;

    public UserAdapter(Context ctx, ArrayList<user> UserArrayList){

        inflater = LayoutInflater.from(ctx);
        this.UserArrayList = UserArrayList;
    }

    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.user, parent, false);
        UserAdapter.MyViewHolder holder = new UserAdapter.MyViewHolder(view);
        return holder;
    }


    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(UserAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtId.setText(UserArrayList.get(position).id);
        holder.txtName.setText(UserArrayList.get(position).name);
        holder.txtCity.setText(UserArrayList.get(position).city);
        holder.txtMobileno.setText(UserArrayList.get(position).mobileno);

    }



    @Override
    public int getItemCount() {
        return UserArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtId,txtName, txtCity, txtMobileno;


        public MyViewHolder(View view) {
            super(view);
            context = itemView.getContext();
            txtId = (TextView) view.findViewById(R.id.txtId);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtCity = (TextView) view.findViewById(R.id.txtCity);
            txtMobileno = (TextView) view.findViewById(R.id.txtMobileno);

        }
    }
}
