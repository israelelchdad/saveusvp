package com.example.saveus.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class AdapterViewPagerOnB extends FragmentStatePagerAdapter {
    ArrayList<Fragment>myListFragmentsOnBoarding;

    public AdapterViewPagerOnB(FragmentManager fm, ArrayList<Fragment>myListFragmentsOnBoarding) {
        super(fm);
        this.myListFragmentsOnBoarding  =myListFragmentsOnBoarding;
    }


    @Override
    public Fragment getItem(int position) {
        return myListFragmentsOnBoarding.get(position);
    }

    @Override
    public int getCount() {
        return myListFragmentsOnBoarding.size();
    }
}
