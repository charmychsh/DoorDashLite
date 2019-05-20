package com.example.cshah.doordashlite.model;

import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("name")
    private String name;
    @SerializedName("status")
    private String status;
    @SerializedName("url")
    private String url;
    @SerializedName("description")
    private String description;
    @SerializedName("delivery_fee")
    private String delivery_fee;

    public Restaurant(String name, String description, String status, String url) {
        this.name = name;
        this.description = description;
        this.status = status;
      //  this.delivery_fee = delivery_fee;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(String delivery_fee) {
        this.delivery_fee = delivery_fee;
    }
}
