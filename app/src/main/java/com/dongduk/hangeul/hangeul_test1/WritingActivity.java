package com.dongduk.hangeul.hangeul_test1;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WritingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvTitle.setText("");

        ViewStub stub = (ViewStub)findViewById(R.id.stub);
        stub.setLayoutResource(R.layout.activity_writing);
        stub.inflate();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.barbtn, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.barBtn:
                Toast.makeText(WritingActivity.this, "내마음속에 저장^,~", Toast.LENGTH_SHORT).show();
                return true;

//            case R.id.home:
//                //Toast.makeText(WritingActivity.this, "뒤로가기", Toast.LENGTH_SHORT).show();
//                final LinearLayout dialogLayout = (LinearLayout) View.inflate(this, R.layout.activity_custom_dialog, null);
//                AlertDialog dialog = new AlertDialog.Builder(this)
//                        //.setTitle("온새미로")
//                        .setView(dialogLayout)
//                        .show();
//                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
