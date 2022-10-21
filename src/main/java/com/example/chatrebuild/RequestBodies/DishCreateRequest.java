package com.example.chatrebuild.RequestBodies;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class DishCreateRequest {
    private String name;
    private double price;
    private String image;

    public DishCreateRequest(String name, double price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public DishCreateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
