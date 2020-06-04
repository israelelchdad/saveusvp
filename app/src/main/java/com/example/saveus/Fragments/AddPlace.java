package com.example.saveus.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.saveus.Objects.Place;
import com.example.saveus.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;


public class AddPlace extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private Place myPlace;
    private ImageView addata;
    private EditText adress;
    private TextView addDate;
    private TextView startTime;
    private TextView timeOfStart;
    private TextView timeEnd;
    private TextView endOfTime;
    private TextView allTime;
    private TextView save;
    private ImageView clearPlace;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;

    private String adressOfUser;




    private updatePlace mListener;

    public AddPlace() {


    }

    public static AddPlace newInstance(String param1, String param2) {
        AddPlace fragment = new AddPlace();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_add_place, container, false);
        myPlace = new Place();
        initViews(view);
        return view;
    }

    private void initViews(View view) {
         addata = view.findViewById(R.id.add_plaice_date_IV);
         addata.setOnClickListener(this);
         addDate = view.findViewById(R.id.f_add_date);
         adress = view.findViewById(R.id.f_add_adress);
         adress.setOnClickListener(this);
         //         initEditorActionListener();
         startTime = view.findViewById(R.id.f_add_starttime);
         startTime.setOnClickListener(this);
         timeOfStart = view.findViewById(R.id.f_add_time_start);
         timeEnd = view.findViewById(R.id.f_add_time_end);
         timeEnd.setOnClickListener(this);
         endOfTime = view.findViewById(R.id.f_add_endttime);
         allTime =  view.findViewById(R.id.f_all_time);
         save =  view.findViewById(R.id.f_add_place_save);
         save.setOnClickListener(this);
         clearPlace = view.findViewById(R.id.f_add_place_clear);
         clearPlace.setOnClickListener(this);




    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof updatePlace) {
            mListener = (updatePlace) context;
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
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.add_plaice_date_IV:
                initDialogFragment();
                break;
            case R.id.f_add_adress:
//           initAdress();
                break;
            case R.id.f_add_starttime:
                initStartTime(timeOfStart);
                break;
            case R.id.f_add_time_end:
                initEndTime(endOfTime);
                break;
            case R.id.f_add_place_save:
               initAdress();
                if (mListener != null) {
                    mListener.setMyPlace(myPlace);
                    mListener.goToPlaceAndMap();

                }
                break;
            case R.id.f_add_place_clear:
                clearMyPlace();
                break;

        }
    }


    private void initDialogFragment() {
        DialogFragment datePicker = new DataPickerFragment();
        datePicker.setTargetFragment(AddPlace.this, 0);
        datePicker.show(getFragmentManager(), "date picker");

    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDate = DateFormat.getDateInstance().format(c.getTime());
        addDate.setText(currentDate);
        myPlace.setYear(year);
        myPlace.setMounth(month+1);
        myPlace.setDay(dayOfMonth);


    }


    private void initAdress() {
        adressOfUser = adress.getText().toString();
        myPlace.setAdressOfUser(adressOfUser);
        LatLng l = getLocationFromAddress(adressOfUser);
        initLatlngOfPlace(l);


    }



    private void initStartTime(final TextView textView) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                textView.setText( setTime(selectedHour) + ":" + setTime(selectedMinute));
                myPlace.setHour(selectedHour);
                myPlace.setMinute(selectedMinute);
//                myPlace.setStartTime( setTime(selectedHour) + ":" + setTime(selectedMinute));
                startHour = selectedHour;
                startMinute =selectedMinute;
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }


    private void initEndTime(final TextView textView) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                endHour = selectedHour;
                endMinute =selectedMinute;
                if(endHour -startHour<0){
                    Toast.makeText(getActivity(), "chose time another!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                textView.setText( setTime(selectedHour) + ":" +setTime( selectedMinute));

                myPlace.setEndTime( setTime(selectedHour) + ":" +setTime( selectedMinute));
                alltime(startHour,startMinute,endHour,endMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }


    private void alltime(int startHour, int startMinute, int endHour, int endMinute) {
        int start= startHour*60+startMinute;
        int end= endHour*60+endMinute;
        int allMinute = end-start;
        int myAllHour =allMinute/60;
        int myallMinute = allMinute%60;
        String myAllTime = setTime(myAllHour) +":"+setTime(myallMinute)+":00";
        allTime.setText(myAllTime);
        myPlace.setAllTime(myAllTime);
        int a=5;

    }

    private void clearMyPlace() {
        myPlace = null;
        adress.getText().clear();
        addDate.setText("הוספת תאריך");
        timeOfStart.setText("00:00:00");
        endOfTime.setText("00:00:00");
        allTime.setText("00:00:00");

    }

    public static String setTime(int time) {
        String myTime;
        if(time<10){
            myTime = "0"+time;

        }else {
            myTime =String.valueOf(time);
        }
        return myTime;

    }








    private void initLatlngOfPlace( LatLng latLng) {
        if(latLng!=null){
            myPlace.setLatitude(latLng.latitude);
            myPlace.setLongitude(latLng.longitude);

        }


    }

    public LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(getActivity());
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng((double) (location.getLatitude() ),
                    (double) (location.getLongitude()));


        } catch (IOException e) {
            e.printStackTrace();
        }
        return p1;
    }


    public interface updatePlace {

        void setMyPlace(Place myPlace);
        void goToPlaceAndMap();
    }
}
