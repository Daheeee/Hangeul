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

import org.w3c.dom.Text;

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
        holder.tvWordRecord.setText(card.getTvWordRecord());
        holder.tvContentRecord01.setText(card.getTvContentRecord01());
        holder.tvContentRecord02.setText(card.getTvContentRecord02());
        holder.tvContentRecord03.setText(card.getTvContentRecord03());
        holder.tvContentRecord04.setText(card.getTvContentRecord04());
//        holder.cardview.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                int p = getLayoutPosition();
//                Toast.makeText(context, position, Toast.LENGTH_SHORT).show();
//                //onItemRemove(cards.get(position));
//                return true;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return this.cards.size();
    }

    public void onItemRemove(int position) {
        cards.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDateRecord;
        TextView tvWordRecord;
        TextView tvContentRecord01;
        TextView tvContentRecord02;
        TextView tvContentRecord03;
        TextView tvContentRecord04;
        Button btnVmoveRecord;
        Button btnDeleteRecord;
        CardView cardview;


        public ViewHolder(View itemView) {
            super(itemView);

            tvDateRecord = (TextView) itemView.findViewById(R.id.tvDateRecord);
            tvWordRecord = (TextView) itemView.findViewById(R.id.tvWordRecord);
            tvContentRecord01 = (TextView) itemView.findViewById(R.id.tvContentRecord01);
            tvContentRecord02 = (TextView) itemView.findViewById(R.id.tvContentRecord02);
            tvContentRecord03 = (TextView) itemView.findViewById(R.id.tvContentRecord03);
            tvContentRecord04 = (TextView) itemView.findViewById(R.id.tvContentRecord04);
            btnVmoveRecord = (Button) itemView.findViewById(R.id.btn_vmore_record);
            //btnDeleteRecord = (Button) v.findViewById(R.id.btnDeleteRecord);
            cardview = (CardView) itemView.findViewById(R.id.cv_myrecord);

            cardview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int p = getLayoutPosition();
                //System.out.println("LongClick: "+p);
                onItemRemove(p);
                //btnDeleteRecord.setVisibility(View.VISIBLE);
                return true;
            }
        });
        }
    }

}
