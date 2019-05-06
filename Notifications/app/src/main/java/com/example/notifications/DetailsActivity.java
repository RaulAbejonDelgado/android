package com.example.notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.textTitle)
    TextView editTextTitle;
    @BindView(R.id.textMessage)
    TextView editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setIntentValues();
    }

    private void setIntentValues(){
        if(getIntent() != null) {
            editTextTitle.setText(getIntent().getStringExtra("title"));
            editTextMessage.setText(getIntent().getStringExtra("message"));
        }
    }
}
