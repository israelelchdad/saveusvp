package com.example.saveus.Fragments;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.saveus.Adapters.AdapterVpPlaceAndMap;
import com.example.saveus.Objects.Place;
import com.example.saveus.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesAndMap extends Fragment implements MyPlaces.OnFragmentInteractionListener, View.OnClickListener{
    private static String Keyplaces= "places";
    private ViewPager myviewpajer;
   AdapterVpPlaceAndMap madapterVpPlaceAndMap;
    ArrayList<Fragment>myListFragmentsPlaceAndMap= new ArrayList<>();
    private LinearLayout mlinearLayout;
    private MoveToAddPlacenListener moveToAddPlacenListener;
    ArrayList<Place> places = new ArrayList<>();



    public static PlacesAndMap newInstance(ArrayList<Place> myPlaces) {
        PlacesAndMap fragment = new PlacesAndMap();
        Bundle args = new Bundle();
        args.putParcelableArrayList(Keyplaces, myPlaces);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            places = getArguments().getParcelableArrayList(Keyplaces);
            int a = 6;
            int b =6;

        }
    }



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
        mlinearLayout = view.findViewById(R.id.f_map_add);
        mlinearLayout.setOnClickListener(this);


        tabLayout.setupWithViewPager(myviewpajer);
        return view;
    }

    private ArrayList<Fragment> listFragmentsPlaceMp() {

        myListFragmentsPlaceAndMap.add(MyPlaces.newInstance(places));
        myListFragmentsPlaceAndMap.add(Map.newInstance(places));


        return myListFragmentsPlaceAndMap;
    }

    @Override
    public void onClick(View v) {
        moveToAddPlacenListener.moveToAddPlace();

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PlacesAndMap.MoveToAddPlacenListener) {
            moveToAddPlacenListener = (MoveToAddPlacenListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public interface MoveToAddPlacenListener {

        void moveToAddPlace();
    }

}
