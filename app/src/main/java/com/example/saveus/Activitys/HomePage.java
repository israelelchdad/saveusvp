package com.example.saveus.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.saveus.Fragments.HomeStart;
import com.example.saveus.Fragments.OnBoarding1;
import com.example.saveus.Fragments.OnBoarding2;
import com.example.saveus.Fragments.OnBoarding3;
import com.example.saveus.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {
    BottomNavigationView mybottomNavigation;
    HomeStart homeStart ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mybottomNavigation = findViewById(R.id.hp_BottomNavigationView);
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
                   openFragment(homeStart);
                case R.id.am_myplaces:
//                    openFragment(onBoarding2);
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

}
