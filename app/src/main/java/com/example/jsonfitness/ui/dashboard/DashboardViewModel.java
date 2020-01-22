package com.example.jsonfitness.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jsonfitness.data.FinalEx;
import com.example.jsonfitness.data.FitnessExersice;
import com.example.jsonfitness.modle.ExerciseJSONParser;

import java.util.List;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<FinalEx>> mLiveData;


    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mLiveData = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");


        new ExerciseJSONParser(mLiveData).execute();


    }

    public LiveData<String> getText() {
        return mText;
    }
}