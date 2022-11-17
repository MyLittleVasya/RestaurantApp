package com.example.chatrebuild.controller;

import com.example.chatrebuild.RequestBodies.DishCreateRequest;
import com.example.chatrebuild.RequestBodies.DishFilterRequest;
import com.example.chatrebuild.dto.FilteredDishesResponseDTO;
import com.example.chatrebuild.entity.DishEntity;
import com.example.chatrebuild.repo.DishRepo;
import com.example.chatrebuild.service.DishService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DishController {

    @Autowired
    private DishRepo dishRepo;

    @Autowired
    private DishService dishService;

    @PostMapping("/dishes")
    public FilteredDishesResponseDTO returnDishes(@RequestBody DishFilterRequest request) {
        return dishService.returnFilteredDishes(request);
    }

    @GetMapping("/allDishes")
    public List<DishEntity> returnAllDishes() {
        return dishRepo.findAll();
    }

    @PostMapping("/createDish")
    public HttpStatus addNewDish(@RequestBody DishCreateRequest request) {
        dishRepo.save(new DishEntity(request.getName(),
                request.getPrice(),
                Base64.decodeBase64(request.getImage().substring(request.getImage().indexOf(',')+1)),
                request.getDishType()));
        return HttpStatus.OK;
    }

    @PostMapping("/updateDish")
    public HttpStatus updateDish(@RequestBody Map<String, String> request) {
        if (dishService.updateDish(request))
            return HttpStatus.OK;
        return HttpStatus.BAD_GATEWAY;
    }

    @PostMapping("/deleteDish")
    public HttpStatus deleteDish(@RequestBody Map<String, String> request) {
        if (dishService.deleteDish(request))
            return HttpStatus.OK;
        return HttpStatus.BAD_GATEWAY;
    }
}
