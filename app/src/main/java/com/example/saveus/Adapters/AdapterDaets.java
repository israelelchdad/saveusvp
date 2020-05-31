package com.example.saveus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saveus.Objects.MyDate;
import com.example.saveus.R;

import java.util.ArrayList;
import java.util.Date;

public class AdapterDaets extends RecyclerView.Adapter<AdapterDaets.ViewHolder>  {
    private LayoutInflater mInflater;
    private ArrayList<MyDate> myMyDates;

    public AdapterDaets(Context context, ArrayList<MyDate> data ) {
        this.mInflater = LayoutInflater.from(context);
        this.myMyDates = data;
    }


    public AdapterDaets.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.itemonedate, parent, false);
        return new ViewHolder(view);
    }
    public void onBindViewHolder(AdapterDaets.ViewHolder holder, int position) {
        holder.setHolder(myMyDates.get(position));

    }
    public int getItemCount() {
        return myMyDates.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView date;
        private TextView startTimeEndTime;
        private TextView allTime;
        private MyDate myDate;

        ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.item_one_date_text_DATE);

           itemView.setOnClickListener(this);
        }


        public void setHolder(MyDate mdate) {
            myDate = mdate;
            date.setText(mdate.getYear()+"/"+mdate.getMounth()+"/"+mdate.getDay());
        }

        @Override
        public void onClick(View view) {
//            if (mClickListener != null) {
//                mClickListener.onItemClick( getAdapterPosition());
            }

        }
    }

