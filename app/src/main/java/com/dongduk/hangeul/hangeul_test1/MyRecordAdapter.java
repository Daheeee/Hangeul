package com.dongduk.hangeul.hangeul_test1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by yoon1 on 2017-09-17.
 */

public class MyRecordAdapter extends RecyclerView.Adapter<MyRecordAdapter.ViewHolder> {
    Context context;
    List<MyRecordCard> cards;
    int item_layout;

    public MyRecordAdapter(Context context, List<MyRecordCard> cards, int item_layout) {
        this.context = context;
        this.cards = cards;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_myrecord, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyRecordCard card = cards.get(position);
        holder.tvDateRecord.setText(card.getTvDateRecord());
        holder.tvWord1.setText(card.getTvWord1());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, card.getTvWord1(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDateRecord;
        TextView tvWord1;
        Button btnVmoveRecord;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDateRecord = (TextView) itemView.findViewById(R.id.tvDateRecord);
            tvWord1 = (TextView) itemView.findViewById(R.id.tvWord1);
            btnVmoveRecord = (Button) itemView.findViewById(R.id.btn_vmore_record);
            cardview = (CardView) itemView.findViewById(R.id.cv_myrecord);
        }
    }

}
