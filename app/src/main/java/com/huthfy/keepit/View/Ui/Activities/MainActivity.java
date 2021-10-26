package com.huthfy.keepit.View.Ui.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.huthfy.keepit.Model.RoomDatabase.Entity.Word;
import com.huthfy.keepit.R;
import com.huthfy.keepit.View.Adapter.WordsAdapter;
import com.huthfy.keepit.View.Ui.Fragments.AddWord;
import com.huthfy.keepit.ViewModel.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private WordViewModel wordViewModel;

    FloatingActionButton addBtn;
    private RecyclerView wordRecyclerView;
    private WordsAdapter wordsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        addBtn = findViewById(R.id.main_addBtn);
        addBtn.setOnClickListener(this);
        wordRecyclerView = findViewById(R.id.main_word_recyclerview);
        wordRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        wordRecyclerView.setHasFixedSize(true);

        wordsAdapter = new WordsAdapter();
        wordRecyclerView.setAdapter(wordsAdapter);

        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {

                // update ui
                // update recyclerview
                wordsAdapter.setWords(words);

            }
        });

        // on word click to update
        wordsAdapter.setOnWordClickListener(new WordsAdapter.OnWordClickListener() {
            @Override
            public void onWordClick(Word word) {
                Intent i = new Intent(MainActivity.this,AddWordActivity.class);
                i.putExtra(AddWordActivity.EXTRA_WORD_ID,word.getId());
                i.putExtra(AddWordActivity.EXTRA_WORD_NAME,word.getWordName());
                i.putExtra(AddWordActivity.EXTRA_WORD_MEANING,word.getWordMeaning());
                i.putExtra(AddWordActivity.EXTRA_WORD_TYPE,word.getWordType());

                startActivity(i);

//                new AddWord().show(getSupportFragmentManager(),null);

            }
        });

        // delete word by swapping
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT
        | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                wordViewModel.delete(wordsAdapter.getWordAt(position));
            }
        }).attachToRecyclerView(wordRecyclerView);
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_addBtn:
                //  start add new word activty
                Intent intent = new Intent(MainActivity.this,AddWordActivity.class);
                startActivity(intent);
        }
    }


}