package com.example.witm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.Update;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateItemActivity extends AppCompatActivity {
    AppDatabase db;
    EditText edtItemPrice;
    EditText edtItemName;
    Button btnUpdate;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        edtItemName = findViewById(R.id.edtUpdateItemName);
        edtItemPrice = findViewById(R.id.edtUpdateItemPrice);
        btnUpdate = findViewById(R.id.btnUpdate);

        int id = getIntent().getIntExtra("id", 0);
        itemId = id;
        String name = getIntent().getStringExtra("Name");
        String price = getIntent().getStringExtra("Price");

        edtItemName.setText(name);
        edtItemPrice.setText(price);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateItemToDatabase();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void updateItemToDatabase() {
        final String name = edtItemName.getText().toString();
        final String price = edtItemPrice.getText().toString();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Item newItem = new Item(name, price);
                newItem.setTid(itemId); // thinking about why we need to set id here
                db.itemDao().update(newItem);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                showSuccessDialog();
            }
        }.execute();
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage("Update Success")
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();
    }
}
