package com.example.nikhil.air_buddies_android.entity;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Victor on 9/24/2016.
 */
public class City {

    private double lat;
    private double longi;
    private String name;

    public City (double lat, double longi, String name) {
        this.lat = lat;
        this.longi = longi;
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(int longi) {
        this.longi = longi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getCoord() {
        return new LatLng(lat, longi);
    }
}
