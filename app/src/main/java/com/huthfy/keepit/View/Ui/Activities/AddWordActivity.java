package com.huthfy.keepit.View.Ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModelProvider;

import com.huthfy.keepit.Model.RoomDatabase.Entity.Word;
import com.huthfy.keepit.R;
import com.huthfy.keepit.ViewModel.AddWordViewModel;

public class AddWordActivity extends AppCompatActivity {
    //constants
    public static final String EXTRA_WORD_ID = "com.huthfy.keepit.AddWordActivity_wordId";
    public static final String EXTRA_WORD_NAME = "com.huthfy.keepit.AddWordActivity_wordName";
    public static final String EXTRA_WORD_MEANING = "com.huthfy.keepit.AddWordActivity_wordMeaning";
    public static final String EXTRA_WORD_TYPE = "com.huthfy.keepit.AddWordActivity_wordType";

    AppCompatEditText wordNameET,wordMeaningET,wordTypeET;
    AddWordViewModel addWordViewModel;

    private boolean editMode;
    Intent intent;
    String wordName,wordMeaning,wordType;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        wordNameET = findViewById(R.id.addWordActivity_wordName);
        wordMeaningET = findViewById(R.id.addWordActivity_wordMeaning);
        wordTypeET = findViewById(R.id.addWordActivity_wordType);

        addWordViewModel = new ViewModelProvider(this).get(AddWordViewModel.class);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

         intent = getIntent();
        if (intent.hasExtra(EXTRA_WORD_ID)){
            //update word
            setTitle("update word");

            wordName = intent.getStringExtra(EXTRA_WORD_NAME);
            wordMeaning = intent.getStringExtra(EXTRA_WORD_MEANING);
            wordType = intent.getStringExtra(EXTRA_WORD_TYPE);

            wordNameET.setText(wordName);
            wordMeaningET.setText(wordMeaning);
            wordTypeET.setText(wordType);
            editMode = true;
        }else {
            // add new word
            setTitle("add new word");
            editMode = false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_word,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAddWord_save:
                //save the new word
                saveWord();
        }
        return super.onOptionsItemSelected(item);
    }

    // this two methods for saving new word
    private void saveWord() {
        String mWordName = wordNameET.getText().toString().trim();
        String mWordMeaning = wordMeaningET.getText().toString().trim();
        String mWordType = wordTypeET.getText().toString().trim();

        if (isValueValid(mWordName,mWordMeaning,mWordType)){

            if (editMode){
                // this is for if there is no change on the word
                if( mWordName.equals(wordName) && mWordMeaning.equals(wordMeaning) && mWordType.equals(wordType) ) {
                    Toast.makeText(this, "word unchanged", Toast.LENGTH_SHORT).show();
                }else {
                    Word word = new Word(mWordName, mWordMeaning, mWordType);
                    word.setId( intent.getIntExtra(EXTRA_WORD_ID, -1) );
                    addWordViewModel.update(word);
                    Toast.makeText(this, "word updated", Toast.LENGTH_SHORT).show();
                }

            }else {
                Word word = new Word(mWordName, mWordMeaning, mWordType);
                addWordViewModel.insert(word);
                Toast.makeText(this, "new word added", Toast.LENGTH_SHORT).show();

            }
            finish();
        }
    }
    private boolean isValueValid(String word,String meaning,String type){
        if (word.isEmpty()){
            wordNameET.setError("name is empty");
            return false;
        }
        if (meaning.isEmpty()){
            wordMeaningET.setError("meaning is empty");

            return false;
        }
        if (type.isEmpty()){
            wordTypeET.setError("type is empty");
            return false;
        }
        return true;

        /*
         * for better checking ,should check if it will be number or samples*/
    }

}
