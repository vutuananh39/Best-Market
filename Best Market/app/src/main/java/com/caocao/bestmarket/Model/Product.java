package com.caocao.bestmarket.Model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by rongc on 3/12/2018.
 */

public class Product {
    private String name;
    private String description;
    private String category;
    private Bitmap photo;
    private Integer price;

    private Date dateCreate;
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }


    public Product(String name, String description,Date dateCreate, Integer price, String category, Bitmap photo) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.photo = photo;
        this.dateCreate = dateCreate;
        this.price = price;
    }



}
