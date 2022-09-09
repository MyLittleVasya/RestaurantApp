package com.example.chatrebuild.service;

import com.example.chatrebuild.entity.UserEntity;
import com.example.chatrebuild.repo.UserRepo;
import com.example.chatrebuild.tool.JwtProvider;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MailService mailService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepo userRepo;
    public boolean createNewUser(String login, String password, String email) {
        var user = new UserEntity(login, encoder.encode(password), email);
        try {
            mailService.sendActivationCode(email, login);
        }
        catch (Exception e) {
            return false;
        }
        userRepo.save(user);
        return true;
    }

    public boolean tryActivateUser(String token, String login) {
        try {
            jwtProvider.validateToken(token, login);
        }
        catch (JwtException exception)
        {
            return false;
        }
        var user = userRepo.findByUsername(login);
        user.setActivated(true);
        userRepo.save(user);
        return true;

    }

    public boolean authUser(String login, String password) {
        var user = userRepo.findByUsername(login);
        if (user != null)
        {
            return encoder.matches(password, user.getPassword());
        }
        return false;
    }
}
