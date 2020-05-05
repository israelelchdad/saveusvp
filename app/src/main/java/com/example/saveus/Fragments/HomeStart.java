package com.example.saveus.Fragments;


import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.saveus.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.BitSet;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeStart extends Fragment implements OnMapReadyCallback,View.OnClickListener {

    View myViewClicGetLocation;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
//    private  LocationListener mLocationListener;



    public HomeStart() {


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_home_start, container, false);
        myViewClicGetLocation = view.findViewById(R.id.f_home_start_onclick_location);
        myViewClicGetLocation.setOnClickListener(this);
//        initmLocationListener();


        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map,mapFragment).commit();

        }
        mapFragment.getMapAsync(this);
        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(1f);
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(31.771959,35.217018 );
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//
//       mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }

    @Override
    public void onClick(View v) {
//        v.setBackgroundResource(R.drawable.circle_butten_gray);
        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return ;
            }
        }

       getLocation();

    }



    //get access to location permission
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   getLocation();
                } else {
                    // Permission Denied
                    Toast.makeText( getContext(),"אם לא תאשר מיקום לא תוכל להשתמש באפליקציה" , Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void getLocation() {


    }
//    private void initmLocationListener() {
//        mLocationListener= new LocationListener() {
//            @Override
//            public void onLocationChanged(final Location location) {
//                Double B=location.getAltitude();
//                Double c=location.getLatitude();
//                Double e=location.getLongitude();
//                location.getLatitude();
//                //your code here
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//
//            }
//        };
//    }


}

