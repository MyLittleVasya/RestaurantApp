package com.example.chatrebuild.repo;

import com.example.chatrebuild.entity.OrderEntity;
import com.example.chatrebuild.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<OrderEntity, Long> {
    OrderEntity findById(long id);
    List<OrderEntity> findByCustomer(UserEntity user);
}
