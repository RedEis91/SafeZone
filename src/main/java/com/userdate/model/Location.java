package com.userdate.model;


/**
 * Created by Jared on 8/28/17.
 */
public class Location {
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
//sets standard location to rosa parks transit center
    public Location() {
        latitude = 42.3322;
        longitude = -83.0529;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }



}
