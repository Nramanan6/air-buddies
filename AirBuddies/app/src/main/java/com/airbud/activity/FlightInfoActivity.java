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
import com.airbud.api.RetrofitCall;
import com.airbud.entity.Link;
import com.airbud.entity.Profile;

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
    private Button AddFlight;
    private Button Continue;
    private String apikey = "apikey";
    private String apivalue = "wK3AT3N4WIteX1z4gfJAZLr2zcWz1l9X";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_flights);
        FlightNoEnter = (EditText)findViewById(R.id.FlNoEnter);
        DateEnter = (EditText) findViewById(R.id.FlightDateEnter);
    }

    public void addAnotherFlight(View v) {
        RetrofitCall retrofitCall = RetrofitCall.retrofit.create(RetrofitCall.class);
        final Call<Link> call =
                retrofitCall.getLinkInfo(FlightNoEnter.getText().toString(), DateEnter.getText().toString());
        call.enqueue(new Callback<Link>() {
            @Override
            public void onResponse(Call<Link> call, Response<Link> response) {
                Link currentLink = response.body();
                FlightNoEnter.setText(currentLink.getStart().getName());
            }

            @Override
            public void onFailure(Call<Link> call, Throwable t) {

            }
        });
    }
}
