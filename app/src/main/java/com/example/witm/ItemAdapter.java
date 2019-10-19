package com.example.witm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.witm.Item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.TaskViewHolder>{
    public List<Item> Items;
    private OnItemClicked onClick;

    public ItemAdapter(Runnable mainActivity, List<Item> items) {
        Items = items;
    }

    public interface OnItemClicked {
        void onClickItemUpdate(int position);

        void onClickItemDelete(int position);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, final int position) {
        String infor = "";
        infor += Items.get(position).itemName + "    " + Items.get(position).itemPrice;
        holder.tvView.setText(infor);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("1", "1  " +position);
                onClick.onClickItemDelete(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvView;
        Button btnUpdate, btnDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvView = itemView.findViewById(R.id.itemName);
//            btnDelete = itemView.findViewById(R.id.deleteTask);
//            btnUpdate = itemView.findViewById(R.id.updateTask);
        }
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }
}
