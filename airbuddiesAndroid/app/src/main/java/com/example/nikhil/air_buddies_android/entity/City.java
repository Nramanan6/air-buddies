package com.example.nikhil.air_buddies_android.entity;

/**
 * Created by Victor on 9/24/2016.
 */
public class City {

    private int lat;
    private int longi;
    private String name;

    public City (int lat, int longi, String name) {
        this.lat = lat;
        this.longi = longi;
        this.name = name;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLongi() {
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
}
