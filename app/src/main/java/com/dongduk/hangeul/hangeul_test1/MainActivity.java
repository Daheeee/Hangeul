package com.dongduk.hangeul.hangeul_test1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BackPressCloseHandler backPressCloseHandler;
    private FirebaseAuth auth;
    private FirebaseUser user;
    String email;
    TextView tvDateMain, tvWordMain, tvMeaning01, tvMeaning02, tvMeaning03, tvMeaning04, tvEmail;

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

        tvEmail.setText(email);

        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        String currentDateTimeString = df.format(new Date());
        tvDateMain.setText(currentDateTimeString);
    }

    public void onClick(View v) {
        Intent mIntent;

        switch(v.getId()) {
            case R.id.btnWrite:
                mIntent = new Intent(this, WritingActivity.class);
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
            AlertDialog dialog = new AlertDialog.Builder(this)
                    //.setTitle("온새미로")
                    .setView(dialogLayout)
                    .show();

            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.alpha = 50;
            dialog.getWindow().setAttributes(params);
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
