package com.example.chatrebuild.controller;

import com.example.chatrebuild.RequestBodies.LoginRequest;
import com.example.chatrebuild.RequestBodies.RegisterRequest;
import com.example.chatrebuild.service.UserService;
import com.example.chatrebuild.tool.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String returnToken(@RequestBody LoginRequest request) {
        if (userService.authUser(request.getUsername(), request.getPassword()))
            return jwtProvider.provideLoginToken(request.getUsername());
        else
            return "403";
    }

    @PostMapping("/register")
    public HttpStatus registration(@RequestBody RegisterRequest registerRequest) {
        if (userService.createNewUser(registerRequest))
            return HttpStatus.OK;
        else
            return HttpStatus.BAD_GATEWAY;
    }

    @PostMapping("/activate")
    public HttpStatus activateUser(@RequestBody RegisterRequest registerRequest) {
        if (userService.tryActivateUser(registerRequest))
            return HttpStatus.OK;
        else
            return HttpStatus.BAD_GATEWAY;
    }
}
