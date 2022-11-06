package com.example.chatrebuild.repo;

import com.example.chatrebuild.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepo extends JpaRepository<DishEntity, Long> {
    DishEntity findById(long id);

    List<DishEntity> findByPriceBetween(double low, double high, PageRequest pageRequest);
}
