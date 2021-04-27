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
import java.util.ArrayList;
import java.util.HashMap;
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
    private long timeLeftInMilliseconds = 10000;
    private boolean timerRunning;
    HashMap<String, String> quoteDict = new HashMap<String, String>();
    List<String> quotesList = new ArrayList<String>();
    List<String> usedQuotes = new ArrayList<String>();
    int randomIndex;
    String selectedQuote = "NOT SET";
    HashMap<String, String> quoteAndMovieUSed = new HashMap<String, String>();
    int randNum = (int) (Math.random() * 4) + 1;
    int roundNums = 5;
    double playerScore = 0.0;
    long timerLength = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       loadScore();
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
        System.out.println("Score " + loadScore());
        randomIndex = (int)(Math.random() * (quotesList.size()));
        if(!(randomIndex > quotesList.size())){
            selectedQuote = quotesList.get(randomIndex);
            while(!usedQuote(selectedQuote)){
                randomIndex = (int)(Math.random() * (quotesList.size()));
                selectedQuote = quotesList.get(randomIndex);
            }
            addUsedQuote(quotesList.get(randomIndex));
            printUsedQuotes();
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
           int randIButtonOne = (int) (Math.random() * (quotesList.size()));
           buttonOneQuote = quotesList.get(randIButtonOne);
           buttonOneTxt = quoteDict.get(quotesList.get(randIButtonOne));
        }
       while(buttonTwoTxt == buttonOneTxt || buttonTwoTxt == buttonThreeTxt || buttonTwoTxt == buttonFourTxt ){
            int randIButtonTwo = (int)(Math.random() * (quotesList.size()));
            buttonTwoQuote = quotesList.get(randIButtonTwo);;
            buttonTwoTxt =  quoteDict.get(quotesList.get(randIButtonTwo));
        }
        while(buttonThreeTxt == buttonOneTxt || buttonThreeTxt == buttonTwoTxt || buttonThreeTxt == buttonFourTxt ){
            int randIButtonThree = (int)(Math.random() * (quotesList.size()));
            buttonThreeQuote = quotesList.get(randIButtonThree);;
            buttonThreeTxt =  quoteDict.get(quotesList.get(randIButtonThree));
        }
        while(buttonFourTxt == buttonOneTxt || buttonFourTxt == buttonTwoTxt || buttonFourTxt == buttonThreeTxt || !fourInit ) {
            int randIButtonFour = (int) (Math.random() * (quotesList.size()));
            buttonFourQuote = quotesList.get(randIButtonFour);;
            fourInit = true;
            buttonFourTxt = quoteDict.get(quotesList.get(randIButtonFour));
        }
        /*System.out.println("GLOBAL " + ((globalClass) this.getApplication()).getTest());
        ((globalClass) this.getApplication()).setTest("hello");
        System.out.println("GLOBAL " +  ((globalClass) this.getApplication()).getTest());*/

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
            fos.write(string.getBytes());
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
            for(int i = 0; i < jsonArray.length(); i++){
                quotesList.add(jsonArray.getString(i));
                quoteDict.put(jsonArray.getString(i), key);
            }
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
    public void addUsedQuote(String quote){
        String FILENAME = "usedQuotes.txt";

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(quote.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean usedQuote(String quote){
       FileInputStream fis = null;
        try {
            fis = openFileInput("usedQuotes.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            System.out.println("USED");
            while((line = br.readLine()) != null){
                if(line == quote){
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public void printUsedQuotes(){
        FileInputStream fis = null;
        try {
            fis = openFileInput("usedQuotes.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            System.out.println("USED");
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createFile(){
        String FILENAME = "usedQuotes.txt";
        String blank = "";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(blank.getBytes())    ;
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

