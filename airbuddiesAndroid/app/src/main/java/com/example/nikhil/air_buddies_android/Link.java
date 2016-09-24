package com.example.nikhil.air_buddies_android;

/**
 * Created by Victor on 9/24/2016.
 */
public class Link {

    private City start;
    private City dest;

    public Link(City start, City dest) {
        this.start = start;
        this.dest = dest;
    }

    public City getStart() {
        return start;
    }

    public City getDest() {
        return dest;
    }
}
