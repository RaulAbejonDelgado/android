package com.example.fragments.activities;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fragments.R;
import com.example.fragments.fragments.DataFragment;
import com.example.fragments.fragments.DetailsFragments;

public class MainActivity extends FragmentActivity implements DataFragment.DataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void sendData(String data) {
        //llamar al metodo redenrizar texto de detail
        // fragment pasandole el texo que recivimos por parametros

        DetailsFragments detailsFragments = (DetailsFragments) getSupportFragmentManager().findFragmentById(R.id.dataFragmentDetails);
        detailsFragments.renderText(data);

    }
}
