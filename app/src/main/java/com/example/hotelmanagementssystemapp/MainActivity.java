package com.example.hotelmanagementssystemapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    TextView text,acc;
    EditText user;
    EditText pass;
    EditText con;
    Button reg;
    Button log;
    DatabaseHelper myDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = (Button)findViewById(R.id.login);
        text =(TextView) findViewById(R.id.welcome);
        user =(EditText) findViewById(R.id.username);
        pass =(EditText) findViewById(R.id.password);
        con =(EditText) findViewById(R.id.confirm);
        acc =(TextView) findViewById(R.id.account);
        reg =(Button) findViewById(R.id.signup);
        log =(Button) findViewById(R.id.login);
        myDb = new DatabaseHelper(this);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String username = user.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String confirm = con.getText().toString().trim();

                if (username.equals("") || password.equals("") || confirm.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                }
                //This checks if password is the same with confirm
                if(password.equals(confirm)) {
                    long res = myDb.addUser(username, password);

                    if(res>1)
                    {
                        Toast.makeText(MainActivity.this, "Registered successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Register Unsuccessful",Toast.LENGTH_SHORT).show();

                    }

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Password do not match",Toast.LENGTH_SHORT).show();

                }
            }

        });
        log.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);

            }
        });




    }
}