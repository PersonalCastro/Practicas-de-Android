package com.example.myapplicationcalculator.ui.estandar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EstandarViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EstandarViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}