package com.example.chatrebuild.repo;

import com.example.chatrebuild.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepo extends JpaRepository<DishEntity, Long> {
    DishEntity findById(long id);
}
