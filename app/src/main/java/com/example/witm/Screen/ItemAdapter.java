package com.example.witm.Screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.witm.Database.Item;
import com.example.witm.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    List<Item> items;
    OnItemClickListener listener;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        holder.showItem.setText(items.get(position).getItemName());
        holder.showPrice.setText(items.get(position).getItemPrice());
        holder.showItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
        holder.showPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView showItem, showPrice;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            showItem = itemView.findViewById(R.id.showItem);
            showPrice = itemView.findViewById(R.id.showPrice);
        }

    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }
}
