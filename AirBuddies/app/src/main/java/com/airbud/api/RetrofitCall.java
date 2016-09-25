package com.airbud.api;

import com.airbud.entity.Link;

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
    Call<Link> getLinkInfo(
            @Query("flightNumber") String flightNumber,
            @Query("flightOriginDate") String originDate,
            @Query("Name:") String apikey,
            @Query("Value:") String apivalue
    );

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://demo30-test.apigee.net/v1/hack/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
