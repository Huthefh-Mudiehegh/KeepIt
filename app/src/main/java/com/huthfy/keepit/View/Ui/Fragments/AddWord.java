package com.huthfy.keepit.View.Ui.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.huthfy.keepit.Model.RoomDatabase.Entity.Word;
import com.huthfy.keepit.R;
import com.huthfy.keepit.ViewModel.WordViewModel;

public class AddWord extends DialogFragment {
    TextView wordNameTv,wordMeaningTv,wordTypeTv;
    Button addBtn;
    WordViewModel wordViewModel;


    public AddWord() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wordViewModel = new ViewModelProvider(getActivity()).get(WordViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_word, container, false);
        wordNameTv = v.findViewById(R.id.fragmentAddWordDialog_WordNameET);
        wordMeaningTv = v.findViewById(R.id.fragmentAddWordDialog_WordMeaningET);
        wordTypeTv = v.findViewById(R.id.fragmentAddWordDialog_WordTypeET);
        addBtn = v.findViewById(R.id.saveWordBtn);
        setStyle(STYLE_NORMAL,R.style.Base_Theme_AppCompat_Light_Dialog_MinWidth);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWord();
            }
        });
    }

    //check values in textView
    private boolean isValueValid(){
        String word = wordNameTv.getText().toString().trim();
        String meaning = wordMeaningTv.getText().toString().trim();
        String type = wordTypeTv.getText().toString().trim();
        if (word.isEmpty()){
            wordNameTv.setError("name is empty");
            return false;
        }
        if (meaning.isEmpty()){
            wordMeaningTv.setError("meaning is empty");

            return false;
        }
        if (type.isEmpty()){
            wordTypeTv.setError("type is empty");
            return false;
        }
        return true;

        /*
        * for better checking ,should check if it will be number or samples*/
    }

    //add word
    private void addWord(){
        if (isValueValid()){
            String wordName = wordNameTv.getText().toString().trim();
            String meaning = wordMeaningTv.getText().toString().trim();
            String type = wordTypeTv.getText().toString().trim();
            Word word = new Word(wordName,meaning,type);
            wordViewModel.insert(word);
            dismiss();
        }
    }
}