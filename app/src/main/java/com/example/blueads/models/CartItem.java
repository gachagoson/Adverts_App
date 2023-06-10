package com.example.blueads.models;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String adName;
    private String adLocation;
    private String adPrice;
    private String adDescription;

    public CartItem(String adName, String adLocation, String adPrice, String adDescription) {
        this.adName = adName;
        this.adLocation = adLocation;
        this.adPrice = adPrice;
        this.adDescription = adDescription;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdLocation() {
        return adLocation;
    }

    public void setAdLocation(String adLocation) {
        this.adLocation = adLocation;
    }

    public String getAdPrice() {
        return adPrice;
    }

    public void setAdPrice(String adPrice) {
        this.adPrice = adPrice;
    }

    public String getAdDescription() {
        return adDescription;
    }

    public void setAdDescription(String adDescription) {
        this.adDescription = adDescription;
    }
}
