package com.example.chatrebuild.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "dish_table")
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String dishName;

    private double price;

    private String dishType;

    @Lob
    private byte[] dishImageFile;

    public DishEntity(String dishName, double price, byte[] dishImageFile, String dishType) {
        this.dishName = dishName;
        this.price = price;
        this.dishImageFile = dishImageFile;
        this.dishType = dishType;
    }

    public DishEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getDishImageFile() {
        return dishImageFile;
    }

    public void setDishImageFile(byte[] dishImageFile) {
        this.dishImageFile = dishImageFile;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }
}
