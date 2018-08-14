package com.dongduk.hangeul.hangeul_test1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritingActivity extends BaseActivity {

    private NetworkService networkService;

    LinearLayout dialogLayout;
    AlertDialog dialog;

    TextView tvWordWriting, tvMeaningWriting;
    EditText etWriting;
    String wid;
    String uid;
    Word word;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private DatabaseReference mDatabase;

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


        mDatabase = FirebaseDatabase.getInstance().getReference();


        ApplicationController application = ApplicationController.getInstance();
        //application.buildNetworkService("ab2a6169.ngrok.io");
        application.buildNetworkService("54.237.215.221", 8000);
        networkService = ApplicationController.getInstance().getNetworkService();

        tvWordWriting = (TextView) findViewById(R.id.tvWordWriting);
        tvMeaningWriting = (TextView) findViewById(R.id.tvMeaningWriting);
        etWriting = (EditText)findViewById(R.id.et_writing);

        SharedPreferences pr = getSharedPreferences("pr", MODE_PRIVATE);
        tvWordWriting.setText(pr.getString("word", ""));
        tvMeaningWriting.setText(pr.getString(tvWordWriting.getText().toString(), ""));

        wid = pr.getString("wid", "");

        Intent intent = getIntent();
        //uid = intent.getStringExtra("user");
        word = (Word)intent.getSerializableExtra("word");
        Log.d("WritingActivity", "word : " + word.getTitle());


        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url


            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            uid = user.getUid();
        }

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
                dialog = new AlertDialog.Builder(this)
                        .setMessage("글을 저장합니다")

                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                post_writing();
                                submitPost();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();

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
    private void submitPost() {

        final Post post = new Post();

        post.setUid(uid);
        post.setWid(wid);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String currentDateTimeString = df.format(new Date());

        post.setDate(currentDateTimeString);
        post.setWriting(etWriting.getText().toString());


        // Disable button so there are no multi-posts
//        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        // [START single_value_read]
        mDatabase.child("users").child(uid).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Log.e("WritingActivity", "User " + uid + " is unexpectedly null");
                            Toast.makeText(WritingActivity.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            writeNewPost(post);

                            Intent mIntent;
                            mIntent = new Intent(WritingActivity.this, MainActivity.class);
                            startActivity(mIntent);
                            finish();
                        }

                        // Finish this Activity, back to the stream
//                        setEditingEnabled(true);
//                        finish();
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("WritingActivity", "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
//                        setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });
        // [END single_value_read]
    }

//    private void setEditingEnabled(boolean enabled) {
//        mTitleField.setEnabled(enabled);
//        mBodyField.setEnabled(enabled);
//        if (enabled) {
//            mSubmitButton.setVisibility(View.VISIBLE);
//        } else {
//            mSubmitButton.setVisibility(View.GONE);
//        }
//    }

    public void onClick(View v){


        switch (v.getId()) {
            case R.id.btn_cancel:
                dialog.dismiss();
                break;
            case R.id.btn_end_writing:
                Intent mIntent;
                mIntent = new Intent(WritingActivity.this, MainActivity.class);
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

    public void post_writing(){
        //POST
        Post post = new Post();


        post.setUid(uid);
        post.setWid(wid);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String currentDateTimeString = df.format(new Date());

        post.setDate(currentDateTimeString);
        post.setWriting(etWriting.getText().toString());

        Log.d("datetime", uid.toString());

        Call<Post> postCall = networkService.post_writing(post);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if( response.isSuccessful()) {
                    Intent mIntent;
                    mIntent = new Intent(WritingActivity.this, MainActivity.class);
                    startActivity(mIntent);
                    finish();

                } else {
                    int StatusCode = response.code();
                    Log.i(ApplicationController.TAG, "Status Code : " + StatusCode);
                }

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i(ApplicationController.TAG, "Fail Message : " + t.getMessage());
            }
        });
    }

    private void writeNewPost(Post post) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("posts").push().getKey();
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + post.getUid() + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
}
