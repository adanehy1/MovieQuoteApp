package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        Stats stats = new Stats(getApplicationContext());

        //stats.testMHS();

        Typeface boldTypeFace = Typeface.defaultFromStyle(Typeface.BOLD);

        TextView saysHighScores = (TextView) findViewById(R.id.textView5);
        saysHighScores.setTextColor(Color.WHITE);

        //Setting Numbers and Bold
        TextView textView1 = (TextView) findViewById(R.id.HS1num);
        textView1.setText("1). ");
        textView1.setTypeface(boldTypeFace);
        textView1.setTextColor(Color.WHITE);

        TextView textView2 = (TextView) findViewById(R.id.HS2num);
        textView2.setText("2). ");
        textView2.setTypeface(boldTypeFace);
        textView2.setTextColor(Color.WHITE);

        TextView textView3 = (TextView) findViewById(R.id.HS3num);
        textView3.setText("3). ");
        textView3.setTypeface(boldTypeFace);
        textView3.setTextColor(Color.WHITE);

        TextView textView4 = (TextView) findViewById(R.id.HS4num);
        textView4.setText("4). ");
        textView4.setTypeface(boldTypeFace);
        textView4.setTextColor(Color.WHITE);

        TextView textView5 = (TextView) findViewById(R.id.HS5num);
        textView5.setText("5). ");
        textView5.setTypeface(boldTypeFace);
        textView5.setTextColor(Color.WHITE);

        //Setting High Score values
        TextView highScore1 = (TextView) findViewById(R.id.HS1);
        highScore1.setText("" + Util.round(stats.getHighScore()[0], 1));
        highScore1.setTextColor(Color.WHITE);

        TextView highScore2 = (TextView) findViewById(R.id.HS2);
        highScore2.setText("" + Util.round(stats.getHighScore()[1], 1));
        highScore2.setTextColor(Color.WHITE);

        TextView highScore3 = (TextView) findViewById(R.id.HS3);
        highScore3.setText("" + Util.round(stats.getHighScore()[2], 1));
        highScore3.setTextColor(Color.WHITE);

        TextView highScore4 = (TextView) findViewById(R.id.HS4);
        highScore4.setText("" + Util.round(stats.getHighScore()[3], 1));
        highScore4.setTextColor(Color.WHITE);

        TextView highScore5 = (TextView) findViewById(R.id.HS5);
        highScore5.setText("" + Util.round(stats.getHighScore()[4], 1));
        highScore5.setTextColor(Color.WHITE);

        TextView stat1 = (TextView) findViewById(R.id.stat1);
        stat1.setText("Rounds Played");
        stat1.setTypeface(boldTypeFace);
        stat1.setTextColor(Color.WHITE);

        TextView stat2 = (TextView) findViewById(R.id.stat2);
        stat2.setText("Average Points Per Round");
        stat2.setTypeface(boldTypeFace);
        stat2.setTextColor(Color.WHITE);

        TextView stat3 = (TextView) findViewById(R.id.stat3);
        stat3.setText("Total Points Accumulated");
        stat3.setTypeface(boldTypeFace);
        stat3.setTextColor(Color.WHITE);

        TextView stat4 = (TextView) findViewById(R.id.stat4);
        stat4.setText("Correct Guess Average");
        stat4.setTypeface(boldTypeFace);
        stat4.setTextColor(Color.WHITE);

        TextView insStat1 = (TextView) findViewById(R.id.actualStat1);
        insStat1.setText(String.valueOf(stats.getRoundsPlayed()));
        insStat1.setTextColor(Color.WHITE);

        TextView insStat2 = (TextView) findViewById(R.id.actualStat2);
        insStat2.setText(String.valueOf(stats.getAvgPointsPerRound()));
        insStat2.setTextColor(Color.WHITE);

        TextView insStat3 = (TextView) findViewById(R.id.actualStat3);
        insStat3.setText(String.valueOf(stats.getTotalPoints()));
        insStat3.setTextColor(Color.WHITE);

        TextView insStat4 = (TextView) findViewById(R.id.actualStat4);
        insStat4.setText(String.valueOf(stats.getCorrectGuessAvg()) + "%");
        insStat4.setTextColor(Color.WHITE);
    }

    public void resetStats(View view) {
        Stats stats = new Stats(getApplicationContext());
        stats.resetAll();
        Intent intent2 = new Intent(this, StatsActivity.class);
        startActivity(intent2);
    }
    @Override

    public void onBackPressed(){
        Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
    }

}