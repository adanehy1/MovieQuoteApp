package com.example.moviequotev2;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class globalClass extends Application {
    List<String> usedQuotes = new ArrayList<String>();
    String[] selectedMovies;
    double score = 0.0;
    public void addQuote(String quote){
        usedQuotes.add(quote);
    }
    public String[] getSelectedMovies(){
        return selectedMovies;
    }
    public void setSelectedMovies(String[] movies){
        selectedMovies = movies;
    }
    public String getQuote(int i){
        return  usedQuotes.get(i);
    }
    public void printQuotes(){
        for(String quote : usedQuotes){
            System.out.println("Printing Quotes: " + quote);
        }
    }
    public boolean contains(String selectedQuote){
        for(String quote : usedQuotes){
            if(quote == selectedQuote){
                return true;
            }
        }
        return false;
    }
    public void clear(){
        usedQuotes.clear();
    }
    public double getScore(){
        return Util.round(Math.floor(score * 100) / 100, 1);
    }
    public void setScore(double newScore){
        score += newScore;
        Util.round(score, 1);
    }
    public void scoreClear(){
        score = 0.0;
    }
}
