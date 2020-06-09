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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnBoarding1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OnBoarding1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnBoarding1 extends Fragment implements View.OnClickListener {
    Button moveToOnboarding1;
    Button moveToOnboarding2;
    Button moveToOnboarding3;


    private OnFragmentInteractionListener mListener;

    public OnBoarding1() {

    }


    public static OnBoarding1 newInstance() {
        OnBoarding1 fragment = new OnBoarding1();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_on_boarding1, container, false);

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



    }


    public interface OnFragmentInteractionListener {


    }
}
