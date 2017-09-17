package com.dongduk.hangeul.hangeul_test1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyWordActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private int prevCenterPos;
    private Button btnDelete;
    private MenuItem item;
    private MyWordAdapter myWordAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("");
        TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText("나의 우리말");

        ViewStub stub = (ViewStub)findViewById(R.id.stub);
        stub.setLayoutResource(R.layout.activity_my_word);
        stub.inflate();

        btnDelete = (Button)findViewById(R.id.btnDelete);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        List<MyWord> wordList = new ArrayList<>();
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));
        wordList.add(new MyWord("09.09", "미\n리\n내\n", "가\n르\n거\n나\n\n쪼\n개\n지\n\n않\n고.", "자\n연\n그\n대\n로\n\n언\n제\n나\n\n변\n함\n없\n이\n"));


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        // set up the RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(layoutManager);
        snapHelper.attachToRecyclerView(recyclerView);

        myWordAdapter = new MyWordAdapter(wordList);

        recyclerView.setAdapter(myWordAdapter);



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int center = recyclerView.getWidth() / 2;
                View centerView = recyclerView.findChildViewUnder(center, recyclerView.getTop());
                int centerPos = recyclerView.getChildAdapterPosition(centerView);

                if (prevCenterPos != centerPos) {
                    // dehighlight the previously highlighted view
                    View prevView = recyclerView.getLayoutManager().findViewByPosition(prevCenterPos);

                    if (prevView != null) {

                        TextView textView = (TextView) prevView.findViewById(R.id.tvMyWord);
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                        textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
                        textView.setTextColor(Color.LTGRAY);

                        TextView desc1 = (TextView)prevView.findViewById(R.id.tvDesc1);
                        TextView desc2 = (TextView)prevView.findViewById(R.id.tvDesc2);

                        desc1.setVisibility(View.GONE);
                        desc2.setVisibility(View.GONE);

                    }

                    // highlight view in the middle
                    if (centerView != null) {
                        TextView textView = (TextView) centerView.findViewById(R.id.tvMyWord);

                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                        textView.setTextColor(Color.BLACK);

                        TextView desc1 = (TextView)centerView.findViewById(R.id.tvDesc1);
                        TextView desc2 = (TextView)centerView.findViewById(R.id.tvDesc2);

                        desc1.setVisibility(View.VISIBLE);
                        desc2.setVisibility(View.VISIBLE);
                    }

                    prevCenterPos = centerPos;
                }
            }
        });






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.barbtn, menu);
        item = menu.getItem(0);
        item.setTitle("수정");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //수정버튼 클릭시
        if (id == R.id.barBtn) {

            if(item.getTitle().equals("수정")){
                item.setTitle("취소");
                btnDelete.setVisibility(View.VISIBLE);
            }
            else if(item.getTitle().equals("취소")){
                item.setTitle("수정");
                btnDelete.setVisibility(View.GONE);
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        Intent mIntent;

        int id = item.getItemId();

        if (id == R.id.todayWord) {
            mIntent = new Intent(this, MainActivity.class);
            startActivity(mIntent);
            finish();


        } else if (id == R.id.myWord) {

        } else if (id == R.id.myRecord) {
            mIntent = new Intent(this, MyRecordActivity.class);
            startActivity(mIntent);
            finish();
        } else if (id == R.id.setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
