package com.example.hotelmanagementssystemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Identity extends AppCompatActivity {

    Button search, view;
    TextView text;
    EditText id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity);

        search = (Button)findViewById(R.id.edit);
        view = (Button)findViewById(R.id.view);
        id = (EditText) findViewById(R.id.id);
        text = (TextView)findViewById(R.id.findout);
    }
}