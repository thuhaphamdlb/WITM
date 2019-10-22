package com.example.witm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.witm.Database.AppDatabase;
import com.example.witm.Database.Item;


public class Add extends AppCompatActivity {
    AppDatabase db;
    EditText edtItemName;
    EditText edtItemPrice;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        edtItemName = findViewById(R.id.name);
        edtItemPrice = findViewById(R.id.price);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewItemToDatabase();
                finish();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void addNewItemToDatabase() {
        final String itemName = edtItemName.getText().toString();
        final String itemPrice = edtItemPrice.getText().toString();
        if (itemName.isEmpty() || itemPrice.isEmpty()) {
            Toast.makeText(this, "Input your data", Toast.LENGTH_SHORT).show();
            return;
        }

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Item newItem = new Item(itemName, itemPrice);
                db.itemDao().insert(newItem);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(Add.this, "New item added", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}




