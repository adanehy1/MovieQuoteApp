package com.example.moviequotev2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;

public class RoundActivity extends AppCompatActivity {
    private TextView countdownText;
    private TextView round;
    private CountDownTimer countDownTimer;
    private CountDownTimer countDownTimerPause;
    HashMap<String, String> quoteDict = new HashMap<String, String>();
    List<String> quotesList = new ArrayList<String>();
    String selectedQuote = "NOT SET";
    long timerLength = 10;
    private long timeLeftInMilliseconds = 10000;
    private long timeLeftInMillisecondsPause = 2000;
    private boolean timerRunning;
    private boolean timerRunningPause;
    Stats stats;
    int randNum = (int) (Math.random() * 4) + 1;
    static final String SHARED_PREFS = "highScore";
    static final String HIGH_SCORE = "highScore";
    final boolean debug  = false;
    boolean firstRound = true;
    private ImageView timerAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Importing selected movies
        String[] selectedMovies = ((globalClass) this.getApplication()).getSelectedMovies();
        stats = new Stats(getApplicationContext()); 
        stats.incrRoundsPlayed();

        try{
            JsonQuotes jQuotes = new JsonQuotes(getApplicationContext(), selectedMovies);
            quotesList = jQuotes.getQList();
            quoteDict = jQuotes.getQDict();
            jQuotes.getMovieNames();
        } catch(JSONException e){
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        //Starting animation
        timerAnimation = (ImageView)findViewById(R.id.timerAnimation);
        timerAnimation.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) timerAnimation.getBackground()).start();
            }
        });


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
        textView.setTextColor(Color.WHITE);
        textView.setText("Score: " + String.valueOf(((globalClass) this.getApplication()).getScore()));
        //string with the title of "globalClass" creates the score

        TextView1.setText(selectedQuote);
        TextView1.setTextColor(Color.WHITE);

        final TextView textViewScore = (TextView) findViewById(R.id.addedScore);
        textViewScore.setVisibility(View.INVISIBLE);

        //Sets the text for top left button
        final Button button1 = (Button)findViewById(R.id.button2);
        button1.setText(buttonOneTxt);

        final Button button2 = (Button)findViewById(R.id.button3);
        button2.setText(buttonTwoTxt);

        final Button button3 = (Button)findViewById(R.id.button4);
        button3.setText(buttonThreeTxt);

        final Button button4 = (Button)findViewById(R.id.button5);
        button4.setText(buttonFourTxt);

        debug(button1, button2, button3, button4);
        startTimer();
        countdownText = findViewById(R.id.countdown_text);

        // Delay to transition to "incorrect" after 10 seconds

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setBtns(1, button1, button2, button3, button4, textViewScore);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setBtns(2, button2, button1, button3, button4, textViewScore);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setBtns(3, button3, button1, button2, button4, textViewScore);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setBtns(4, button4, button1, button2, button3, textViewScore);
            }
        });
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

    public void setBtns(int round, Button primButton, Button secButton, Button thirdButton, Button fourtButton, TextView textViewScore){
        startPauseTimer();
        //Intent intent = new Intent(this, DisplayIncorrectActivity.class);
        if(randNum == round) {
        //    intent = new Intent(this, DisplayCorrectActivity.class);
        }
        stopTimer();
        if(randNum == round){
            ((globalClass) this.getApplication()).setScore(calculateScore(timeLeftInMilliseconds));
            stats.incrTotalAccumPoints(((globalClass) this.getApplication()).getScore());
            stats.incrCorrectGuesses();
        }
        //Makes the other 3 buttons unclickable
        primButton.setEnabled(false);
        secButton.setEnabled(false);
        thirdButton.setEnabled(false);
        fourtButton.setEnabled(false);
        //initially sets color to red meaning incorrect
        primButton.setBackgroundColor(Color.RED);

        //Stopping the animation on the button press
        timerAnimation = (ImageView)findViewById(R.id.timerAnimation);
        timerAnimation.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) timerAnimation.getBackground()).stop();
            }
        });

        //if statement makes it so color is different if correct
        if(randNum == round) {
            primButton.setBackgroundColor(Color.GREEN);
            textViewScore.setVisibility(View.VISIBLE);
            textViewScore.setTextColor(Color.WHITE);
            Animation plusScoreAnim = AnimationUtils.loadAnimation(RoundActivity.this,R.anim.bounce);
            textViewScore.startAnimation(plusScoreAnim);
            textViewScore.setText("+" + calculateScore(timeLeftInMilliseconds));
        }
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
        if (seconds <= 0) {
            final Button button1 = (Button)findViewById(R.id.button2);
            final Button button2 = (Button)findViewById(R.id.button3);
            final Button button3 = (Button)findViewById(R.id.button4);
            final Button button4 = (Button)findViewById(R.id.button5);



            final TextView textViewScore = (TextView) findViewById(R.id.addedScore);
            textViewScore.setVisibility(View.VISIBLE);
            textViewScore.setText("Added Score");


            final TextView timerText = (TextView) findViewById(R.id.countdown_text);
            timerText.setVisibility((View.INVISIBLE));

            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);

            stopTimer();
            startPauseTimer();
               }
    }

    public void startPauseTimer() {
        countDownTimerPause = new CountDownTimer(timeLeftInMillisecondsPause, timerLength) {
            @Override
            public void onTick(long l) {
                timeLeftInMillisecondsPause = l;
                updatePauseTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        timerRunningPause = true;
    }

    public void stopPauseTimer() {
        countDownTimerPause.cancel();
        timerRunningPause = false;
    }

    public void updatePauseTimer() {
        int seconds = (int) timeLeftInMillisecondsPause / 1000;

        Intent next = new Intent(this, NextRoundCounter.class);
        if (seconds <= 0) {
            startActivity(next);
            stopPauseTimer();
        }
    }


    public double calculateScore(long timeRemaining){
        double timeInSec = (timeRemaining)/1000.0;
        return Util.round(timeInSec*100, 2);
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
    public void debug(Button b1, Button b2, Button b3, Button b4){
        if(debug) {
            if (randNum == 1) {
                b1.setBackgroundColor(Color.GREEN);
            } else if (randNum == 2) {
                b2.setBackgroundColor(Color.GREEN);
            } else if (randNum == 3) {
                b3.setBackgroundColor(Color.GREEN);
            } else if (randNum == 4) {
                b4.setBackgroundColor(Color.GREEN);
            } else {
                System.out.println("Error with debug randNum");
            }
        } else {return;}
    }
}


