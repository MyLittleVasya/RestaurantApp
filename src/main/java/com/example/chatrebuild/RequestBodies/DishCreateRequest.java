package com.example.chatrebuild.RequestBodies;

public class DishCreateRequest {
    private String name;
    private double price;
    private String image;

    private String dishType;

    public DishCreateRequest(String name, String dishType, double price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.dishType = dishType;
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

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }
}
