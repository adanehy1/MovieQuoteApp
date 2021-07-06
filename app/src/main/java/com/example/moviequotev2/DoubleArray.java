package com.example.moviequotev2;
import android.content.SharedPreferences;
import android.content.Context;

import java.util.Arrays;

public class DoubleArray {
    double[] highScore = new double[5];
    final int size = highScore.length;
    Context context;
    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "Double Array";


    public DoubleArray(Context cntx){
        context = cntx;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        highScore[0] = sharedPreferences.getFloat("hs1", 0);
        highScore[1] = sharedPreferences.getFloat("hs2", 0);
        highScore[2] = sharedPreferences.getFloat("hs3", 0);
        highScore[3] = sharedPreferences.getFloat("hs4", 0);
        highScore[4] = sharedPreferences.getFloat("hs5", 0);
    }
    public boolean addNewHS(double score){
        if(highScore[highScore.length - 1] >= score){ return false; }
        for(int s = 0; s < size; s++){
            if(score > highScore[s]){
                shift(s, score);
                return true;
            }
        }
        return false;
    }
    public double getFirstHS(){
        return highScore[0];
    }
    public double[] getHighScore(){
        return highScore;
    }
    public void resetScores(){
        Arrays.fill(highScore, 0.0);
        refreshScores();

    }
    public String toString(){
        String ret = "[";
        for(int s  = 0; s < size; s++){
            ret += String.valueOf(highScore[s]);
            ret += s != size - 1 ? ", " : "";
        }
        ret += "]";
        return ret;
    }
    private void shift(int start, double newScore){
        double temp;
        for(int i = start; i < size; i++){
            temp = highScore[i];
            highScore[i] = newScore;
            newScore = temp;
        }
        refreshScores();
    }
    private void refreshScores(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("hs1", (float)highScore[0]);
        editor.putFloat("hs2", (float)highScore[1]);
        editor.putFloat("hs3", (float)highScore[2]);
        editor.putFloat("hs4", (float)highScore[3]);
        editor.putFloat("hs5", (float)highScore[4]);
        editor.apply();
    }
}
