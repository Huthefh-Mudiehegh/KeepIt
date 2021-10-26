package com.huthfy.keepit.Model.Repositry;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.huthfy.keepit.Model.RoomDatabase.Dao.WordDao;
import com.huthfy.keepit.Model.RoomDatabase.Database.WordDatabase;
import com.huthfy.keepit.Model.RoomDatabase.Entity.Word;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class WordRepo {
    private WordDatabase wordDatabase;
    private WordDao wordDao;
    private LiveData<List<Word>> wordsLive;

    public WordRepo(Application app) {
        wordDatabase = WordDatabase.getInstance(app);
        wordDao = wordDatabase.WordDao();
        if (wordsLive != null)
            wordsLive = getAllWords();
    }

    //operation

    //insert
    public void insert(Word word) {
        new InsertAsyncTask(wordDao).execute(word);
    }

    //update
    public void update(Word word) {
        new UpdateAsyncTask(wordDao).execute(word);
    }

    //delete
    public void delete(Word word) {
        new DeleteAsyncTask(wordDao).execute(word);
    }

    //getAllWords
    public LiveData<List<Word>> getAllWords() {
        GetAllWordAsyncTask task = new GetAllWordAsyncTask(wordDao);
        task.execute();
        try {
            wordsLive = task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return wordsLive;
    }

    private class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mWordDao;

        public InsertAsyncTask(WordDao wordDao) {
            mWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.insert(words[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mWordDao;

        public UpdateAsyncTask(WordDao wordDao) {
            mWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.update(words[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mWordDao;

        public DeleteAsyncTask(WordDao wordDao) {
            mWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.delete(words[0]);
            return null;
        }
    }

    private class GetAllWordAsyncTask extends AsyncTask<Word, Void, LiveData<List<Word>>> {
        private WordDao mWordDao;

        public GetAllWordAsyncTask(WordDao wordDao) {
            mWordDao = wordDao;
        }

        @Override
        protected LiveData<List<Word>> doInBackground(Word... words) {

            return mWordDao.getAllWords();
        }
    }
}
