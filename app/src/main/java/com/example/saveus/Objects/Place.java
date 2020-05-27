package com.example.saveus.Objects;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class Place implements Parcelable {
    private String cityOfUser;
    private String adressOfUser;
    private int hour;
    private int minute;
    private int secends;
    private int endsecends;
    private String endTime;
    private String allTime;
    private   int year;
    private  int mounth;
    private   int day;
    private Double latitude   ;
    private double longitude;

    public Place(String cityOfUser, String adressOfUser, int hour, int minute, int secends, int endsecends, String endTime, String allTime, int year, int mounth, int day, Double latitude, double longitude) {
        this.cityOfUser = cityOfUser;
        this.adressOfUser = adressOfUser;
        this.hour = hour;
        this.minute = minute;
        this.secends = secends;
        this.endsecends = endsecends;
        this.endTime = endTime;
        this.allTime = allTime;
        this.year = year;
        this.mounth = mounth;
        this.day = day;
        this.latitude = latitude;
        this.longitude = longitude;
    }




    public Place() {
    }

    public String getCityOfUser() {
        return cityOfUser;
    }

    public void setCityOfUser(String cityOfUser) {
        this.cityOfUser = cityOfUser;
    }

    public String getAdressOfUser() {
        return adressOfUser;
    }

    public void setAdressOfUser(String adressOfUser) {
        this.adressOfUser = adressOfUser;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecends() {
        return secends;
    }

    public void setSecends(int secends) {
        this.secends = secends;
    }

    public int getEndsecends() {
        return endsecends;
    }

    public void setEndsecends(int endsecends) {
        this.endsecends = endsecends;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAllTime() {
        return allTime;
    }

    public void setAllTime(String allTime) {
        this.allTime = allTime;
    }

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public static Creator<Place> getCREATOR() {
        return CREATOR;
    }

    public Place(Place place) {
        this.cityOfUser = place.getCityOfUser();
        this.adressOfUser = place.getAdressOfUser();
        this.hour = place.getHour();
        this.minute = place.getMinute();
        this.endsecends = place.endsecends;
        this.secends  = place.getSecends();
        this.endTime = place.getEndTime();
        this.allTime = place.getAllTime();
        this.year = place.getYear();
        this.mounth = place.getMounth();
        this.day = place.getDay();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
    }


    protected Place(Parcel in) {
        cityOfUser = in.readString();
        adressOfUser = in.readString();
        hour = in.readInt();
        minute = in.readInt();
        secends = in.readInt();
        endsecends = in.readInt();
        endTime = in.readString();
        allTime = in.readString();
        year = in.readInt();
        mounth = in.readInt();
        day = in.readInt();
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        longitude = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cityOfUser);
        dest.writeString(adressOfUser);
        dest.writeInt(hour);
        dest.writeInt(minute);
        dest.writeInt(secends);
        dest.writeInt(endsecends);
        dest.writeString(endTime);
        dest.writeString(allTime);
        dest.writeInt(year);
        dest.writeInt(mounth);
        dest.writeInt(day);
        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitude);
        }
        dest.writeDouble(longitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}
