package com.example.nikhil.air_buddies_android.api;

import com.example.nikhil.air_buddies_android.entity.Link;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nikhil on 9/24/2016.
 */

public interface RetrofitCall {

    @GET("search/flight")
    Call<Link> getLinkInfo();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://demo30-test.apigee.net/v1/hack/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
