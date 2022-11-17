package com.example.chatrebuild.controller;

import com.example.chatrebuild.RequestBodies.CreateEmployeeRequest;
import com.example.chatrebuild.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/registerEmployee")
    public HttpStatus createEmployee(@RequestBody CreateEmployeeRequest request) {
        if (adminService.createNewEmployee(request))
            return HttpStatus.OK;
        return HttpStatus.BAD_GATEWAY;
    }
}
