package com.huthfy.keepit.View.Ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.huthfy.keepit.R;

public class UpdateWord extends DialogFragment {
    TextView wordNameTv,wordMeaningTv,wordTypeTv;
    Button addBtn;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_WORD_NAME = "word_name";
    private static final String ARG_WORD_MEANING = "word_meaning";
    private static final String ARG_WORD_TYPE = "word_type";

    // TODO: Rename and change types of parameters
    private String wordName;
    private String wordMeaning;
    private String wordType;

    public UpdateWord() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddWord newInstance(String wordName, String wordMeaning,String wordType) {
        AddWord fragment = new AddWord();
        Bundle args = new Bundle();
        args.putString(ARG_WORD_NAME, wordName);
        args.putString(ARG_WORD_MEANING, wordMeaning);
        args.putString(ARG_WORD_TYPE, wordType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // this line for fragment style
//        setStyle(STYLE_NO_TITLE,R.style.Base_Theme_AppCompat_Dialog_Alert);

        if (getArguments() != null) {
            wordName = getArguments().getString(ARG_WORD_NAME);
            wordMeaning = getArguments().getString(ARG_WORD_MEANING);
            wordType = getArguments().getString(ARG_WORD_TYPE);
        }
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
            wordNameTv.setError("meaning is empty");

            return false;
        }
        if (type.isEmpty()){
            wordNameTv.setError("type is empty");
            return false;
        }
        return true;

        /*
         * for better checking ,should check if it will be number or samples*/
    }

    //add word
    private void addWord(){
        if (isValueValid()){
            wordNameTv.setText(wordName);
            wordMeaningTv.setText(wordMeaning);
            wordTypeTv.setText(wordType);
        }
    }

}
