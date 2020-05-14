package com.example.saveus.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.saveus.Fragments.AddPlace;
import com.example.saveus.Fragments.HomeStart;
import com.example.saveus.Fragments.OnBoarding1;
import com.example.saveus.Fragments.OnBoarding2;
import com.example.saveus.Fragments.OnBoarding3;
import com.example.saveus.Fragments.PlacesAndMap;
import com.example.saveus.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity implements PlacesAndMap.MoveToAddPlacenListener,AddPlace.OnFragmentInteractionListener {
    BottomNavigationView mybottomNavigation;
    HomeStart homeStart ;
    LinearLayout linearLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mybottomNavigation = findViewById(R.id.hp_BottomNavigationView);
        linearLayout = findViewById(R.id.linear_tag);
        mybottomNavigation.setItemIconTintList(null);
        homeStart = new HomeStart();
        openFragment(homeStart);



        initLisitnerOfNavigation();

    }

    private void initLisitnerOfNavigation() {
        mybottomNavigation.setOnNavigationItemReselectedListener(navigationItemSelectedListener);
    }

    BottomNavigationView.OnNavigationItemReselectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.am_main:
                    linearLayout.setVisibility(View.VISIBLE);
                   openFragment(homeStart);
                   break;
                case R.id.am_myplaces:
                    linearLayout.setVisibility(View.VISIBLE);
                    openFragment(new PlacesAndMap(getParent()));
                    break;
                case R.id.am_notifacation:
//                    openFragment(onBoarding3);



            }
        }

    };
    public void openFragment(Fragment myfragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.homePage_fremlayot,myfragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void moveToAddPlace() {
        linearLayout.setVisibility(View.GONE);
        openFragment(new AddPlace());

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
