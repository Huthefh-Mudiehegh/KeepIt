package com.huthfy.keepit.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.huthfy.keepit.Model.Repositry.WordRepo;
import com.huthfy.keepit.Model.RoomDatabase.Entity.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepo wordRepo;
    private LiveData<List<Word>> wordsLive;

    public WordViewModel(Application application){
        super(application);
        wordRepo = new WordRepo(application);
        wordsLive = wordRepo.getAllWords();
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

    //delete
    public void delete(Word word) {
        wordRepo.delete(word);
    }

    //getAllWords
    public LiveData<List<Word>> getAllWords() {
        return wordsLive;
    }

}
