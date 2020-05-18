package com.example.saveus.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saveus.Objects.Place;
import com.example.saveus.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;


public class AddPlace extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private Place myPlace;
    ImageView addata;
    EditText adress;
    CalendarView calendarView;

    private OnFragmentInteractionListener mListener;

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
         adress = view.findViewById(R.id.f_add_adress);
         initEditorActionListener();



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
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDate = DateFormat.getDateInstance().format(c.getTime());
        myPlace.setDate(currentDate);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.add_plaice_date_IV:
                initDialogFragment();
                break;
            case R.id.f_add_adress:

                break;


    }
    }



    private void initDialogFragment() {
        DialogFragment datePicker = new DataPickerFragment();
        datePicker.setTargetFragment(AddPlace.this, 0);
        datePicker.show(getFragmentManager(), "date picker");

    }

    private void initEditorActionListener() {
        adress.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {


                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {
                                initAdress();
                                // the user is done typing.

                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                }
        );
    }
    private void initAdress() {
        myPlace.setAdressOfUser(adress.getText().toString());
        String myAdress =adress.getText().toString();
        LatLng l = getLocationFromAddress(myAdress);
        initLatlngOfPlace(l);
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

            p1 = new LatLng((double) (location.getLatitude() * 1E6),
                    (double) (location.getLongitude() * 1E6));


        } catch (IOException e) {
            e.printStackTrace();
        }
        return p1;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
