package com.airbud.entity;

import java.io.Serializable;

public class Profile implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Route trip;

    /**
     * Profile constructor
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param major the user's major
     * @param email the user's email
     */
    public Profile(String firstName, String lastName, String major, String
            email, Route trip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.trip = trip;
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

    /**
     * getter for email
     * @return email the email to get
     */
    public String getEmail() {
        return email;
    }

    /**
     * set email
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public Route getTrip() {
        return trip;
    }

    public void setTrip(Route trip) {
        this.trip = trip;
    }

    @Override
    public String toString() {
        return "Profile: " + firstName + " " + lastName;
    }
}
