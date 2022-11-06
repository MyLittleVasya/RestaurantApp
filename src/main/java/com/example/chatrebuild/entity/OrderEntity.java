package com.example.chatrebuild.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders_table")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne()
    private UserEntity customer;

    @ManyToMany
    private List<DishEntity> dishList;

    private double price;

    private OrderState orderState;

    public OrderEntity(UserEntity customer, ArrayList<DishEntity> dishList) {
        this.customer = customer;
        this.dishList = dishList;
        for (var dish: dishList) {
            this.price += dish.getPrice();
        }
        this.orderState = OrderState.PROCESSING;
    }

    public OrderEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }

    public List<DishEntity> getDishList() {
        return dishList;
    }

    public void setDishList(List<DishEntity> dishList) {
        this.dishList = dishList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}
