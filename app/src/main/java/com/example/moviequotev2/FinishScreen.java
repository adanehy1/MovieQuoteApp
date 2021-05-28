package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FinishScreen extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 3000; //Inital start time of the timer
    private TextView mTextViewCountDown; //Creates the variable that sets the text in the text view
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning; //true/false of whether the timer is running
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS; //Variable of the time left
    static int roundNums = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_screen);

        TextView textView = (TextView) findViewById(R.id.finalScore);
        textView.setText(String.valueOf(((globalClass) this.getApplication()).getScore()));
        //string with the title of "globalClass" creates the score

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

    public void openButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    @Override
    public void onBackPressed(){

    }
}
