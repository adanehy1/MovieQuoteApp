package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class FinishScreen extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 3000; //Inital start time of the timer
    private TextView mTextViewCountDown; //Creates the variable that sets the text in the text view
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning; //true/false of whether the timer is running
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS; //Variable of the time left
    static int roundNums = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((globalClass) this.getApplication()).clear();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_screen);
        Stats stats = new Stats(getApplicationContext());

        TextView textView = (TextView) findViewById(R.id.finalScore);
        textView.setText(String.valueOf(((globalClass) this.getApplication()).getScore()));
        //string with the title of "globalClass" creates the score
        stats.checkNewHighScore(((globalClass) this.getApplication()).getScore());

        Typeface boldTypeFace = Typeface.defaultFromStyle(Typeface.BOLD);

        //Setting Numbers and Bold
        TextView textView1 = (TextView) findViewById(R.id.HS1num);
        textView1.setText("1). ");
        textView1.setTypeface(boldTypeFace);

        TextView textView2 = (TextView) findViewById(R.id.HS2num);
        textView2.setText("2). ");
        textView2.setTypeface(boldTypeFace);

        TextView textView3 = (TextView) findViewById(R.id.HS3num);
        textView3.setText("3). ");
        textView3.setTypeface(boldTypeFace);

        TextView textView4 = (TextView) findViewById(R.id.HS5num);
        textView4.setText("4). ");
        textView4.setTypeface(boldTypeFace);

        TextView textView5 = (TextView) findViewById(R.id.HS4num);
        textView5.setText("5). ");
        textView5.setTypeface(boldTypeFace);

        //Setting High Score values
        TextView highScore1 = (TextView) findViewById(R.id.HS1);
        highScore1.setText("" + Util.round(stats.getHighScore()[0], 1));

        TextView highScore2 = (TextView) findViewById(R.id.HS2);
        highScore2.setText("" + Util.round(stats.getHighScore()[1], 1));

        TextView highScore3 = (TextView) findViewById(R.id.HS3);
        highScore3.setText("" + Util.round(stats.getHighScore()[2], 1));

        TextView highScore4 = (TextView) findViewById(R.id.HS5);
        highScore4.setText("" + Util.round(stats.getHighScore()[3], 1));

        TextView highScore5 = (TextView) findViewById(R.id.HS4);
        highScore5.setText("" + Util.round(stats.getHighScore()[4], 1));

    }

    public void openButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void restartButton(View view) {
        Intent intent2 = new Intent(this, RoundActivity.class);
        startActivity(intent2);
    }
    public void movieSelectButton(View view) {
        Intent intent3 = new Intent(this, MovieSelectActivity.class);
        startActivity(intent3);
    }



    @Override
    public void onBackPressed(){

    }
}
