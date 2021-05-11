package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class MovieSelectActivity extends AppCompatActivity {

    ArrayList<String> movies = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_select);
        TextView textView = (TextView) findViewById(R.id.movieSelect);
        textView.setText("Select Your Movies");
        //string with the title of "globalClass" creates the score


        /*for(String movie : movies){
           System.out.println(movie); // the String movie is assigned to each "movie" in the ArrayList
        }*/
        //int[] yearToAdd = {2000, 2001, 2002, 2003};
        //LinearLayout parentLayout = (LinearLayout)findViewById(R.id.button_parent);
        //for (int i =0; i< yearToAdd.length; i++){
        //   int yearToAdd = yearToAdd[i];
        //    Button myButton = new Button(this);
        //    myButton.setText(String.valueOf(yearToAdd));
        //    yearButtons.add(myButton);
        //    yearButtons.setOnClickListener(this);
        //    parentLayout.addView(myButton);
        // }



    }
    public void getMovies() throws JSONException{
        String jsonStr = getJsonFromAssets(getApplicationContext(), "quotes.json");
        JSONObject json = new JSONObject(jsonStr);
        Iterator<String> keys = json.keys();
        while(keys.hasNext()){
            String key = keys.next();
            movies.add(key);
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