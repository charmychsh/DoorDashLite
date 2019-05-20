package com.example.cshah.doordashlite.Controller;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.cshah.doordashlite.frameworks.GetDataService;
import com.example.cshah.doordashlite.R;
import com.example.cshah.doordashlite.model.Restaurant;
import com.example.cshah.doordashlite.model.RestaurantAdapter;
import com.example.cshah.doordashlite.frameworks.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<Restaurant> restaurantsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RestaurantAdapter mAdapter;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext() , DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Restaurant>> call = service.getAllRestaurants();
        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepareMovieData() {
  /*      Restaurant restaurant = new Restaurant("Mad Max: Fury Road", "Action & Adventure", "2015");
        restaurantsList.add(restaurant);

        restaurant = new Restaurant("Inside Out", "Animation, Kids & Family", "2015");
        restaurantsList.add(restaurant);

        mAdapter.notifyDataSetChanged();*/
    }

    private void generateDataList(List<Restaurant> restaurantsList) {

        mAdapter = new RestaurantAdapter(this, restaurantsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
       // mAdapter.notifyDataSetChanged();
    }

}
