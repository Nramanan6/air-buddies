package com.example.nikhil.air_buddies_android.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Profile implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private Route trip;
    private int age;
    private Bitmap bm;


    /**
     * Profile constructor
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param age the user's age
     * @param bm the user's photo bitmap
     */
    public Profile(String firstName, String lastName, int age, Route trip, Bitmap bm) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.trip = trip;
        this.bm = bm;
    }

    /**
     * getter for id
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * setter for id
     * @param id to set to
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * getter
     * @return the firstName of user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter for first name
     * @param firstName the name to change to
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getter for last name
     * @return the last name of user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter for last name
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Route getTrip() {
        return trip;
    }

    public void setTrip(Route trip) {
        this.trip = trip;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

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
        return "Profile: " + firstName + " " + lastName;
    }
}
