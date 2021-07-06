package com.example.moviequotev2;

public class Util {
    public static boolean contains(double[] arr, double val){
        for(double data : arr){
            if(val == data) { return true; }
        }
        return false;
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
