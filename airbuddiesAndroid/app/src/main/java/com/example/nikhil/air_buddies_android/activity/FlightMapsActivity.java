package com.example.nikhil.air_buddies_android.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nikhil.air_buddies_android.entity.City;
import com.example.nikhil.air_buddies_android.entity.Link;
import com.example.nikhil.air_buddies_android.R;
import com.example.nikhil.air_buddies_android.entity.Route;
import com.example.nikhil.air_buddies_android.entity.Profile;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FlightMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int flightNum;
    private String currCity;

    private Map<City, List<Profile>> interestingPeople;
    private Set<Link> paths;
    private List<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Set up toolbar and back button
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //get all the passengers

        //get all the cities linked to this flight
        for (Link edge : paths) {
            if (!cities.contains(edge.getStart())) {
                cities.add(edge.getStart());
            }
            if (!cities.contains(edge.getDest())) {
                cities.add(edge.getDest());
            }
        }

        //Populate map with cities and people on this flight flying from them
        Map<Profile, Route> flightData = AWSGimmeData();
        interestingPeople = new HashMap<>();
        for (Profile p : flightData.keySet()) {
            for (Link edge : p.getTrip().getLegs()) {
                List<Profile> temp = new ArrayList<>();
                if (!interestingPeople.keySet().contains(edge.getStart())) {
                    temp.add(p);
                    interestingPeople.put(edge.getStart(), temp);
                } else {
                    temp.addAll(interestingPeople.get(edge.getStart()));
                    temp.add(p);
                }
            }
        }



    }

    //Methods to display the toolbar and its options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_flightmap, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                //TODO: go back
                break;
            case R.id.action_settings:
                //TODO: make settings page
                break;
            case R.id.idkyet:
                //TODO: other options
                break;
        }
        return (super.onOptionsItemSelected(item));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void markCities() {

    }
}
