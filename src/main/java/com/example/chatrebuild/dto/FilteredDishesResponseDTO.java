package com.example.chatrebuild.dto;

import com.example.chatrebuild.entity.DishEntity;

import java.util.List;

public class FilteredDishesResponseDTO {
    private List<DishEntity> dishes;
    private boolean isNextPageExists;

    public FilteredDishesResponseDTO(List<DishEntity> dishes, boolean isNextPageExists) {
        this.dishes = dishes;
        this.isNextPageExists = isNextPageExists;
    }

    public List<DishEntity> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishEntity> dishes) {
        this.dishes = dishes;
    }

    public boolean isNextPageExists() {
        return isNextPageExists;
    }

    public void setNextPageExists(boolean nextPageExists) {
        isNextPageExists = nextPageExists;
    }
}
