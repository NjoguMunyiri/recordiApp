package com.example.james.recordiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class homeartist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeartist);


        Toast.makeText(homeartist.this, "Welcome Dear Artist", Toast.LENGTH_SHORT).show();

        Button btnforum = (Button)findViewById(R.id.btnforum);
        btnforum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_forum = new Intent(homeartist.this, forum.class);
                startActivity(activity_forum);
            }
        });



        Button btnlgout = (Button)findViewById(R.id.btnlgout);
        btnlgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent activity_login = new Intent(homeartist.this, login.class);
                startActivity(activity_login);
            }
        });


    }
}
