package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Model.Barbershop;
import com.example.shavesasa.R;

import java.util.List;

public class MyBarberShopAdapter extends RecyclerView.Adapter<MyBarberShopAdapter.MyViewHolder> {

    Context context;
    List<Barbershop> barbershopList;

    public MyBarberShopAdapter(Context context, List<Barbershop> barbershopList) {
        this.context = context;
        this.barbershopList = barbershopList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_barbershop, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txt_barbershop_name.setText(barbershopList.get(position).getName());
        holder.txt_barbershop_address.setText(barbershopList.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return barbershopList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_barbershop_name, txt_barbershop_address;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_barbershop_name = (TextView)itemView.findViewById(R.id.txt_barbershop_name);
            txt_barbershop_address = (TextView)itemView.findViewById(R.id.txt_barbershop_address);

        }
    }
}
