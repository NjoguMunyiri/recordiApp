package com.example.james.recordiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class home extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toast.makeText(home.this, "Welcome Mr Producer", Toast.LENGTH_SHORT).show();

        Button btnforum = (Button)findViewById(R.id.btnforumn);
        btnforum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_forum = new Intent(home.this, forum.class);
                startActivity(activity_forum);
            }
        });



        Button btnlgout = (Button)findViewById(R.id.btnlgout);
        btnlgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent activity_login = new Intent(home.this, login.class);
                startActivity(activity_login);
            }
        });


    }
}
