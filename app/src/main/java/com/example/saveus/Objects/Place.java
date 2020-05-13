package com.example.saveus.Objects;

public class Place {
    private String cityOfUser;
    private String adressOfUser;
    private String startTime;
    private String endTime;
    private String allTime;
    private String date;
    private Double latitude   ;
    private double longitude;

    public Place(String cityOfUser, String adressOfUser, String startTime, String endTime, String allTime, String date, Double latitude, double longitude) {
        this.cityOfUser = cityOfUser;
        this.adressOfUser = adressOfUser;
        this.startTime = startTime;
        this.endTime = endTime;
        this.allTime = allTime;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
