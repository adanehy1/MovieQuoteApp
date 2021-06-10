package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
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
        textView.setText("Select Movies");
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

        List<String> list = new ArrayList<String>();
        list.add("Movie one");
        list.add("Movie two");
        list.add("Movie three");
        list.add("Movie four");
        list.add("Movie one");
        list.add("Movie two");
        list.add("Movie three");
        list.add("Movie four");
        list.add("Movie one");
        list.add("Movie two");
        list.add("Movie three");
        list.add("Movie four");
        list.add("Movie one");
        list.add("Movie two");
        list.add("Movie three");
        list.add("Movie four");

        System.out.println("For Loop");
        for (int counter = 0; counter < list.size(); counter++) {
            System.out.println(list.get(counter));
            LinearLayout row = new LinearLayout(this);

            Button button = new Button(this);

            int id = counter;
            button.setText(list.get(counter));
            button.setId(id);
            button.setWidth(1200);
            row.addView(button);

            layout.addView(row);

        }

        setContentView(total);

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