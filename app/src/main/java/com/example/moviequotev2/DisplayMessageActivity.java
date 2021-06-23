package com.example.moviequotev2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DisplayMessageActivity extends AppCompatActivity {
    private TextView countdownText;
    private TextView round;
    private CountDownTimer countDownTimer;
    HashMap<String, String> quoteDict = new HashMap<String, String>();
    List<String> quotesList = new ArrayList<String>();
    String selectedQuote = "NOT SET";
    long timerLength = 10;
    private long timeLeftInMilliseconds = 10000;
    private boolean timerRunning;
    int randNum = (int) (Math.random() * 4) + 1;
    public static final String SHARED_PREFS = "highScore";
    public static final String HIGH_SCORE = "highScore";
    final String[] testNames = {"Step Brothers", "The Interview", "Hot Tub Time Machine", "Zoolander", "Super Bad", "Dodgeball"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Rand " + randNum );
        //Intent intentMovie = getIntent();
        //String[] selectedMovies = intentMovie.getStringArrayExtra("selectedMovies");
        //String[] selectedMoviesSeparated = selectedMovies.split(",");

        try{
            JsonQuotes jQuotes = new JsonQuotes(getApplicationContext(), testNames);
            //JsonQuotes jQuotes = new JsonQuotes(getApplicationContext(), selectedMovies);
            quotesList = jQuotes.getQList();
            quoteDict = jQuotes.getQDict();
            jQuotes.getMovieNames();
        } catch(JSONException e){
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        TextView TextView1 = (TextView) findViewById(R.id.textView);
        TextView1.setText("Welcome to Dynamic TextView");
        String buttonOneTxt = "NOT SET YET";
        String buttonTwoTxt = "NOT SET YET";
        String buttonThreeTxt = "NOT SET YET";
        String buttonFourTxt = "NOT SET YET";

        boolean fourInit = false;

        while(buttonOneTxt == buttonTwoTxt || buttonOneTxt == buttonThreeTxt || buttonOneTxt == buttonFourTxt ) {
           buttonOneTxt = setBtnQuotes(1);

        }
       while(buttonTwoTxt == buttonOneTxt || buttonTwoTxt == buttonThreeTxt || buttonTwoTxt == buttonFourTxt ) {
           buttonTwoTxt = setBtnQuotes(2);
       }
        while(buttonThreeTxt == buttonOneTxt || buttonThreeTxt == buttonTwoTxt || buttonThreeTxt == buttonFourTxt ){
            buttonThreeTxt = setBtnQuotes(3);
        }
        while(buttonFourTxt == buttonOneTxt || buttonFourTxt == buttonTwoTxt || buttonFourTxt == buttonThreeTxt || !fourInit ) {
            buttonFourTxt = setBtnQuotes(4);
            fourInit = true;
        }

        TextView textView = (TextView) findViewById(R.id.textScore2);
        textView.setText("Score: " + String.valueOf(((globalClass) this.getApplication()).getScore()));
        //string with the title of "globalClass" creates the score

        TextView1.setText(selectedQuote);

        //Sets the text for top left button
        Button button1 = (Button)findViewById(R.id.button2);
        button1.setText(buttonOneTxt);

        Button button2 = (Button)findViewById(R.id.button3);
        button2.setText(buttonTwoTxt);

        Button button3 = (Button)findViewById(R.id.button4);
        button3.setText(buttonThreeTxt);

        Button button4 = (Button)findViewById(R.id.button5);
        button4.setText(buttonFourTxt);

        Intent intent = getIntent();
        startTimer();
        countdownText = findViewById(R.id.countdown_text);

        // Delay to transition to "incorrect" after 10 seconds


    }

    public void movie1Button(View view) throws InterruptedException {
        setBtns(1);
    }

    public void movie2Button(View view) throws FileNotFoundException {
        setBtns(2);
    }
    public void movie3Button(View view) throws FileNotFoundException {
        setBtns(3);
    }
    public void movie4Button(View view) throws FileNotFoundException {
        setBtns(4);
    }

    public void yesexitButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        stopTimer();

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
    public String setBtnQuotes(int r){
        int randIButtonFour = (int) (Math.random() * (quotesList.size()));
        if(randNum == r) {
            while (((globalClass) this.getApplication()).contains(quotesList.get(randIButtonFour))) {
                randIButtonFour = (int) (Math.random() * (quotesList.size()));
            }
            selectedQuote = quotesList.get(randIButtonFour);
            ((globalClass) this.getApplication()).addQuote(selectedQuote);
        }
        return quoteDict.get(quotesList.get(randIButtonFour));
    }
    public void setBtns(int round){
        Intent intent = new Intent(this, DisplayIncorrectActivity.class);
        if(randNum == round) {
            intent = new Intent(this, DisplayCorrectActivity.class);
        }
        stopTimer();
        if(randNum == round){
            ((globalClass) this.getApplication()).setScore(calculateScore(timeLeftInMilliseconds));
        }
        checkNewHighScore((double)((globalClass) this.getApplication()).getScore());
        startActivity(intent);
    }
    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, timerLength) {
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
        Intent incorrect = new Intent(this, DisplayIncorrectActivity.class);
        if (seconds <= 0) {
            startActivity(incorrect);
            stopTimer();
               }
    }
    public double calculateScore(long timeRemaining){
        double timeInSec = (timeRemaining)/1000.0;
        return timeInSec*100;
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
    public double getHighScore() {
        float score;
        Set<String> temp = new HashSet<>();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        score = sharedPreferences.getFloat(HIGH_SCORE, 12);
        return score;
    }
    public void checkNewHighScore(double curScore){
        if(curScore <= 0.0){
            return;
        }
        if(getHighScore() < curScore){
            setHighScore(curScore);
        }
    }
    public void setHighScore(double newScore){
        float truncSore = (float) round(newScore, 2);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(HIGH_SCORE, truncSore);
        editor.apply();
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}


