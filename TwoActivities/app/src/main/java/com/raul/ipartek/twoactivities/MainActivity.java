package com.raul.ipartek.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =  MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE =
            "com.raul.ipartek.twoactivities.extra.MESSAGE";
    public static final String REPLY_VISIBLE = "reply_visible";
    public static final String REPLY_TEXT = "reply_text";
    private EditText mMessageEditText;
    public static final int TEXT_REQUEST = 1;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "*********");
        Log.d(LOG_TAG, "onCreate - MainActivity");

        mMessageEditText = findViewById(R.id.editText);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);

        // Restore the state.
        if (savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean(REPLY_VISIBLE);

            if (isVisible) {
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
                mReplyTextView.setVisibility(View.VISIBLE);

            }
        }
    }

    public void launchSecondActivity(View view) {
    //le pasamos los datos a la segunda actividad
        Log.d(LOG_TAG, "Button clicked!");

        Intent intent = new Intent(this,SecondActivity.class);

        String message = mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivityForResult(intent, TEXT_REQUEST);

        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * Guardamos los datos
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mReplyHeadTextView.getVisibility() == View.VISIBLE) {
            outState.putBoolean(REPLY_VISIBLE, true);
            //Guardamos el valor del campo en el cambio de estado
            outState.putString(REPLY_TEXT,mReplyTextView.getText().toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "*********************************************");
        Log.d(LOG_TAG, "onStart - MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "*********************************************");
        Log.d(LOG_TAG, "onStop - MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "*********************************************");
        Log.d(LOG_TAG, "onPause - MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "*********************************************");
        Log.d(LOG_TAG, "onResume - MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "*********************************************");
        Log.d(LOG_TAG, "onDestroy - MainActivity");
    }
}
