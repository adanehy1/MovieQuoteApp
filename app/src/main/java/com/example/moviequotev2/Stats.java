package com.example.moviequotev2;

import android.content.Context;
import android.content.SharedPreferences;

public class Stats {
    double score;
    int roundsPlayed;
    double avgPointsPerRound;
    long totalAccumPoints;
    int correctGuesses;
    double correctGuessAvg;
    Context context;
    public static final String SHARED_PREFS = "Statistics";
    SharedPreferences sharedPreferences;

    public Stats(Context cntx) {
        context = cntx;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        score = round(sharedPreferences.getFloat("highScore", 0), 2);
        roundsPlayed = sharedPreferences.getInt("roundsPlayed", 0);
        avgPointsPerRound = sharedPreferences.getFloat("avgPointsPerRound", 0);
        totalAccumPoints = sharedPreferences.getLong("totalAccumPoints", 0);
        correctGuesses = sharedPreferences.getInt("correctGuesses", 0);
        correctGuessAvg = (roundsPlayed > 0 ? (100 * round(correctGuesses / (double) roundsPlayed, 4)) : 0);

    }
    public void incrRoundsPlayed(){
        roundsPlayed ++;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("roundsPlayed", roundsPlayed);
        editor.apply();
    }
    public void incrTotalAccumPoints(double score){
        totalAccumPoints += (long) score;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("totalAccumPoints", totalAccumPoints);
        editor.apply();
    }
    public void incrCorrectGuesses(){
        correctGuesses++;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("correctGuesses", correctGuesses);
        editor.apply();
    }
    public void checkNewHighScore(double curScore) {
        if (curScore <= 0.0) {
            return;
        }
        if (getHighScore() < curScore) {
            System.out.println("Prev Score: " + score + " New HighScore: " + curScore);
            setHighScore(curScore);
        }
    }

    public void setHighScore(double newScore) {
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
    public void resetScore() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("highScore", 0);
        editor.apply();
        score = 0;
    }
    public void resetRoundsPlayed(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("roundsPlayed", 0);
        editor.apply();
        roundsPlayed = 0;
    }
    public void resetTotalAccumPoints(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("totalAccumPoints", 0);
        editor.apply();
        totalAccumPoints = 0;
    }
    public void resetCorrectGuessAvg(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("correctGuessAvg",  0);
        editor.apply();
        correctGuessAvg = 0.0;
    }
    public void resetAll(){
        resetScore();
        resetRoundsPlayed();
        resetTotalAccumPoints();
        resetCorrectGuessAvg();

    }
    public String toString() {
        return "High Score: " + score + " Rounds Played: " + roundsPlayed + " Avg Points Per Round: " + (roundsPlayed > 0 ? (totalAccumPoints/roundsPlayed) : "0")  +
                " Total Points: " + totalAccumPoints + " Correct Guess Avg: " + correctGuessAvg + "% Correct Guesses: " + correctGuesses;
    }

    public double getHighScore() {
        return score;
    }
    public int getRoundsPlayed(){
        return roundsPlayed;
    }
    public double getAvgPointsPerRound(){
        return (roundsPlayed > 0 ? (totalAccumPoints/roundsPlayed) : 0);
    }
    public long getTotalPoints(){
        return totalAccumPoints;
    }
    public double getCorrectGuessAvg(){
        return correctGuessAvg;
    }
}
