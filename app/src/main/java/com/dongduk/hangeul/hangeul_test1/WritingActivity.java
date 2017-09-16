package com.dongduk.hangeul.hangeul_test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WritingActivity extends BaseActivity {

    LinearLayout dialogLayout;
    AlertDialog dialog;

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

            case android.R.id.home:
                dialogLayout = (LinearLayout) View.inflate(this, R.layout.dialog_end_writing, null);
                dialog = new AlertDialog.Builder(this)
                        .setView(dialogLayout)
                        .show();

                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.width = WindowManager.LayoutParams.WRAP_CONTENT;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setAttributes(params);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        Intent mIntent;

        switch (v.getId()) {
            case R.id.btn_cancel:
                dialog.dismiss();
                break;
            case R.id.btn_end_writing:
                mIntent = new Intent(this, MainActivity.class);
                startActivity(mIntent);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        dialogLayout = (LinearLayout) View.inflate(this, R.layout.dialog_end_writing, null);
        dialog = new AlertDialog.Builder(this)
                .setView(dialogLayout)
                .show();

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(params);

    }
}
