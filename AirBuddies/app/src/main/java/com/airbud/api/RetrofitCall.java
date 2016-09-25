package com.airbud.api;

import com.airbud.entity.City;
import com.airbud.entity.Link;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nikhil on 9/24/2016.
 */

public interface RetrofitCall {

    @GET("status")
    Call<ParseDeltaJSON> getLinkInfo(
            @Query("flightNumber") String flightNumber,
            @Query("flightOriginDate") String flightDate,
            @Query("apikey") String apikey);
    public static final String BASE_URL = "https://demo30-test.apigee.net/v1/hack/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
