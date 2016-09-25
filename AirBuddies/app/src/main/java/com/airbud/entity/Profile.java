package com.airbud.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Profile implements Serializable {

    private String name;
    private Route trip;
    private int age;
    private Bitmap bm;
    private String description;

    public Profile() {
        //empty constructor just in case AWS needs it (like firebase)
    }
    /**
     * Profile constructor
     * @param name whole name
     * @param age the user's age
     * @param bm the user's photo bitmap
     */
    public Profile(String name, int age, Route trip, Bitmap bm, String description) {
        this.name = name;
        this.age = age;
        this.trip = trip;
        this.bm = bm;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Route getTrip() {
        return trip;
    }

    public void setTrip(Route trip) {
        this.trip = trip;
    }

    public String getName() {
        return name; }

    public int getAge() {
        return age;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm() {
        this.bm = bm;
    }

    @Override
    public String toString() {
        return "Profile: " + name;
    }
}
