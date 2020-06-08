package com.example.saveus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saveus.Fragments.AddPlace;
import com.example.saveus.Objects.MyDate;
import com.example.saveus.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AdapterDaets extends RecyclerView.Adapter<AdapterDaets.ViewHolder> {
    private LayoutInflater mInflater;

    private Context context;
    private ButtonClickListener buttonClickListener;
    private ArrayList<MyDate> myMyDates;
    private ArrayList<MyDate> myDatesFilter = new ArrayList<>();
    private ArrayList<MyDate> myAllDates = new ArrayList<>();

    public AdapterDaets(Context context, ArrayList<MyDate> data,ButtonClickListener buttonClickListener ) {

        this.context =context;

        this.buttonClickListener = buttonClickListener;
        this.mInflater = LayoutInflater.from(context);
        this.myMyDates = data;
        myAllDates.addAll(myMyDates);
    }


    public AdapterDaets.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.itemonedate, parent, false);
        return new ViewHolder(view);
    }
    public void onBindViewHolder(AdapterDaets.ViewHolder holder, int position) {
        holder.setHolder(myAllDates.get(position));

    }
    public int getItemCount() {
        return myAllDates.size();
    }


   public void FilterOfDates(Calendar start , Calendar end){
        myDatesFilter.clear();
        for (int i = 0; i < myMyDates.size() ; i++) {
           Calendar c = Calendar.getInstance();
           c.set(myMyDates.get(i).getYear(),myMyDates.get(i).getMounth(),myMyDates.get(i).getDay());
           if(start.before(c)&& end.after(c)){
               myDatesFilter.add(myMyDates.get(i));

           }


       }
       myAllDates.clear();
       myAllDates.addAll(myDatesFilter);
       notifyDataSetChanged();

        int v = 5;


   }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView date;
        private TextView dateTody;
        private TextView todayOryYesterdey;

        private ImageView imageView;
        View viewItemOneDate;

        private MyDate myDate;

        ViewHolder(View itemView) {
            super(itemView);
            viewItemOneDate = itemView;
            date = itemView.findViewById(R.id.item_one_date_text_DATE);
            dateTody = itemView.findViewById(R.id.item_one_date_text_DATE_TODAY);
            todayOryYesterdey =itemView.findViewById(R.id.item_one_date_text_today_yesterdey);
            imageView =itemView.findViewById(R.id.item_one_date_button_to_list);
//            mbutton.setOnClickListener(this);

           itemView.setOnClickListener(this);
        }


        public void setHolder(MyDate mdate) {
            myDate = mdate;
            iftodayoryesterday();


        }

        private void iftodayoryesterday() {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int mounth = Calendar.getInstance().get(Calendar.MONTH)+1;
            int day= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            if(myDate.getYear()==year&&myDate.getMounth()==mounth&&myDate.getDay()==day){
                date.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                dateTody.setVisibility(View.VISIBLE);
                dateTody.setText(AddPlace.setTime(myDate.getDay())+"/"+AddPlace.setTime(myDate.getMounth())+"/"+AddPlace.setTime(myDate.getYear()));
                todayOryYesterdey.setVisibility(View.VISIBLE);
                todayOryYesterdey.setText("היום");
//                if (buttonClickListener != null){
//                    buttonClickListener.onButtonClicMoveDate(viewItemOneDate,myDate);
//                }




            }
//            if(myDate.getYear()==year&&myDate.getMounth()==mounth&&myDate.getDay()==day-1){
//                date.setVisibility(View.GONE);
//                imageView.setVisibility(View.GONE);
//                dateTody.setVisibility(View.VISIBLE);
//                dateTody.setText(AddPlace.setTime(myDate.getDay())+"/"+AddPlace.setTime(myDate.getMounth())+"/"+AddPlace.setTime(myDate.getYear()));
//                todayOryYesterdey.setVisibility(View.VISIBLE);
//                todayOryYesterdey.setText("אתמול");
//                if (buttonClickListener != null){
//                    buttonClickListener.onButtonClicMoveDate(viewItemOneDate,myDate);
//                }




//            }
            else {
                date.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                dateTody.setVisibility(View.GONE);
                todayOryYesterdey.setVisibility(View.GONE);
                date.setText(AddPlace.setTime(myDate.getDay())+"/"+AddPlace.setTime(myDate.getMounth())+"/"+AddPlace.setTime(myDate.getYear()));

            }
        }

        @Override
        public void onClick(View view) {
            if (buttonClickListener != null){
                buttonClickListener.onButtonClicMoveDate(viewItemOneDate,myDate);
            }



            }

        }

    public interface ButtonClickListener {
        void onButtonClicMoveDate(View viewItemOneDate, MyDate myDate);
    }
    }

