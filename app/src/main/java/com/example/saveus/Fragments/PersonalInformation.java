package com.example.saveus.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saveus.Activitys.LoginActivity;
import com.example.saveus.Objects.Place;
import com.example.saveus.Objects.Profil;
import com.example.saveus.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class PersonalInformation extends Fragment implements View.OnClickListener {
    private ImageView editProfile;
    private Profil myProfile;
    private TextView name;
    private TextView email;
    private TextView phone;

    private OnFragmentInteractionListener mListener;

    public PersonalInformation() {

    }


    public static PersonalInformation newInstance() {
        PersonalInformation fragment = new PersonalInformation();
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
        // Inflate the layout for this fragment
         View view= inflater.inflate(R.layout.fragment_personal_information, container, false);
         name = view.findViewById(R.id.profile_name);
         email = view.findViewById(R.id.profile_email);
         phone = view.findViewById(R.id.profile_phone);
         getMyProfile();
         editProfile = view.findViewById(R.id.profile_edit);
         editProfile.setOnClickListener(this);


         return view;
    }

    private void getMyProfile() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = sharedPrefs.getString(LoginActivity.KEYOPROFILE, null);
        Type type = new TypeToken<Profil>() {}.getType();
        myProfile = gson.fromJson(json,type);
        if(myProfile!=null){
            setMyprofil();
        }

    }

    private void setMyprofil() {
        name.setText(myProfile.getName());
        email.setText(myProfile.getName());
        phone.setText(myProfile.getPhone());
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
    public void onClick(View v) {
        mListener.moveToEditProfile();

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void moveToEditProfile();
    }
}
