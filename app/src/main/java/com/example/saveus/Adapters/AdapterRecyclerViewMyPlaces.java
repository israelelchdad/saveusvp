package com.example.saveus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saveus.Fragments.MyPlaces;
import com.example.saveus.Objects.Place;
import com.example.saveus.R;


import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerViewMyPlaces extends  RecyclerView.Adapter<AdapterRecyclerViewMyPlaces.ViewHolder> {

    private ArrayList<Place> myPlaces;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public AdapterRecyclerViewMyPlaces(Context context, ArrayList<Place> data,ItemClickListener mClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.myPlaces = data;
    }



    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setHolder(myPlaces.get(position));

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return myPlaces.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView adress;
        private TextView startTimeEndTime;
        private TextView allTime;
        private Place myPlace;

        ViewHolder(View itemView) {
            super(itemView);
            adress = itemView.findViewById(R.id.item_text_adress);
            startTimeEndTime =itemView.findViewById(R.id.item_text_starttime_endtime);
            allTime = itemView.findViewById(R.id.item_text_alltime);
            itemView.setOnClickListener(this);
        }


        public void setHolder(Place place) {
            myPlace =place;
            adress.setText(place.getAdressOfUser());
            startTimeEndTime.setText(place.getStartTime()+" - "+place.getEndTime());
            allTime.setText(place.getAllTime());




        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick( getAdapterPosition());
            }

        }
    }



    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick( int position);
    }
}

