package com.dongduk.hangeul.hangeul_test1;

import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jiyoungwon on 2017. 9. 10..
 */
public class MyWordAdapter extends RecyclerView.Adapter<MyWordAdapter.MyViewHolder> {

    private List<MyWord> wordList;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView myword;
        private TextView desc1;
        private TextView desc2;
        private RadioButton radioButton;

        public MyViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.tvDate);
            myword = (TextView) view.findViewById(R.id.tvMyWord);
            desc1 = (TextView) view.findViewById(R.id.tvDesc1);
            desc2 = (TextView) view.findViewById(R.id.tvDesc2);
            radioButton = (RadioButton) view.findViewById(R.id.radio);
        }


    }

    public MyWordAdapter(List<MyWord> countryList) {
        this.wordList = countryList;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        MyWord myword = wordList.get(position);
        holder.date.setText(myword.getDate());
        holder.myword.setText(myword.getWord());
        holder.desc1.setText(myword.getDesc1());
        holder.desc2.setText(myword.getDesc2());

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.radioButton.isChecked()){
                    holder.radioButton.setChecked(false);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_view,parent, false);
        return new MyViewHolder(v);
    }



}
