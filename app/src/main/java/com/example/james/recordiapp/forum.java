package com.example.james.recordiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class forum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);


        Button btnlgout = (Button)findViewById(R.id.btnlgout);
        btnlgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent activity_login = new Intent(forum.this, login.class);
                startActivity(activity_login);
            }
        });

    }
}
