package com.example.moviequotev2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DisplayMessageActivity extends AppCompatActivity {
    private TextView countdownText;
    private TextView round;
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
    int roundNums = 5;
    double playerScore = 0.0;
    long timerLength = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            double playerScore = getPlayerScore();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Reading an Excel File

        try {
            getQuotes(); // initializes quotes array and QuoteDict
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        System.out.println("Score " + loadScore());



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
            addScore(calculateScore(timeLeftInMilliseconds));
        }
        System.out.println("Player score: " + loadScore());
        startActivity(intent);

        //intent = new Intent(this, DisplayMessageActivity.class);
        //startActivity(intent);

        // pause for 2 seconds
        // display nextRound activity \
        // add counter for rounds
        // make sure it doesn't include quote another time


    }
    public void movie2Button(View view) throws FileNotFoundException {
        Intent intent = new Intent(this, DisplayIncorrectActivity.class);

        if(randNum == 2) {
            intent = new Intent(this, DisplayCorrectActivity.class);
        }
        stopTimer();
       if(randNum == 2){
            addScore(calculateScore(timeLeftInMilliseconds));
        }
        System.out.println("Player score: " + loadScore());
        startActivity(intent);
        // Add Pause
        //intent = new Intent(this, DisplayMessageActivity.class);
        //startActivity(intent);
    }
    public void movie3Button(View view) throws FileNotFoundException {
        Intent intent = new Intent(this, DisplayIncorrectActivity.class);

        if(randNum == 3) {
            intent = new Intent(this, DisplayCorrectActivity.class);
        }
        stopTimer();
        if(randNum == 3){
            addScore(calculateScore(timeLeftInMilliseconds));
        }
        System.out.println("Player score: " + loadScore());
        startActivity(intent);
        // Add Pause
        //intent = new Intent(this, DisplayMessageActivity.class);
        //startActivity(intent);
    }
    public void movie4Button(View view) throws FileNotFoundException {
        Intent intent = new Intent(this, DisplayIncorrectActivity.class);

        if(randNum == 4) {
            intent = new Intent(this, DisplayCorrectActivity.class);
        }
        stopTimer();
        if(randNum == 4){
            addScore(calculateScore(timeLeftInMilliseconds));
        }
        System.out.println("Player score: " + loadScore());
        startActivity(intent);
        // Add Pause
        //intent = new Intent(this, DisplayMessageActivity.class);
        //startActivity(intent);
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
    public void writeToScoresFile(String score){
        String FILENAME = "scores.txt";
        String string = score;

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
    public double calculateScore(long timeRemaining){
        double timeInSec = (timeRemaining)/1000.0;
        //System.out.println("Score with " + timeInSec + " time remaining: " + ((-0.1* (Math.pow(timeInSec, 3)))+10)/10.0);
        //return (-0.1* (Math.pow(timeInSec, 3)))+10;
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
    public void addScore(double score){
        double curScore =  Double.parseDouble(loadScore());
        score += curScore;
        writeToScoresFile(String.valueOf(score));
    }
    public double getPlayerScore() throws FileNotFoundException {
        return 0.0;
    }
    public void getQuotes() throws JSONException {
        String jsonStr = getJsonFromAssets(getApplicationContext(), "quotes.json");
        JSONObject json = new JSONObject(jsonStr);
        Iterator<String> keys = json.keys();

        while(keys.hasNext()) {
            String key = keys.next();
            JSONArray jsonArray = (JSONArray) json.get(key);
            System.out.println(jsonArray);
        }
    }
    static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }
}

