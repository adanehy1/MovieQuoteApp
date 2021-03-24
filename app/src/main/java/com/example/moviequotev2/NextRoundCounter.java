package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class NextRoundCounter extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 3000; //Inital start time of the timer
    private TextView mTextViewCountDown; //Creates the variable that sets the text in the text view
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning; //true/false of whether the timer is running
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS; //Variable of the time left

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

    }
}
