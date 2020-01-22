package com.example.jsonfitness.data;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.example.jsonfitness.modle.ExerciseJSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class FitnessExersice {

    //properties
    private String exName;
    private String sets;
    private String repitition;
    private String rest;





    //constructor
    public FitnessExersice(String exName, String sets, String repitition, String rest) {
        this.exName = exName;
        this.sets = sets;
        this.repitition = repitition;
        this.rest = rest;




    }

    //getter and setter
    public String getExName() {
        return exName;
    }
    public void setExName(String exName) {
        this.exName = exName;
    }
    public String getSets() {
        return sets;
    }
    public void setSets(String sets) {
        this.sets = sets;
    }
    public String getRepitition() {
        return repitition;
    }
    public void setRepitition(String repitition) {
        this.repitition = repitition;
    }
    public String getRest() {
        return rest;
    }
    public void setRest(String rest) {
        this.rest = rest;
    }


    //toString


    @Override
    public String toString() {
        return "FitnessExersice{" +
                "exName='" + exName + '\'' +
                ", sets='" + sets + '\'' +
                ", repitition='" + repitition + '\'' +
                ", rest='" + rest + '\'' +
                '}';
    }

    private class getExDetails extends AsyncTask<String,Integer,String> {

        ExerciseJSONParser firstJsonPaser = new ExerciseJSONParser();
        protected String doInBackground(String... strings) {
            if (firstJsonPaser.getStatus() == AsyncTask.Status.FINISHED) {
                firstJsonPaser.getStatus();
                try {
                    URL url = new URL(exName);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = con.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    String test = sb.toString();
                    return test;

                } catch (IOException e) {
                    doInBackground();
                }
            }else {

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }
}
