package com.example.moviequotev2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Context;

public class JsonQuotes {
    HashMap<String, String> quoteDictionary = new HashMap<>();
    List<String> quotesArrList = new ArrayList<>();
    List<String> allMovieNames = new ArrayList<>();

    public JsonQuotes(Context context, String[] movieNames) throws JSONException {
        String jsonStr = getJsonFromAssets(context.getApplicationContext(), "quotes.json");
        JSONObject json = new JSONObject(jsonStr);
        Iterator<String> keys = json.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            allMovieNames.add(key);
            if(arrContains(movieNames, key)) {
                JSONArray jsonArray = (JSONArray) json.get(key);
                for(int i = 0; i < jsonArray.length(); i++){
                    quotesArrList.add(jsonArray.getString(i));
                    quoteDictionary.put(jsonArray.getString(i), key);
                }
            }
        }
    }
    public JsonQuotes(Context context) throws JSONException {
        String jsonStr = getJsonFromAssets(context.getApplicationContext(), "quotes.json");
        JSONObject json = new JSONObject(jsonStr);
        Iterator<String> keys = json.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            allMovieNames.add(key);
        }
    }
        static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }
    public boolean arrContains(String[] arr, String value){
        if (arr != null) {
            for(String s : arr){
                if(s.equals(value)){
                    return true;
                }
            }
        }
        return false;
    }
    public List<String> getMovieNames(){
        return allMovieNames;
    }
    public List<String> getQList(){
        return quotesArrList;
    }
    public HashMap<String, String> getQDict(){
        return quoteDictionary;
    }

}
