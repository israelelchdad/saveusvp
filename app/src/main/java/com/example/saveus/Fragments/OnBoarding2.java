package com.example.saveus.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.saveus.R;


public class OnBoarding2 extends Fragment implements View.OnClickListener {
    Button moveToOnboarding1;
    Button moveToOnboarding2;
    Button moveToOnboarding3;
    

    private OnFragmentInteractionListener mListener;

    public OnBoarding2() {

    }

    public static OnBoarding2 newInstance() {
        OnBoarding2 fragment = new OnBoarding2();
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

        View view= inflater.inflate(R.layout.fragment_on_boarding2, container, false);
        moveToOnboarding1 = view.findViewById(R.id.f_boarding1_button);
        moveToOnboarding1.setOnClickListener(this);
        moveToOnboarding2= view.findViewById(R.id.f_boarding2_button);
        moveToOnboarding2.setOnClickListener(this);
        moveToOnboarding3= view.findViewById(R.id.f_boarding3_button);
        moveToOnboarding3.setOnClickListener(this);
        return view;
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
        mListener.chengCorentListFragment(v);
        
    }


    public interface OnFragmentInteractionListener {

        void chengCorentListFragment(View v);
    }
}
