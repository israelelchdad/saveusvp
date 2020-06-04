package com.example.saveus.Objects;

import java.util.ArrayList;

public class MyDate {
    private int year;
    private int mounth;
    private int day;
    private ArrayList<Place> places;
    private String dayOfDate;
    private boolean isOpen;


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMounth() {
        return mounth;
    }

    public void setMounth(int mounth) {
        this.mounth = mounth;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public String getDayOfDate() {
        return dayOfDate;
    }

    public void setDayOfDate(String dayOfDate) {
        this.dayOfDate = dayOfDate;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public MyDate(int year, int mounth, int day, ArrayList<Place> places, String dayOfDate, boolean isOpen) {
        this.year = year;
        this.mounth = mounth;
        this.day = day;
        this.places = places;
        this.dayOfDate = dayOfDate;
        this.isOpen = isOpen;
    }

    public MyDate(){

    }


}
