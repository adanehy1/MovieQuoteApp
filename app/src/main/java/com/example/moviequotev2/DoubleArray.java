package com.example.moviequotev2;
import android.content.SharedPreferences;
import android.content.Context;

public class DoubleArray {
    double[] highScore = new double[4];
    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "Double Array";


    public DoubleArray(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        highScore[0] = sharedPreferences.getFloat("hs1", 0);
        highScore[1] = sharedPreferences.getFloat("hs2", 0);
        highScore[2] = sharedPreferences.getFloat("hs3", 0);
        highScore[3] = sharedPreferences.getFloat("hs4", 0);
        highScore[4] = sharedPreferences.getFloat("hs5", 0);
    }
    public void addNewHS(double score){

    }

}
