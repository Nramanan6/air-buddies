package com.example.nikhil.air_buddies_android;

/**
 * Created by Victor on 9/24/2016.
 */
public class Link {

    private City start;
    private City dest;
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
