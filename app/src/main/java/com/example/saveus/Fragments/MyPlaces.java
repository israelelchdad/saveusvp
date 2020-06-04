package com.example.saveus.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saveus.Adapters.AdapterDaets;
import com.example.saveus.Adapters.AdapterRecyclerViewMyPlaces;
import com.example.saveus.Objects.MyDate;
import com.example.saveus.Objects.Place;
import com.example.saveus.R;

import java.util.ArrayList;
import java.util.Calendar;


public class MyPlaces extends Fragment implements AdapterRecyclerViewMyPlaces.ItemClickListener,AdapterDaets.ButtonClickListener,View.OnClickListener {
    private static String KeyMPlaces ="MYPLACES";
    public ArrayList<Place> places = new ArrayList<>();
    public ArrayList <MyDate> myDates = new ArrayList<>();
    public View view;
    private TextView startDate;
    private TextView endDate;
    private ImageView imgStartDate;
    private ImageView imgEndDate;
    private Button show;
    private  Calendar srart;
    private  Calendar end;
    private AdapterDaets myAdapter;
    private boolean first = true;

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



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_places2, container, false);
        initViews(view);
        initRvDates(view);
        return view;

    }
     public void initViews(View view){
          startDate = view.findViewById(R.id.f_myplaces_text_date_start);
          endDate = view.findViewById(R.id.f_myplaces_text_date_end);
          imgStartDate =view.findViewById(R.id.f_myplaces_img_date_start);
          imgStartDate.setOnClickListener(this);
          imgEndDate =view.findViewById(R.id.f_myplaces_img_date_end);
          imgEndDate.setOnClickListener(this);
          show = view.findViewById(R.id.f_myplaces_text_show);
          show.setOnClickListener(this);

     }
    private void initRvDates(View v) {

        RecyclerView recyclerView = v.findViewById(R.id.f_myplaces_RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new AdapterDaets(getContext(), myDates,this);
//        myAdapter.setClickListener(this);
        recyclerView.setAdapter(myAdapter);
    }


    private void setDates() {
        if(places.size()>0){
            myDates.add(setOneDates(places.get(0)));

        }
        if(places.size()==1){
            return;

        }

        for (int i = 1; i < places.size(); i++) {

            if(places.get(i).getYear()== places.get(i-1).getYear()
                    &&places.get(i).getMounth()==places.get(i-1).getMounth()
                    &&places.get(i).getDay()==places.get(i-1).getDay()){

                myDates.get(myDates.size()-1).getPlaces().add(places.get(i));

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
    public void onItemClick( Place place) {
        if(mListener!=null){
            mListener.onItemClickPlace(place);

        }

    }

    @Override
    public void onButtonClicMoveDate(View viewItemOneData,MyDate myDate) {
        if(!myDate.isOpen()){
            myDate.setOpen(true);
            initRecyrclerViewOfOneOneDate(viewItemOneData,myDate.getPlaces());

        }
        else {
            myDate.setOpen(false);
            RecyclerView recyclerView = viewItemOneData.findViewById(R.id.myplaces_RV_of_oneDate);
            recyclerView.setVisibility(View.GONE);
        }




    }
    private void initRecyrclerViewOfOneOneDate(View v,ArrayList<Place>myPlaces) {

        RecyclerView recyclerView = v.findViewById(R.id.myplaces_RV_of_oneDate);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterRecyclerViewMyPlaces  myAdapter = new AdapterRecyclerViewMyPlaces(getContext(),myPlaces,this );
        myAdapter.setClickListener(this);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.f_myplaces_img_date_start :
                initSartDate();

                break;
            case R.id.f_myplaces_img_date_end:
                initEndDate();
                break;


            case R.id.f_myplaces_text_show :
          initFilterDates();
                break;

        }


    }



    public void initSartDate(){
          srart = Calendar.getInstance();

        srart.set(Calendar.HOUR_OF_DAY, 00);
        srart.set(Calendar.MINUTE, 0);
        srart.set(Calendar.SECOND, 1);
          int yearStart = srart.get(Calendar.YEAR);
          int mounthStart = srart.get(Calendar.MONTH);
          int dayStart = srart.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        startDate.setText(dayOfMonth + "."
                                + (monthOfYear + 1) + "." + year);
                        srart.set(year,monthOfYear+1,dayOfMonth);

                    }
                }, yearStart, mounthStart, dayStart);
        dpd.show();

    };





    public void initEndDate(){
        end = Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 55);
         int yearEnd = end.get(Calendar.YEAR);
         int monthEnd = end.get(Calendar.MONTH);
         int dayOfMonthEnd = end.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        endDate.setText(dayOfMonth + "."
                                + (monthOfYear + 1) + "." + year);
                        end.set(year,monthOfYear+1,dayOfMonth);

                    }
                }, yearEnd, monthEnd, dayOfMonthEnd);
        dpd.show();


    };
    private void initFilterDates() {

            myAdapter.FilterOfDates(srart,end);

    }





    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void onItemClickPlace(Place myPlace);
    }
}
