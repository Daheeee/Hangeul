package com.dongduk.hangeul.hangeul_test1;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CustomDialogActivity extends Dialog {

    private TextView tvWord;
    private Button btnSave;
    private String word;

    private  View.OnClickListener mClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        tvWord = (TextView)findViewById(R.id.tvWord);
        btnSave = (Button)findViewById(R.id.btnSave);
    }

    public CustomDialogActivity(Context context, String title, View.OnClickListener singleListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.word = title;
        this.mClickListener = singleListener;
    }
}
