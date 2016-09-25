package com.example.nikhil.air_buddies_android.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Victor on 9/24/2016.
 */
public class Link {

    @SerializedName("departureCityName")
    private City start;
    @SerializedName("arrivalCityName")
    private City dest;
    @SerializedName("flightNumber")
    private int flightNo;

    public Link(City start, City dest, int flightNo) {
        this.start = start;
        this.dest = dest;
        this.flightNo = flightNo;
    }

    public City getStart() {
        return start;
    }

    public City getDest() {
        return dest;
    }

    public int getFlightNo() {
        return flightNo;
    }
}
