package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NextRoundCounter extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 3000; //Inital start time of the timer
    private TextView mTextViewCountDown; //Creates the variable that sets the text in the text view
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning; //true/false of whether the timer is running
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS; //Variable of the time left
    static int roundNums = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_round_counter);

        mTextViewCountDown = findViewById(R.id.counter); //This assigns the text view of "counter" in xml file to the variable we created

        startTimer();

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

        String timeLeft;
        timeLeft = "" + seconds;

        mTextViewCountDown.setText(timeLeft);
        Intent intent = new Intent(this, RoundActivity.class);
        if (seconds <= 0) {
            startActivity(intent);
            stopTimer();
        }
    }
    public void stopTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        roundNums--;
        Intent finish = new Intent(this, FinishScreen.class);
        if (roundNums <= 0) {
            resetScoresToZero();
            startActivity(finish);
            stopTimer();
            roundNums = 5;
        }
    }
    @Override
    public void onBackPressed(){

    }
    public void resetScoresToZero(){
        String FILENAME = "scores.txt";
        String string = "0.0";

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(string.getBytes())    ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
