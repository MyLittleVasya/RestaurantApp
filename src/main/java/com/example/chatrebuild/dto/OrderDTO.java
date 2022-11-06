package com.example.chatrebuild.dto;

import java.util.List;

public class OrderDTO {
    private long id;
    private List<DishDTO> dishes;
    private double price;

    public OrderDTO(long id, List<DishDTO> dishes, double price) {
        this.id = id;
        this.dishes = dishes;
        this.price = price;
    }

    public OrderDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<DishDTO> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishDTO> dishes) {
        this.dishes = dishes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
