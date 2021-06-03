package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        TextView textView1 = (TextView) findViewById(R.id.HS1);
        textView1.setText("First High Score");

        TextView textView2 = (TextView) findViewById(R.id.HS2);
        textView2.setText("Second High Score");

        TextView textView3 = (TextView) findViewById(R.id.HS3);
        textView3.setText("Third High Score");

        TextView textView4 = (TextView) findViewById(R.id.HS4);
        textView4.setText("Fourth High Score");

        TextView textView5 = (TextView) findViewById(R.id.HS5);
        textView5.setText("Fifth High Score");
    }
    @Override

    public void onBackPressed(){
        View popup = findViewById(R.id.popup);
        popup.setVisibility(View.VISIBLE);
        View areyousure = findViewById(R.id.areyousure);
        areyousure.setVisibility(View.VISIBLE);
        View noexit = findViewById(R.id.noexit);
        noexit.setVisibility(View.VISIBLE);
        View yesexit = findViewById(R.id.yesexit);
        yesexit.setVisibility(View.VISIBLE);
    }

    public void yesexitButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void noexitButton(View view){
        View popup = findViewById(R.id.popup);
        popup.setVisibility(View.INVISIBLE);
        View areyousure = findViewById(R.id.areyousure);
        areyousure.setVisibility(View.INVISIBLE);
        View noexit = findViewById(R.id.noexit);
        noexit.setVisibility(View.INVISIBLE);
        View yesexit = findViewById(R.id.yesexit);
        yesexit.setVisibility(View.INVISIBLE);

    }
}