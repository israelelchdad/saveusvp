
package com.example.saveus.Fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saveus.Objects.Place;
import com.example.saveus.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;
import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeStart extends Fragment implements OnMapReadyCallback,View.OnClickListener {

    private View myViewClicGetLocation;
    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private View viewOut;
    private View viewMiddle;

    private View viewInto;
    private TextView textCom;
    private TextView textStartRegis;
    private Chronometer mchronometer;
    private long allTime;
    private LocationManager  mLocationManager;
    private Double latitude;
    private double longitude;
    private Location final_loc;
    private Location gps_loc = null;
    private boolean isStart = true;
    private Place myPlace;
    private AddPlace.updatePlace mListener;
    private static String KeyMPla ="MYPLA";
    private ArrayList<Place> places =new ArrayList<>();


    public static HomeStart newInstance(ArrayList<Place> myPlaces) {
        HomeStart fragment = new HomeStart();
        Bundle args = new Bundle();
        args.putParcelableArrayList(KeyMPla, myPlaces);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            places = getArguments().getParcelableArrayList(KeyMPla);
            int a =6;
            int b =6;

        }
    }







    public HomeStart() {


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_home_start, container, false);
        initViews(view);

        myViewClicGetLocation = view.findViewById(R.id.f_home_start_com_into);
        myViewClicGetLocation.setOnClickListener(this);
        mLocationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
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

    private void initViews(View view) {
        viewOut = view.findViewById(R.id.f_home_start_com_out);
        viewMiddle =view.findViewById(R.id.f_home_start_com_middle);
        viewInto =view.findViewById(R.id.f_home_start_com_into);
        textCom = view.findViewById(R.id.f_home_start_text_come);
        textStartRegis =view.findViewById(R.id.f_home_start_text_start_regis);
        mchronometer = view.findViewById(R.id.chronometer1);


    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddPlace.updatePlace) {
            mListener = (AddPlace.updatePlace) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(14f);
        setMarkersOfUsers(mMap);



    }

    private void setMarkersOfUsers(GoogleMap mMap) {
        int sizePlaces = places.size();
        if(sizePlaces >0){
            for (int i = 0; i <places.size() ; i++) {
                LatLng location = new LatLng(places.get(i).getLatitude(),places.get(i).getLongitude() );
                mMap.addMarker(new MarkerOptions().position(location).title("Marker in "+ location));
                if(i==0){
                    LatLng mlatLng = new LatLng(places.get(i).getLatitude(),places.get(i).getLongitude() );

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(mlatLng));


                }

            }

        }

        else {
            LatLng jeruslem = new LatLng(31.78573509999,35.217018 );

            mMap.moveCamera(CameraUpdateFactory.newLatLng(jeruslem));


        }
    }


    @Override
    public void onClick(View v) {
        if (!isStart){
            try {
                chengBeckgroundViews();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
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

        try {
            getLocation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get access to location permission
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        getLocation();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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



    @SuppressLint("MissingPermission")

    private void getLocation() throws IOException {
        gps_loc = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (gps_loc != null) {
            final_loc = gps_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();

        }
        else {
            latitude =31.78573509999 ;
            longitude = 35.217018;
        }

        chengBeckgroundViews();
         mMap.setMyLocationEnabled(true);
         //To add marker
        addMarkerAndMoveLocation();

    }

    private void addMarkerAndMoveLocation() {
        LatLng myLocation = new LatLng(latitude, longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        mMap.addMarker(new MarkerOptions().position(myLocation).title("Title").snippet("Marker Description"));
    }

    private void chengBeckgroundViews() throws IOException {
        if(isStart){
            isStart = false;
            viewOut.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.out_red ));
            viewMiddle.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.middle_red ));
            viewInto.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.into_red));
            setstratMyPlace();
            textCom.setText(R.string.f_start_stop);
            textStartRegis.setVisibility(View.GONE);
            mchronometer.setVisibility(View.VISIBLE);
            mchronometer.setBase(SystemClock.elapsedRealtime());
            mchronometer.start();


        }else {
            isStart = true;
            viewOut.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.circle_butten_com_out ));
            viewMiddle.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.circle_butten_com_middle));
            viewInto.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.circle_butten_com_into));
            textCom.setText(R.string.f_homestart_icoming);
            setEndMyPlace();
            setTime();
            addMyplaceToMyPlaces();
            mchronometer.setVisibility(View.GONE);
            textStartRegis.setVisibility(View.VISIBLE);


        }



    }




    private void setstratMyPlace() throws IOException {
        myPlace = new Place();
        myPlace.setYear(Calendar.getInstance().get(Calendar.YEAR));
        myPlace.setMounth(Calendar.getInstance().get(Calendar.MONTH)+1);
        myPlace.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        myPlace.setHour(Calendar.getInstance().get(Calendar.HOUR));
        myPlace.setMinute(Calendar.getInstance().get(Calendar.MINUTE));
        myPlace.setSecends(Calendar.getInstance().get(Calendar.SECOND));
//        myPlace.setStartTime(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
        myPlace.setLatitude(latitude);
        myPlace.setLongitude(longitude);

        List<Address> addresses;
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            myPlace.setAdressOfUser( addresses.get(0).getAddressLine(0));
            myPlace.setAdressOfUser(addresses.get(0).getSubThoroughfare());
            myPlace.setCityOfUser(addresses.get(0).getLocality());
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    private void setEndMyPlace() {

//        myPlace.setEndTime(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
        myPlace.setEndTime(AddPlace.setTime(Calendar.getInstance().get(Calendar.HOUR))+":"+AddPlace.setTime( Calendar.getInstance().get(Calendar.MINUTE)));
        myPlace.setEndsecends(Calendar.getInstance().get(Calendar.SECOND));

    }


    private void setTime() {
        allTime = SystemClock.elapsedRealtime() - mchronometer.getBase();
        int h   = (int)(allTime /3600000);
        int m = (int)(allTime - h*3600000)/60000;
        int s= (int)(allTime - h*3600000- m*60000)/1000 ;
        String t = (h < 10 ? "0"+h: h)+":"+(m < 10 ? "0"+m: m)+":"+ (s < 10 ? "0"+s: s);
        myPlace.setAllTime(t);
        mchronometer.setBase(SystemClock.elapsedRealtime());


    }
    private void addMyplaceToMyPlaces() {

        mListener.setMyPlace(myPlace);
        int a= 5;
    }
}


