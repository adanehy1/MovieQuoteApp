package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

        TextView textView = new TextView(this);
        textView.setText("Select 4 or More Movies");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(34);

        Button button2 = new Button(this);
        button2.setText("Start Game");
        button2.setHeight(400);

        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, 1500));
        //This restricts the height of the scroll view. In this situtation the height is 1500

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        total.addView(textView);
        total.addView(scroll);
        total.addView(button2);
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

            final Button button = new Button(this);

            int id = counter;
            button.setText(movieNames.get(counter));
            button.setId(id);
            button.setWidth(1200);
            final int[] num = {3};
            final int finalCounter = counter;
            final List<String> finalMovieNames1 = movieNames;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num[0] %2==0){
                        //First Click (ADD)
                        num[0]++;
                        //System.out.println(num[0]);
                        button.setBackgroundColor(Color.WHITE);
                        button.setTextColor(Color.BLACK);
                        finalMovieNames.remove(finalMovieNames1.get(finalCounter));
                        System.out.println(finalMovieNames.toString());
                    }
                    else{
                        //Second Click (REMOVE)
                        num[0]++;
                        //System.out.println(num[0]);
                        button.setBackgroundColor(Color.BLACK);
                        button.setTextColor(Color.WHITE);
                        finalMovieNames.add(finalMovieNames1.get(finalCounter));
                        System.out.println(finalMovieNames.toString());
                    }
                    }
            });

            row.addView(button);

            layout.addView(row);

        }
        final String[] selectedMovies = finalMovieNames.toArray(new String[0]);

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /*
                Intent myIntent = new Intent(CurrentActivity.this, NextActivity.class);
                myIntent.putExtra("key", value); //Optional parameters
                CurrentActivity.this.startActivity(myIntent);
                */

                //
                // String[] T = {"Step Brothers", "The Interview", "Hot Tub Time Machine", "Zoolander", "Super Bad", "Dodgeball"};

                Intent myIntent = new Intent(MovieSelectActivity.this, DisplayMessageActivity.class);
                //myIntent.putExtra("selectedMovies", T);
                MovieSelectActivity.this.startActivity(myIntent);
                System.out.println(selectedMovies);
            }
            });

        setContentView(total);

    }
}