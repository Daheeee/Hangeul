package com.dongduk.hangeul.hangeul_test1;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NetworkService networkService;
    private BackPressCloseHandler backPressCloseHandler;
    private FirebaseAuth auth;
    private FirebaseUser user;
    String email;
    TextView tvDateMain, tvWordMain, tvMeaning01, tvMeaning02, tvMeaning03, tvMeaning04, tvEmail;
    Button btnWrite;

    SimpleDateFormat df;

    String meaning, meaning01, meaning02, meaning03, meaning04;
    String wordMeaning;     // 오늘의 단어 전체 뜻 저장 변수
    int countSpace = 0;

    MyWordDBHelper helper;
    private DatabaseReference mDatabase;
    String id = "";
//    SharedPreferences pr = getSharedPreferences("pr", MODE_PRIVATE);
    Word value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewStub stub = (ViewStub)findViewById(R.id.stub);
        stub.setLayoutResource(R.layout.content_main);
        stub.inflate();

        backPressCloseHandler = new BackPressCloseHandler(this);
        helper = new MyWordDBHelper(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            email = user.getEmail();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }

        tvDateMain = (TextView) findViewById(R.id.tvDateMain);
        tvWordMain = (TextView) findViewById(R.id.tvWordMain);
        tvMeaning01 = (TextView) findViewById(R.id.tvMeaning01);
        tvMeaning02 = (TextView) findViewById(R.id.tvMeaning02);
        tvMeaning03 = (TextView) findViewById(R.id.tvMeaning03);
        tvMeaning04 = (TextView) findViewById(R.id.tvMeaning04);
        tvEmail = (TextView)navigationView.getHeaderView(0).findViewById(R.id.tvUserId);
        btnWrite = (Button) findViewById(R.id.btnWrite);

        tvEmail.setText(email);

        df = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        String currentDateTimeString = df.format(new Date());
        tvDateMain.setText(currentDateTimeString);

        ApplicationController application = ApplicationController.getInstance();
        application.buildNetworkService("us-central1-hanguel-6c329.cloudfunctions.net/");
//        application.buildNetworkService("54.237.215.221", 8000);
        networkService = ApplicationController.getInstance().getNetworkService();


//        if(currentDateTimeString.equals(pr.getString("date", ""))){
//
//        }

        Call<String> getCall = networkService.get_word_id();
        getCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if( response.isSuccessful()) {
                    id = response.body();

                    Log.d("success", "id : " + id);

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("word").child(id);
                    // Read from the database
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.

                            value = dataSnapshot.getValue(Word.class);
                            Log.d("MainActivity", "Value is: " + value.getTitle());


                            meaning01 = ""; meaning02 = ""; meaning03 = ""; meaning04 = "";
                            countSpace = 0;

                            SharedPreferences pr = getSharedPreferences("pr", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pr.edit();

                            editor.putString("word", value.getTitle());
                            editor.putString("wid", Long.toString(value.getId()));
//        editor.putString(tvWordMain.getText().toString(), tvMeaning01.getText().toString() + tvMeaning02.getText().toString() + tvMeaning03.getText().toString() + tvMeaning04.getText().toString());
                            editor.putString(value.getTitle(), value.getContent());
//                            editor.putString("date", currentDateTimeString);

                            editor.commit();

                            for(int i = 0; i < value.getContent().length(); i++) {
                                meaning = "";

                                if(value.getContent().charAt(i) == ' ') {
                                    meaning += value.getContent().charAt(i) + "\n\n";
                                    countSpace ++;
                                } else {
                                    meaning += value.getContent().charAt(i);
                                }

                                if (countSpace <3) meaning01 += meaning;
                                else if (countSpace >=3 && countSpace < 6) meaning02 += meaning;
                                else if (countSpace >=6 && countSpace < 9) meaning03 += meaning;
                                else if (countSpace >=9) meaning04 += meaning;
                            }

                            tvWordMain.setText(value.getTitle().toString());
                            tvMeaning01.setText("\n\n" + meaning01);
                            tvMeaning02.setText(meaning02);
                            tvMeaning03.setText(meaning03);
                            tvMeaning04.setText(meaning04);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("MainActivity", "Failed to read value.", error.toException());
                        }
                    });

                } else {
                    int StatusCode = response.code();
                    Log.i(ApplicationController.TAG, "Status Code : " + StatusCode + " Error Message : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i(ApplicationController.TAG, "Fail Message : " + t.getMessage());
            }
        });






        //word GET
//        Call<Word> getCall = networkService.get_word();
//        getCall.enqueue(new Callback<Word>() {
//            @Override
//            public void onResponse(Call<Word> call, Response<Word> response) {
//                if( response.isSuccessful()) {
//                    Word word = response.body();
//
//                    wordMeaning = word.getContent();
//
//                    meaning01 = ""; meaning02 = ""; meaning03 = ""; meaning04 = "";
//                    countSpace = 0;
//
//                    SharedPreferences pr = getSharedPreferences("pr", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = pr.edit();
//
//                    editor.putString("word", word.getTitle());
//                    editor.putString("wid", Long.toString(word.getId()));
////        editor.putString(tvWordMain.getText().toString(), tvMeaning01.getText().toString() + tvMeaning02.getText().toString() + tvMeaning03.getText().toString() + tvMeaning04.getText().toString());
//                    editor.putString(word.getTitle(), word.getContent());
//
//                    editor.commit();
//
//                    for(int i = 0; i < word.getContent().length(); i++) {
//                        meaning = "";
//
//                        if(word.getContent().charAt(i) == ' ') {
//                            meaning += word.getContent().charAt(i) + "\n\n";
//                            countSpace ++;
//                        } else {
//                            meaning += word.getContent().charAt(i);
//                        }
//
//                        if (countSpace <3) meaning01 += meaning;
//                        else if (countSpace >=3 && countSpace < 6) meaning02 += meaning;
//                        else if (countSpace >=6 && countSpace < 9) meaning03 += meaning;
//                        else if (countSpace >=9) meaning04 += meaning;
//                    }
//
//                    tvWordMain.setText(word.getTitle().toString());
//                    tvMeaning01.setText("\n\n" + meaning01);
//                    tvMeaning02.setText(meaning02);
//                    tvMeaning03.setText(meaning03);
//                    tvMeaning04.setText(meaning04);
//
//                } else {
//                    int StatusCode = response.code();
//                    Log.i(ApplicationController.TAG, "Status Code : " + StatusCode + " Error Message : " + response.errorBody());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Word> call, Throwable t) {
//                Log.i(ApplicationController.TAG, "Fail Message : " + t.getMessage());
//            }
//        });





    }

    public void onClick(View v) {
        Intent mIntent;

        switch(v.getId()) {
            case R.id.btnWrite:
                mIntent = new Intent(this, WritingActivity.class);
                mIntent.putExtra("word" , value);
                startActivity(mIntent);
                finish();


                break;
            case R.id.btnList:
                mIntent = new Intent(this, ListingActivity.class);
                startActivity(mIntent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            backPressCloseHandler.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.barbtn, menu);
        MenuItem item = menu.getItem(0);
        item.setTitle("담기");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.barBtn) {
            final LinearLayout dialogLayout = (LinearLayout) View.inflate(this, R.layout.dialog_saveword, null);

//            TextView title = new TextView(this);
//            title.setText(tvWordMain.getText());
//            title.setGravity(Gravity.CENTER);
//            title.setTextColor(Color.parseColor("#c4792f"));

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(tvWordMain.getText())
                    .setView(dialogLayout)
                    .show();

            //dialog.setCustomTitle(title);

            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.alpha = 50;
            dialog.getWindow().setAttributes(params);

            // DB 저장 부분
            SimpleDateFormat df2 = new SimpleDateFormat("YYYY.MM.dd", Locale.KOREA);
            String currentDateTimeString2 = df2.format(new Date());

            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues row = new ContentValues();
            row.put("date",currentDateTimeString2);
            row.put("word",tvWordMain.getText().toString());
            row.put("meaning", wordMeaning);

            db.insert(MyWordDBHelper.TABLE_NAME, null, row);
            helper.close();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent mIntent;

        int id = item.getItemId();

        if (id == R.id.todayWord) {
            // 현재페이지
        } else if (id == R.id.myWord) {
            mIntent = new Intent(this, MyWordActivity.class);
            startActivity(mIntent);
            finish();
        } else if (id == R.id.myRecord) {
            mIntent = new Intent(this, MyRecordActivity.class);
            startActivity(mIntent);
            finish();
        } else if (id == R.id.setting) {
            // 로그아웃
            auth.getInstance().signOut();
            Intent sign_intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(sign_intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "클릭", Toast.LENGTH_SHORT).show();

        }
    };
}
