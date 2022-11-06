package com.example.chatrebuild.controller;

import com.example.chatrebuild.RequestBodies.DishCreateRequest;
import com.example.chatrebuild.entity.DishEntity;
import com.example.chatrebuild.repo.DishRepo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class DishController {

    @Autowired
    private DishRepo dishRepo;

    @GetMapping("/dishes")
    @Transactional
    public List<DishEntity> returnDishes() {
        return dishRepo.findByPriceBetween(0, 100,
                PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "price"))
        );
    }

    @PostMapping("/createDish")
    public HttpStatus addNewDish(@RequestBody DishCreateRequest request) {
        dishRepo.save(new DishEntity(request.getName(),
                request.getPrice(),
                Base64.decodeBase64(request.getImage().substring(request.getImage().indexOf(',')+1)),
                request.getDishType()));
        return HttpStatus.OK;
    }
}
