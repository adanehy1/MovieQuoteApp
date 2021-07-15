package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Movie;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MovieSelectActivity extends AppCompatActivity {

    ArrayList<String> movies = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //The linear layout Total includes the start game button, the select movies text view and the scroll view
        // The order in which they are added to the total layout matters. The scroll view will overlap anything

        LinearLayout total = new LinearLayout(this);
        total.setOrientation(LinearLayout.VERTICAL);
        total.setBackgroundColor(Color.DKGRAY);

        TextView textView = new TextView(this);
        textView.setText("Select 4 or More Movies");
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(34);

        final Button startGameButton = new Button(this);
        startGameButton.setText("Start Game");
        startGameButton.setEnabled(false);
        startGameButton.setHeight(400);
        startGameButton.setBackground(getDrawable(R.drawable.movie_not_selected_button));

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.GRAY);
        scroll.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, 1600));
        //This restricts the height of the scroll view. In this situtation the height is 1500

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        total.addView(textView);
        total.addView(scroll);
        total.addView(startGameButton);
        scroll.addView(layout);
        List<String> movieNames = new ArrayList<>();
        final List<String> finalMovieNames = new ArrayList<>();
        try {
            JsonQuotes jQuotes = new JsonQuotes(getApplicationContext());
            movieNames = jQuotes.getMovieNames();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int counter = 0; counter < movieNames.size(); counter++) {
            System.out.println(movieNames.get(counter));
            LinearLayout row = new LinearLayout(this);
            row.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
            final Button movieSelectButton = new Button(this);
            int id = counter;
            movieSelectButton.setText(movieNames.get(counter));
            movieSelectButton.setId(id);
            movieSelectButton.setWidth(1000);
            movieSelectButton.setHeight(150);
            movieSelectButton.setBackground(getDrawable(R.drawable.movie_not_selected_button));
            final int[] num = {3};
            final int finalCounter = counter;
            final List<String> finalMovieNames1 = movieNames;
            movieSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num[0] % 2 == 0) {
                        //First Click (ADD)
                        num[0]++;
                        //System.out.println(num[0]);
                        movieSelectButton.setBackground(getDrawable(R.drawable.movie_not_selected_button));
                        movieSelectButton.setTextColor(Color.BLACK);
                        finalMovieNames.remove(finalMovieNames1.get(finalCounter));
                        System.out.println(finalMovieNames.toString());
                    } else {
                        //Second Click (REMOVE)
                        num[0]++;
                        //System.out.println(num[0]);
                        movieSelectButton.setBackground(getDrawable(R.drawable.movie_selected_button));
                        movieSelectButton.setTextColor(Color.WHITE);
                        finalMovieNames.add(finalMovieNames1.get(finalCounter));
                        System.out.println(finalMovieNames.toString());
                    }
                    final int size = finalMovieNames.size();
                    if (size >= 4) {
                        startGameButton.setEnabled(true);
                    }
                    if (size == 3) {
                        startGameButton.setEnabled(false);
                    }
                }
            });

            row.addView(movieSelectButton);

            layout.addView(row);

        }

        startGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String[] selectedMovies = finalMovieNames.toArray(new String[0]);
                ((globalClass) MovieSelectActivity.this.getApplication()).setSelectedMovies(selectedMovies);
                Intent myIntent = new Intent(MovieSelectActivity.this, RoundActivity.class);
                startActivity(myIntent);

            }
        });

        setContentView(total);

    }
    @Override
    public void onBackPressed(){
        Intent back = new Intent(MovieSelectActivity.this, MainActivity.class);
        startActivity(back);
    }

    }