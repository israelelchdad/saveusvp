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

import com.example.saveus.Adapters.AdapterRecyclerViewMyPlaces;
import com.example.saveus.Objects.Place;
import com.example.saveus.R;

import java.util.ArrayList;


public class MyPlaces extends Fragment implements AdapterRecyclerViewMyPlaces.ItemClickListener{
    private static String KeyMPlaces ="MYPLACES";
    public ArrayList<Place> places = new ArrayList<>();


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

            int a =5;
            int b =6;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_places2, container, false);
        initRecyrclerView(view);
        return view;

    }

    private void initRecyrclerView(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.f_myplaces_RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterRecyclerViewMyPlaces  myAdapter= new AdapterRecyclerViewMyPlaces(getContext(),places );
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
    public void onItemClick(View view, int position) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
