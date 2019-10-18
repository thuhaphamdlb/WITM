package com.example.witm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AddItem extends AppCompatActivity {
    TextView tvUserName = (TextView) findViewById(R.id.tvUserName);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        final Intent infor = this.getIntent();
        String userName = infor.getStringExtra("email");
        tvUserName.setText(":" + userName);
    }
}
