package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.moviequotev2.MESSAGE";

    private ImageView timerAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((globalClass) this.getApplication()).clear();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        //button.setText("Start");
        button.setBackground(getDrawable(R.drawable.startgamebutton));

        TextView titleText = (TextView)findViewById(R.id.titleText);
        titleText.setTextColor(getResources().getColor(R.color.DarkCyan));
        titleText.setText("Fast Movies");
        titleText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        titleText.setTextSize(50);

        TextView titleText2 = (TextView)findViewById(R.id.titleText2);
        titleText2.setTextColor(Color.BLACK);
        titleText2.setText("Fast Movies");
        titleText2.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        titleText2.setTextSize(50);

        Button statsButton = (Button)findViewById(R.id.button7);
        statsButton.setText("Statistics");
        statsButton.setBackground(getDrawable(R.drawable.movie_not_selected_button));

        //Animations
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
        button.startAnimation(animation);
        statsButton.startAnimation(animation);

        Animation titleAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_down);
        titleText.startAnimation(titleAnimation);
        titleText2.startAnimation(titleAnimation);

        ((globalClass) this.getApplication()).scoreClear();
    }
    /** When the Open Button is pressed*/
    public void openButton(View view) {
        Intent intent = new Intent(this, MovieSelectActivity.class);
        startActivity(intent);
    }

    public void highScore(View view) {
        Intent intent2 = new Intent(this, StatsActivity.class);
        startActivity(intent2);
    }

    public void movieSelect(View view) {
        Intent intent3 = new Intent(this, MovieSelectActivity.class);
        startActivity(intent3);
    }

}
