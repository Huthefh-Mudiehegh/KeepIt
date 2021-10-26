package com.huthfy.keepit.Model.RoomDatabase.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.huthfy.keepit.Model.RoomDatabase.Dao.WordDao;
import com.huthfy.keepit.Model.RoomDatabase.Entity.Word;

@Database(entities = Word.class, version = 1)
public abstract class WordDatabase extends RoomDatabase {

    private static WordDatabase instance;
    public abstract WordDao WordDao();

    //singlton
    public static WordDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class,
                    "WordDatabase")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new publishDataBaseAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    // this will call when database created for the first time just.
    private static class publishDataBaseAsyncTask extends AsyncTask<Void, Void, Void> {
        private static WordDao mWordDao;

        public publishDataBaseAsyncTask(WordDatabase instance) {
            mWordDao = instance.WordDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mWordDao.insert(new Word("word", "كلمة", "noun"));
            mWordDao.insert(new Word("word", "كلمة", "noun"));
            mWordDao.insert(new Word("word", "كلمة", "noun"));
            mWordDao.insert(new Word("word", "كلمة", "noun"));
            mWordDao.insert(new Word("word", "كلمة", "noun"));
            mWordDao.insert(new Word("word", "كلمة", "noun"));
            mWordDao.insert(new Word("word", "كلمة", "noun"));
            mWordDao.insert(new Word("word", "كلمة", "noun"));
            mWordDao.insert(new Word("word", "كلمة", "noun"));
            mWordDao.insert(new Word("word", "كلمة", "noun"));
            mWordDao.insert(new Word("word", "كلمة", "noun"));
            mWordDao.insert(new Word("word", "كلمة", "noun"));
            mWordDao.insert(new Word("word", "كلمة", "noun"));
            return null;
        }
    }
}
