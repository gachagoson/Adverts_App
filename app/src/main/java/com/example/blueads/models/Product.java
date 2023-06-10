package com.example.blueads.models;
import java.io.Serializable;

import android.graphics.Bitmap;

public class Product implements Serializable{
    private String name;
    private String location;
    private String price;
    private String contact;
    private String description;
    private String datePosted;
    private int image;

    public Product(String name, String location, String price, String contact, String description, String datePosted, int image) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.contact = contact;
        this.description = description;
        this.datePosted = datePosted;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
