package com.example.cshah.doordashlite.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cshah.doordashlite.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {

    private List<Restaurant> restaurantsList;
    private Context context;


    @NonNull
    @Override
    public RestaurantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_layout, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.MyViewHolder myViewHolder, int i) {
        Restaurant restaurant = restaurantsList.get(i);
        myViewHolder.name.setText(restaurant.getName());
        myViewHolder.description.setText(restaurant.getDescription());
        myViewHolder.status.setText(restaurant.getStatus());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(restaurantsList.get(i).getUrl())
                .placeholder((R.mipmap.default_restaurants))
                .error(R.mipmap.default_restaurants)
                .into(myViewHolder.image);

    }

    @Override
    public int getItemCount() {
        return restaurantsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, status;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.nameText);
            description = (TextView) view.findViewById(R.id.descriptionText);
            status = (TextView) view.findViewById(R.id.statusText);
            image = (ImageView)view.findViewById(R.id.imageView);
        }
    }

    public RestaurantAdapter(Context context, List<Restaurant> restaurantsList) {
        this.context = context;
        this.restaurantsList = restaurantsList;
    }


}
