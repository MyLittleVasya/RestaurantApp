package com.example.chatrebuild.controller;

import com.example.chatrebuild.service.MailService;
import com.example.chatrebuild.service.UserService;
import com.example.chatrebuild.tool.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String returnToken(@RequestParam String login, @RequestParam String password) {
        if (userService.authUser(login, password))
            return jwtProvider.provideToken(login);
        else
            return "502";
    }

    @PostMapping("/register")
    public HttpStatus registration(@RequestParam String login,
                                   @RequestParam String password,
                                   @RequestParam String email) {
        if (userService.createNewUser(login, password, email))
            return HttpStatus.OK;
        else
            return HttpStatus.BAD_GATEWAY;
    }

    @GetMapping("/activate")
    public HttpStatus activateUser(@RequestParam String token,
                                   @RequestParam String login) {
        if (userService.tryActivateUser(token, login))
            return HttpStatus.OK;
        else
            return HttpStatus.BAD_GATEWAY;
    }



}
