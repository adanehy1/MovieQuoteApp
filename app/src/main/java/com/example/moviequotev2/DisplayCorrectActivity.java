package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class DisplayCorrectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_correct);
        Intent intent = getIntent();
        Handler handler = new Handler();
        // This handler creates the delay of 2000 milliseconds
        final Intent intent2 = new Intent(this, DisplayMessageActivity.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent2);
            }
        }, 2000);
    }
}
