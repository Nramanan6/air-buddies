package com.example.nikhil.air_buddies_android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nikhil.air_buddies_android.R;
import com.example.nikhil.air_buddies_android.api.RetrofitCall;
import com.example.nikhil.air_buddies_android.entity.Link;
import com.example.nikhil.air_buddies_android.entity.Profile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nikhil on 9/24/2016.
 */

public class FlightInfoActivity extends AppCompatActivity {

    private Profile currentUser;
    private EditText FlNoEnter;
    private EditText DateEnter;
    private EditText SeatEnter;
    private Button AddFlight;
    private Button Continue;
    private String apikey = "apikey";
    private String apivalue = "wK3AT3N4WIteX1z4gfJAZLr2zcWz1l9X";

    protected void OnCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_flights);
        FlNoEnter = (EditText) findViewById(R.id.FlNoEnter);
        DateEnter = (EditText) findViewById(R.id.FlightDateEnter);
        SeatEnter = (EditText) findViewById(R.id.SeatEnter);
        AddFlight = (Button) findViewById(R.id.AddFlight);
        Continue = (Button) findViewById(R.id.Continue);
    }

    public void addAnotherFlight(View v) {
        RetrofitCall retrofitCall = RetrofitCall.retrofit.create(RetrofitCall.class);
        final Call<Link> call =
                retrofitCall.getLinkInfo(FlNoEnter.getText().toString(), DateEnter.getText().toString(), apikey, apivalue);
        call.enqueue(new Callback<Link>() {
            @Override
            public void onResponse(Call<Link> call, Response<Link> response) {
                Link currentLink = response.body();
            }

            @Override
            public void onFailure(Call<Link> call, Throwable t) {

            }
        });
    }
}
