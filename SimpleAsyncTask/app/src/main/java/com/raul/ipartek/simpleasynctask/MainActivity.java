package com.raul.ipartek.simpleasynctask;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int contador = 0;
    private TextView mTextView;
    private static final String TEXT_STATE = "currentText";
    private ProgressBar mProgresBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView1);
        mProgresBar = findViewById(R.id.progressBar);
    }

    public void startTask(View view){

        // Put a message in the text view
        mTextView.setText(R.string.napping);
        // Start the AsyncTask.
        new MyAsyncTask(mTextView, mProgresBar, ++contador).execute();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
}
