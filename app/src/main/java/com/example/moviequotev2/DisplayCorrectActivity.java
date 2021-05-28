package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class DisplayCorrectActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 1500; //Inital start time of the timer
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning; //true/false of whether the timer is running
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS; //Variable of the time left
    static int roundNums = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_correct);
        Intent intent = getIntent();
        startTimer();
        TextView textView = (TextView) findViewById(R.id.score);
        textView.setText(String.valueOf(((globalClass) this.getApplication()).getScore()));
        //string with the title of "globalClass" creates the score

    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) { //This creates a new countdown timer starting with the mTimerLeftInMillis and
            // and counts down every 1000 milliseconds
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {

            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDownText(){
        int seconds = (int) mTimeLeftInMillis/1000;
        final Intent intent2 = new Intent(this, NextRoundCounter.class);

        if (seconds <= 0) {
            startActivity(intent2);
            stopTimer();

        }
    }
    public void stopTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    public void onBackPressed(){

    }
    public String loadScore(){
        FileInputStream fis = null;
        try {
            fis = openFileInput(    "scores.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while((text = br.readLine()) != null){
                return text;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
