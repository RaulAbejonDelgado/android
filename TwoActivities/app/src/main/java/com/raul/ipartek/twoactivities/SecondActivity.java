package com.raul.ipartek.twoactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY ="com.raul.ipartek.twoactivities.extra.REPLY";
    private static final String LOG_TAG =  SecondActivity.class.getSimpleName();
    private EditText mReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "");
        Log.d(LOG_TAG, "onCreate - SecondActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mReply = findViewById(R.id.editText_second);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView tW= findViewById(R.id.textView1);
        tW.setText(message);

    }

    public void returnReply(View view) {

        String reply = mReply.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK,replyIntent);
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "*********************************************");
        Log.d(LOG_TAG, "onStart - SecondActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "*********************************************");
        Log.d(LOG_TAG, "onStop - SecondActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "*********************************************");
        Log.d(LOG_TAG, "onPause - SecondActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "*********************************************");
        Log.d(LOG_TAG, "onResume - SecondActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "*********************************************");
        Log.d(LOG_TAG, "onDestroy - SecondActivity");
    }

}
