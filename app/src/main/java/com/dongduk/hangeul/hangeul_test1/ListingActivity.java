package com.dongduk.hangeul.hangeul_test1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;
import java.util.List;

public class ListingActivity extends BaseActivity {

    final int ITEM_SIZE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText("");

        ViewStub stub = (ViewStub)findViewById(R.id.stub);
        stub.setLayoutResource(R.layout.activity_listing);
        stub.inflate();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_listing);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(layoutManager);
        snapHelper.attachToRecyclerView(recyclerView);

        List<ListingCard> cards = new ArrayList<>();
        ListingCard[] card = new ListingCard[ITEM_SIZE];
        card[0] = new ListingCard("2017.09.17","테\n스\n트\n","입\n니\n다\n","","","","");
        card[1] = new ListingCard("2017.09.17","하\n하\n","하\n하\n","","","","");
        card[2] = new ListingCard("2017.09.16","호\n호\n","호\n호\n","","","","");
        card[3] = new ListingCard("2017.09.14","히\n히\n","히\n히\n","","","","");
        card[4] = new ListingCard("2017.09.10","헤\n헤\n","헤\n헤\n","","","","");

        for (int i = 0; i < ITEM_SIZE; i++) {
            cards.add(card[i]);
        }

        recyclerView.setAdapter(new ListingAdapter(getApplicationContext(), cards, R.layout.activity_listing));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onClick(View v) {
        final LinearLayout dialogLayout;
        AlertDialog dialog;

        switch (v.getId()) {

            case R.id.btn_vmore_list:
                dialogLayout = (LinearLayout) View.inflate(this, R.layout.dialog_viewmore, null);
                dialog = new AlertDialog.Builder(this)
                        .setView(dialogLayout)
                        .show();

                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setAttributes(params);
                break;

        }
    }
}
