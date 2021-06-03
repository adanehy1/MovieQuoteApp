package com.example.moviequotev2;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class globalClass extends Application {
    List<String> usedQuotes = new ArrayList<String>();
    double score = 0.0;
    public void addQuote(String quote){
        usedQuotes.add(quote);
    }
    public String getQuote(int i){
        return usedQuotes.get(i);
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
        return Math.floor(score * 100) / 100;
    }
    public void setScore(double newScore){
        score += newScore;
    }
    public void scoreClear(){
        score = 0.0;
    }
}
