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







    public HomeStart() {


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_home_start, container, false);
        initViews(view);
        myPlace = new Place();
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

       LatLng jeruslem = new LatLng(31.771959,35.217018 );
        mMap.addMarker(new MarkerOptions().position(jeruslem).title("Marker in jeruslem"));

      mMap.moveCamera(CameraUpdateFactory.newLatLng(jeruslem));


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
            latitude = 0.0;
            longitude = 0.0;

        }

        chengBeckgroundViews();
         mMap.setMyLocationEnabled(true);
         //To add marker
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
        myPlace.setYear(Calendar.getInstance().get(Calendar.YEAR));
        myPlace.setMounth(Calendar.getInstance().get(Calendar.MONTH)+1);
        myPlace.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        myPlace.setStartTime(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
        myPlace.setLatitude(latitude);
        myPlace.setLongitude(longitude);

        List<Address> addresses;
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

        addresses = geocoder.getFromLocation(latitude, longitude, 1);
        myPlace.setAdressOfUser( addresses.get(0).getAddressLine(0));
        myPlace.setAdressOfUser(addresses.get(0).getSubThoroughfare());
        myPlace.setCityOfUser(addresses.get(0).getLocality());




    }
    private void setEndMyPlace() {
        myPlace.setEndTime(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
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

//package com.example.saveus.wall_screen;
//
//        import android.Manifest;
//        import android.content.Context;
//        import android.content.pm.PackageManager;
//        import android.graphics.Bitmap;
//        import android.graphics.Canvas;
//        import android.graphics.drawable.Drawable;
//        import android.location.Address;
//        import android.location.Geocoder;
//        import android.location.Location;
//        import android.location.LocationManager;
//        import android.os.Bundle;
//
//        import androidx.annotation.NonNull;
//        import androidx.annotation.Nullable;
//        import androidx.core.app.ActivityCompat;
//        import androidx.core.content.ContextCompat;
//        import androidx.fragment.app.Fragment;
//
//        import android.os.SystemClock;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.Chronometer;
//        import android.widget.RelativeLayout;
//        import android.widget.Toast;
//
//        import com.example.saveus.R;
//        import com.google.android.gms.maps.CameraUpdateFactory;
//        import com.google.android.gms.maps.GoogleMap;
//        import com.google.android.gms.maps.MapView;
//        import com.google.android.gms.maps.MapsInitializer;
//        import com.google.android.gms.maps.OnMapReadyCallback;
//        import com.google.android.gms.maps.model.BitmapDescriptor;
//        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//        import com.google.android.gms.maps.model.CameraPosition;
//        import com.google.android.gms.maps.model.LatLng;
//        import com.google.android.gms.maps.model.MarkerOptions;
//
//        import java.util.List;
//        import java.util.Locale;
//        import java.util.Objects;
//
//
//public class WallFragment extends Fragment {
//
//
//    public static final String TAG = WallFragment.class.getSimpleName();
//    private OnWallFragmentListener mListener;
//    private MapView mMapView;
//    private GoogleMap googleMap;
//    private LocationManager mLocationManager;
//    private Location gps_loc;
//    private Location network_loc;
//    private Location final_loc;
//    private double latitude;
//    private double longitude;
//    private RelativeLayout mStartBtnRL;
//    private RelativeLayout mStopBtnRL;
//    private Chronometer mChronometerTimerCM;
//    private String userCountry;
//    private String userAddress;
//    private boolean isFirstTime = true;
//    private View mRootView;
//
//    public WallFragment() {
//        // Required empty public constructor
//    }
//
//    public static WallFragment newInstance() {
//        WallFragment fragment = new WallFragment();
//        Bundle args = new Bundle();
//
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//
//        }
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        if (mRootView == null) {
//            mRootView = inflater.inflate(R.layout.fragment_wall, container, false);
//        }
//        return mRootView;
//
//
//    }
//
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//        if (mMapView != null) {
//
//            isFirstTime = false;
//        }
//
//        initViews();
//        initListeners();
//        getLocation(savedInstanceState);
//
//
//    }
//
//
//    private void getLocation(Bundle savedInstanceState) {
//
//
//        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 1);
//
//        } else {
//
//
//            mMapView.onCreate(savedInstanceState);
//            mMapView.onResume();
//            try {
//                MapsInitializer.initialize(getActivity().getApplicationContext());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//            mMapView.getMapAsync(new OnMapReadyCallback() {
//                @Override
//                public void onMapReady(GoogleMap mMap) {
//
//
//                    try {
//
//                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//                            return;
//                        }
//                        gps_loc = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                        network_loc = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    if (gps_loc != null) {
//                        final_loc = gps_loc;
//                        latitude = final_loc.getLatitude();
//                        longitude = final_loc.getLongitude();
//                    } else if (network_loc != null) {
//                        final_loc = network_loc;
//                        latitude = final_loc.getLatitude();
//                        longitude = final_loc.getLongitude();
//                    } else {
//                        latitude = 0.0;
//                        longitude = 0.0;
//                    }
//
//
//                    googleMap = mMap;
//                    googleMap.setMyLocationEnabled(true);
//                    //To add marker
//                    LatLng myLocation = new LatLng(latitude, longitude);
//                    googleMap.addMarker(new MarkerOptions().position(myLocation).title("Title").snippet("Marker Description"));
//                    // For zooming functionality
//
//
//                    addOtherUsersMarkers(googleMap);
//                    addMyMarkers(googleMap);
//
//                    if (isFirstTime) {
//
//                        CameraPosition cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(13).build();
//                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//
//                    } else {
//
//                        CameraPosition cameraPosition = new CameraPosition.Builder().target(myLocation).zoom(13).build();
//                        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//
//                    }
//
//
//                }
//            });
//        }
//
//    }
//
//
//    private void addMyMarkers(GoogleMap googleMap) {
//
//        // latitude and longitude
//        double latitude = 31.806253;
//        double longitude = 35.103138;
//
//        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps");
//        marker.icon(bitmapDescriptorFromVector(getContext(), R.drawable.my_marker_icon));
//        googleMap.addMarker(marker);
//
//
//    }
//
//
//    private void addOtherUsersMarkers(GoogleMap googleMap) {
//
//        // latitude and longitude
//        double latitude = 31.803964;
//        double longitude = 35.100004;
//
//        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps");
//        marker.icon(bitmapDescriptorFromVector(getContext(), R.drawable.others_marker_icon));
//        googleMap.addMarker(marker);
//
//
//    }
//
//
//
//
//    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
//        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
//        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
//        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        vectorDrawable.draw(canvas);
//        return BitmapDescriptorFactory.fromBitmap(bitmap);
//    }
//
//
//
//
//    private void initViews() {
//
//        mMapView = (MapView) Objects.requireNonNull(getView()).findViewById(R.id.mapView);
//        mLocationManager = (LocationManager) Objects.requireNonNull(getContext()).getSystemService(Context.LOCATION_SERVICE);
//        mStartBtnRL = getView().findViewById(R.id.WF_start_btn_RL);
//        mStopBtnRL = getView().findViewById(R.id.WF_stop_btn_RL);
//        mChronometerTimerCM = (Chronometer) getView().findViewById(R.id.WF_chronometer_timer_CM);
//
//    }
//
//
//    private void initListeners() {
//
//        mStartBtnRL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                mStartBtnRL.setVisibility(View.GONE);
//                mStopBtnRL.setVisibility(View.VISIBLE);
//
//                mChronometerTimerCM.setBase(SystemClock.elapsedRealtime());
//                setStopBtnTimer();
//
//            }
//        });
//
//        mStopBtnRL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                showElapsedTime();
//                mChronometerTimerCM.setBase(SystemClock.elapsedRealtime());
//
//                mStopBtnRL.setVisibility(View.GONE);
//                mStartBtnRL.setVisibility(View.VISIBLE);
//
//
//            }
//        });
//    }
//
//
//    private void showElapsedTime() {
//
//        long elapsedMillis = SystemClock.elapsedRealtime() - mChronometerTimerCM.getBase();
//
//        try {
//
//            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
//            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
//            if (addresses != null && addresses.size() > 0) {
//                userCountry = addresses.get(0).getCountryName();
//                userAddress = addresses.get(0).getAddressLine(0);
//            } else {
//                userCountry = "Unknown";
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        Toast.makeText(getContext(), "זמן: " + elapsedMillis + "  milliseconds\n " + "מיקום: " + userCountry + ", " + userAddress,
//                Toast.LENGTH_LONG).show();
//    }
//
//
//    private void setStopBtnTimer() {
//
//        mChronometerTimerCM.start();
//
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            return;
//        }
//
//        mMapView.onResume();
//
//    }
//
//
//    @Override
//    public void onPause() {
//        super.onPause();
//
//        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            return;
//        }
//
//        mMapView.onPause();
//
//    }
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        if (mMapView != null) {
//
//            mMapView.onDestroy();
//        }
//    }
//
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//
//        if (mMapView != null) {
//
//            mMapView.onLowMemory();
//        }
//    }
//
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnWallFragmentListener) {
//            mListener = (OnWallFragmentListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//
//    public interface OnWallFragmentListener {
//
//    }
//}


