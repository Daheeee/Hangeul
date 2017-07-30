package com.dongduk.hangeul.hangeul_test1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.tsengvn.typekit.TypekitContextWrapper;

public class CardviewActivity extends BaseActivity {

    private final int MAX_PAGE=10;                         //View Pager의 총 페이지 갯수를 나타내는 변수 선언
    Fragment cur_fragment=new Fragment();   //현재 Viewpager가 가리키는 Fragment를 받을 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);

        ViewPager viewPager=(ViewPager)findViewById(R.id.pager);        //Viewpager 선언 및 초기화
        viewPager.setAdapter(new adapter(getSupportFragmentManager()));     //선언한 viewpager에 adapter를 연결

//        ImageButton back=(ImageButton)findViewById(R.id.backbtn);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
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
