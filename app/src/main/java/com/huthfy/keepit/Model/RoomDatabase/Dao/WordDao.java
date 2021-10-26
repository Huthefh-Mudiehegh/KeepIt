package com.huthfy.keepit.Model.RoomDatabase.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.huthfy.keepit.Model.RoomDatabase.Entity.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);
    @Update
    void update(Word word);

    @Delete
    void delete(Word word);

    //get all words
    @Query("SELECT * FROM Word")
    LiveData<List<Word>> getAllWords();


}
