package com.example.witm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.witm.Database.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    List<Item> items;
    OnItemClickListener listener;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.tvItemName.setText(items.get(position).getItemName());
        holder.tvItemPrice.setText(items.get(position).getItemPrice());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemName;
        TextView tvItemPrice;
        Button btnUpdate;
        Button btnDelete;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItemName = itemView.findViewById(R.id.name);
            tvItemPrice = itemView.findViewById(R.id.price);
            btnUpdate = itemView.findViewById(R.id.btnAdd);
            btnDelete = itemView.findViewById(R.id.btnAdd);

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onUpdateClick(getAdapterPosition());
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDeleteClick(getAdapterPosition());
                }
            });
        }

    }

    interface OnItemClickListener {
        void onUpdateClick(int position);

        void onDeleteClick(int position);
    }
}
