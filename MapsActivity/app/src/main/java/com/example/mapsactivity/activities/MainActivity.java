package com.example.mapsactivity.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mapsactivity.R;
import com.example.mapsactivity.fragments.MapFragment;
import com.example.mapsactivity.fragments.MapV2Fragment;
import com.example.mapsactivity.fragments.WelcomeFragment;

public class MainActivity extends AppCompatActivity {

    Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            currentFragment = new WelcomeFragment();
            changeFragment(currentFragment);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.optionWelcome:

                currentFragment = new WelcomeFragment();

                break;

            case R.id.optionMaps:

                currentFragment = new MapFragment();

                break;

            case R.id.optionMapsV2:

                currentFragment = new MapV2Fragment();

        }

        changeFragment(currentFragment);

        return super.onOptionsItemSelected(item);
    }

    private void changeFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContent, fragment)
                .commit();
    }
}
