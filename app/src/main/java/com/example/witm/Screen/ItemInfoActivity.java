package com.example.witm.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.witm.Database.AppDatabase;
import com.example.witm.Database.Item;
import com.example.witm.R;

public class ItemInfoActivity extends AppCompatActivity {
    EditText name, price;
    Button btnUpdate, btnDelete;
    AppDatabase db;
    int itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        name = findViewById(R.id.tvItemName);
        price = findViewById(R.id.tvItemPrice);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        int id = getIntent().getIntExtra("id", 0);
        itemID = id;

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        price.setText(intent.getStringExtra("price"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateItemToDatabase();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void updateItemToDatabase() {
        final String itemName = name.getText().toString();
        final String itemPrice = price.getText().toString();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Item newItem = new Item(itemName, itemPrice);
                newItem.setTid(itemID);
                db.itemDao().update(newItem);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(ItemInfoActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void deleteItem() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.itemDao().deleteAll();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(ItemInfoActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        }.execute();
    }
}
