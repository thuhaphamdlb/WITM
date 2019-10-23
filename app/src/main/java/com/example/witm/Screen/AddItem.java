package com.example.witm.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.Button;

import com.example.witm.Database.AppDatabase;
import com.example.witm.Database.Item;
import com.example.witm.R;

import java.util.List;

public class AddItem extends AppCompatActivity {
    AppDatabase db;
    Button btnAdd;
    RecyclerView rvItem;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        btnAdd = findViewById(R.id.btnAdd);
        rvItem = findViewById(R.id.recyclerView);
        rvItem.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvItem.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ItemAdapter();
        adapter.listener = new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getOneItem(position);
            }
        };
        rvItem.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddItemScreen();
            }
        });
    }

    void getOneItem(int position) {
        Item item = adapter.items.get(position);
        Intent intent = new Intent(AddItem.this, ItemInfoActivity.class);
        intent.putExtra("id", item.getTid());
        intent.putExtra("name", item.getItemName());
        intent.putExtra("price", item.getItemPrice());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getItemFromDatabase();
    }

    @SuppressLint("StaticFieldLeak")
    private void getItemFromDatabase() {
        new AsyncTask<Void, Void, List<Item>>() {

            @Override
            protected List<Item> doInBackground(Void... voids) {
                return db.itemDao().getAll();
            }

            @Override
            protected void onPostExecute(List<Item> items) {
                super.onPostExecute(items);
                adapter.items = items;
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

    private void openAddItemScreen() {
        startActivity(new Intent(AddItem.this, Add.class));
    }

}