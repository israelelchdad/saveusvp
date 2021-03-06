package com.example.saveus.Fragments;


import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saveus.Objects.Place;
import com.example.saveus.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Map extends Fragment implements OnMapReadyCallback,View.OnClickListener {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private static String KeyMPlace ="MYPLACE";
    private ArrayList<Place> places =new ArrayList<>();
    LatLng location;


    public static Map newInstance(ArrayList<Place> myPlaces) {
        Map fragment = new Map();
        Bundle args = new Bundle();
        args.putParcelableArrayList(KeyMPlace, myPlaces);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            places = getArguments().getParcelableArrayList(KeyMPlace);
            int a =6;
            int b =6;

        }
    }








    public Map() {


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_map, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        initMapFragment();
        return view;
    }

    private void initMapFragment() {
        if (mapFragment == null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map,mapFragment).commit();

        }
        mapFragment.getMapAsync(this);
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(14f);
        setMarkersOfUsers(mMap);




    }

    private void setMarkersOfUsers(GoogleMap googleMap) {
        int lengthPlaces = places.size();
        if(lengthPlaces>0){
            for (int i = 0; i < places.size() ; i++) {
                if(places.get(i).getLatitude()!=null){
                     location = new LatLng(places.get(i).getLatitude(),places.get(i).getLongitude() );
                    googleMap.addMarker(new MarkerOptions().position(location).title("Marker in jeruslem"));

                }

                if(i==0){
                    if(location!=null){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

                    }


                }

            }

            }else {
            LatLng jeruslem = new LatLng(31.78573509999,35.212945 );

            mMap.moveCamera(CameraUpdateFactory.newLatLng(jeruslem));


        }
    }


    @Override
    public void onClick(View v) {



    }

}








