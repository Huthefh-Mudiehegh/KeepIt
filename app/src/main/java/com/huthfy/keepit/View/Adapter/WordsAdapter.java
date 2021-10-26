package com.huthfy.keepit.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huthfy.keepit.Model.RoomDatabase.Entity.Word;
import com.huthfy.keepit.R;

import java.util.ArrayList;
import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordsViewHolder> {
    List<Word> wordList = new ArrayList<>();
    OnWordClickListener onWordClickListener;
    @NonNull
    @Override
    public WordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new WordsViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.words_adapter_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WordsViewHolder holder, int position) {
        Word word = wordList.get(position);
        holder.wordName.setText(word.getWordName());
        holder.wordMeaning.setText(word.getWordMeaning());
        holder.wordType.setText(word.getWordType());
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }


    //set words in array list
    public void setWords(List<Word> words){
        wordList = words;
        notifyDataSetChanged();
    }




    class WordsViewHolder extends RecyclerView.ViewHolder {
        TextView wordName,wordMeaning,wordType;
        public WordsViewHolder(@NonNull View itemView) {
            super(itemView);
            wordName = itemView.findViewById(R.id.wordsAdapter_wordName);
            wordMeaning = itemView.findViewById(R.id.wordsAdapter_wordMeaning);
            wordType = itemView.findViewById(R.id.wordsAdapter_wordType);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    onWordClickListener.onWordClick(wordList.get(index));
                }
            });
        }
    }
    // get word by index
    public Word getWordAt(int index){
        return wordList.get(index);
    }

    public interface OnWordClickListener{
        void onWordClick(Word word);
    }
    public void setOnWordClickListener(OnWordClickListener listener){
        onWordClickListener = listener;
    }
}
