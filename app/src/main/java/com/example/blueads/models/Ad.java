package com.example.blueads.models;

public class Ad {
    private String adId;
    private String adName;
    private String adLocation;
    private String adPrice;
    private String adDescription;
    private String imageUrl;
    private String date;

    public Ad() {
        // Empty constructor needed for Firebase serialization
    }

    public Ad(String adId, String adName, String adLocation, String adPrice, String adDescription,String date) {
        this.adId = adId;
        this.adName = adName;
        this.adLocation = adLocation;
        this.adPrice = adPrice;
        this.adDescription = adDescription;
        this.date = date;
    }

    public Ad(String adId, String adName, String adLocation, String adPrice, String adDescription, String date, String imageUrl) {

    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
