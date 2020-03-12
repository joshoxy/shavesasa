package com.example.joshuaochieng.newsasa.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.joshuaochieng.newsasa.Interface.ItemClickListener;
import com.example.joshuaochieng.newsasa.R;

import de.hdodenhof.circleimageview.CircleImageView;

class ListSourceViewHolder extends RecyclerView.ViewHolder
{
    ItemClickListener itemClickListener;

    TextView source_title;
    CircleImageView source_image;

    public ListSourceViewHolder(View itemView) {
        super(itemView);

       source_image = (CircleImageView)itemView.findViewById(R.id.source_image);
       source_title = (TextView)itemView.findViewById(R.id.source_name);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
public class ListSourceAdapter {
}
