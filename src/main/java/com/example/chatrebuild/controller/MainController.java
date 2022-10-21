package com.example.chatrebuild.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/main")
    public HttpStatus newMethod() {
        return HttpStatus.OK;
    }

    @GetMapping("/customer")
    public HttpStatus newMethod2() {
        return HttpStatus.OK;
    }

    @GetMapping("/secured")
    public HttpStatus newSecured() {return HttpStatus.OK;}
}
