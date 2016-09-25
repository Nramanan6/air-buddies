package com.airbud.activity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.airbud.entity.City;
import com.airbud.entity.Link;
import com.airbud.R;
import com.airbud.entity.ProfileAdapter;
import com.airbud.entity.Route;
import com.airbud.entity.Profile;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FlightMapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private int flightNum;
    private String currCity;

    private Map<City, List<Profile>> interestingPeople;
    private Set<Link> paths;
    private Set<City> cities;

    private List<Marker> locs;
    private LatLngBounds.Builder builder;
    private RecyclerView rv;

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

        locs = new ArrayList<>();
        paths = new HashSet<>();
        cities = new HashSet<>();
        interestingPeople = new HashMap<>();

        mMap.setOnMapClickListener(this);

        //get all the passengers
        List<Profile> flightData = new ArrayList<>();
        //TODO:load all profiles that have this flight number into the list

        //RECYCLERVIEW STUFF
        rv = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        builder = LatLngBounds.builder();

        //get all the cities linked to this flight
        for (Link edge : paths) {
            cities.add(edge.getStart());
            cities.add(edge.getDest());
        }

        //Populate map with cities and people
        for (Profile p : flightData) {
            for (Link edge : p.getTrip().getLegs()) {
                List<Profile> temp = new ArrayList<>();
                if (!interestingPeople.keySet().contains(edge.getStart())) {
                    temp.add(p);
                    interestingPeople.put(edge.getStart(), temp);
                } else {
                    temp.addAll(interestingPeople.get(edge.getStart()));
                    temp.add(p);
                    interestingPeople.put(edge.getDest(), temp);
                }
                temp.clear();
                if (!interestingPeople.keySet().contains(edge.getDest())) {
                    temp.add(p);
                    interestingPeople.put(edge.getDest(), temp);
                } else {
                    temp.addAll(interestingPeople.get(edge.getDest()));
                    temp.add(p);
                    interestingPeople.put(edge.getDest(), temp);
                }
            }
        }



    }

    @Override
    public void onMapClick(LatLng coord) {

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

        markCities();
        drawLines();
        LatLngBounds bounds = builder.build();
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));

    }

    private void markCities() {
        for (City c : cities) {
            if(Geocoder.isPresent()){
                try {
                    String location = c.getName();
                    Geocoder gc = new Geocoder(this);
                    List<Address> addresses= gc.getFromLocationName(location, 1); // get the found Address Objects

                    List<LatLng> ll = new ArrayList<LatLng>(addresses.size()); // A list to save the coordinates if they are available
                    for(Address a : addresses){
                        if(a.hasLatitude() && a.hasLongitude()){
                            ll.add(new LatLng(a.getLatitude(), a.getLongitude()));
                        }
                    }
                    Marker mMarker = mMap.addMarker(new MarkerOptions().position(ll.get(0)).title(c.getName()));
                    mMarker.setTag(c.getName());
                    builder.include(ll.get(0));
                    locs.add(mMarker);
                } catch (IOException e) {
                    // handle the exception
                }
            }
        }
    }

    private void drawLines() {
        for (Link edge : paths) {
            Polyline line = mMap.addPolyline(new PolylineOptions().add(edge.getStart().getCoord(), edge.getDest().getCoord())
                    .width(5).color(Color.RED));
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        String name = (String) marker.getTag();
        List<Profile> data = interestingPeople.get(name);
        ProfileAdapter adapter = new ProfileAdapter(data);
        rv.setAdapter(adapter);
        return true;
    }
}
