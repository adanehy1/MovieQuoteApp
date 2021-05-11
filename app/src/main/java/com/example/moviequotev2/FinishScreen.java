package com.example.moviequotev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class FinishScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_screen);

        TextView textView = (TextView) findViewById(R.id.finalScore);
        textView.setText(String.valueOf(((globalClass) this.getApplication()).getScore()));
        //string with the title of "globalClass" creates the score
    }
    public void mainMenu(View view) throws FileNotFoundException {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed(){

    }
}
