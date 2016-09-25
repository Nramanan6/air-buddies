package com.airbud.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.airbud.R;
import com.airbud.api.ParseDeltaJSON;
import com.airbud.api.RetrofitCall;
import com.airbud.entity.City;
import com.airbud.entity.Link;
import com.airbud.entity.Profile;
import com.airbud.entity.Route;
import com.amazonaws.util.Throwables;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nikhil on 9/24/2016.
 */

public class FlightInfoActivity extends AppCompatActivity {

    private Profile currentUser;
    private EditText FlightNoEnter;
    private EditText DateEnter;
    private EditText SeatEnter;
    private String apivalue = "wK3AT3N4WIteX1z4gfJAZLr2zcWz1l9X";
    private Route buildRoute = new Route();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_flights);
        FlightNoEnter = (EditText)findViewById(R.id.FlNoEnter);
        DateEnter = (EditText) findViewById(R.id.FlightDateEnter);
        SeatEnter = (EditText) findViewById(R.id.SeatEnter);
    }

    public void addAnotherFlight(View v) {
        RetrofitCall retrofitCall = RetrofitCall.retrofit.create(RetrofitCall.class);
        final Call<ParseDeltaJSON> call =
                retrofitCall.getLinkInfo(FlightNoEnter.getText().toString(), DateEnter.getText().toString(), apivalue);
        call.enqueue(new Callback<ParseDeltaJSON>() {
            @Override
            public void onResponse(Call<ParseDeltaJSON> call, Response<ParseDeltaJSON> response) {
                ParseDeltaJSON structuredResponse = response.body();
                City departure = new City(structuredResponse.getDepartureLat(), structuredResponse.getDepartureLong(), structuredResponse.getDepartureCity());
                City arrival = new City(structuredResponse.getArrivalLat(), structuredResponse.getArrivalLong(), structuredResponse.getArrivalCity());
                Link currentLink = new Link(departure, arrival, Integer.parseInt(FlightNoEnter.getText().toString()));
                buildRoute.addLeg(currentLink);
                FlightNoEnter.setText("");
                DateEnter.setText("");
                SeatEnter.setText("");
            }

            @Override
            public void onFailure(Call<ParseDeltaJSON> call, Throwable t) {
            }
        });
    }

    public void addLastFlight(View v) {
        RetrofitCall retrofitCall = RetrofitCall.retrofit.create(RetrofitCall.class);
        final Call<ParseDeltaJSON> call =
                retrofitCall.getLinkInfo(FlightNoEnter.getText().toString(), DateEnter.getText().toString(), apivalue);
        call.enqueue(new Callback<ParseDeltaJSON>() {
            @Override
            public void onResponse(Call<ParseDeltaJSON> call, Response<ParseDeltaJSON> response) {
                ParseDeltaJSON structuredResponse = response.body();
                City departure = new City(structuredResponse.getDepartureLat(), structuredResponse.getDepartureLong(), structuredResponse.getDepartureCity());
                City arrival = new City(structuredResponse.getArrivalLat(), structuredResponse.getArrivalLong(), structuredResponse.getArrivalCity());
                Link currentLink = new Link(departure, arrival, Integer.parseInt(FlightNoEnter.getText().toString()));
                buildRoute.addLeg(currentLink);
                currentUser.setTrip(buildRoute);
                //TODO - Advance to next activity
            }

            @Override
            public void onFailure(Call<ParseDeltaJSON> call, Throwable t) {
            }
        });
    }
}
