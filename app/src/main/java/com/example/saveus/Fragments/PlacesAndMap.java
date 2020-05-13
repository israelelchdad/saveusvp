package com.example.saveus.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saveus.Adapters.AdapterVpPlaceAndMap;
import com.example.saveus.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesAndMap extends Fragment {
   private ViewPager myviewpajer;
   AdapterVpPlaceAndMap madapterVpPlaceAndMap;
    ArrayList<Fragment>myListFragmentsPlaceAndMap= new ArrayList<>();


    public PlacesAndMap() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_places_and_map, container, false);
        myviewpajer = view.findViewById(R.id.place_map_vp);
        madapterVpPlaceAndMap = new AdapterVpPlaceAndMap(getChildFragmentManager(),listFragmentsPlaceMp(),view.getContext());
        myviewpajer.setAdapter(madapterVpPlaceAndMap);
        TabLayout tabLayout= view.findViewById(R.id.tab_layout_places_map);

        tabLayout.setupWithViewPager(myviewpajer);
        return view;
    }

    private ArrayList<Fragment> listFragmentsPlaceMp() {

        myListFragmentsPlaceAndMap.add(new MyPlaces());
        myListFragmentsPlaceAndMap.add(new Map());


        return myListFragmentsPlaceAndMap;
    }

}
