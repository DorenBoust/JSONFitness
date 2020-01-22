package com.example.jsonfitness.data;

import java.util.List;

public class FitnessDays {

    //properties
    private List<FitnessExersice> day;

    //constructor
    public FitnessDays(List<FitnessExersice> day) {
        this.day = day;
    }

    //getter setter
    public List<FitnessExersice> getDay() {
        return day;
    }
    public void setDay(List<FitnessExersice> day) {
        this.day = day;
    }

    //toString
    @Override
    public String toString() {
        return "FitnessDays{" +
                "day=" + day +
                '}';
    }
}
