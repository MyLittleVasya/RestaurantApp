package com.example.chatrebuild.service;

import com.example.chatrebuild.RequestBodies.DishFilterRequest;
import com.example.chatrebuild.dto.FilteredDishesResponseDTO;
import com.example.chatrebuild.repo.DishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
public class DishService {
    @Autowired
    private DishRepo dishRepo;

    @Transactional
    public FilteredDishesResponseDTO returnFilteredDishes(DishFilterRequest request) {
        if (request.isSorting()) {
            var returnList = dishRepo.findByPriceBetween(request.getLow(), request.getHigh(),
                    PageRequest.of(request.getPage(), 3, Sort.by(Sort.Direction.ASC, "price")));
            if (!returnList.isEmpty()) {
                var nextList = dishRepo.findByPriceBetween(request.getLow(), request.getHigh(),
                        PageRequest.of(request.getPage()+1, 3, Sort.by(Sort.Direction.ASC, "price")));
                if (nextList.isEmpty())
                    return new FilteredDishesResponseDTO(returnList, false);
                return new FilteredDishesResponseDTO(returnList, true);
            }
        }
        else {
            var returnList = dishRepo.findByPriceBetween(request.getLow(), request.getHigh(),
                    PageRequest.of(request.getPage(), 3, Sort.by(Sort.Direction.DESC, "price")));
            if (!returnList.isEmpty()) {
                var nextList = dishRepo.findByPriceBetween(request.getLow(), request.getHigh(),
                        PageRequest.of(request.getPage()+1, 3, Sort.by(Sort.Direction.DESC, "price")));
                if (nextList.isEmpty())
                    return new FilteredDishesResponseDTO(returnList, false);
                return new FilteredDishesResponseDTO(returnList, true);
            }
        }
        return null;
    }

    public boolean updateDish(Map<String, String> request) {
        var dish = dishRepo.findById(Long.parseLong(request.get("id")));
        if (dish != null) {
            dish.setDishName(request.get("name"));
            dish.setPrice(Double.parseDouble(request.get("price")));
            dishRepo.save(dish);
            return true;
        }
        return false;
    }

    public boolean deleteDish(Map<String, String> request) {
        var dish = dishRepo.findById(Long.parseLong(request.get("id")));
        if (dish != null) {
            dishRepo.delete(dish);
            return true;
        }
        return false;
    }
}
