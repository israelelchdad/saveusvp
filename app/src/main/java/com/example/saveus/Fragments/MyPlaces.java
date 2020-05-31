package com.example.saveus.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saveus.Adapters.AdapterDaets;
import com.example.saveus.Adapters.AdapterRecyclerViewMyPlaces;
import com.example.saveus.Objects.MyDate;
import com.example.saveus.Objects.Place;
import com.example.saveus.R;

import java.util.ArrayList;


public class MyPlaces extends Fragment implements AdapterRecyclerViewMyPlaces.ItemClickListener{
    private static String KeyMPlaces ="MYPLACES";
    public ArrayList<Place> places = new ArrayList<>();
    public ArrayList <MyDate> myDates = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public MyPlaces() {
        // Required empty public constructor
    }



    public static MyPlaces newInstance(ArrayList<Place> myPlaces) {
        MyPlaces fragment = new MyPlaces();
        Bundle args = new Bundle();
        args.putParcelableArrayList(KeyMPlaces, myPlaces);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            places = getArguments().getParcelableArrayList(KeyMPlaces);
            setDates();


            int a =5;
            int b =6;

        }
    }

    private void setDates() {
        if(places.size()>0){
            myDates.add(setOneDates(places.get(0)));

        }
        if(places.size()==1){
            return;

        }

        for (int i = 1; i <= places.size(); i++) {

            if(places.get(i).getYear()== places.get(i-1).getYear()
                    &&places.get(i).getMounth()==places.get(i-1).getMounth()
                    &&places.get(i).getDay()==places.get(i-1).getDay()){

                myDates.get(myDates.size()).getPlaces().add(places.get(i));

            }else {
                myDates.add(setOneDates(places.get(i)));
            }



        }

    }

    private MyDate setOneDates(Place place) {
        MyDate mMyDate = new MyDate();
        mMyDate.setYear(place.getYear());
        mMyDate.setMounth(place.getMounth());
        mMyDate.setDay(place.getDay());
        ArrayList<Place> myplaces = new ArrayList<>();
        myplaces.add(place);
        mMyDate.setPlaces(myplaces);
        return mMyDate;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_places2, container, false);
//        initRecyrclerView(view);
        initRvDates(view);
        return view;

    }

    private void initRvDates(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.f_myplaces_RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterDaets myAdapter = new AdapterDaets(getContext(), myDates);
//        myAdapter.setClickListener(this);
        recyclerView.setAdapter(myAdapter);
    }

    private void initRecyrclerView(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.f_myplaces_RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterRecyclerViewMyPlaces  myAdapter = new AdapterRecyclerViewMyPlaces(getContext(),places,this );
        myAdapter.setClickListener(this);
        recyclerView.setAdapter(myAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public void onItemClick( int position) {
        if(mListener!=null){
            mListener.onItemClickPlace(places,position);

        }

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void onItemClickPlace(ArrayList<Place> myPlaces,int position);
    }
}
