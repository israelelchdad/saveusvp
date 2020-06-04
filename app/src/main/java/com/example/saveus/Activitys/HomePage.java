package com.example.saveus.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.saveus.Fragments.AddPlace;
import com.example.saveus.Fragments.HomeStart;
import com.example.saveus.Fragments.MyPlaces;
import com.example.saveus.Fragments.PersonalInformation;
import com.example.saveus.Fragments.PlacesAndMap;
import com.example.saveus.Fragments.ChangePlace;
import com.example.saveus.Objects.Place;
import com.example.saveus.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class HomePage extends AppCompatActivity implements PlacesAndMap.MoveToAddPlacenListener,AddPlace.updatePlace, MyPlaces.OnFragmentInteractionListener ,
        ChangePlace.OnFragmentInteractionListener, View.OnClickListener,PersonalInformation.OnFragmentInteractionListener {
    BottomNavigationView mybottomNavigation;
    HomeStart homeStart ;
    LinearLayout linearLayout;
    ImageView personalInformation;
    ImageView close;
    ArrayList<Place> myPlaces;
    public static String KEYOFPLACES ="myplaces";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mybottomNavigation = findViewById(R.id.hp_BottomNavigationView);
        linearLayout = findViewById(R.id.linear_tag);
        initializeMyPlaces();
        personalInformation = findViewById(R.id.ActivityHome_Personal_Information);
        personalInformation.setOnClickListener(this);
        close = findViewById(R.id.Ac_home_close_img);



        mybottomNavigation.setItemIconTintList(null);
        homeStart = new HomeStart();
        openFragment(HomeStart.newInstance(myPlaces));


        initLisitnerOfNavigation();

    }

    private void initializeMyPlaces() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(KEYOFPLACES, null);
        Type type = new TypeToken<ArrayList<Place>>() {}.getType();
        myPlaces = gson.fromJson(json,type);
        if(myPlaces == null){
            myPlaces = new ArrayList();

        }
        else {
            myPlaces = gson.fromJson(json,type);
        }
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
                   openFragment(HomeStart.newInstance(myPlaces));
                   break;
                case R.id.am_myplaces:
                    linearLayout.setVisibility(View.VISIBLE);
                    PlacesAndMap fragment = PlacesAndMap.newInstance(myPlaces);

                    openFragment(fragment);

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
    public void setMyPlace(Place myPlace) {
//        myPlaces.add(0,myPlace);
      int index=  addplaceToMyPlaces(myPlace);
        myPlaces.add(index,myPlace);
//        Collections.sort(myPlaces);
        setMyPlacesTosharedPreferences();



    }




    private int addplaceToMyPlaces(Place mPlace) {
        for (int i = 0; i <= myPlaces.size(); i++) {
            if( i == myPlaces.size()){

                return i;
            }
            if (mPlace.getYear() > myPlaces.get(i).getYear()
                    || mPlace.getYear() == myPlaces.get(i).getYear() && mPlace.getMounth() > myPlaces.get(i).getMounth()
                    || mPlace.getYear() == myPlaces.get(i).getYear() && mPlace.getMounth() == myPlaces.get(i).getMounth() && mPlace.getDay() > myPlaces.get(i).getDay()
                    || mPlace.getYear() == myPlaces.get(i).getYear() && mPlace.getMounth() == myPlaces.get(i).getMounth() && mPlace.getDay() == myPlaces.get(i).getDay() && mPlace.getHour() > myPlaces.get(i).getHour()

                    || mPlace.getYear() == myPlaces.get(i).getYear() && mPlace.getMounth() == myPlaces.get(i).getMounth() && mPlace.getDay() == myPlaces.get(i).getDay() && mPlace.getHour() == myPlaces.get(i).getHour() && mPlace.getMinute() > myPlaces.get(i).getMinute()
                    || mPlace.getYear() == myPlaces.get(i).getYear() && mPlace.getMounth() == myPlaces.get(i).getMounth() && mPlace.getDay() == myPlaces.get(i).getDay() && mPlace.getHour() == myPlaces.get(i).getHour() && mPlace.getMinute() == myPlaces.get(i).getMinute() && mPlace.getSecends() > myPlaces.get(i).getSecends())
            {

                return i;

            }

        }
        return  0;
    }

    private void setMyPlacesTosharedPreferences() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(myPlaces);

        editor.putString(KEYOFPLACES, json);
        editor.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public void goToPlaceAndMap() {

        openFragment(PlacesAndMap.newInstance(myPlaces));

    }

    @Override
    public void addAndChangeMyPlacesInSharedPrefs(ArrayList<Place> myPlaces) {
        this.myPlaces = myPlaces;
        setMyPlacesTosharedPreferences();

    }


    @Override
    public void onItemClickPlace(Place myPlace) {
        linearLayout.setVisibility(View.GONE);
        openFragment(ChangePlace.newInstance(myPlaces,myPlace));

    }

    @Override
    public void onClick(View v) {
        close.setVisibility(View.VISIBLE);
        personalInformation.setVisibility(View.GONE);
        openFragment(PersonalInformation.newInstance());

    }
}
