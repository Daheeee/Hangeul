package com.dongduk.hangeul.hangeul_test1;

import android.content.Context;
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

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder> {
    Context context;
    List<ListingCard> cards;
    int item_layout;

    public ListingAdapter(Context context, List<ListingCard> cards, int item_layout) {
        this.context = context;
        this.cards = cards;
        this.item_layout = item_layout;
    }

    @Override
    public ListingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_listing, null);
        return new ListingAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListingAdapter.ViewHolder holder, int position) {
        final ListingCard card = cards.get(position);
        holder.tvDateList.setText(card.getTvDateList());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, card.getTvDateList(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDateList;
        Button btnVmoveList;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDateList = (TextView) itemView.findViewById(R.id.tvDateList);
            btnVmoveList = (Button) itemView.findViewById(R.id.btn_vmore_list);
            cardview = (CardView) itemView.findViewById(R.id.cv_listing);
        }
    }

}

