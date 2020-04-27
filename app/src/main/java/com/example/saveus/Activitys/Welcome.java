package com.example.saveus.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.saveus.Adapters.AdapterViewPagerOnB;
import com.example.saveus.Fragments.LoginF;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        myviewpajer = findViewById(R.id.aw_vp);
        myadapterviewpager = new AdapterViewPagerOnB(getSupportFragmentManager(),listFragmentsOnBoarding());
        myviewpajer.setAdapter(myadapterviewpager);

    }

    private ArrayList<Fragment> listFragmentsOnBoarding() {

        myListFragmentsOnBoarding.add(onBoarding1);
        myListFragmentsOnBoarding.add(onBoarding2);
        myListFragmentsOnBoarding.add(onBoarding3);
        myListFragmentsOnBoarding.add(new LoginF());
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
