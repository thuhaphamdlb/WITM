package com.example.witm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {
    final EditText edtUserName = (EditText) findViewById(R.id.edtUserName);
    Button button_login = (Button) findViewById(R.id.btnUserName);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        findViewById(R.id.btnUserName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), AddItem.class);
                startActivityForResult(myIntent, 0);
                String email = edtUserName.getText().toString();
                startActivityForResult(myIntent,0);
            }
        });
    }
}
