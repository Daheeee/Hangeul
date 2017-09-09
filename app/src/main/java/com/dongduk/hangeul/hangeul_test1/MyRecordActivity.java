package com.dongduk.hangeul.hangeul_test1;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.tsengvn.typekit.TypekitContextWrapper;

public class MyRecordActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private final int MAX_PAGE=10;                         //View Pager의 총 페이지 갯수를 나타내는 변수 선언
    Fragment cur_fragment=new Fragment();   //현재 Viewpager가 가리키는 Fragment를 받을 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText("나의 글자취");

        ViewStub stub = (ViewStub)findViewById(R.id.stub);
        stub.setLayoutResource(R.layout.activity_my_record);
        stub.inflate();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager viewPager=(ViewPager)findViewById(R.id.pager);        //Viewpager 선언 및 초기화
        viewPager.setAdapter(new adapter(getSupportFragmentManager()));

//        ImageButton back=(ImageButton)findViewById(R.id.backbtn);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.barbtn, menu);
        MenuItem item = menu.getItem(0);
        item.setTitle("수정");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.barBtn) {    // 수정버튼
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent mIntent;

        int id = item.getItemId();

        if (id == R.id.todayWord) {
            mIntent = new Intent(this, MainActivity.class);
            startActivity(mIntent);

        } else if (id == R.id.myWord) {

        } else if (id == R.id.myRecord) {
            mIntent = new Intent(this, MyRecordActivity.class);
            startActivity(mIntent);
        } else if (id == R.id.setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class adapter extends FragmentPagerAdapter {                    //adapter클래스
        public adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position<0 || MAX_PAGE<=position)        //가리키는 페이지가 0 이하거나 MAX_PAGE보다 많을 시 null로 리턴
                return null;
            switch (position){              //포지션에 맞는 Fragment찾아서 cur_fragment변수에 대입
                case 0:
                    cur_fragment=new page1();
                    break;

                case 1:
                    cur_fragment=new page1();
                    break;

                case 2:
                    cur_fragment=new page1();
                    break;
                case 3:
                    cur_fragment=new page1();
                    break;
                case 4:
                    cur_fragment=new page1();
                    break;
                case 5:
                    cur_fragment=new page1();
                    break;
                case 6:
                    cur_fragment=new page1();
                    break;
                case 7:
                    cur_fragment=new page1();
                    break;
                case 8:
                    cur_fragment=new page1();
                    break;
                case 9:
                    cur_fragment=new page1();
                    break;
            }

            return cur_fragment;
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));

    }
}
