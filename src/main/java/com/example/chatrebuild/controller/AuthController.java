package com.example.chatrebuild.controller;

import com.example.chatrebuild.tool.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtProvider jwtProvider;

    public class Test {
        String login;
        String token;

        public Test(String login, String token) {
            this.login = login;
            this.token = token;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    @GetMapping("/login")
    public Test returnToken(@RequestParam String login) {
        return new Test(login, jwtProvider.provideToken(login));
    }
}
