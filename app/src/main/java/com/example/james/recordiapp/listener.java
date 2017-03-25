package com.example.james.recordiapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class listener extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener);


        Button btnlgout = (Button)findViewById(R.id.btnlgout);
        btnlgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent activity_login = new Intent(listener.this, login.class);
                startActivity(activity_login);
            }
        });


        Button play = (Button)findViewById(R.id.btnplay);
        Button pause = (Button) findViewById(R.id.btnpause);
        Button stop = (Button) findViewById(R.id.btnstop);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.badbele);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                finish();
                Intent activity_login = new Intent(listener.this, login.class);
                startActivity(activity_login);

            }
        });


    }
}
