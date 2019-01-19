package xom.raul.ipartek.hola_mundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import xom.raul.ipartek.hola_mundo2.R;


public class MainActivity extends AppCompatActivity {


    private static final String TAG =MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Log.d(TAG,"********Todo ok**********");
    }
}
