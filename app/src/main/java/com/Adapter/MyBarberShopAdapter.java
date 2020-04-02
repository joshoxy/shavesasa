package com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Interface.IRecyclerItemSelectedListener;
import com.Model.Barbershop;
import com.example.shavesasa.Common.Common;
import com.example.shavesasa.R;

import java.util.ArrayList;
import java.util.List;

public class MyBarberShopAdapter extends RecyclerView.Adapter<MyBarberShopAdapter.MyViewHolder> {

    Context context;
    List<Barbershop> barbershopList;
    List<CardView> cardViewList;
    LocalBroadcastManager localBroadcastManager;

    public MyBarberShopAdapter(Context context, List<Barbershop> barbershopList) {
        this.context = context;
        this.barbershopList = barbershopList;
        cardViewList = new ArrayList<>();
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
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
        if (!cardViewList.contains(holder.card_barbershop))
            cardViewList.add(holder.card_barbershop);

        holder.setiRecyclerItemSelectedListener(new IRecyclerItemSelectedListener() {
            @Override
            public void onItemSelectedListener(View view, int pos) {
                //set white background
                for (CardView cardView:cardViewList)
                    cardView.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));

                //set selected background
                holder.card_barbershop.setCardBackgroundColor(context.getResources()
                .getColor(android.R.color.holo_orange_dark));

                //enable button next
                Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
                intent.putExtra(Common.KEY_BARBERSHOP_STORE, barbershopList.get(pos));
                localBroadcastManager.sendBroadcast(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return barbershopList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_barbershop_name, txt_barbershop_address;
        CardView card_barbershop;

        IRecyclerItemSelectedListener iRecyclerItemSelectedListener;

        public void setiRecyclerItemSelectedListener(IRecyclerItemSelectedListener iRecyclerItemSelectedListener) {
            this.iRecyclerItemSelectedListener = iRecyclerItemSelectedListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_barbershop_name = (TextView)itemView.findViewById(R.id.txt_barbershop_name);
            txt_barbershop_address = (TextView)itemView.findViewById(R.id.txt_barbershop_address);
            card_barbershop = (CardView)itemView.findViewById(R.id.card_barbershop);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            iRecyclerItemSelectedListener.onItemSelectedListener(v, getAdapterPosition());
        }
    }
}
