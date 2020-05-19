package com.example.saveus.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {
    private String cityOfUser;
    private String adressOfUser;
    private String startTime;
    private String endTime;
    private String allTime;
    private   int year;
    private  int mounth;
    private   int day;
    private Double latitude   ;
    private double longitude;


    public Place(String cityOfUser, String adressOfUser, String startTime, String endTime, String allTime, int year, int mounth, int day, Double latitude, double longitude) {
        this.cityOfUser = cityOfUser;
        this.adressOfUser = adressOfUser;
        this.startTime = startTime;
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

    protected Place(Parcel in) {
        cityOfUser = in.readString();
        adressOfUser = in.readString();
        startTime = in.readString();
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cityOfUser);
        dest.writeString(adressOfUser);
        dest.writeString(startTime);
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
}
