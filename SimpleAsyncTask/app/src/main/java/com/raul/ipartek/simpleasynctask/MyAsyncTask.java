package com.raul.ipartek.simpleasynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MyAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;
    private int id;

    MyAsyncTask(TextView tv,ProgressBar pb, int id) {
        this.id = id;
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(pb);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //super.onProgressUpdate(values);
        mProgressBar.get().setProgress(values[0]);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(10);
        int s = n * 150;

        for (int i = 10; i <= 100; i+=10) {


            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Milisegundos --> " + s);
            publishProgress(i);

        }
        System.out.println("termino");
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onPostExecute(String s) {


        mTextView.get().setText(s);
    }
}
