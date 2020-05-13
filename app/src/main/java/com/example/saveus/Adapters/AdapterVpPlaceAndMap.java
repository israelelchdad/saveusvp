package com.example.saveus.Adapters;

import android.content.Context;
import android.content.res.Resources;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.saveus.R;

import java.util.ArrayList;

public class AdapterVpPlaceAndMap extends FragmentStatePagerAdapter {
    Context context;

    ArrayList<Fragment> myListFragmentsPlaceAndMap;


    public AdapterVpPlaceAndMap(FragmentManager fm, ArrayList<Fragment> myListFragmentsPlaceAndMap,Context context) {
        super(fm);
        this.myListFragmentsPlaceAndMap = myListFragmentsPlaceAndMap;
        this.context = context;

    }






    @Override
    public Fragment getItem(int position) {
        return myListFragmentsPlaceAndMap.get(position);
    }

    @Override
    public int getCount() {
        return myListFragmentsPlaceAndMap.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = context.getString(R.string.f_placemap_myplaces);
        }
        else if (position == 1)
        {
            title = context.getString(R.string.f_placemap_onmap);

        }


        return title;
    }
}
