package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
        //The order in which they are added to the total layout matters. The scroll view will overlap anything

        LinearLayout total = new LinearLayout(this);
        total.setOrientation(LinearLayout.VERTICAL);
        total.setBackgroundColor(Color.DKGRAY);
        //total.setBackgroundColor(getResources().getColor(R.color.LightSteelBlue));

        TextView textView = new TextView(this);
        textView.setText("Select 4 or More Movies");
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(34);

        final Button selectAllButton = new Button(this);
        selectAllButton.setText("Select All");
        Drawable selectMovies = getResources().getDrawable(R.drawable.selectedmovieoff);
        Bitmap bitmapSelect = ((BitmapDrawable) selectMovies).getBitmap();
        Drawable selectAllMapped = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmapSelect, 500, 50, true));
        selectAllButton.setBackground(selectAllMapped);

        final Button startGameButton = new Button(this);
        startGameButton.setText("Start");
        startGameButton.setEnabled(false);
        startGameButton.setHeight(400);
        //startGameButton.setVisibility(View.INVISIBLE);
        startGameButton.setBackground(getDrawable(R.drawable.final_start_round_button_custom));
        startGameButton.setTextColor(Color.WHITE);
        startGameButton.setTextSize(75);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(getResources().getColor(R.color.DarkCyan));
        scroll.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, 1600));
        //This restricts the height of the scroll view. In this situtation the height is 1600

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        total.addView(textView);
        total.addView(selectAllButton);
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

            Drawable drawableOff = getResources().getDrawable(R.drawable.selectedmovieoff);
            Bitmap bitmap = ((BitmapDrawable) drawableOff).getBitmap();
            Drawable offMapped = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 500, 50, true));
            movieSelectButton.setBackground(offMapped);

            final int[] num = {3};
            final int finalCounter = counter;
            final List<String> finalMovieNames1 = movieNames;

            selectAllButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    if (num[0] % 2 == 0) {
                        //First Click (ADD)
                        num[0]++;
                        //System.out.println(num[0]);

                        Drawable drawableOff = getResources().getDrawable(R.drawable.selectedmovieoff);
                        Bitmap bitmap = ((BitmapDrawable) drawableOff).getBitmap();
                        Drawable offMapped = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 500, 50, true));
                        selectAllButton.setBackground(offMapped);

                        selectAllButton.setTextColor(Color.BLACK);

                    } else {
                        //Second Click (REMOVE)
                        num[0]++;
                        //System.out.println(num[0]);

                        Drawable drawableOn = getResources().getDrawable(R.drawable.selectedmovieon);
                        Bitmap bitmap = ((BitmapDrawable) drawableOn).getBitmap();
                        Drawable onMapped = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 500, 50, true));
                        selectAllButton.setBackground(onMapped);

                        selectAllButton.setTextColor(Color.WHITE);
                }
            }});

            movieSelectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num[0] % 2 == 0) {
                        //First Click (ADD)
                        num[0]++;
                        //System.out.println(num[0]);

                        Drawable drawableOff = getResources().getDrawable(R.drawable.selectedmovieoff);
                        Bitmap bitmap = ((BitmapDrawable) drawableOff).getBitmap();
                        Drawable offMapped = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 500, 50, true));
                        movieSelectButton.setBackground(offMapped);

                        movieSelectButton.setTextColor(Color.BLACK);
                        finalMovieNames.remove(finalMovieNames1.get(finalCounter));
                        System.out.println(finalMovieNames.toString());
                    } else {
                        //Second Click (REMOVE)
                        num[0]++;
                        //System.out.println(num[0]);

                        Drawable drawableOn = getResources().getDrawable(R.drawable.selectedmovieon);
                        Bitmap bitmap = ((BitmapDrawable) drawableOn).getBitmap();
                        Drawable onMapped = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 500, 50, true));
                        movieSelectButton.setBackground(onMapped);

                        movieSelectButton.setTextColor(Color.WHITE);
                        finalMovieNames.add(finalMovieNames1.get(finalCounter));
                        System.out.println(finalMovieNames.toString());
                    }
                    final int size = finalMovieNames.size();
                    if (size >= 4) {
                        startGameButton.setEnabled(true);
                        //startGameButton.setTextColor(Color.WHITE);
                        //Animation startGame = AnimationUtils.loadAnimation(MovieSelectActivity.this,R.anim.fade_in);
                        //startGameButton.startAnimation(startGame);
                        //startGameButton.setVisibility(View.VISIBLE);
                    }
                    if (size == 3) {
                        startGameButton.setEnabled(false);
                        //startGameButton.setTextColor(Color.WHITE);
                        //Animation startGame = AnimationUtils.loadAnimation(MovieSelectActivity.this,R.anim.fade_out);
                        //startGameButton.startAnimation(startGame);
                        //startGameButton.setVisibility(View.INVISIBLE);
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