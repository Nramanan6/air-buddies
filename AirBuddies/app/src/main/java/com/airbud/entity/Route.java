package com.airbud.entity;

import java.util.List;

/**
 * Created by Victor on 9/24/2016.
 */
public class Route {

    private List<Link> legs;


    public Route(List<Link> legs) {
        this.legs = legs;
    }

    public List<Link> getLegs() {
        return legs;
    }
}
