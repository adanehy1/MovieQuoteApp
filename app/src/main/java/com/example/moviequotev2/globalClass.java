package com.example.moviequotev2;

import android.app.Application;

public class globalClass extends Application {
    private String test = "initial";
    public void setTest(String newTest){
        test = newTest;
    }
    public String getTest(){
        return test;
    }
}
