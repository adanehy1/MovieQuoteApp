package com.example.moviequotev2;

import android.content.Context;
import android.content.SharedPreferences;

public class Stats {
    double score;
    Context context;
    public static final String SHARED_PREFS = "Statistics";
    SharedPreferences sharedPreferences;
    public Stats(Context context){
        context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        score = sharedPreferences.getFloat("highScore", 0);
    }
    public double getHighScore(){
        return score;
    }
    public void checkNewHighScore(double curScore){
        if(curScore <= 0.0){
            return;
        }
        if(getHighScore() < curScore){
            setHighScore(curScore);
        }
    }
    public void setHighScore(double newScore){
        float truncSore = (float) round(newScore, 2);
        System.out.println(this);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("highScore", truncSore);
        editor.apply();
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    public String toString(){
        return "Invoked";
    }
}
