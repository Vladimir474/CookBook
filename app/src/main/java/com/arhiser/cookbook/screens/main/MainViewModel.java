package com.arhiser.cookbook.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.arhiser.cookbook.App;
import com.arhiser.cookbook.model.Recept;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Recept>> noteLiveData = App.getInstance().getNoteDao().getAllLiveData();

    public LiveData<List<Recept>> getNoteLiveData() {
        return noteLiveData;
    }
}
