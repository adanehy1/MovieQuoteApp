package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Set;
import java.lang.Math;



public class DisplayMessageActivity extends AppCompatActivity {
    private TextView countdownText;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 10000;
    private boolean timerRunning;
    HashMap<String, String> quoteDict = new HashMap<String, String>();
    String[] quotes = {"You are the one that stares at me. Why is this?", "Ask me about my wiener!", "I wasn't like every other kid, you know, who dreams about being an astronaut, I was always more interested in what bark was made out of on a tree"};
    int randomIndex = (int)(Math.random() * (quotes.length + 1));
    String selectedQuote;
    HashMap<String, String> quoteAndMovieUSed = new HashMap<String, String>();
    quoteDict.put("Key", "value");
    quoteDict.put("If you can dodge a wrench, you can dodge a ball.", "Dodgeball”);
    quoteDict.put("You get bit in the ass. Well let me tell you: my ass looks like hamburger meat, but I can still sit down", "Accepted”);
    quoteDict.put("A sandwich? You’re the SHIT Sandwiches?", "Accepted”);
    quoteDict.put("Glen, I love your wads!", "Accepted”);
    quoteDict.put("I want to learn how to blow shit up with my mind.", "Accepted”);
    quoteDict.put("Rufus, Brint, and Meekus were like brothers to me. And when I say brother, I don't mean, like, an actual brother, but I mean it like the way black people use it.", "Zoolander”);
    quoteDict.put("Uhh Earth to Matilda, I was at a day spa. Day, D-A-I-Y-E. Okay?", "Zoolander”);
    quoteDict.put("McLovin? What kind of a stupid name is that, Fogell? What, are you trying to be an Irish R&B singer?", "Superbad”);
    quoteDict.put("Prepare to be fucked by the long dick of the law!", "Superbad”);
    quoteDict.put("You know what I do? I flip my boner up into my waistband. It hides it AND it feels awesome. I almost blew a load into my belly button.", "Superbad”);
    quoteDict.put("Great White Buffalo.", "Hot Tub Time Machine”);
    quoteDict.put("It's called male bonding okay. Haven't you even seen 'Wild Hogs'?", "Hot Tub Time Machine”);
    quoteDict.put("Wait, I know that squirrel. That’s a magic fuckin’ squirrel.", "Hot Tub Time Machine”);



    // put value and pairs here


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(!(randomIndex > quotes.length)){
            selectedQuote = quotes[randomIndex];
        } else {
            System.out.println("ERROR on selecting random quote");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Set<String> keys = quoteDict.keySet();
        String buttonOneTxt = "NOT SET YET";
        String buttonTwoTxt = "NOT SET YET";
        String buttonThreeTxt = "NOT SET YET";
        String buttonFourTxt = "NOT SET YET";

       while(buttonOneTxt == buttonTwoTxt || buttonOneTxt == buttonThreeTxt || buttonOneTxt == buttonFourTxt ){
           int randIButtonOne = (int)(Math.random() * (quotes.length + 1));
           buttonOneTxt =  quoteDict.get(quotes[randIButtonOne]);
       }
       while(buttonTwoTxt == buttonOneTxt || buttonTwoTxt == buttonThreeTxt || buttonTwoTxt == buttonFourTxt ){
            int randIButtonTwo = (int)(Math.random() * (quotes.length + 1));
            buttonTwoTxt =  quoteDict.get(quotes[randIButtonTwo]);
        }
        while(buttonThreeTxt == buttonOneTxt || buttonThreeTxt == buttonTwoTxt || buttonThreeTxt == buttonFourTxt ){
            int randIButtonThree = (int)(Math.random() * (quotes.length + 1));
            buttonThreeTxt =  quoteDict.get(quotes[randIButtonThree]);
        }
        while(buttonFourTxt == buttonOneTxt || buttonFourTxt == buttonTwoTxt || buttonFourTxt == buttonThreeTxt ){
            int randIButtonThree = (int)(Math.random() * (quotes.length + 1));
            buttonThreeTxt =  quoteDict.get(quotes[randIButtonThree]);
        }



        TextView TextView1 = (TextView) findViewById(R.id.textView);
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
    }

    public void movie1Button(View view) {
        Intent intent = new Intent(this, DisplayCorrectActivity.class);
        startActivity(intent);

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
