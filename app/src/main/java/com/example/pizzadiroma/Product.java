package com.example.pizzadiroma;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pizzadiroma.ui.main.DBHalper;

import java.util.ArrayList;
import java.util.List;

public class Product {


    private String id, name, description, weight, type, imageUrl, price;

    public Product() {
    }

    public Product(String id, String name, String description, String weight, String type, String imageUrl, String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.type = type;
        this.imageUrl = imageUrl;
        this.price = price;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
