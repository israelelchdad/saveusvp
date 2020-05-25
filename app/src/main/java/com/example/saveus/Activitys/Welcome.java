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
        setOnTuchOfViewPager();


//        myviewpajer.setCurrentItem(myadapterviewpager.getCount()-1);





    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnTuchOfViewPager() {
        myviewpajer.setOnTouchListener(new View.OnTouchListener() {
            int downX, upX;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(myadapterviewpager.getCount() - 1 == myviewpajer.getCurrentItem()){
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        upX = (int) event.getX();

                        if (downX - upX > -100) {
                            moveToActivty();



                        }
                    }
                    return true;

                }

                return false;
            }
        });
    }

    private void moveToActivty() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }


    private ArrayList<Fragment> listFragmentsOnBoarding() {

        myListFragmentsOnBoarding.add(onBoarding1);
        myListFragmentsOnBoarding.add(onBoarding2);
        myListFragmentsOnBoarding.add(onBoarding3);

        return myListFragmentsOnBoarding;
    }
    @Override
    public void chengCorentListFragment(View view) {
        switch(view.getId()) {
            case R.id.f_boarding1_button:
               myviewpajer.setCurrentItem(0);
                break;
            case R.id.f_boarding2_button:
                myviewpajer.setCurrentItem(1);
                break;
            case R.id.f_boarding3_button:
                myviewpajer.setCurrentItem(2);
                break;
        }
    }
}
