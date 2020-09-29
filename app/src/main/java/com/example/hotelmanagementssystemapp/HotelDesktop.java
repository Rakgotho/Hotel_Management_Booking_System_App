package com.example.hotelmanagementssystemapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HotelDesktop extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText Name, Surname, Bed, id;
    Button addButton;
    Button viewButton;
    Button updateButton, search;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_desktop);

        myDb= new DatabaseHelper(this);
        Name=(EditText)findViewById(R.id.firstname);
        Surname=(EditText)findViewById(R.id.surname);
        Bed=(EditText)findViewById(R.id.beds);
        id=(EditText)findViewById(R.id.identity);
        search = (Button) findViewById(R.id.edit);
        addButton = (Button) findViewById(R.id.add);
        viewButton = (Button) findViewById(R.id.view);
        updateButton =(Button) findViewById(R.id.update);
        deleteButton =(Button) findViewById(R.id.delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
        SearchData();
    }

    private void SearchData()
    {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelDesktop.this, Identity.class);
                startActivity(intent);
            }
        });
    }

    public void DeleteData()
    {
        deleteButton.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String iden = id.getText().toString().trim();
                        Integer deletedRows = myDb.deleteData(iden);
                        if (deletedRows > 0) {
                            Toast.makeText(HotelDesktop.this, "Data deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(HotelDesktop.this, "Data not deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }
    public void UpdateData()
    {

        updateButton.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String iden = id.getText().toString().trim();
                        String user = Name.getText().toString().trim();
                        String surN  = Surname.getText().toString();
                        String beding = Bed.getText().toString().trim();
                        boolean IsUpdate= myDb.updateData(iden, user, surN, beding);
                        if(IsUpdate==true)
                        {
                            Toast.makeText(HotelDesktop.this, "Data updated", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(HotelDesktop.this, "Data not updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
    public void AddData()
    {
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String iden = id.getText().toString().trim();
                        String user = Name.getText().toString().trim();
                        String surN  = Surname.getText().toString();
                        String beding = Bed.getText().toString().trim();

                        boolean Isinserted= myDb.insertData(iden, user, surN, beding);
                        if (Isinserted==true)
                        {
                            Toast.makeText(HotelDesktop.this, "Data inserted", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(HotelDesktop.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


        );
    }
    public void viewAll()
    {
        viewButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=myDb.getAllData();
                        if(res.getCount()==0){
                            showMessage("RE-TRY","NO DATA FOUND");
                            return;
                        }

                        StringBuffer buffer= new StringBuffer();
                        while(res.moveToNext()) {
                            buffer.append("ID: "+res.getString(0)+"\n");
                            buffer.append("NAME: "+res.getString(1)+"\n");
                            buffer.append("SURNAME: "+res.getString(2)+"\n");
                            buffer.append("NUMBER OF ROOMS: "+res.getString(3)+"\n\n");
                        }
                        showMessage("Information",buffer.toString());
                    }
                });

    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();


    }
}