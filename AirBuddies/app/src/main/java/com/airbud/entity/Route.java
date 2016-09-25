package com.airbud.entity;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Victor on 9/24/2016.
 */
public class Route {

    private List<Link> legs;

    public Route() {this(new ArrayList<Link>());}

    public Route(List<Link> legs) {
        this.legs = legs;
    }

    public List<Link> getLegs() {
        return legs;
    }

    public void addLeg(Link leg) { legs.add(leg); }
}
