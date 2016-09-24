package com.example.nikhil.air_buddies_android;

import java.util.List;

/**
 * Created by Victor on 9/24/2016.
 */
public class Route {

    private List<Integer> flights;

    public Route(List<Integer> flightNums) {
        this.flights = flightNums;
    }

    public List<Integer> getFlightNums() {
        return flights;
    }
}
