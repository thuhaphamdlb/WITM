package com.example.witm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    AppDatabase db;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-item").build();
    }

    public void addNewItem(View view) {
        TextView tvName = findViewById(R.id.name);
        TextView tvPrice = findViewById(R.id.price);
        final String itemName = tvName.getText().toString();
        final String itemPrice = tvPrice.getText().toString();

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Item user = new Item(itemName, itemPrice);
                db.itemDao().insertAll(user);
                return null;
            }
        }.execute();

        Toast.makeText(getApplicationContext(), "Add item successfully", Toast.LENGTH_SHORT).show();
    }
}
