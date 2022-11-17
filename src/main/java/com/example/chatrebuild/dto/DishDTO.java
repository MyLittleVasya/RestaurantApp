package com.example.chatrebuild.dto;

import javax.persistence.Lob;

public class DishDTO {
    private String name;
    private double price;

    public DishDTO(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public DishDTO() {
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
}
