package com.example.chatrebuild.service;

import com.example.chatrebuild.RequestBodies.RegisterRequest;
import com.example.chatrebuild.entity.UserEntity;
import com.example.chatrebuild.entity.UserRole;
import com.example.chatrebuild.repo.UserRepo;
import com.example.chatrebuild.tool.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MailService mailService;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepo userRepo;
    public boolean createNewUser(RegisterRequest request) {
        if (userRepo.findByUsername(request.getUsername()) == null)
        {
            var user = new UserEntity(request.getUsername(),
                    encoder.encode(request.getPassword()),
                    request.getEmail());
            user.setRole(UserRole.CUSTOMER);
            try {
                mailService.sendActivationCode(request.getEmail(),
                        user.getActivationCode());
            }
            catch (Exception e) {
                return false;
            }
            userRepo.save(user);
            return true;
        }
        else
            return false;
    }

    public boolean tryActivateUser(RegisterRequest request) {
        var user = userRepo.findByUsername(request.getUsername());
        if (user != null && user.getActivationCode().equals(request.getActivation())){
            user.setActivated(true);
            userRepo.save(user);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean authUser(String login, String password) {
        var user = userRepo.findByUsername(login);
        if (user != null && user.isActivated())
        {
            return encoder.matches(password, user.getPassword());
        }
        return false;
    }
}
