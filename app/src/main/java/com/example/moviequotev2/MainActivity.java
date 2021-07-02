package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.moviequotev2.MESSAGE";

    private ImageView timerAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((globalClass) this.getApplication()).clear();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setText("Start");

        ImageView imageView = (ImageView)findViewById(R.id.imageView4);

        Button statsButton = (Button)findViewById(R.id.button7);
        statsButton.setText("Statistics");

        //Animations
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
        button.startAnimation(animation);
        statsButton.startAnimation(animation);
        imageView.startAnimation(animation);

        ((globalClass) this.getApplication()).scoreClear();
    }
    /** When the Open Button is pressed*/
    public void openButton(View view) {
        Intent intent = new Intent(this, MovieSelectActivity.class);
        startActivity(intent);
    }

    public void highScore(View view) {
        Intent intent2 = new Intent(this, HighScoreActivity.class);
        startActivity(intent2);
    }

    public void movieSelect(View view) {
        Intent intent3 = new Intent(this, MovieSelectActivity.class);
        startActivity(intent3);
    }

}
