package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MovieSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_select);

        TextView textView = (TextView) findViewById(R.id.movieSelect);
        textView.setText("Select Your Movies");
        //string with the title of "globalClass" creates the score

        //int[] yearToAdd = {2000, 2001, 2002, 2003};
        //LinearLayout parentLayout = (LinearLayout)findViewById(R.id.button_parent);
        //for (int i =0; i< yearToAdd.length; i++){
        //   int yearToAdd = yearToAdd[i];git
        //    Button myButton = new Button(this);
        //    myButton.setText(String.valueOf(yearToAdd));
        //    yearButtons.add(myButton);
        //    yearButtons.setOnClickListener(this);
        //    parentLayout.addView(myButton);
        //}


    }
}