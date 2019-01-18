package com.raul.ipartek.hellotoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount;
    private TextView textSumView;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textSumView =  findViewById(R.id.textView);

    }

    public void showToast(View view) {
        toast = Toast.makeText(this,R.string.toast_message,Toast.LENGTH_LONG);
        toast.show();
    }

    public void countUp(View view) {

        mCount++;
        if (textSumView != null)
            textSumView.setText(String.valueOf(mCount));
    }

}
