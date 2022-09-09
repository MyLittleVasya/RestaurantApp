package com.example.chatrebuild.repo;

import com.example.chatrebuild.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findById(long id);

    UserEntity findByUsername(String username);
}
