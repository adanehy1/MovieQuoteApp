package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        TextView textView1 = (TextView) findViewById(R.id.HS1);
        textView1.setText("First High Score");

        TextView textView2 = (TextView) findViewById(R.id.HS2);
        textView2.setText("Second High Score");

        TextView textView3 = (TextView) findViewById(R.id.HS3);
        textView3.setText("Third High Score");

        TextView textView4 = (TextView) findViewById(R.id.HS4);
        textView4.setText("Fourth High Score");

        TextView textView5 = (TextView) findViewById(R.id.HS5);
        textView5.setText("Fifth High Score");

        TextView stat1 = (TextView) findViewById(R.id.stat1);
        stat1.setText("First Statistic Title");

        TextView stat2 = (TextView) findViewById(R.id.stat2);
        stat2.setText("Second Statistic Title");

        TextView stat3 = (TextView) findViewById(R.id.stat3);
        stat3.setText("Third Statistic Title");

        TextView stat4 = (TextView) findViewById(R.id.stat4);
        stat4.setText("Fourth Statistic Title");

        TextView insStat1 = (TextView) findViewById(R.id.actualStat1);
        insStat1.setText("Insert Stat 1");

        TextView insStat2 = (TextView) findViewById(R.id.actualStat2);
        insStat2.setText("Insert Stat 2");

        TextView insStat3 = (TextView) findViewById(R.id.actualStat3);
        insStat3.setText("Insert Stat 3");

        TextView insStat4 = (TextView) findViewById(R.id.actualStat4);
        insStat4.setText("Insert Stat 4");
    }
    @Override

    public void onBackPressed(){
        Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);

    }

}