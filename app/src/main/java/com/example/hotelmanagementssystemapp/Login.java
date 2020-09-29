package com.example.hotelmanagementssystemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    TextView text1;
    EditText user1;
    EditText pass1;
    Button login;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        text1 =(TextView) findViewById(R.id.login);
        user1 =(EditText) findViewById(R.id.username1);
        pass1 =(EditText) findViewById(R.id.password1);
        login =(Button) findViewById(R.id.login1);
        myDb = new DatabaseHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                String username = user1.getText().toString();
                String password = pass1.getText().toString();
                //boolean res = myDb.checkUser(username,password);

                if(user1.getText().toString().trim().equals("") || pass1.getText().toString().trim().equals(""))
                {
                    Toast.makeText(Login.this, "Please enter your login credentials", Toast.LENGTH_SHORT).show();
                }
                /*if(res = true)
                {
                    Intent intent = new Intent(Login.this, HotelDesktop.class);
                    startActivity(intent);
                }*/

                else
                {
                    //Toast.makeText(Login.this, "Details do not match", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, HotelDesktop.class);
                    startActivity(intent);


                }


            }


        });
    }

}