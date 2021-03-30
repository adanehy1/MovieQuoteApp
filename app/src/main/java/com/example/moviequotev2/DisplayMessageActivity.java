package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Set;
import java.lang.Math;
import android.os.Handler;



public class DisplayMessageActivity extends AppCompatActivity {
    private TextView countdownText;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 10000;
    private boolean timerRunning;
    HashMap<String, String> quoteDict = new HashMap<String, String>();
    String[] quotes = {"If you can dodge a wrench, you can dodge a ball.", "Uhh Earth to Matilda, I was at a day spa. Day, D-A-I-Y-E. Okay?", "It's called male bonding okay. Haven't you even seen 'Wild Hogs'?", "Prepare to be fucked by the long dick of the law!",  "You get bit in the ass. Well let me tell you: my ass looks like hamburger meat, but I can still sit down", "Glen, I love your wads!", "Great White Buffalo.",
            "Are you reading the dictionary?","“L” for love! Good times","Hang on a second. You wanna become a cheerleader to prove you are not a loser?","God damn you, Bernice!","That's it boy. Get in there all nice and deep-like.","I have shareholders. You haven’t even got cup holders.","I’m going to take a pillowcase and fill it full of bars of soap and beat the shit out of you!","I swear, I’m so pissed at my mom. As soon as she’s of age, I’m putting her in a home.",
            "Robert better not get in my face… ‘cause I’ll drop that motherfucker!", "You and your mom are hillbillies. This is a house of learned doctors.", "Did we just become best friends?", "Mom, I honestly thought I was gonna be raped for a second. He had the craziest look in his eyes. And at one point he said, “Lets get it on.”","Your drum set is a whore! I teabagged your drum set!", "They hate us cause they ain’t us!","You know what's more destructive than a nuclear bomb?... Words.",
            "Team Skylark never backs down from a jerkoff.", "This is 2014, women are smart now!", "He does not have a butt hole. He has no need for one","When you score a Bin Laden, or a Hitler, or an Un, you take it by the balls! It's the first rule of journalism. You give the people what they waaant!","Do not fight that tiger, you WILL die!","Eminem's gay on our show!","Aaron, are you inside the tiger?"
                };
    int randomIndex = (int)(Math.random() * (quotes.length));
    String selectedQuote = "NOT SET";
    HashMap<String, String> quoteAndMovieUSed = new HashMap<String, String>();
    int randNum = (int) (Math.random() * 4) + 1;
    int roundNums = 3;
    double playerScore = 0.0;
    long timerLength = 10;
    private boolean pressed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pressed = false;

        //Dodgeball Quotes
        quoteDict.put("If you can dodge a wrench, you can dodge a ball.", "Dodgeball");
        quoteDict.put("Are you reading the dictionary?", "Dodgeball");
        quoteDict.put("“L” for love! Good times", "Dodgeball");
        quoteDict.put("Hang on a second. You wanna become a cheerleader to prove you are not a loser?", "Dodgeball");
        quoteDict.put("God damn you, Bernice!", "Dodgeball");
        quoteDict.put("That's it boy. Get in there all nice and deep-like.", "Dodgeball");
        quoteDict.put("I have shareholders. You haven’t even got cup holders.", "Dodgeball");

        //Accepted Quotes
        quoteDict.put("You get bit in the ass. Well let me tell you: my ass looks like hamburger meat, but I can still sit down", "Accepted");
        quoteDict.put("A sandwich? You’re the SHIT Sandwiches?", "Accepted");
        quoteDict.put("Glen, I love your wads!", "Accepted");
        quoteDict.put("I want to learn how to blow shit up with my mind.", "Accepted");

        //Zoolander Quotes
        quoteDict.put("Rufus, Brint, and Meekus were like brothers to me. And when I say brother, I don't mean, like, an actual brother, but I mean it like the way black people use it.", "Zoolander");
        quoteDict.put("Uhh Earth to Matilda, I was at a day spa. Day, D-A-I-Y-E. Okay?", "Zoolander");

        //Super Bad Quotes
        quoteDict.put("McLovin? What kind of a stupid name is that, Fogell? What, are you trying to be an Irish R&B singer?", "Superbad");
        quoteDict.put("Prepare to be fucked by the long dick of the law!", "Superbad");

        //Hot Tub Time Machine Quotes
        quoteDict.put("Great White Buffalo.", "Hot Tub Time Machine");
        quoteDict.put("It's called male bonding okay. Haven't you even seen 'Wild Hogs'?", "Hot Tub Time Machine");
        quoteDict.put("Wait, I know that squirrel. That’s a magic fuckin’ squirrel.", "Hot Tub Time Machine");

        //Step Brothers Quotes
        quoteDict.put("I’m going to take a pillowcase and fill it full of bars of soap and beat the shit out of you!", "Step Brothers");
        quoteDict.put("I swear, I’m so pissed at my mom. As soon as she’s of age, I’m putting her in a home.", "Step Brothers");
        quoteDict.put("Robert better not get in my face… ‘cause I’ll drop that motherfucker!", "Step Brothers");
        quoteDict.put("You and your mom are hillbillies. This is a house of learned doctors.", "Step Brothers");
        quoteDict.put("Did we just become best friends?", "Step Brothers");
        quoteDict.put("Mom, I honestly thought I was gonna be raped for a second. He had the craziest look in his eyes. And at one point he said, “Lets get it on.”", "Step Brothers");
        quoteDict.put("Your drum set is a whore! I teabagged your drum set!", "Step Brothers");

        //The Interview Quotes
        quoteDict.put("They hate us cause they ain’t us!", "The Interview");
        quoteDict.put("You know what's more destructive than a nuclear bomb?... Words.", "The Interview");
        quoteDict.put("Team Skylark never backs down from a jerkoff.", "The Interview");
        quoteDict.put("This is 2014, women are smart now!", "The Interview");
        quoteDict.put("He does not have a butt hole. He has no need for one", "The Interview");
        quoteDict.put("When you score a Bin Laden, or a Hitler, or an Un, you take it by the balls! It's the first rule of journalism. You give the people what they waaant!", "The Interview");
        quoteDict.put("Do not fight that tiger, you WILL die!", "The Interview");
        quoteDict.put("Eminem's gay on our show!", "The Interview");
        quoteDict.put("Aaron, are you inside the tiger?", "The Interview");
        //quoteDict.put("", "");





        if(!(randomIndex > quotes.length)){
            selectedQuote = quotes[randomIndex];
        } else {
            System.out.println("ERROR on selecting random quote");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        TextView TextView1 = (TextView) findViewById(R.id.textView);
        TextView1.setText("Welcome to Dynamic TextView");
        Set<String> keys = quoteDict.keySet();
        String buttonOneTxt = "NOT SET YET";
        String buttonOneQuote = "";
        String buttonTwoTxt = "NOT SET YET";
        String buttonTwoQuote = "";
        String buttonThreeTxt = "NOT SET YET";
        String buttonThreeQuote = "";
        String buttonFourTxt = "NOT SET YET";
        String buttonFourQuote = "";

        boolean fourInit = false;


        while(buttonOneTxt == buttonTwoTxt || buttonOneTxt == buttonThreeTxt || buttonOneTxt == buttonFourTxt ) {
           int randIButtonOne = (int) (Math.random() * (quotes.length));
           buttonOneQuote = quotes[randIButtonOne];
           buttonOneTxt = quoteDict.get(quotes[randIButtonOne]);
        }
       while(buttonTwoTxt == buttonOneTxt || buttonTwoTxt == buttonThreeTxt || buttonTwoTxt == buttonFourTxt ){
            int randIButtonTwo = (int)(Math.random() * (quotes.length));
            buttonTwoQuote = quotes[randIButtonTwo];
            buttonTwoTxt =  quoteDict.get(quotes[randIButtonTwo]);
        }
        while(buttonThreeTxt == buttonOneTxt || buttonThreeTxt == buttonTwoTxt || buttonThreeTxt == buttonFourTxt ){
            int randIButtonThree = (int)(Math.random() * (quotes.length));
            buttonThreeQuote = quotes[randIButtonThree];
            buttonThreeTxt =  quoteDict.get(quotes[randIButtonThree]);
        }
        while(buttonFourTxt == buttonOneTxt || buttonFourTxt == buttonTwoTxt || buttonFourTxt == buttonThreeTxt || !fourInit ) {
            int randIButtonFour = (int) (Math.random() * (quotes.length));
            buttonFourQuote = quotes[randIButtonFour];
            fourInit = true;
            buttonFourTxt = quoteDict.get(quotes[randIButtonFour]);
        }


        if(randNum == 1){
            selectedQuote = buttonOneQuote;
        } else if (randNum == 2){
            selectedQuote = buttonTwoQuote;
        } else if (randNum == 3){
            selectedQuote = buttonThreeQuote;
        } else {
            selectedQuote = buttonFourQuote;
        }


        System.out.println(quoteDict.get(buttonOneTxt));
        //TextView TextView2 = (TextView) findViewById(R.id.textView);
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
        Intent intent = new Intent(this, DisplayIncorrectActivity.class);

        if(randNum == 1) {
            intent = new Intent(this, DisplayCorrectActivity.class);
        }
        stopTimer();
        if(randNum == 1){
            playerScore += calculateScore(timeLeftInMilliseconds);
        }
        System.out.println("Player score: " + playerScore);
        startActivity(intent);
        pressed = true;

        //intent = new Intent(this, DisplayMessageActivity.class);
        //startActivity(intent);

        // pause for 2 seconds
        // display nextRound activity \
        // add counter for rounds
        // make sure it doesn't include quote another time


    }
    public void movie2Button(View view) {
        Intent intent = new Intent(this, DisplayIncorrectActivity.class);

        if(randNum == 2) {
            intent = new Intent(this, DisplayCorrectActivity.class);
        }
        stopTimer();
        startActivity(intent);
        // Add Pause
        //intent = new Intent(this, DisplayMessageActivity.class);
        //startActivity(intent);
        pressed = true;
    }
    public void movie3Button(View view) {
        Intent intent = new Intent(this, DisplayIncorrectActivity.class);

        if(randNum == 3) {
            intent = new Intent(this, DisplayCorrectActivity.class);
        }
        stopTimer();
        startActivity(intent);
        // Add Pause
        //intent = new Intent(this, DisplayMessageActivity.class);
        //startActivity(intent);
        pressed = true;
    }
    public void movie4Button(View view) {
        Intent intent = new Intent(this, DisplayIncorrectActivity.class);

        if(randNum == 4) {
            intent = new Intent(this, DisplayCorrectActivity.class);
        }
        stopTimer();
        startActivity(intent);
        // Add Pause
        //intent = new Intent(this, DisplayMessageActivity.class);
        //startActivity(intent);
        pressed = true;
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


    }
    public double calculateScore(long timeRemaining){
        double timeInSec = (timerLength * 1000) - (timeRemaining / 1000.0);
        return (-0.1* (Math.pow(timeInSec, 3)))+10;
    }
}
