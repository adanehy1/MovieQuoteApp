package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {
    private TextView countdownText;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 10000;
    private boolean timerRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        startTimer();
        countdownText = findViewById(R.id.countdown_text);
    }

    public void movie1Button(View view) {
        Intent intent = new Intent(this, DisplayCorrectActivity.class);
        startActivity(intent);
        Button b = null;
        b = (Button) view;
        b.setText("test");
    }
    public void movie2Button(View view) {
        Intent intent = new Intent(this, DisplayIncorrectActivity.class);
        startActivity(intent);
    }
    public void movie3Button(View view) {
        Intent intent = new Intent(this, DisplayIncorrectActivity.class);
        startActivity(intent);
    }
    public void movie4Button(View view) {
        Intent intent = new Intent(this, DisplayIncorrectActivity.class);
        startActivity(intent);
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 10) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        timerRunning = true;
    }

    public void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
    }

    public void updateTimer() {
        int seconds = (int) timeLeftInMilliseconds / 1000;
        int milli = (int) timeLeftInMilliseconds % 1000 / 10;

        String timeLeftText;

        timeLeftText = "" + seconds;
        timeLeftText += ".";
        timeLeftText += milli;

        countdownText.setText(timeLeftText);


    }
}
