package com.huthfy.keepit.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.huthfy.keepit.Model.Repositry.WordRepo;
import com.huthfy.keepit.Model.RoomDatabase.Entity.Word;

import java.util.List;

public class AddWordViewModel extends AndroidViewModel {
    private WordRepo wordRepo;

    public AddWordViewModel(Application application){
        super(application);
        wordRepo = new WordRepo(application);

    }

    //operation

    //insert
    public void insert(Word word) {
        wordRepo.insert(word);
    }

    //update
    public void update(Word word) {
        wordRepo.update(word);
    }

}
