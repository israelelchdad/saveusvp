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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ChangePlace extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener{
    private Place myPlace;
    private EditText editText;
    private TextView changeDate;
    private TextView changStartTime;
    private TextView changEndTime;
    private TextView startTime;
    private TextView endtTime;
    private TextView allTime;
    private TextView save;
    private ImageView chngeOfDate;
    private ImageView clearPlace;
    private static String KeyMPlaces ="MYPLACES";
    private static String KeyMPLACE ="MYPLACE";
    public ArrayList<Place> places = new ArrayList<>();
    private int position;
    private AddPlace.updatePlace myListener ;

    private Place p;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private String adressOfUser;




    private OnFragmentInteractionListener mListener;

    public ChangePlace() {
        // Required empty public constructor
    }

    public static ChangePlace newInstance(ArrayList<Place> myPlaces, Place myPlace) {
        ChangePlace fragment = new ChangePlace();
        Bundle args = new Bundle();
        args.putParcelableArrayList(KeyMPlaces, myPlaces);
        args.putParcelable(KeyMPLACE,myPlace);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            places = getArguments().getParcelableArrayList(KeyMPlaces);
            p = getArguments().getParcelable(KeyMPLACE);
            myPlace = new Place();


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_place, container, false);
        initPlaceToViews(view);
        return view;
    }

    private void initPlaceToViews(View view) {
        myPlace = new Place(p);
        editText = view.findViewById(R.id.f_cange_add_adress);
        editText.setHint(myPlace.getAdressOfUser());
        changeDate = view.findViewById(R.id.f_change_add_date);
        changeDate.setText(myPlace.getDay()+"/"+myPlace.getMounth()+"/"+myPlace.getYear());
        startTime = view.findViewById(R.id.f_cehnge_time_start);
        startTime.setText(AddPlace.setTime(myPlace.getHour())+":"+AddPlace.setTime(myPlace.getMinute())+":"+AddPlace.setTime(myPlace.getSecends()));
        endtTime = view.findViewById(R.id.f_chenge_endttime);
        endtTime.setText(myPlace.getEndTime()+":"+AddPlace.setTime(myPlace.getEndsecends()));
        allTime =view.findViewById(R.id.f_cange_all_time);
        allTime.setText(myPlace.getAllTime());
        chngeOfDate = view.findViewById(R.id.chnge_plaice_date_IV);
        chngeOfDate.setOnClickListener(this);
        changStartTime = view.findViewById(R.id.f_chang_starttime);
        changStartTime.setOnClickListener(this);
        changEndTime = view.findViewById(R.id.f_change_time_end);
        changEndTime.setOnClickListener(this);
        save =  view.findViewById(R.id.f_cange_place_save);
        save.setOnClickListener(this);
        clearPlace = view.findViewById(R.id.f_change_place_clear);
        clearPlace.setOnClickListener(this);





    }


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
        if (context instanceof AddPlace.updatePlace) {
            myListener = (AddPlace.updatePlace) context;
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
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDate = DateFormat.getDateInstance().format(c.getTime());
        changeDate.setText(currentDate);
        myPlace.setYear(year);
        myPlace.setMounth(month+1);
        myPlace.setDay(dayOfMonth);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.chnge_plaice_date_IV:
                initDialogFragment();
                break;
            case R.id.f_add_adress:
//           initAdress();
                break;
            case R.id.f_chang_starttime:
                initStartTime(startTime);
                break;
            case R.id.f_change_time_end:
                initEndTime(endtTime);
                break;
            case R.id.f_cange_place_save:
                initAdressAndSsetNewPlace();



                break;
            case R.id.f_change_place_clear:
                clearMyPlace();
                break;

        }

    }



    private void initDialogFragment() {
        DialogFragment datePicker = new DataPickerFragment();
        datePicker.setTargetFragment(ChangePlace.this, 0);
        datePicker.show(getFragmentManager(), "date picker");

    }
    private void initStartTime(final TextView textView) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                textView.setText( AddPlace.setTime(selectedHour) + ":" + AddPlace.setTime(selectedMinute));
                myPlace.setHour(selectedHour);
                myPlace.setMinute(selectedMinute);

//                myPlace.setStartTime( AddPlace.setTime(selectedHour) + ":" + AddPlace.setTime(selectedMinute));
                startHour = selectedHour;
                startMinute = selectedMinute;
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
                textView.setText( AddPlace.setTime(selectedHour) + ":" +AddPlace.setTime( selectedMinute));
                myPlace.setEndTime( AddPlace.setTime(selectedHour) + ":" +AddPlace.setTime( selectedMinute));
                alltime(startHour,startMinute,endHour,endMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }


    private void alltime(int startHour, int startMinute, int endHour, int endMinute) {
        int start = startHour*60+startMinute;
        int end= endHour*60+endMinute;
        int allMinute = end-start;
        int myAllHour =allMinute/60;
        int myallMinute = allMinute%60;
        String myAllTime = AddPlace.setTime(myAllHour) +":"+AddPlace.setTime(myallMinute)+":00";
        allTime.setText(myAllTime);
        myPlace.setAllTime(myAllTime);
        int a=5;

    }
    private void initAdressAndSsetNewPlace() {

        adressOfUser = editText.getText().toString();
        LatLng l = getLocationFromAddress(adressOfUser);
        if (l==null){
            setNewPlaceToPlaces();



        }
        else {
            myPlace.setAdressOfUser(adressOfUser);
            initLatlngOfPlace(l);
            setNewPlaceToPlaces();
        }


    }
    private void initLatlngOfPlace( LatLng latLng) {
        myPlace.setLatitude(latLng.latitude);
        myPlace.setLongitude(latLng.longitude);
        int a =5;
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

    private void setNewPlaceToPlaces() {
        removePlaceOld();

        if(myListener !=null){
            myListener.setMyPlace(myPlace);
            myListener.goToPlaceAndMap();
        }

    }




    private void clearMyPlace() {
        myPlace = null;
        editText.setHint("");
        changeDate.setText("הוספת תאריך");
        startTime.setText("00:00:00");
        endtTime.setText("00:00:00");
        allTime.setText("00:00:00");
        removePlaceOld();

        if(myListener !=null){

            myListener.goToPlaceAndMap();
        }





    }
    private void removePlaceOld() {
        int indexOfPlaceOld = 0;
        for (int i = 0; i < places.size() ; i++) {
            if(places.get(i).equals(p)){
                indexOfPlaceOld = i;

            }

        }
        places.remove(indexOfPlaceOld);
        if(mListener !=null){
            mListener.addAndChangeMyPlacesInSharedPrefs(places);
        }
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void addAndChangeMyPlacesInSharedPrefs(ArrayList<Place> myPlaces);


    }
}
