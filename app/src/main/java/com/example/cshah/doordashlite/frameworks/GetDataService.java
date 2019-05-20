package com.example.cshah.doordashlite.frameworks;

import com.example.cshah.doordashlite.model.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/v2/restaurant/?lat=37.422740&lng=-122.139956")
    Call<List<Restaurant>> getAllRestaurants();
}
