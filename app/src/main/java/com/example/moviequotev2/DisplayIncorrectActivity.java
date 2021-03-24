package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class DisplayIncorrectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_incorrect);
        Intent intent = getIntent();
        Handler handler = new Handler();
        final Intent intent2 = new Intent(this, NextRoundCounter.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent2);
            }
        }, 1000);
        Handler handler2 = new Handler();
        final Intent intent3 = new Intent(this, DisplayMessageActivity.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent3);
            }
        }, 3000);

    }
}
