package com.example.notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editText1)
    EditText editText;
    @BindView(R.id.editText2)
    EditText editText1;
    @BindView(R.id.switch1)
    Switch switch1;
//    @BindView(R.id.button)
//    Button button;

    private boolean isHighImportante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//
//            }
//        });
    }

    @OnClick(R.id.button)
    public void click(){
        sendNotification();
    }

    private void sendNotification() {
        Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();
    }

    @OnCheckedChanged(R.id.switch1)
    public void change(CompoundButton compoundButton, boolean isChecked){
        isHighImportante = isChecked;
        Toast.makeText(this, "Working -> " + isHighImportante, Toast.LENGTH_SHORT).show();
    }
}
