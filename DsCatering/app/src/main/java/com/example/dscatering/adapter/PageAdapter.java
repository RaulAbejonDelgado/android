package com.example.dscatering.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dscatering.fragments.FirstFragment;
import com.example.dscatering.fragments.SecondFragment;
import com.example.dscatering.fragments.ThirdFragment;

public class PageAdapter extends FragmentStatePagerAdapter {

    private int nTabs;

    public PageAdapter(FragmentManager fm, int tab) {
        super(fm);
        this.nTabs = tab;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();


        }
        return null;
    }


    @Override
    public int getCount() {
        return nTabs;
    }
}
