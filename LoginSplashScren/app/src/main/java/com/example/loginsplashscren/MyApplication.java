package com.example.loginsplashscren;

import android.app.Application;
import android.os.SystemClock;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(2000);
    }

}
