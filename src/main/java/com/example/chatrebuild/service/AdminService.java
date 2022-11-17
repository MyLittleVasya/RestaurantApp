package com.example.chatrebuild.service;

import com.example.chatrebuild.RequestBodies.CreateEmployeeRequest;
import com.example.chatrebuild.entity.UserEntity;
import com.example.chatrebuild.entity.UserRole;
import com.example.chatrebuild.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder encoder;
    public boolean createNewEmployee(CreateEmployeeRequest request) {
        if (!request.getEmployeeName().isEmpty()) {
            if (userRepo.findByUsername(request.getEmployeeName()) == null) {
                var user = new UserEntity(request.getEmployeeName(),
                        encoder.encode(request.getEmployeePassword()),
                        request.getEmployeeEmail());
                if (request.getEmployeeRole().equals("WORKER"))
                    user.setRole(UserRole.WORKER);
                else
                    user.setRole(UserRole.ADMIN);
                user.setActivated(true);
                userRepo.save(user);
                return true;
            }
        }
        return false;
    }
}
