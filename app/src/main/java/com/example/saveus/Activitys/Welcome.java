package com.example.saveus.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.saveus.Adapters.AdapterViewPagerOnB;
import com.example.saveus.Fragments.OnBoarding1;
import com.example.saveus.Fragments.OnBoarding2;
import com.example.saveus.Fragments.OnBoarding3;
import com.example.saveus.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity implements OnBoarding1.OnFragmentInteractionListener,OnBoarding2.OnFragmentInteractionListener,OnBoarding3.OnFragmentInteractionListener {
    OnBoarding1 onBoarding1 = new OnBoarding1();
    OnBoarding2 onBoarding2 = new OnBoarding2();
    OnBoarding3 onBoarding3 = new OnBoarding3();
    ViewPager myviewpajer;
    ArrayList<Fragment>myListFragmentsOnBoarding= new ArrayList<>();
    AdapterViewPagerOnB myadapterviewpager;




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        myviewpajer = findViewById(R.id.aw_vp);
        myadapterviewpager = new AdapterViewPagerOnB(getSupportFragmentManager(),listFragmentsOnBoarding());
        myviewpajer.setAdapter(myadapterviewpager);
        TabLayout tabLayout = findViewById(R.id.tab_layout_Welcom);
        tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        tabLayout.setupWithViewPager(myviewpajer, true);
        setOnTuchOfViewPager();


//        myviewpajer.setCurrentItem(myadapterviewpager.getCount()-1);





    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnTuchOfViewPager() {
        myviewpajer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private boolean isMoveToNextActivity;


            @Override
            public void onPageScrolled(int position, float positionOffset, int i1) {

                if (position == 2 ) {
                    if (isMoveToNextActivity) {
                        moveToActivty();

                    }
                    isMoveToNextActivity = true;

                } else {
                    isMoveToNextActivity = false;;
                }

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    private void moveToActivty() {
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }


    private ArrayList<Fragment> listFragmentsOnBoarding() {

        myListFragmentsOnBoarding.add(onBoarding1);
        myListFragmentsOnBoarding.add(onBoarding2);
        myListFragmentsOnBoarding.add(onBoarding3);

        return myListFragmentsOnBoarding;
    }


}
